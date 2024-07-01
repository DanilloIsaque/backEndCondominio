package br.com.api.condominio.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CONDOMINIO")
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;

    @Column(name="senha")
    private String senha;

    @Column(name="logradouro")
    private String logradouro;

    @Column(name="bairro")
    private String bairro;

    @Column(name="cidade")
    private String cidade;

    @Column(name="estado")
    private String estado;

    @Column(name="numero")
    private Integer numero;

    @Column(name="cep")
    private String cep;

    @Column(name="proprietario")
    private String proprietario;

    @Column(name="plano")
    private Integer plano;

    @Column(name="nome_proprietario")
    private String nomeProprietario;

 /*   @OneToMany(mappedBy = "condominio")
    private List<Morador> moradores;

    @OneToMany(mappedBy = "condominio")
    private List<Blocos> blocos;

    @OneToMany(mappedBy = "condominio")
    private List<AreaDeLazer> areasDeLazer;

    @OneToMany(mappedBy = "condominio")
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "condominio")
    private List<ReservaAreaDeLazer> reservas;

    @OneToMany(mappedBy = "condominio")
    private List<Entregas> entregas;

    @OneToMany(mappedBy = "condominio")
    private List<AvisosAlertas> avisosAlertas;

    @OneToMany(mappedBy = "condominio")
    private List<Visitas> visitas;

    @OneToMany(mappedBy = "condominio")
    private List<Estacionamento> estacionamentos;

    @OneToMany(mappedBy = "condominio")
    private List<Casa> casas;*/

}
