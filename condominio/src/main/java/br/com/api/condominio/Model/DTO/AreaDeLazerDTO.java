package br.com.api.condominio.Model.DTO;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaDeLazerDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private Integer bloco;
    private Integer condominioId;

    
}
