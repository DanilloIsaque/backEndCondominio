package br.com.api.condominio.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.Estacionamento;

@Repository
public interface EstacionamentoDAO extends JpaRepository<Estacionamento,Integer> {
    List<Estacionamento> findByCondominioId(Integer condominioId);
}
