package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemEstoqueDAO;
import br.com.fatec.ellentex.livrariales.dao.ItemReservadoDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemEstoque;
import br.com.fatec.ellentex.livrariales.dominio.ItemReservado;
import br.com.fatec.ellentex.livrariales.dominio.Produto;

import java.util.List;

/**
 * @author EllenTex
 */
public class ConsultarDisponibilidadeItem implements IStrategy{

    @Override
    public java.lang.Object processar(EntidadeDominio entidade) {

        if(entidade instanceof Produto){
            System.out.println("Consultando disponibilidade do item em estoque ...");
            Produto produto = (Produto) entidade;

            ItemReservadoDAO daoItemReservado = new ItemReservadoDAO();
            ItemEstoqueDAO daoItemEstoque = new ItemEstoqueDAO();

            ItemReservado itemRes = new ItemReservado();
            ItemEstoque itemEst = new ItemEstoque();
            itemRes.setProduto(produto);
            itemEst.setProduto(produto);

            List<EntidadeDominio> listaItemReservado = daoItemReservado.consultar(itemRes);
            List<EntidadeDominio> listaItemEstoque = daoItemEstoque.consultar(itemEst);

            int qtdeItemDisponível = 0;

            if(!listaItemEstoque.isEmpty()){
                ItemEstoque itemEstoque = (ItemEstoque) listaItemEstoque.get(0);
                qtdeItemDisponível += itemEstoque.getQuantidade();
            }

            if(!listaItemReservado.isEmpty()){
                ItemReservado itemReservado = (ItemReservado) listaItemReservado.get(0);
                qtdeItemDisponível -= itemReservado.getQtde();
            }

            if(qtdeItemDisponível >= produto.getItemReservado().getQtde()){
                return null;
            }

            return "estoque indisponivel para esse item";
        }

        return null;
    }
}
