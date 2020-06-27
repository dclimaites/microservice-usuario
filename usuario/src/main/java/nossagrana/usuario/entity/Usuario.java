package nossagrana.usuario.entity;

import nossagrana.usuario.dto.UsuarioDTO;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

public class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private boolean isAtivo;
    private ZonedDateTime dataDesativacao;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, ZonedDateTime dataDesativacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataDesativacao = dataDesativacao;
    }

    /**
     * O Usuário sempre é criado ativo.
     */
    public Usuario(UsuarioDTO usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.isAtivo =true;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(ZonedDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        this.isAtivo = ativo;
    }

    public boolean isSenhaValida(String senha){
        return this.senha.equals(senha);
    }

    public void desativar(){
        this.isAtivo = false;
        this.dataDesativacao = ZonedDateTime.now();
    }
}