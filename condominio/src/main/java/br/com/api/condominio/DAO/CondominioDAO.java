package br.com.api.condominio.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.condominio.Model.Condominio;

public interface CondominioDAO extends JpaRepository<Condominio,Integer> {
    Optional<Condominio> findByEmail(String email);
    
}
