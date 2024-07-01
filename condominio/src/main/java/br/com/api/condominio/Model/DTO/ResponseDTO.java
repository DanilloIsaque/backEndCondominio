package br.com.api.condominio.Model.DTO;

import br.com.api.condominio.Model.MoradorId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    
    private Integer condominioId;
    private String nome;
    private Integer flag;
    private String moradorId;
    private String email;
    private String token;
    

}
