package br.com.api.condominio.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condominio.Model.Funcionario;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario ,String> {

    List<Funcionario> findByCondominioId(Integer condominioId);
    
}
