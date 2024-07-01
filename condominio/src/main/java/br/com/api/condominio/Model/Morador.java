package br.com.api.condominio.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="MORADOR")
public class Morador {

    @EmbeddedId
    private MoradorId id;

    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;

    @Column(name="data_nascimento")
    private Date dataNascimento;

    @Column(name="senha")
    private String senha;

    @Column(name="numero_casa")
    private String numeroCasa;

    @Column(name="bloco")
    private Integer bloco;

    @Column(name="flag")
    private Integer flag;



}
