package br.com.api.condominio.Model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CASA")
public class Casa {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

     @Column(name="condominio_id")
    private Integer condominioId;

    @Column(name="bloco")
    private Integer bloco;

    @Column(name="numero_casa")
    private Integer numeroCasa;

    @Column(name = "vazia")
    private Integer vazia;
    
    @Column(name="morador_id")
    private String moradorId;

    @Column(name = "aluguel")
    private Integer aluguel;

    @Column(name="telefone")
    private String telefone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bloco", insertable = false, updatable = false)
    private Blocos blocoEntity;


    

}
