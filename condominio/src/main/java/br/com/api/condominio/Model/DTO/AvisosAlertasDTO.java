package br.com.api.condominio.Model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AvisosAlertasDTO {
    private Integer id;
    private Integer condominioId;
    private String moradorId;
    private String titulo;
    private String texto;
    private Date dataPost;
    private Integer avisoAlerta;
    
}
