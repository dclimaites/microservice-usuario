package nossagrana.usuario.service;

import nossagrana.usuario.dto.LogarUsuarioDTO;
import nossagrana.usuario.dto.UsuarioDTO;
import nossagrana.usuario.entity.Usuario;

public interface UsuarioService {
    Usuario autenticar(LogarUsuarioDTO usuario);

    Usuario criar(UsuarioDTO usuario);

    Usuario atualizar(UsuarioDTO usuario);

    void remover(UsuarioDTO usuario);
}
