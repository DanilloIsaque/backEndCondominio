package br.com.api.condominio.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESTACIONAMENTO")
public class Estacionamento {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="vaga")
    private String vaga;
    
    @Column(name="condominio_id")
    private Integer condominioId;
    
    @Column(name="bloco")
    private Integer bloco;

    @Column(name="numero_casa")
    private String numeroCasa;

    @Column(name = "ocupado")
    private Integer ocupado;




}
