package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.AvisosAlertasDAO;
import br.com.api.condominio.DAO.MoradorDAO;
import br.com.api.condominio.Model.AvisosAlertas;
import br.com.api.condominio.Model.DTO.AvisosAlertasDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;

@Service
public class AvisosAlertasService {
    
     @Autowired
    private AvisosAlertasDAO dao;

    @Autowired
    private MoradorDAO moradorDAO;

    public AvisosAlertasDAO getDao(){
       return dao;
    }

    
     public MensagemDTO salvar(AvisosAlertasDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        AvisosAlertas avisosAlertas;

        if (isAlterar) {
            Optional<AvisosAlertas> optionalAvisos = dao.findById(dto.getId());
            if (optionalAvisos.isPresent()) {
                avisosAlertas = optionalAvisos.get();
                avisosAlertas.setId(dto.getId());
                avisosAlertas.setCondominioId(dto.getCondominioId());
                avisosAlertas.setTitulo(dto.getTitulo());
                avisosAlertas.setTexto(dto.getTexto());
                avisosAlertas.setDataPost(dto.getDataPost());
                avisosAlertas.setMoradorId(dto.getMoradorId());
                avisosAlertas.setAvisoAlerta(dto.getAvisoAlerta());
                mensagem.setMensagem("Aviso/alerta atualizado com sucesso.");
            } else {
                mensagem.setMensagem("Aviso/alerta não encontrado para atualização.");
                return mensagem;
            }
        } else {
            avisosAlertas = new AvisosAlertas();
                avisosAlertas.setCondominioId(dto.getCondominioId());
                avisosAlertas.setTitulo(dto.getTitulo());
                avisosAlertas.setTexto(dto.getTexto());
                avisosAlertas.setDataPost(dto.getDataPost());
                avisosAlertas.setMoradorId(dto.getMoradorId());
                avisosAlertas.setAvisoAlerta(dto.getAvisoAlerta());
            mensagem.setMensagem("Aviso/alerta cadastrado com sucesso.");
        }

        dao.save(avisosAlertas);
        return mensagem;
    }

 public List<AvisosAlertas> listarPorCondominio(Integer condominioId,String moradorId) {
    List<AvisosAlertas> avisosAlertas = dao.findByCondominioIdAndMoradorId(condominioId, moradorId);
        
    // Ordena os resultados em ordem descendente pela data de postagem
    avisosAlertas.sort((a, b) -> b.getDataPost().compareTo(a.getDataPost()));
    
    return avisosAlertas;
    }

    public MensagemDTO excluirAviso(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<AvisosAlertas> optionalAviso = dao.findById(id);
        if (optionalAviso.isPresent()) {
            AvisosAlertas aviso = optionalAviso.get();
            dao.delete(aviso);
            mensagem.setMensagem("Aviso/alerta Excluído com sucesso.");
        } else {
            mensagem.setMensagem("Não foi encontrado nenhum aviso/alerta com esse código.");
        }
        return mensagem;
    }
}
