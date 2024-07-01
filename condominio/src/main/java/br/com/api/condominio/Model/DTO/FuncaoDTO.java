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
public class FuncaoDTO {
    @NotNull(message = "É preciso estar logado para realizar tal função.")
    private Integer id;
    @NotNull(message = "A descrição não pode ser vazia")
    private String descricao;
    @NotNull(message = "A vaga não pode ser vazia")
    private Integer condominioId;

    
}
