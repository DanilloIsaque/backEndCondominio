package br.com.api.condominio.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="FUNCAO")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @Column(name = "condominio_id")
    private Integer condominioId;

    @Column(name="descricao")
    private String descricao;
    
@OneToMany(mappedBy = "funcao", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
private List<Funcionario> funcionarios = new ArrayList<>();
}
