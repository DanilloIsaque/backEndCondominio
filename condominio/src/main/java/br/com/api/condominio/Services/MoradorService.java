package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.MoradorDAO;
import br.com.api.condominio.Model.Morador;
import br.com.api.condominio.Model.MoradorId;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.MoradorDTO;

@Service
public class MoradorService {

    @Autowired
    private MoradorDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MoradorDAO getDao(){
        return dao;
    }
     
    public MensagemDTO salvar(MoradorDTO dto, Boolean isAlterar) {
    MensagemDTO mensagem = new MensagemDTO();
    Morador morador;
    MoradorId moradorId = new MoradorId();
    moradorId.setId(dto.getId());
    moradorId.setCondominioId(dto.getCondominioId());
    
    Optional<Morador> optionalMorador = dao.findById(moradorId);
    if (isAlterar) {
        if (optionalMorador.isPresent()) {
            morador = optionalMorador.get();
            if (!morador.getEmail().equals(dto.getEmail())) {
                Optional<Morador> existingEmail = dao.findByEmail(dto.getEmail());
                if (existingEmail.isPresent()) {
                    mensagem.setMensagem("Email já cadastrado no sistema.");
                    return mensagem;
                }
            }
            morador.setId(moradorId);
            morador.setNome(dto.getNome());
            morador.setSenha(passwordEncoder.encode(dto.getSenha()));
            morador.setEmail(dto.getEmail());
            morador.setFlag(dto.getFlag());
            morador.setBloco(dto.getBloco());
            morador.setNumeroCasa(dto.getNumeroCasa());
            morador.setDataNascimento(dto.getDataNascimento());
            mensagem.setMensagem("Morador(a) atualizado(a) com sucesso.");
        } else {
            mensagem.setMensagem("Morador(a) não encontrado(a) para atualização.");
            return mensagem;
        }
    } else {
        if (optionalMorador.isPresent()){
            mensagem.setMensagem("Já existe um morador com esse CPF nesse condomínio.");
            return mensagem;
        }
        Optional<Morador> existingEmail = dao.findByEmail(dto.getEmail());
        if (existingEmail.isPresent()) {
            mensagem.setMensagem("Email já cadastrado no sistema.");
            return mensagem;
        }
        morador = new Morador();
        morador.setId(moradorId);
        morador.setNome(dto.getNome());
        morador.setSenha(passwordEncoder.encode(dto.getSenha()));
        morador.setEmail(dto.getEmail());
        morador.setFlag(dto.getFlag());
        morador.setBloco(dto.getBloco());
        morador.setNumeroCasa(dto.getNumeroCasa());
        morador.setDataNascimento(dto.getDataNascimento());
        mensagem.setMensagem("Morador(a) cadastrado(a) com sucesso.");
    }

    dao.save(morador);
    return mensagem;
}


    public List<Morador> listarTodos(Integer condominioId) {
       return dao.findByIdCondominioId(condominioId);
    }

    public MensagemDTO excluirMorador(String id, Integer condominio) {
        MensagemDTO mensagem = new MensagemDTO();
        MoradorId moradorId = new MoradorId();
        moradorId.setId(id);
        moradorId.setCondominioId(condominio);
        Optional<Morador> optionalMorador = dao.findById(moradorId);
        if (optionalMorador.isPresent()) {
            Morador morador = optionalMorador.get();
            dao.delete(morador);
            mensagem.setMensagem("Morador(a) excluído(a) com sucesso.");
        } else {
            mensagem.setMensagem("Não foi encontrado(a) nenhum(a) morador(a) com esse cpf/cnpj.");
        }
        return mensagem;
    }


}
