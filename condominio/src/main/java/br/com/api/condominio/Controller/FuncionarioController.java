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
import br.com.api.condominio.Model.DTO.FuncionarioDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping("/salvar")
    public ResponseEntity<MensagemDTO> salvar(@RequestBody FuncionarioDTO dto, @RequestParam Boolean isAlterar) {
        MensagemDTO mensagem = service.salvar(dto, isAlterar);
        return new ResponseEntity<>(mensagem, isAlterar ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @GetMapping("/buscarFuncionarios")
    public ResponseEntity<List<Funcionario>> buscarFuncionarios(Integer condominioId ) {
        List<Funcionario> funcoes = service.listaFuncionarios(condominioId);
        return new ResponseEntity<>(funcoes, HttpStatus.OK);
    } 

    @DeleteMapping("/excluir")
    public ResponseEntity<MensagemDTO> excluir(@RequestParam String id) {
        MensagemDTO mensagem = service.excluirFuncionario(id);
        return new ResponseEntity<>(mensagem, mensagem.getMensagem().equals("Morador(a) Excluído(a) com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    } 
    
}
