package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Mensagem;

import java.util.List;

public class MensagemDAO extends AbstractDAO{
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        Mensagem mensagem = (Mensagem) entidade;

        try{
            if(mensagem.getId() <= 0){
                getEntityManager().persist(mensagem);
            }

            mensagem = getEntityManager().merge(mensagem);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Mensagem!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return mensagem;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar() {
        return null;
    }
}
