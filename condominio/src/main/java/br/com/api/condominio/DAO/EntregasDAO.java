package br.com.api.condominio.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.Entregas;

@Repository
public interface EntregasDAO extends JpaRepository<Entregas,Integer> {
    List<Entregas> findByCondominioId(Integer condominioId);
}
