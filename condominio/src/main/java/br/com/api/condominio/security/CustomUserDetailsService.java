package br.com.api.condominio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.api.condominio.DAO.CondominioDAO;
import br.com.api.condominio.DAO.MoradorDAO;
import br.com.api.condominio.Model.Condominio;
import br.com.api.condominio.Model.Morador;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private CondominioDAO condominioDAO;

    @Autowired
    private MoradorDAO moradorDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Condominio> condominioOpt = this.condominioDAO.findByEmail(username);
        if (condominioOpt.isPresent()) {
            Condominio condominio = condominioOpt.get();
            return new org.springframework.security.core.userdetails.User(condominio.getEmail(), condominio.getSenha(), new ArrayList<>());
        }
        
        Optional<Morador> moradorOpt = this.moradorDAO.findByEmail(username);
        if (moradorOpt.isPresent()) {
            Morador morador = moradorOpt.get();
            return new org.springframework.security.core.userdetails.User(morador.getEmail(), morador.getSenha(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }
}
