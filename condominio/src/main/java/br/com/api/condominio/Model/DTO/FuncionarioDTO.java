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
public class FuncionarioDTO {
    @NotNull(message = "É preciso estar logado para realizar tal função.")
    private String id;

    @NotNull(message = "Nome não pode ser vazio")
    private String nome;
    @NotNull(message = "Email não pode ser vazio")
    private String email;
    @NotNull(message = "Senha não pode ser vazia")
    private String senha;
    @NotNull(message = "A data de nascimento não pode ser vazia")
    private Date dataNascimento;
    @NotNull(message = "Função não pode ser vazio")
    private Integer funcao;
}
