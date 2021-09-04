package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemReservadoDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemPedido;
import br.com.fatec.ellentex.livrariales.dominio.ItemReservado;

public class DesbloquearItemPedido implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {
        System.out.println("Desbloqueando Itens do Pedido ... ");
        if(entidade instanceof ItemPedido){
            ItemPedido itemPedido = (ItemPedido) entidade;
            ItemReservadoDAO dao = new ItemReservadoDAO();

            ItemReservado itemReservado = new ItemReservado();
            itemReservado.setQtde(itemPedido.getQtde());
            itemReservado.setProduto(itemPedido.getProduto());
            dao.excluir(itemReservado);
        }

        return null;
    }
}
