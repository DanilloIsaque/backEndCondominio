package br.com.api.condominio.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.condominio.DAO.FuncaoDAO;
import br.com.api.condominio.DAO.FuncionarioDAO;
import br.com.api.condominio.Model.Funcao;
import br.com.api.condominio.Model.Funcionario;
import br.com.api.condominio.Model.DTO.FuncionarioDTO;
import br.com.api.condominio.Model.DTO.MensagemDTO;

@Service
public class FuncionarioService {

     @Autowired
    private FuncionarioDAO dao;

    @Autowired
    private FuncaoDAO funcaoDAO;

    public FuncionarioDAO getDao(){
        return dao;
    }
     
    public MensagemDTO salvar(FuncionarioDTO dto, Boolean isAlterar) {
        MensagemDTO mensagem = new MensagemDTO();
        Funcionario funcionario = new Funcionario();
        
        Optional<Funcionario> optionalFuncionario = dao.findById(dto.getId());
        Optional<Funcao> optionalFuncao = funcaoDAO.findById(dto.getFuncao());
        Funcao funcao = optionalFuncao.get();
        if (isAlterar) {
            if (optionalFuncao.isPresent()) {
                funcionario = optionalFuncionario.get();
                funcionario.setId(dto.getId());
                funcionario.setNome(dto.getNome());
                funcionario.setFuncao(dto.getFuncao());
                funcionario.setSenha(dto.getSenha());
                funcionario.setEmail(dto.getEmail());
                funcionario.setDataNascimento(dto.getDataNascimento());
                funcionario.setFuncaoEntity(funcao);
                mensagem.setMensagem("Funcionário(a) atualizado(a) com sucesso.");
            } else {
                mensagem.setMensagem("Funcionário(a) não encontrado(a) para atualização.");
                return mensagem;
            }
        } else {
            if (optionalFuncao.isPresent()){
                mensagem.setMensagem("Já existe um funcionário com esse cpf nesse condomínio.");
                return mensagem;
            }
            funcionario.setId(dto.getId());
            funcionario.setNome(dto.getNome());
            funcionario.setFuncao(dto.getFuncao());
            funcionario.setSenha(dto.getSenha());
            funcionario.setEmail(dto.getEmail());
            funcionario.setDataNascimento(dto.getDataNascimento());
            funcionario.setFuncaoEntity(funcao);
            mensagem.setMensagem("Funcionário(a) cadastrado(a) com sucesso.");
        }

        dao.save(funcionario);
        return mensagem;
    }

    public List<Funcionario> listaFuncionarios(Integer condominioId){
        return dao.findByCondominioId(condominioId);
    }

    public MensagemDTO excluirFuncionario(String id) {
        MensagemDTO mensagem = new MensagemDTO();
        Optional<Funcionario> optionalFuncionario = dao.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            dao.delete(funcionario);
            mensagem.setMensagem("Funcionário(a) excluído(a) com sucesso.");
        } else {
            mensagem.setMensagem("Não foi encontrado(a) nenhum(a) funcionário(a) com esse cpf/cnpj.");
        }
        return mensagem;
    }
    
}
