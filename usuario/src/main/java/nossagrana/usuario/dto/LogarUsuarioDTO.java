package nossagrana.usuario.dto;

import nossagrana.usuario.entity.Usuario;

public class LogarUsuarioDTO {
    private String email;
    private String senha;

    public LogarUsuarioDTO() {}

    public LogarUsuarioDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public LogarUsuarioDTO(Usuario usuario) {
        email = usuario.getEmail();
        senha = usuario.getSenha();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}