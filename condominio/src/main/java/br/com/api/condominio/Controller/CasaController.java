package br.com.api.condominio.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condominio.Model.Casa;
import br.com.api.condominio.Model.DTO.CasaDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Services.CasaService;

@RestController
@RequestMapping("/casa")
public class CasaController {
    
    @Autowired
    private CasaService service;

    private static final Logger logger = LoggerFactory.getLogger(CasaController.class);

    @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@RequestBody CasaDTO dto, @RequestParam Boolean isAlterar) {
        logger.info("Recebida solicitação para salvar casa: {}", dto);
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        HttpStatus status = mensagem.getMensagem().contains("sucesso") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(mensagem, status);
    }

    @GetMapping("/casas")
    public ResponseEntity<List<Casa>> casas(@RequestParam Integer condominioId) {
        logger.info("Recebida solicitação para listar casas do condomínio: {}", condominioId);
        List<Casa> casas = service.buscar(condominioId);
        return ResponseEntity.ok(casas);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<MensagemDTO> excluir(@RequestParam Integer id) {
        logger.info("Recebida solicitação para excluir casa com ID: {}", id);
        MensagemDTO mensagem = service.excluirCasa(id);
        HttpStatus status = mensagem.getMensagem().equals("Casa excluída com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(mensagem, status);
    }
}
