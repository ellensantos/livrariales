package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.LogDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Log;

import java.util.Date;

public class GerarLog implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof Log){

            Log log = (Log) entidade;
            if(log.getEntidade()!= null){
                log.setOperacao(log.getOperacao().replace("Command", "").toUpperCase());
                if(log.getUsuarioLogado()!= null) {
                    if (log.getOperacao().contains("SALVAR") || log.getOperacao().contains("ALTERAR")) {
                        System.out.println("Gerando Log ... ");
                        //Fachada efetuou a operação
                        EntidadeDominio entidadeDominio = (EntidadeDominio) log.getEntidade();
                        log.setDtOperacao(new Date());
                        log.setUsuarioResponsavel(log.getUsuarioLogado().getUsuario());
                        log.setNomeEntidade(log.getEntidade().getClass().getSimpleName());
                        log.setIdEntidade(entidadeDominio.getId());
                        LogDAO logDAO = new LogDAO();
                        logDAO.salvar(log);
                    }
                }
            }
        }
        return null;
    }
}
