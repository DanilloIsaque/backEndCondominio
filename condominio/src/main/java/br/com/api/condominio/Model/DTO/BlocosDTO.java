package br.com.api.condominio.Model.DTO;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlocosDTO {
 
    private Integer id ;
    @NotNull(message = "É preciso estar logado para realizar tal função.")
    private Integer condominioId; 

    @NotNull(message = "Descrição não pode ser nula")
    private String descricao;

    @NotNull(message = "Quantidade de casas não pode ser nula")
    private Integer qtdCasas;

    @NotNull(message = "Quantidade de andares não pode ser nula")
    private Integer qtdAndares;

    @NotNull(message = "Divisão não pode ser nula")
    private Integer divisao;

    
    private List<CasaDTO> casas = new ArrayList<>();
}
