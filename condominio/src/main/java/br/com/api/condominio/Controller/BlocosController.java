package br.com.api.condominio.Controller;

import java.util.List;
import java.util.Optional;

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

import br.com.api.condominio.Model.Blocos;
import br.com.api.condominio.Model.Condominio;
import br.com.api.condominio.Model.DTO.BlocosDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Services.BlocosService;

@RestController
@RequestMapping("/blocos")
public class BlocosController {

    @Autowired
    private BlocosService service;

    @PostMapping("/salvar")
    public ResponseEntity<Void> salvar(@RequestBody BlocosDTO dto, @RequestParam Boolean isAlterar) {
        service.salvar(dto, isAlterar);
        return ResponseEntity.ok().build(); // Retorna apenas HttpStatus.OK sem corpo de resposta
    }
    

    
         @GetMapping("/listarBlocos")
    public ResponseEntity<List<Blocos>> listarBlocosPorCondominio(@RequestParam Integer condominioId) {
   
        List<Blocos> blocos = service.listarBlocosPorCondominio(condominioId);
        return ResponseEntity.ok(blocos);
    }
        @DeleteMapping("/excluir")
        public ResponseEntity<MensagemDTO> excluir(@RequestParam Integer id) {
            MensagemDTO mensagem = service.excluirBloco(id);
            return new ResponseEntity<>(mensagem, mensagem.getMensagem().equals("Bloco Excluído com sucesso.") ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
        }
    
    
}
