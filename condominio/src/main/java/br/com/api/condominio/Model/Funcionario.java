package br.com.api.condominio.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="FUNCIONARIO")
public class Funcionario {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="email")
    private String email;

    @Column(name="nome")
    private String nome;

    @Column(name="senha")
    private String senha;

   
     @Column(name = "funcao")
    private Integer funcao;

   
    @Column(name = "condominio_id")
    private Integer condominioId; 

    @Column(name="data_nascimento")
    private Date dataNascimento;

  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "funcao", insertable = false, updatable = false)
    private Funcao funcaoEntity;


   
}
