package br.com.api.condominio.Model.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaAreaDeLazerDTO {
   
    private Integer id;
    private Integer condominioId;
    private Integer bloco;
    private Date data;
    private Integer areaDeLazer;
    private String horarioInicio;
    private String horarioFinal;
    private Integer morador;
    private Integer participantes;
    private Integer status;
}
