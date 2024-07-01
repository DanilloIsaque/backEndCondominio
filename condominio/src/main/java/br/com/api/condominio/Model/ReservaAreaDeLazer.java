package br.com.api.condominio.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name="RESERVA_AREA_DE_LAZER")
public class ReservaAreaDeLazer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @Column(name="condominio_id")
    private Integer condominioId;

    @Column(name="bloco")
    private Integer bloco;

    @Column(name="data")
    private Date data;
    

    @Column(name="area")
    private Integer areaDeLazer;

    @Column(name="horario_inicio")
    private String horarioInicio;

    @Column(name="horario_final")
    private String horarioFinal;


    @Column(name = "morador_id")
    private Integer morador;


    @Column(name="participantes")
    private Integer participantes;


    @Column(name="status")
    private Integer status;

      @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "areaDeLazer", insertable = false, updatable = false)
    private AreaDeLazer areaEntity;
}
