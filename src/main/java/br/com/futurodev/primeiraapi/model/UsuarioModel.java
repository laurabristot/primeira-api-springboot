package br.com.futurodev.primeiraapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
//    @SequenceGenerator(name = "seq_usuarios", sequenceName = "seq_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // not null
    private String login;

    @Column(unique = true)
    private String senha;


    private String nome;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) // o nome do mappedBy tem que ser IGUAL ao que vamos criar no TelefoneModel // o cascade serve pra quando fizer uma atualização nos dados pela classe mãe, interferir na classe filha também
    @JsonManagedReference
    private List<TelefoneModel> telefones = new ArrayList<TelefoneModel>();

    public List<TelefoneModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneModel> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
