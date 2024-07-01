package br.com.api.condominio.Model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstacionamentoDTO {

    private Integer id;
    
    @NotNull(message = "A vaga não pode ser vazia")
    private String vaga;
    @NotNull(message = "É preciso estar logado para realizar tal função.")
    private Integer condominioId;
    @NotNull(message = "É necessário assinalar se está ocupado ou não")
    private Integer ocupado ;
    private String numeroCasa;
    private Integer bloco;
}
