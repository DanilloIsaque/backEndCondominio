package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.BlocosDAO;
import br.com.api.condominio.DAO.CasaDAO;
import br.com.api.condominio.Model.Blocos;
import br.com.api.condominio.Model.Casa;
import br.com.api.condominio.Model.DTO.CasaDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import jakarta.transaction.Transactional;

@Service
public class CasaService {

    @Autowired
    private CasaDAO dao;

    @Autowired
    private BlocosDAO blocosDAO;

    public MensagemDTO salvar(CasaDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Casa casa;

        Optional<Blocos> optionalBloco = blocosDAO.findById(dto.getBloco());
        if (!optionalBloco.isPresent()) {
            mensagem.setMensagem("Bloco não encontrado.");
            return mensagem;
        }
        Blocos bloco = optionalBloco.get();

        if (isAlterar) {
            Optional<Casa> optionalCasa = dao.findById(dto.getId());
            if (optionalCasa.isPresent()) {
                casa = optionalCasa.get();
                atualizarCasa(casa, dto, bloco);
                mensagem.setMensagem("Casa atualizada com sucesso.");
            } else {
                mensagem.setMensagem("Casa não encontrada para atualização.");
                return mensagem;
            }
        } else {
            casa = new Casa();
            atualizarCasa(casa, dto, bloco);
            mensagem.setMensagem("Casa cadastrada com sucesso.");
        }

        dao.save(casa);
        return mensagem;
    }

    private void atualizarCasa(Casa casa, CasaDTO dto, Blocos bloco) {
        casa.setCondominioId(dto.getCondominioId());
        casa.setNumeroCasa(dto.getNumeroCasa());
        casa.setBloco(dto.getBloco());
        casa.setAluguel(dto.getAluguel());
        casa.setTelefone(dto.getTelefone());
        casa.setMoradorId(dto.getMoradorId());
        casa.setVazia(dto.getVazia());
        casa.setBlocoEntity(bloco);
    }

    public List<Casa> buscar(Integer condominioId) {
        return dao.findByCondominioIdWithBloco(condominioId);
    }

    @Transactional
    public MensagemDTO excluirCasa(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Casa> optionalCasa = dao.findById(id);
        if (optionalCasa.isPresent()) {
            Casa casa = optionalCasa.get();
            dao.delete(casa);
            mensagem.setMensagem("Casa excluída com sucesso.");
        } else {
            mensagem.setMensagem("Nenhuma casa encontrada com esse código.");
        }
        return mensagem;
    }

    @Transactional
    public void desassociarCasasDoBloco(Integer blocoId) {
        List<Casa> casas = dao.findByBloco(blocoId);
        if (!casas.isEmpty()) {
            for (Casa casa : casas) {
                System.out.println("Desassociando casa com ID: " + casa.getId());
                System.out.println("Desassociando casa com bloco: " + casa.getBloco());
                casa.setBloco(5); // Desassocia o bloco
                System.out.println("Bloco atual: " + casa.getBloco());
                dao.save(casa);
            }
            // Salvando as alterações após desassociar todas as casas do bloco
          
        } else {
            System.out.println("Nenhuma casa encontrada para desassociação com bloco " + blocoId);
        }
    }
    
}
