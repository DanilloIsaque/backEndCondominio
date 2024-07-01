package br.com.api.condominio.Controller;

import java.util.List;

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

import br.com.api.condominio.Model.AvisosAlertas;
import br.com.api.condominio.Model.DTO.AvisosAlertasDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Services.AvisosAlertasService;

@RestController
@RequestMapping(value = "/avisosAlertas")
public class AvisosAlertasController {

    @Autowired
    private AvisosAlertasService service;

    @PostMapping("/salvar")
       public ResponseEntity<Void> salvar(@RequestBody AvisosAlertasDTO dto, @RequestParam Boolean isAlterar) {
        service.salvar(dto, isAlterar);
        return ResponseEntity.ok().build(); // Retorna apenas HttpStatus.OK sem corpo de resposta
    }
    
        @GetMapping("/buscarAvisosAlertas")
        public ResponseEntity<List<AvisosAlertas>> buscarAvisosAlertas( @RequestParam Integer condominioId, @RequestParam String moradorId) {
            List<AvisosAlertas> avisos = service.listarPorCondominio(condominioId,moradorId);
            return new ResponseEntity<>(avisos, HttpStatus.OK);
        }
    
        @DeleteMapping("/excluir")
        public ResponseEntity<MensagemDTO> excluir(@RequestParam Integer id) {
            MensagemDTO mensagem = service.excluirAviso(id);
            return new ResponseEntity<>(mensagem, mensagem.getMensagem().equals("Aviso/Alerta Excluído com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
        }
    
}
