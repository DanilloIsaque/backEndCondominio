package br.com.api.condominio.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="ENTREGAS")
public class Entregas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @Column(name="condominio_id")
    private Integer condominioId;

    @Column(name="cpf_destinatario")
    private String cpfDestinatario;

    @Column(name="destinatario")
    private String destinatario;

    @Column(name="data")
    private Date data;

    @Column(name="hora")
    private String hora;

    @Column(name="numero_casa")
    private Integer numeroCasa;
    
   
    @Column(name="bloco")
    private Integer bloco;

    @Column(name="recebido_por")
    private String recebidoPor;

    @Column(name="retirado")
    private Integer retirado;

    @Column(name="status")
    private Integer status;

    @Column(name="data_retirada")
    private Date dataRetirada;

    @Column(name="hora_retirada")
    private String horaRetirada;

    @Column(name="retirado_por")
    private String retiradoPor;




    
}
