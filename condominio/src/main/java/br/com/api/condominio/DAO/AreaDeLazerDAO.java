package br.com.api.condominio.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.AreaDeLazer;

@Repository
public interface AreaDeLazerDAO extends JpaRepository<AreaDeLazer,Integer>{
    public List<AreaDeLazer> findByCondominioId(Integer condominioId);

    public List<AreaDeLazer> findByBloco(Integer blocoId);
    
}
