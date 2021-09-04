package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.Dimensao;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Livro;
import br.com.fatec.ellentex.livrariales.hibernate.LivroPeriodo;
import br.com.fatec.ellentex.livrariales.hibernate.LivroPeriodoSQL;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroLivro;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class LivroDAO extends AbstractDAO {

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Livro livro = (Livro) entidade;

        try {
            getEntityManager().clear();
            List<EntidadeDominio> listaLivro = new ArrayList<EntidadeDominio>();

            FiltroLivro filtroLivro = new FiltroLivro();
            TypedQuery<EntidadeDominio> query = filtroLivro.criarQuery(livro, getEntityManager());

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        if (getEntityManager().getTransaction().isActive()) {
            ctrlTransacao = false;
        } else {
            getEntityManager().getTransaction().begin();
        }

        Livro livro = (Livro) entidade;

        //DAOS
        DimensaoDAO dimensaoDao = new DimensaoDAO();

        try {

            //Salvando dimensão
            EntidadeDominio dimensao = dimensaoDao.salvar(livro.getDimensao());
            //Atribuindo dimensão
            livro.setDimensao((Dimensao) dimensao);
            //Justificativa Status
            livro.setJustificativaStatus("Novo");
            getEntityManager().persist(livro);
            ctrlTransacao = true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Livro!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (ctrlTransacao) {
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return livro;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        Livro livro = (Livro) entidade;

        //DAOS
        DimensaoDAO dimensaoDao = new DimensaoDAO();

        try {
            //Alterando a dimensão
            dimensaoDao.alterar(livro.getDimensao());
            livro = getEntityManager().merge(livro);
            ctrlTransacao = true;

        } catch (Exception e) {
            System.out.println("Erro ao alterar Livro!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }
        return livro;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {

        try {
            alterar(entidade);
        } catch (Exception e) {
            return "falha na operação";
        }
        return "sucesso";
    }

    @Override
    public List<EntidadeDominio> listar() {
        try {

            getEntityManager().clear();

            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
            Root<Livro> root = cQuery.from(Livro.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root).orderBy(builder.asc(root.get("titulo")));
            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }
    }

    public List<LivroPeriodo> consultaMaisVendidoPorPeriodo(String dataInicio, String dataFim){
        try {
            getEntityManager().clear();

            LivroPeriodoSQL livroPeriodo = new LivroPeriodoSQL();
            return livroPeriodo.getLivros(getEntityManager(), dataInicio, dataFim);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
