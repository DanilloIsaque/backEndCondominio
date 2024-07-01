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
public class LoginDTO {
    
    @NotNull(message = "O E-mail não pode ser vazio.")
    private String email ;
    @NotNull(message = "A senha não pode ser vazia.")
    private String senha;
}
