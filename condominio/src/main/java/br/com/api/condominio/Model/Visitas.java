package br.com.api.condominio.Model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "VISITAS")
public class Visitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "condominio_id")
    private Integer condominioId;

    @Column(name = "data_visita")
    private Date dataVisita;

    @Column(name = "documento_visita")
    private String documentoVisita;


    @Column(name = "morador_id")
    private String morador;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "nome_visita")
    private String nomeVisita;
}
