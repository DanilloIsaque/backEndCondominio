package br.com.api.condominio.Model;

import java.util.Date;

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
@Table(name="SOLICITACAO")
public class Solicitacao {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="condominio_id")
    private Integer condominioId;

    @Column(name="data_solicitacao")
    private Date dataSolicitacao;
    

    @Column(name="morador_id")
    private String moradorId;
    
    @Column(name="texto_solicitacao")
    private String textoSolicitacao;

    @Column(name="flag")
    private Integer flag;

}
