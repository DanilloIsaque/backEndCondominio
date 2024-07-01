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

import br.com.api.condominio.Model.ReservaAreaDeLazer;
import br.com.api.condominio.Model.Solicitacao;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.ReservaAreaDeLazerDTO;
import br.com.api.condominio.Model.DTO.SolicitacaoDTO;
import br.com.api.condominio.Services.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService service;

    @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@RequestBody SolicitacaoDTO dto, @RequestParam Boolean isAlterar) {
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        HttpStatus status = mensagem.getMensagem().contains("sucesso") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(mensagem, status);
    }

    @GetMapping("/solicitacoes")
    public ResponseEntity<List<Solicitacao>> solicitacoes(@RequestParam Integer condominioId) {
        List<Solicitacao> solicitacoes = service.listarSolicitacoes(condominioId);
        return ResponseEntity.ok(solicitacoes);
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<MensagemDTO> excluir(@RequestParam Integer id) {
        MensagemDTO mensagem = service.excluirSolicitacao(id);
        HttpStatus status = mensagem.getMensagem().equals("Solicitação excluída com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(mensagem, status);
    }
}
