package br.com.api.condominio.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.condominio.DAO.CondominioDAO;
import br.com.api.condominio.DAO.MoradorDAO;
import br.com.api.condominio.Model.Condominio;
import br.com.api.condominio.Model.Morador;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    CondominioDAO condominioDao;

    @Autowired
    MoradorDAO moradorDao;

    private static final Logger logger = Logger.getLogger(SecurityFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        logger.info("Token recuperado: " + token);
        var login = tokenService.validarToken(token);
        logger.info("Login validado: " + login);

        if (login != null) {
            var userOpt = condominioDao.findByEmail(login);
            if (!userOpt.isPresent()) {
                var moradorOpt = moradorDao.findByEmail(login);
                if (moradorOpt.isPresent()) {
                    Morador morador = moradorOpt.get();
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("morador"));
                    var authentication = new UsernamePasswordAuthenticationToken(morador, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Morador autenticado: " + morador.getEmail());
                }
            } else {
                Condominio condominio = userOpt.get();
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("condominio"));
                var authentication = new UsernamePasswordAuthenticationToken(condominio, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Usuário autenticado: " + condominio.getEmail());
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
