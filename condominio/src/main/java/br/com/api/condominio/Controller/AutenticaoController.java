package br.com.api.condominio.Controller;

import br.com.api.condominio.DAO.CondominioDAO;
import br.com.api.condominio.DAO.MoradorDAO;
import br.com.api.condominio.Model.Condominio;
import br.com.api.condominio.Model.Morador;
import br.com.api.condominio.Model.DTO.LoginDTO;
import br.com.api.condominio.Model.DTO.ResponseDTO;
import br.com.api.condominio.security.TokenService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticaoController {
    private final CondominioDAO condominioDAO;
    private final MoradorDAO moradorDAO;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) {
        Optional<Condominio> optionalUser = this.condominioDAO.findByEmail(body.getEmail());
        Optional<Morador> optionalMorador = this.moradorDAO.findByEmail(body.getEmail());

        if (optionalUser.isPresent()) {
            Condominio user = optionalUser.get();
            if (passwordEncoder.matches(body.getSenha(), user.getSenha())) {
                String token = this.tokenService.gerarToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getId(), user.getNome(), 1, "a", user.getEmail(), token));
            } else {
                // Senha incorreta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } else if (optionalMorador.isPresent()) {
            Morador morador = optionalMorador.get();
            if (passwordEncoder.matches(body.getSenha(), morador.getSenha())) {
                String token = this.tokenService.gerarToken(morador);
                return ResponseEntity.ok(new ResponseDTO(morador.getId().getCondominioId(), morador.getNome(), 0, morador.getId().getId(), morador.getEmail(), token));
            } else {
                // Senha incorreta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } else {
            // E-mail não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}
