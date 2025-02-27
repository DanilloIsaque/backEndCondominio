package br.com.api.condominio.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condominio.Model.Morador;
import br.com.api.condominio.Model.MoradorId;

import org.springframework.stereotype.Repository;

@Repository
public interface MoradorDAO extends JpaRepository<Morador,MoradorId> {
    
    List<Morador> findByIdCondominioId(Integer condominioId);

    Optional<Morador> findByEmail(String email);
    
}
