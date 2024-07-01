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
public class SalaoDeFestasDTO {
    
    private Integer id ;
    private Integer condominioId;
    @NotNull(message = "Bloco não pode ser vazio")
    private Integer bloco;
    @NotNull(message = "Tipo não pode ser vazio")
    private Integer tipo;
    @NotNull(message = "CPF/CNPJ do morador não pode ser vazio")
    private String moradorId;
    @NotNull(message = "A data não pode ser vazia")
    private Date data;
    @NotNull(message = "O número de participantes não pode ser vazio")
    private Integer participantes;
    @NotNull(message = "Horário de ínicio não pode ser vazio")
    private String horario_inicio;
    @NotNull(message = "Horario de término não pode ser vazio")
    private String horario_final;
}
