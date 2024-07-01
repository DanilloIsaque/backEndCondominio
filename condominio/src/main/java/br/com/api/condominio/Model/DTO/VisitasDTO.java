package br.com.api.condominio.Model.DTO;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitasDTO {
     private Integer id ;
    private Integer condominioId;
     @NotNull(message = "O CPF/CPNJ do morador não pode ser vazio.")
    private String moradorId;  
    @NotNull(message = "O CPF/CPNJ da visita não pode ser vazio.")
    private String documentoVisita;  
    @NotNull(message = "Nome da visita não pode ser vazia.")
    private String nomeVisita;
    @NotNull(message = "A data não pode ser vazio.")
    private Date dataVisita;
    @NotNull(message = "O motivo pode ser vazio")
    private String motivo;
    @NotNull(message = "O telefone não pode ser vazio.")
    private String telefone;
}
