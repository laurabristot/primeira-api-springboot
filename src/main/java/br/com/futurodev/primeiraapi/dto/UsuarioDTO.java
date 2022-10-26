package br.com.futurodev.primeiraapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String login;

    private String senha;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataAtualizacao;


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @JsonIgnoreProperties(value = "tipo", allowGetters = true)
    private List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
