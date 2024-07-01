package br.com.api.condominio.DAO;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.Blocos;

@Repository
public interface BlocosDAO extends JpaRepository<Blocos,Integer> {
    List<Blocos> findByCondominioId(Integer condominioId);
}
