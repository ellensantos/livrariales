package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Fornecedor;
import br.com.fatec.ellentex.livrariales.dominio.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class ProdutoDAO extends AbstractDAO{

    public ProdutoDAO() {
        getEntityManager();
    }

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
        if(!(entityManager.isOpen())){
            getEntityManager();
        }

        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }

        Produto produto = (Produto) entidade;
        Produto produtoPesquisado = null;

        try{
            ctrlTransacao = false;

            produtoPesquisado = entityManager.find(Produto.class, produto.getId());
            List<Fornecedor> fornecedor = new ArrayList<Fornecedor>();
            fornecedor = produto.getFornecedor();
            produtoPesquisado.setFornecedor(fornecedor);
            produtoPesquisado.setValor(produto.getValor());

            entityManager.merge(produtoPesquisado);
            entityManager.getTransaction().commit();
            ctrlTransacao = true;

        }catch (Exception e){
            System.out.println("Erro ao salvar Produto!");
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

        return produtoPesquisado;
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
