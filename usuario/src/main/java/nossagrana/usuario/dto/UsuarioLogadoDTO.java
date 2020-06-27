package nossagrana.usuario.dto;

import nossagrana.usuario.entity.Usuario;

import java.time.ZonedDateTime;

public class UsuarioLogadoDTO {
    private String nome;
    private String email;
    private boolean ativo;
    private ZonedDateTime dataDesativacao;

    public UsuarioLogadoDTO() {
    }

    public UsuarioLogadoDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public UsuarioLogadoDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ZonedDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(ZonedDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}
