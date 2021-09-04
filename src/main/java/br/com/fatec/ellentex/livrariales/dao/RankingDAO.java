package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Ranking;

import java.util.List;

public class RankingDAO extends AbstractDAO{
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
        Ranking ranking = (Ranking) entidade;

        try{
            if(ranking.getId() <= 0){
                getEntityManager().persist(ranking);
            }

            ranking = getEntityManager().merge(ranking);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Ranking!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return ranking;
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
