package br.com.api.condominio.Services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.CondominioDAO;
import br.com.api.condominio.Model.Condominio;
import br.com.api.condominio.Model.DTO.CondominioDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;

@Service
public class CondominioService {

    @Autowired
    private CondominioDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CondominioDAO getDao(){
        return dao;
    }

    public MensagemDTO salvar(CondominioDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Condominio condominio;
    
        if (isAlterar) {
            Optional<Condominio> optionalCondo = dao.findById(dto.getId());
            if (optionalCondo.isPresent()) {
                condominio = optionalCondo.get();
                if (!condominio.getEmail().equals(dto.getEmail())) {
                    Optional<Condominio> existingEmail = dao.findByEmail(dto.getEmail());
                    if (existingEmail.isPresent()) {
                        mensagem.setMensagem("Email já cadastrado no sistema.");
                        return mensagem;
                    }
                }
                condominio.setId(dto.getId());
                condominio.setNome(dto.getNome());
                condominio.setEmail(dto.getEmail());
                condominio.setSenha(passwordEncoder.encode(dto.getSenha()));
                condominio.setLogradouro(dto.getLogradouro());
                condominio.setBairro(dto.getBairro());
                condominio.setCidade(dto.getCidade());
                condominio.setEstado(dto.getEstado());
                condominio.setNumero(dto.getNumero());
                condominio.setCep(dto.getCep());
                condominio.setProprietario(dto.getProprietario());
                condominio.setNomeProprietario(dto.getNomeProprietario());
                condominio.setPlano(dto.getPlano());
                mensagem.setMensagem("Condominio atualizado com sucesso.");
            } else {
                mensagem.setMensagem("Condominio não encontrado para atualização.");
                return mensagem;
            }
        } else {
            Optional<Condominio> existingEmail = dao.findByEmail(dto.getEmail());
            if (existingEmail.isPresent()) {
                mensagem.setMensagem("Email já cadastrado no sistema.");
                return mensagem;
            }
            condominio = new Condominio();
            condominio.setNome(dto.getNome());
            condominio.setEmail(dto.getEmail());
            condominio.setSenha(passwordEncoder.encode(dto.getSenha()));
            condominio.setLogradouro(dto.getLogradouro());
            condominio.setCidade(dto.getCidade());
            condominio.setEstado(dto.getEstado());
            condominio.setNumero(dto.getNumero());
            condominio.setCep(dto.getCep());
            condominio.setProprietario(dto.getProprietario());
            condominio.setNomeProprietario(dto.getNomeProprietario());
            condominio.setPlano(dto.getPlano());
            mensagem.setMensagem("Condominio cadastrado com sucesso.");
        }
    
        dao.save(condominio);
        return mensagem;
    }
    

    public MensagemDTO updatePlano(Integer id, Integer plano) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Condominio> condominioOptional = dao.findById(id);
        if (condominioOptional.isPresent()) {
            Condominio condominio = condominioOptional.get();
            condominio.setPlano(plano);
            dao.save(condominio);
            mensagem.setTitulo(null);
            if(plano==1){
                mensagem.setMensagem("Plano modificado com sucesso!");
            }
            else if(plano ==2){
            mensagem.setMensagem("Plano modificado com sucesso! Agora os moradores têm acesso ao sistema.");
            }else if(plano ==0){
                mensagem.setMensagem("Seu plano estará inativo até que o senhor(a) ative novamente");
            }
            return mensagem;
        } else {
            throw new RuntimeException("Condominio não encontrado");
        }
    }

    
}
