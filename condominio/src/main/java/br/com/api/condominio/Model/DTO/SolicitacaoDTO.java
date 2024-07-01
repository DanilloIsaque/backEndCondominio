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
public class SolicitacaoDTO {
    

    private Integer id;

    private Integer condominioId;

    private Date dataSolicitacao;
    
    private String moradorId;
    
    private String textoSolicitacao;

    private Integer flag;

}
