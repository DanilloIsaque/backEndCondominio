package br.com.api.condominio.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="BLOCOS")
@NoArgsConstructor
public class Blocos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

  
    @Column(name="condominio_id")
    private Integer condominioId;

    @Column(name="descricao")
    private String descricao;

    @Column(name="qtd_casas")
    private Integer qtdCasas;

    @Column(name="qtd_andares")
    private Integer qtdAndares;

    @Column(name="divisao")
    private Integer divisao;

    @OneToMany(mappedBy = "bloco", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
private List<Casa> casas = new ArrayList<>();


@OneToMany(mappedBy = "bloco", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
private List<AreaDeLazer> areas = new ArrayList<>();


    

}
