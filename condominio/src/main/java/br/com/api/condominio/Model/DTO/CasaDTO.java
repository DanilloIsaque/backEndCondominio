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
public class CasaDTO {

    private Integer id;
    
    @NotNull(message = "É preciso estar logado para realizar tal função.")
    private Integer condominioId;
     @NotNull(message = "O número da casa não pode ser vazio")
    private Integer numeroCasa;
    @NotNull(message = "O bloco não pode ser vazio")
    private Integer bloco;
    @NotNull(message = "É necessário assinalar se a casa é vazia ou não")
    private Integer vazia;
    @NotNull(message = "O CPF/CNPJ não ser vazio")
    private String moradorId;

    private String telefone;

    @NotNull(message = "É necessário assinalar se é aluguel ou não")
    private Integer aluguel;
}
