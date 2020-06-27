package nossagrana.usuario.controller;

import nossagrana.usuario.dto.LogarUsuarioDTO;
import nossagrana.usuario.dto.UsuarioDTO;
import nossagrana.usuario.dto.UsuarioLogadoDTO;
import nossagrana.usuario.entity.Usuario;
import nossagrana.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("autenticar")
    public UsuarioLogadoDTO Logar(@RequestBody LogarUsuarioDTO usuario) {
        return this.service.autenticar(usuario);
    }

    @PostMapping
    public UsuarioLogadoDTO criar(@RequestBody UsuarioDTO usuario) {
        return this.service.create(usuario);
    }

    @PutMapping
    public UsuarioLogadoDTO atualizar(@RequestBody UsuarioDTO usuario) {
        return service.update(usuario);
    }

    @DeleteMapping
    public void excluir(@RequestBody UsuarioDTO usuario) {
        service.deactivate(usuario);
    }
}
