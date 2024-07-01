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

import br.com.api.condominio.Model.Casa;
import br.com.api.condominio.Model.ReservaAreaDeLazer;
import br.com.api.condominio.Model.DTO.CasaDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.ReservaAreaDeLazerDTO;
import br.com.api.condominio.Services.ReservaAreaDeLazerService;

@RestController
@RequestMapping("/reserva")
public class ReservaAreaDeLazerController {
    
@Autowired
private ReservaAreaDeLazerService service;

 @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@RequestBody ReservaAreaDeLazerDTO dto, @RequestParam Boolean isAlterar) {
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        HttpStatus status = mensagem.getMensagem().contains("sucesso") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(mensagem, status);
    }

    @GetMapping("/reservas")
    public ResponseEntity<List<ReservaAreaDeLazer>> reservas(@RequestParam Integer condominioId) {
        List<ReservaAreaDeLazer> reserva = service.buscar(condominioId);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<MensagemDTO> excluir(@RequestParam Integer id) {
        MensagemDTO mensagem = service.excluirReserva(id);
        HttpStatus status = mensagem.getMensagem().equals("Reserva excluída com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(mensagem, status);
    }

}
