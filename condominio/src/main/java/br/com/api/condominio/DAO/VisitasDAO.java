package br.com.api.condominio.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.Visitas;

@Repository
public interface VisitasDAO extends JpaRepository<Visitas,Integer> {
    List<Visitas> findByCondominioId(Integer condominioId);
}
