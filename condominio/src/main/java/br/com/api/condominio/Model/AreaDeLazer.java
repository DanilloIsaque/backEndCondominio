package br.com.api.condominio.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="AREA_DE_LAZER")
public class AreaDeLazer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nome")
    private String nome;

 
    @Column(name="condominio_id")
    private Integer condominioId;

 
    @Column(name="bloco")
    private Integer bloco;

    @Column(name="descricao")
    private String descricao;

     @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bloco", insertable = false, updatable = false)
    private Blocos blocoEntity;

    @OneToMany(mappedBy = "areaDeLazer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
private List<ReservaAreaDeLazer> reservas = new ArrayList<>();
    
   
}
