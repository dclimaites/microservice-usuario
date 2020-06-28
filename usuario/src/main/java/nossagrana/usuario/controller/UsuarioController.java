package nossagrana.usuario.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nossagrana.usuario.dto.LogarUsuarioDTO;
import nossagrana.usuario.dto.UsuarioDTO;
import nossagrana.usuario.dto.UsuarioLogadoDTO;
import nossagrana.usuario.entity.Usuario;
import nossagrana.usuario.entity.UsuarioJaExisteException;
import nossagrana.usuario.entity.UsuarioNaoAutenticadoException;
import nossagrana.usuario.entity.UsuarioNaoEncontradoException;
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

    @ApiOperation(value = "Realiza a autenticação de um usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autenticação realizada com sucesso."),
            @ApiResponse(code = 403, message = "Acesso negado para autenticacão.")}
    )
    @CrossOrigin
    @PostMapping("autenticar")
    public UsuarioLogadoDTO Logar(@RequestBody LogarUsuarioDTO usuarioDto) {
        try {
            Usuario usuario = this.service.autenticar(usuarioDto);
            return new UsuarioLogadoDTO(usuario);
        }
        catch (UsuarioNaoAutenticadoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Realiza o cadastro de um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario criado com sucesso."),
            @ApiResponse(code = 400, message = "Usuario já existe.")}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    @PostMapping
    public UsuarioLogadoDTO criar(@RequestBody UsuarioDTO usuarioDto) {
        try{
            Usuario usuario = this.service.criar(usuarioDto);
            return new UsuarioLogadoDTO(usuario);
        }
        catch (UsuarioJaExisteException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Atualiza um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualização realizada com sucesso."),
            @ApiResponse(code = 403, message = "Usuário não encontrado.")}
    )
    @CrossOrigin
    @PutMapping
    public UsuarioLogadoDTO atualizar(@RequestBody UsuarioDTO usuarioDto) {
        try {
            Usuario usuario = service.atualizar(usuarioDto);
            return new UsuarioLogadoDTO(usuario);
        }
        catch (UsuarioNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Remove um usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário removido com sucesso."),
            @ApiResponse(code = 403, message = "Usuário não encontrado.")}
    )
    @CrossOrigin
    @DeleteMapping
    public void excluir(@RequestBody UsuarioDTO usuarioDto) {
        try {
            service.remover(usuarioDto);
        }
        catch (UsuarioNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}