package br.com.api.condominio.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="AVISOS_ALERTAS")
public class AvisosAlertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
 
    @Column(name="condominio_id")
    private Integer condominioId;
    
 
    @Column(name="morador_id")
    private String moradorId;
    
    @Column(name="titulo")
    private String titulo;

    @Column(name="texto")
    private String texto;

    @Column(name="data_post")
    private Date dataPost;

    @Column(name="aviso_alerta")
    private Integer avisoAlerta;

    
}
