package br.com.api.condominio.DAO;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.Casa;

@Repository
public interface CasaDAO extends JpaRepository<Casa,Integer> {
    List<Casa> findByCondominioId(Integer condominioId);
    List<Casa> findByBloco(Integer blocoId);
    
       @Query("SELECT c FROM Casa c JOIN FETCH c.blocoEntity WHERE c.condominioId = :condominioId")
    List<Casa> findByCondominioIdWithBloco(@Param("condominioId") Integer condominioId);
}
