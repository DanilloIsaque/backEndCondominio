package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.BlocosDAO;
import br.com.api.condominio.Model.Blocos;
import br.com.api.condominio.Model.DTO.BlocosDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import jakarta.transaction.Transactional;

@Service
public class BlocosService {

    @Autowired
    private BlocosDAO dao;

    @Autowired
    private CasaService casaService;

    @Autowired
    private AreaDeLazerService areaService;

    @Transactional
    public MensagemDTO salvar(BlocosDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Blocos bloco;

        if (isAlterar) {
            Optional<Blocos> optionalBloco = dao.findById(dto.getId());
            if (optionalBloco.isPresent()) {
                bloco = optionalBloco.get();
                bloco.setCondominioId(dto.getCondominioId());
                bloco.setQtdAndares(dto.getQtdAndares());
                bloco.setDescricao(dto.getDescricao());
                bloco.setQtdCasas(dto.getQtdCasas());
                bloco.setDivisao(dto.getDivisao());
                mensagem.setMensagem("Bloco atualizado com sucesso.");
            } else {
                mensagem.setMensagem("Bloco não encontrado para atualização.");
                return mensagem;
            }
        } else {
            bloco = new Blocos();
            bloco.setCondominioId(dto.getCondominioId());
            bloco.setQtdAndares(dto.getQtdAndares());
            bloco.setDescricao(dto.getDescricao());
            bloco.setQtdCasas(dto.getQtdCasas());
            bloco.setDivisao(dto.getDivisao());
            mensagem.setMensagem("Bloco cadastrado com sucesso.");
        }

        dao.save(bloco);
        return mensagem;
    }

    @Transactional
    public MensagemDTO excluirBloco(Integer id) {
        casaService.desassociarCasasDoBloco(id);
        areaService.desassociarAreasDoBloco(id);
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Blocos> optionalBloco = dao.findById(id);
        if (optionalBloco.isPresent()) {
            Blocos bloco = optionalBloco.get();
            dao.delete(bloco);
            mensagem.setMensagem("Bloco excluído com sucesso.");
        } else {
            mensagem.setMensagem("Nenhum bloco encontrado com esse código.");
        }
        return mensagem;
    }

    public List<Blocos> listarBlocosPorCondominio(Integer condominioId) {
        return dao.findByCondominioId(condominioId);
    }
}
