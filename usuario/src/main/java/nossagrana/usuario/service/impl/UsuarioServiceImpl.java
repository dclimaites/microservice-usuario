package nossagrana.usuario.service.impl;

import nossagrana.usuario.dto.LogarUsuarioDTO;
import nossagrana.usuario.dto.UsuarioDTO;
import nossagrana.usuario.dto.UsuarioLogadoDTO;
import nossagrana.usuario.entity.Usuario;
import nossagrana.usuario.repository.UsuarioRepository;
import nossagrana.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioLogadoDTO autenticar(LogarUsuarioDTO usuario) {

        if(usuario == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Usuario usuarioLogado = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioLogado == null || (!usuario.getSenha().equals(usuarioLogado.getSenha())))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        UsuarioLogadoDTO teste = new UsuarioLogadoDTO(usuarioLogado);
        String nome = teste.getNome();

        return teste;
    }

    @Override
    public UsuarioLogadoDTO create(UsuarioDTO usuario) {

        Usuario usuarioJaCadastrado = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioJaCadastrado != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este e-mail já está vinculado a um cadastro");

        Usuario usuarioCriado = new Usuario(usuario);

        usuario.setAtivo(true);

        usuarioRepository.save(usuarioCriado);

        return new UsuarioLogadoDTO(usuarioCriado);
    }

    @Override
    public UsuarioLogadoDTO update(UsuarioDTO usuario) {
        Usuario original = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuario == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        original.setNome(usuario.getNome());
        original.setSenha(usuario.getSenha());

        usuarioRepository.save(original);

        return new UsuarioLogadoDTO(original);
    }

    @Override
    public void deactivate(UsuarioDTO usuario) {
        Usuario original = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuario == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        original.setAtivo(false);
        original.setDataDesativacao(ZonedDateTime.now());

        usuarioRepository.save(original);
    }
}
