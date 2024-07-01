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
public class EntregasDTO {
      private Integer id ;
     @NotNull(message = "É preciso estar logado para realizar tal função.")
      private Integer condominioId ;
      @NotNull(message = "O CPF/CNPJ do destinatário não pode ser vazio.")
      private String cpfDestinatario ;
      @NotNull(message = "O nome do destinatario não pode ser vazio.")
      private String destinatario ;
      @NotNull(message = "A data não pode ser vazia")
      private Date  data; 
      @NotNull(message = "A hora não pode ser vazia")
      private String hora ;
      @NotNull(message = "O número da casa não pode ser vazio")
      private Integer  numeroCasa ;
      @NotNull(message = "O bloco não pode ser vazio.")
      private Integer bloco ;
      @NotNull(message = "O nome do funcionário que recebeu não pode ser vazio.")
      private String recebidoPor ;
      @NotNull(message = "É preciso assinalar se já foi retirado ou não.")
      private Integer retirado ;
      @NotNull(message = "É preciso informar o status.")
      private Integer status ;

      private Date dataRetirada;
      private String horaRetirada;
      private String retiradoPor;
}
