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

import br.com.api.condominio.Model.AreaDeLazer;
import br.com.api.condominio.Model.Casa;
import br.com.api.condominio.Model.DTO.AreaDeLazerDTO;
import br.com.api.condominio.Model.DTO.CasaDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Services.AreaDeLazerService;
import br.com.api.condominio.Services.CasaService;

@RestController
@RequestMapping("/areaDeLazer")
public class AreaDeLazerController {
     @Autowired
    private AreaDeLazerService service;

    private static final Logger logger = LoggerFactory.getLogger(CasaController.class);

    @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@RequestBody AreaDeLazerDTO dto, @RequestParam Boolean isAlterar) {
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        HttpStatus status = mensagem.getMensagem().contains("sucesso") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(mensagem, status);
    }

    @GetMapping("/areas")
    public ResponseEntity<List<AreaDeLazer>> areasDeLazer(@RequestParam Integer condominioId) {
        List<AreaDeLazer> casas = service.buscar(condominioId);
        return ResponseEntity.ok(casas);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<MensagemDTO> excluir(@RequestParam Integer id) {
        MensagemDTO mensagem = service.excluirAreaDeLazer(id);
        HttpStatus status = mensagem.getMensagem().equals("Área de lazer excluída com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(mensagem, status);
    }
}
