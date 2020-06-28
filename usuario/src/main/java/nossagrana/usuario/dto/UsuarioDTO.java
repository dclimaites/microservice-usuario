package nossagrana.usuario.dto;

import nossagrana.usuario.entity.Usuario;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private boolean ativo;

    public UsuarioDTO() {}

    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDTO(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
