package nossagrana.usuario.service.impl;

import nossagrana.usuario.dto.LogarUsuarioDTO;
import nossagrana.usuario.dto.UsuarioDTO;
import nossagrana.usuario.entity.Usuario;
import nossagrana.usuario.entity.UsuarioJaExisteException;
import nossagrana.usuario.entity.UsuarioNaoAutenticadoException;
import nossagrana.usuario.entity.UsuarioNaoEncontradoException;
import nossagrana.usuario.repository.UsuarioRepository;
import nossagrana.usuario.service.UsuarioService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(LogarUsuarioDTO usuarioDto){
        Usuario usuario = usuarioRepository.findByEmail(usuarioDto.getEmail());

        if(usuario == null)
            throw new UsuarioNaoAutenticadoException();

        if(!usuario.isSenhaValida(usuarioDto.getSenha()))
            throw new UsuarioNaoAutenticadoException();

        return usuario;
    }

    @Override
    public Usuario criar(UsuarioDTO usuarioDto) {
        certificaQueUsuarioPodeSerCriado(usuarioDto.getEmail());

        Usuario usuario = new Usuario(usuarioDto);
        usuarioRepository.save(usuario);

        return usuario;
    }

    private void certificaQueUsuarioPodeSerCriado(String email){
        if( usuarioRepository.findByEmail(email) != null){
            throw new UsuarioJaExisteException("Já existe um usuario vinculado ao e-mail informado.");
        }
    }

    @Override
    public Usuario atualizar(UsuarioDTO usuarioDto) {
        Usuario usuario = usuarioRepository.findByEmail(usuarioDto.getEmail());

        if (usuario == null) throw new UsuarioNaoEncontradoException();

        usuario.setNome(usuarioDto.getNome());
        usuario.setSenha(usuarioDto.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void remover(UsuarioDTO usuarioDto) {
        Usuario usuario = consultaPorEmail(usuarioDto.getEmail());

        usuario.desativar();
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario consultaPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) throw new UsuarioNaoEncontradoException();
        return usuario;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void criaUsuarioAdminCasoNaoExista(){
        final String emailUserAdmin = "admin@admin.com";
        try {
            Usuario usuarioAdminExistente = usuarioRepository.findByEmail(emailUserAdmin);
            if (usuarioAdminExistente == null) {
                Usuario usuarioAdmin = new Usuario("admin", "admin@admin.com", "admin", true, null);
                usuarioRepository.save(usuarioAdmin);
            }
        } catch (Exception e) {
            /* Abafa */
        }
    }
}
