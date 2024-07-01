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
public class CondominioDTO {
    

    private Integer id ;
     @NotNull(message = "Nome não pode ser vazio")
    private String nome ;
    @NotNull(message = "Email não pode ser vazio")
    private String email ;

    @NotNull(message = "Senha não pode ser vazio")
    private String senha ;

    private String logradouro ;
    @NotNull(message = "Bairro não pode ser vazio")
    private String bairro ;
    @NotNull(message = "Cidade não pode ser vazio")
    private String cidade ;
    @NotNull(message = "Estado não pode ser vazio")
    private String estado ;
    @NotNull(message = "Número não pode ser vazio")
    private Integer numero ;
    @NotNull(message = "Cep não pode ser vazio")
    private String cep ;
    @NotNull(message = "O CPF do proprietário não pode ser vazio")
    private String proprietario;

    private Integer plano;
    
    private String nomeProprietario;






}
