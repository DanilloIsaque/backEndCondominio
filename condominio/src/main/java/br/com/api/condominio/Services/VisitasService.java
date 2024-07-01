package br.com.api.condominio.Services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.VisitasDAO;
import br.com.api.condominio.Model.Visitas;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.VisitasDTO;

@Service
public class VisitasService {
    
    @Autowired
    private VisitasDAO dao;

    public VisitasDAO getDao(){
        return dao;
    }
     
    public MensagemDTO salvar(VisitasDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Visitas visitas;
        
        Optional<Visitas> optional = dao.findById(dto.getId());
        if (isAlterar) {
            if (optional.isPresent()) {
                visitas = optional.get();
                visitas.setId(dto.getId());
                visitas.setNomeVisita(dto.getNomeVisita());
                visitas.setCondominioId(dto.getCondominioId());
                visitas.setDocumentoVisita(dto.getDocumentoVisita());
                visitas.setMotivo(dto.getMotivo());
                visitas.setDataVisita(dto.getDataVisita());
                visitas.setTelefone(dto.getTelefone());
                mensagem.setMensagem("Visita atualizada com sucesso.");
            } else {
                mensagem.setMensagem("Visita não encontrada para atualização.");
                return mensagem;
            }
        } else {
            visitas = new Visitas();
            visitas.setNomeVisita(dto.getNomeVisita());
            visitas.setCondominioId(dto.getCondominioId());
            visitas.setDocumentoVisita(dto.getDocumentoVisita());
            visitas.setMotivo(dto.getMotivo());
            visitas.setDataVisita(dto.getDataVisita());
            visitas.setTelefone(dto.getTelefone());
            mensagem.setMensagem("Visita cadastrada com sucesso.");
        }

        dao.save(visitas);
        return mensagem;
    }


     public List<Visitas> listarVisitasPorCondominio(Integer condominioId) {
        return dao.findByCondominioId(condominioId);
    }
    

    public MensagemDTO excluirVisita(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Visitas> optionalVisita = dao.findById(id);
        if (optionalVisita.isPresent()) {
            Visitas visita = optionalVisita.get();
            dao.delete(visita);
            mensagem.setMensagem("Visita excluída com sucesso.");
        } else {
            mensagem.setMensagem("Não foi encontrada nenhuma visita para a exclusão");
        }
        return mensagem;
    }
}
