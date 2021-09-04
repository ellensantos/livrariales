package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.LivroDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemLivro;
import br.com.fatec.ellentex.livrariales.dominio.Livro;
import br.com.fatec.ellentex.livrariales.dominio.StatusItemLivro;

/**
 * @author EllenTex
 */
public class CalcularValorVendaItemLivro implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof ItemLivro) {
            System.out.println("Calculando valor de venda do item de Livro ... ");
            ItemLivro itemLivro = (ItemLivro) entidade;
            LivroDAO livroDAO = new LivroDAO();
            itemLivro.setLivro((Livro) livroDAO.consultar(itemLivro.getLivro()).get(0));

            double margemLucroDefinida = (itemLivro.getValorCusto() + (itemLivro.getValorCusto() * itemLivro.getLivro().getPrecificacao().getMargemLucro()));

            if (itemLivro.getId() <= 0) {
                if (itemLivro.getValorVenda() > 0) {
                    //Foi inserido manualmente um valor para venda
                    if (itemLivro.getValorVenda() < margemLucroDefinida) {
                        itemLivro.setStatus(StatusItemLivro.PENDENTE_APROVACAO);
                    } else {
                        itemLivro.setStatus(StatusItemLivro.REGISTRADO);
                    }
                } else {
                    itemLivro.setValorVenda(margemLucroDefinida);
                    itemLivro.setStatus(StatusItemLivro.REGISTRADO);
                }
            }
            else{
                if (itemLivro.getValorVenda() < margemLucroDefinida) {
                    itemLivro.setStatus(StatusItemLivro.PENDENTE_APROVACAO);
                }
            }
        }
        return null;
    }
}
