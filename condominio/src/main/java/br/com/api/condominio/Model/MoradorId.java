package br.com.api.condominio.Model;

import java.io.Serializable;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MoradorId implements Serializable {

    @Column(name="id")
    private String id;

    @Column(name="condominio_id")
    private Integer condominioId;
}
