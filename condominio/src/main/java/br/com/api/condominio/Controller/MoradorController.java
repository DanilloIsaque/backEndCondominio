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

import br.com.api.condominio.Model.Funcionario;
import br.com.api.condominio.Model.Morador;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.MoradorDTO;
import br.com.api.condominio.Services.MoradorService;

@RestController
@RequestMapping("/morador")
public class MoradorController {

    @Autowired
    private MoradorService service;

    @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@RequestBody MoradorDTO dto, @RequestParam Boolean isAlterar) {
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        return new ResponseEntity<>(mensagem, isAlterar ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @GetMapping("/buscarMoradores")
    public ResponseEntity<List<Morador>> buscarMoradores(Integer condominioId ) {
        List<Morador> funcoes = service.listarTodos(condominioId);
        return new ResponseEntity<>(funcoes, HttpStatus.OK);
    } 

    @DeleteMapping("/excluir")
    public ResponseEntity<MensagemDTO> excluir(@RequestParam String id,@RequestParam Integer condominio) {
        MensagemDTO mensagem = service.excluirMorador(id,condominio);
        return new ResponseEntity<>(mensagem, mensagem.getMensagem().equals("Morador excluído com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);    }

}
