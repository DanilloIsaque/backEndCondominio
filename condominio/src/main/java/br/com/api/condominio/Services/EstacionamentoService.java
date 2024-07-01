package br.com.api.condominio.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.EstacionamentoDAO;
import br.com.api.condominio.Model.Estacionamento;
import br.com.api.condominio.Model.DTO.EstacionamentoDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;

@Service
public class EstacionamentoService {
      @Autowired
    private EstacionamentoDAO dao;

    private EstacionamentoDAO getDao(){
        return dao;
    }

     public MensagemDTO salvar(EstacionamentoDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Estacionamento estacionamento;

        if (isAlterar) {
            Optional<Estacionamento> optionalEstacionamento = dao.findById(dto.getId());
            if (optionalEstacionamento.isPresent()) {
                estacionamento = optionalEstacionamento.get();
                estacionamento.setId(dto.getId());
                estacionamento.setCondominioId(dto.getCondominioId());
                estacionamento.setBloco(dto.getBloco());
                estacionamento.setNumeroCasa(dto.getNumeroCasa());
                estacionamento.setVaga(dto.getVaga());
                estacionamento.setOcupado(dto.getOcupado());
                mensagem.setMensagem("Vaga atualizada com sucesso.");
            } else {
                mensagem.setMensagem("Vaga não encontrada para atualização.");
                return mensagem;
            }
        } else {
           estacionamento = new Estacionamento();
           estacionamento.setCondominioId(dto.getCondominioId());
           estacionamento.setBloco(dto.getBloco());
           estacionamento.setNumeroCasa(dto.getNumeroCasa());
           estacionamento.setVaga(dto.getVaga());
           estacionamento.setOcupado(dto.getOcupado());
            mensagem.setMensagem("Vaga cadastradoa com sucesso.");
        }

        dao.save(estacionamento);
        return mensagem;
    }


    public MensagemDTO excluirVaga(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Estacionamento> optionalVaga = dao.findById(id);
        if (optionalVaga.isPresent()) {
            Estacionamento estacionamento = optionalVaga.get();
            dao.delete(estacionamento);
            mensagem.setMensagem("Vaga Excluída com sucesso.");
        } else {
            mensagem.setMensagem("Não foi encontrado nenhuma vaga.");
        }
        return mensagem;
    }    
}
