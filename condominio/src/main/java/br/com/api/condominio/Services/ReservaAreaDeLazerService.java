package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.AreaDeLazerDAO;
import br.com.api.condominio.DAO.BlocosDAO;
import br.com.api.condominio.DAO.CasaDAO;
import br.com.api.condominio.DAO.ReservaAreaDeLazerDAO;
import br.com.api.condominio.Model.AreaDeLazer;
import br.com.api.condominio.Model.Blocos;
import br.com.api.condominio.Model.Casa;
import br.com.api.condominio.Model.ReservaAreaDeLazer;
import br.com.api.condominio.Model.DTO.CasaDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;
import br.com.api.condominio.Model.DTO.ReservaAreaDeLazerDTO;
import jakarta.transaction.Transactional;

@Service
public class ReservaAreaDeLazerService {
     @Autowired
    private ReservaAreaDeLazerDAO dao;

    @Autowired
    private AreaDeLazerDAO areaDAO;

    public MensagemDTO salvar(ReservaAreaDeLazerDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        ReservaAreaDeLazer reserva;

        Optional<AreaDeLazer> optionalArea = areaDAO.findById(dto.getAreaDeLazer());
        if (!optionalArea.isPresent()) {
            mensagem.setMensagem("Área não encontrado.");
            return mensagem;
        }
        AreaDeLazer area = optionalArea.get();

        if (isAlterar) {
            Optional<ReservaAreaDeLazer> optionalReserva = dao.findById(dto.getId());
            if (optionalReserva.isPresent()) {
                reserva = optionalReserva.get();
                reserva.setId(dto.getId());
                atualizarReserva(reserva, dto, area);
                mensagem.setMensagem("Reserva atualizada com sucesso.");
            } else {
                mensagem.setMensagem("Reserva não encontrada para atualização.");
                return mensagem;
            }
        } else {
            reserva = new ReservaAreaDeLazer();
            atualizarReserva(reserva, dto, area);
            mensagem.setMensagem("Reserva cadastrada com sucesso.");
        }

        dao.save(reserva);
        return mensagem;
    }

    private void atualizarReserva(ReservaAreaDeLazer reserva, ReservaAreaDeLazerDTO dto, AreaDeLazer area) {
       reserva.setAreaDeLazer(dto.getAreaDeLazer());
       reserva.setBloco(dto.getBloco());
       reserva.setCondominioId(dto.getCondominioId());
       reserva.setData(dto.getData());
       reserva.setHorarioFinal(dto.getHorarioFinal());
       reserva.setHorarioInicio(dto.getHorarioInicio());
        reserva.setMorador(dto.getMorador());
        reserva.setStatus(dto.getStatus());
        reserva.setParticipantes(dto.getParticipantes());
        reserva.setAreaEntity(area);
    }

    public List<ReservaAreaDeLazer> buscar(Integer condominioId) {
        return dao.findByCondominioId(condominioId);
    }

    @Transactional
    public MensagemDTO excluirReserva(Integer id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<ReservaAreaDeLazer> optionalArea = dao.findById(id);
        if (optionalArea.isPresent()) {
            ReservaAreaDeLazer reserva = optionalArea.get();
            dao.delete(reserva);
            mensagem.setMensagem("Reserva excluída com sucesso.");
        } else {
            mensagem.setMensagem("Nenhuma reserva encontrada com esse código.");
        }
        return mensagem;
    }



}
