package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.AreaDeLazerDAO;
import br.com.api.condominio.DAO.BlocosDAO;
import br.com.api.condominio.DAO.CasaDAO;
import br.com.api.condominio.Model.AreaDeLazer;
import br.com.api.condominio.Model.Blocos;
import br.com.api.condominio.Model.Casa;
import br.com.api.condominio.Model.DTO.AreaDeLazerDTO;
import br.com.api.condominio.Model.DTO.CasaDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import jakarta.transaction.Transactional;

@Service
public class AreaDeLazerService {
    
  @Autowired
   private AreaDeLazerDAO dao;

   @Autowired
   private BlocosDAO blocosDAO;

    public MensagemDTO salvar(AreaDeLazerDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        AreaDeLazer area;

        Optional<Blocos> optionalBloco = blocosDAO.findById(dto.getBloco());
        if (!optionalBloco.isPresent()) {
            mensagem.setMensagem("Bloco não encontrado.");
            return mensagem;
        }
        Blocos bloco = optionalBloco.get();

        if (isAlterar) {
            Optional<AreaDeLazer> optionalArea = dao.findById(dto.getId());
            if (optionalArea.isPresent()) {
                area = optionalArea.get();
                atualizarArea(area, dto, bloco);
                mensagem.setMensagem("Casa atualizada com sucesso.");
            } else {
                mensagem.setMensagem("Casa não encontrada para atualização.");
                return mensagem;
            }
        } else {
            area = new AreaDeLazer();
            atualizarArea(area, dto, bloco);
            mensagem.setMensagem("Casa cadastrada com sucesso.");
        }

        dao.save(area);
        return mensagem;
    }

    private void atualizarArea(AreaDeLazer area, AreaDeLazerDTO dto, Blocos bloco) {
        area.setDescricao(dto.getDescricao());
        area.setBloco(dto.getBloco());
        area.setCondominioId(dto.getCondominioId());
        area.setNome(dto.getNome());
        area.setBlocoEntity(bloco);
    }

    public List<AreaDeLazer> buscar(Integer condominioId) {
        return dao.findByCondominioId(condominioId);
    }

    @Transactional
    public MensagemDTO excluirAreaDeLazer(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<AreaDeLazer> optionalArea = dao.findById(id);
        if (optionalArea.isPresent()) {
            AreaDeLazer area = optionalArea.get();
            dao.delete(area);
            mensagem.setMensagem("Área de lazer excluída com sucesso.");
        } else {
            mensagem.setMensagem("Nenhuma área encontrada com esse código.");
        }
        return mensagem;
    }

    @Transactional
    public void desassociarAreasDoBloco(Integer blocoId) {
        List<AreaDeLazer> areas = dao.findByBloco(blocoId);
        if (!areas.isEmpty()) {
            for (AreaDeLazer areaDeLazer : areas) {
                areaDeLazer.setBloco(5); // Desassocia o bloco
                dao.save(areaDeLazer);
            }
            // Salvando as alterações após desassociar todas as areas do bloco
          
        } else {
            System.out.println("Nenhuma casa encontrada para desassociação com bloco " + blocoId);
        }
    }
    
    
}
