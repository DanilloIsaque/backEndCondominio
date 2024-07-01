package br.com.api.condominio.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.AvisosAlertas;

@Repository
public interface AvisosAlertasDAO extends JpaRepository<AvisosAlertas,Integer> {
    List<AvisosAlertas> findByCondominioIdAndMoradorId(Integer condominioId,String moradorId);
    List<AvisosAlertas> findByMoradorId(String moradorId);

}
