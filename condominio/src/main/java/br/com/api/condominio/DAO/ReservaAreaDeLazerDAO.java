package br.com.api.condominio.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.ReservaAreaDeLazer;

@Repository
public interface ReservaAreaDeLazerDAO extends JpaRepository<ReservaAreaDeLazer,Integer> {

    List<ReservaAreaDeLazer> findByCondominioId(Integer condominioId);
    
}
