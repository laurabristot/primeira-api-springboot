package br.com.futurodev.primeiraapi.dto;

import br.com.futurodev.primeiraapi.model.TelefoneModel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String login;

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
}