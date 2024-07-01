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
public class MoradorDTO {

    

    @NotNull(message = "O CPF não pode ser vazio")
    private String id;

    @NotNull(message = "É preciso estar logado para realizar tal função.")
    private Integer condominioId;

    @NotNull(message = "Nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Email não pode ser vazio")
    private String email;

    @NotNull(message = "Data de nascimento não pode ser vazia")
    private Date dataNascimento;

    @NotNull(message = "Senha não pode ser vazia")
    private String senha;

    @NotNull(message = "O número da casa não pode ser vazio")
    private String numeroCasa;

    @NotNull(message = "O bloco não pode ser vazio")
    private Integer bloco;

 
    private Integer flag;
}
