package br.com.api.condominio.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condominio.Model.DTO.CondominioDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Services.CondominioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/condominio")
public class CondominioController {
    
    @Autowired
    private CondominioService service;

    @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@Valid @RequestBody CondominioDTO dto, BindingResult result, @RequestParam Boolean isAlterar) {
        // Verifica se há erros de validação no DTO
        if (result.hasErrors()) {
            // Itera sobre os erros de validação e retorna o primeiro erro encontrado
            for (ObjectError error : result.getAllErrors()) {
                String mensagem = error.getDefaultMessage();
                return ResponseEntity.badRequest().body(new MensagemDTO("Erro de validação", mensagem));
            }
        }
        
        // Se não houver erros de validação, continua com a lógica de salvamento no serviço
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        return ResponseEntity.ok(mensagem);
    }
}
