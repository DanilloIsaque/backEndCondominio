package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.SolicitacaoDAO;
import br.com.api.condominio.Model.Solicitacao;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.SolicitacaoDTO;

@Service
public class SolicitacaoService {
    
    @Autowired
    private SolicitacaoDAO dao;

    public SolicitacaoDAO getDao(){
        return dao;
    }
     
    public MensagemDTO salvar(SolicitacaoDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Solicitacao solicitacao = new Solicitacao();
        
        Optional<Solicitacao> optional = dao.findById(dto.getId());
        if (isAlterar) {
            if (optional.isPresent()) {
                solicitacao.setCondominioId(dto.getCondominioId());
                solicitacao.setDataSolicitacao(dto.getDataSolicitacao());
                solicitacao.setFlag(dto.getFlag());
                solicitacao.setId(dto.getId());
                solicitacao.setMoradorId(dto.getMoradorId());
                solicitacao.setTextoSolicitacao(dto.getTextoSolicitacao());
                mensagem.setMensagem("Solicitação atualizada com sucesso.");
            } else {
                mensagem.setMensagem("Solicitação não encontrada para atualização.");
                return mensagem;
            }
        } else {
            solicitacao.setCondominioId(dto.getCondominioId());
            solicitacao.setDataSolicitacao(dto.getDataSolicitacao());
            solicitacao.setFlag(dto.getFlag());
            solicitacao.setId(dto.getId());
            solicitacao.setMoradorId(dto.getMoradorId());
            solicitacao.setTextoSolicitacao(dto.getTextoSolicitacao());
            mensagem.setMensagem("Solicitação cadastrada com sucesso.");
        }

        dao.save(solicitacao);
        return mensagem;
    }


     public List<Solicitacao> listarSolicitacoes(Integer condominioId) {
        return dao.findByCondominioId(condominioId);
    }
    

    public MensagemDTO excluirSolicitacao(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Solicitacao> optionalSolici = dao.findById(id);
        if (optionalSolici.isPresent()) {
            Solicitacao soli = optionalSolici.get();
            dao.delete(soli);
            mensagem.setMensagem("Solicitação excluída com sucesso.");
        } else {
            mensagem.setMensagem("Não foi encontrada nenhuma solicitação para a exclusão");
        }
        return mensagem;
    }
}
