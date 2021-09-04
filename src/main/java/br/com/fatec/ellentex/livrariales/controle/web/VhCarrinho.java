package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.aplicacao.ParametrosSistema;
import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.negocio.ConsultarDisponibilidadeItem;
import br.com.fatec.ellentex.livrariales.util.ComboBoxUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author EllenTex
 */
public class VhCarrinho implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        //Carrinho carrinho = null;

        String operacao = request.getParameter("operacao");

        if(operacao.equals("ADD_ITEM_CARRINHO")){
            return addItemCarrinho(request);
        }

        else if(operacao.equals("REMOVER_ITEM_CARRINHO")){
            return removerItemCarrinho(request);
        }

        else if(operacao.equals("ADD_UNIDADE_ITEM")){
            return addUnidadeItemCarrinho(request);
        }

        else if(operacao.equals("DEL_UNIDADE_ITEM")){
            return removerUnidadeItemCarrinho(request);
        }

        return null;
    }

    private ItemReservado addItemCarrinho(HttpServletRequest request) {
        //Recuperar carrinho e itens do carrinho
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");
        List<Livro> livrosCatalogo = (List<Livro>) request.getSession().getAttribute("livrosCatalogo");
        long idProduto = Long.parseLong(request.getParameter("idProduto"));
        int qtdeLivroSelecionado = Integer.parseInt(request.getParameter("qtdeLivroSelecionado00"));
        ItemReservado itemReservado = new ItemReservado();
        Produto prod = new Livro();
        prod.setId(idProduto);
        itemReservado.setQtde(qtdeLivroSelecionado);
        prod.setItemReservado(itemReservado);

        //Validar disponibilidade da quantidade através do Item Reservado.
        ConsultarDisponibilidadeItem consultarDisponibilidadeItem = new ConsultarDisponibilidadeItem();

        if(consultarDisponibilidadeItem.processar(prod) == null){
            if (carrinho.getQtdeItens() > 0) {
                List<ItemPedido> itensPedido = carrinho.getItens();

                for (ItemPedido item : itensPedido) {
                    if (item.getProduto().getId() == idProduto) {
                        //Item já está no carrinho devo somar uma unidade
                        item.setQtde(item.getQtde() + qtdeLivroSelecionado);
                        item.setPreco((item.getProduto().getItemEstoque().getValorVenda()) * item.getQtde()); //Valor total do Item x Qtde
                        carrinho.setValorTotal(carrinho.getValorTotal() + ((item.getProduto().getItemEstoque().getValorVenda()) * qtdeLivroSelecionado));

                        //Reservando a quantidade do livro adicionada no carrinho
                        itemReservado.setQtde(qtdeLivroSelecionado);
                        itemReservado.setProduto(item.getProduto());
                        carrinho.setSegDesbloqueioItens(ParametrosSistema.SEGUNDOS_BLOQUEIO_PRODUTO);
                        request.getSession().setAttribute("carrinhoCompra", carrinho);
                        return itemReservado;
                    }
                }
            }

            //Não está no carrinho, devo ADD
            ItemPedido item = new ItemPedido();

            for (Livro livro : livrosCatalogo) {
                if (livro.getId() == idProduto) {
                    item.setProduto(livro);
                    item.setQtde(qtdeLivroSelecionado);
                }
            }

            //Reservando a quantidade do livro adicionada no carrinho
            itemReservado.setQtde(item.getQtde());
            itemReservado.setProduto(item.getProduto());

            item.setPreco(item.getProduto().getItemEstoque().getValorVenda() * item.getQtde()); //valor * qtde

            carrinho.getItens().add(item);
            carrinho.setQtdeItens(carrinho.getQtdeItens() + 1);

            carrinho.setValorTotal(carrinho.getValorTotal() + item.getPreco());

            carrinho.setSegDesbloqueioItens(ParametrosSistema.SEGUNDOS_BLOQUEIO_PRODUTO);
            request.getSession().setAttribute("carrinhoCompra", carrinho);

            return itemReservado;
        }

        // A ideia é retornar o item para reservar
        return null;
    }

    private ItemReservado removerItemCarrinho(HttpServletRequest request){
        //Recuperar carrinho e itens do carrinho
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");

        List<Livro> livrosCatalogo = (List<Livro>) request.getSession().getAttribute("livrosCatalogo");

        long idProduto = Long.parseLong(request.getParameter("idProduto"));
        ItemReservado itemReservado = new ItemReservado();

        List<ItemPedido> itensPedido = carrinho.getItens();

        System.out.println("TOTAL de ITENS = " + itensPedido.size());

        for(ItemPedido item : itensPedido){
            if(item.getProduto().getId() == idProduto){
                carrinho.setValorTotal(carrinho.getValorTotal() - item.getPreco());
                carrinho.setQtdeItens(carrinho.getQtdeItens() - 1);

                //Liberando as quantidades
                itemReservado.setQtde(item.getQtde());
                itemReservado.setProduto(item.getProduto());
                itensPedido.remove(item);
                break;
            }
        }

        request.getSession().setAttribute("carrinhoCompra", carrinho);

        if(carrinho.getQtdeItens() == 0){
            carrinho.setSegDesbloqueioItens(-1);
        }

        return itemReservado;
    }

    private ItemReservado addUnidadeItemCarrinho(HttpServletRequest request){
        //Recuperar carrinho e itens do carrinho
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");
        List<Livro> livrosCatalogo = (List<Livro>) request.getSession().getAttribute("livrosCatalogo");
        long idProduto = Long.parseLong(request.getParameter("idProduto"));
        ItemReservado itemReservado = new ItemReservado();
        List<ItemPedido> itensPedido = carrinho.getItens();

        for(ItemPedido item : itensPedido){
            if(item.getProduto().getId() == idProduto){
                Produto prod = item.getProduto();
                ConsultarDisponibilidadeItem consultarDisponibilidadeItem = new ConsultarDisponibilidadeItem();
                itemReservado.setQtde(1);
                prod.setItemReservado(itemReservado);

                if(consultarDisponibilidadeItem.processar(prod) == null) {

                    //Item já está no carrinho devo somar uma unidade
                    item.setQtde(item.getQtde() + 1);
                    item.setPreco((item.getProduto().getItemEstoque().getValorVenda()) * item.getQtde()); //Valor total do Item x Qtde
                    carrinho.setValorTotal(carrinho.getValorTotal() + ((item.getProduto().getItemEstoque().getValorVenda())));

                    //Reservando a quantidade do livro adicionada no carrinho
                    itemReservado.setQtde(1);
                    itemReservado.setProduto(item.getProduto());
                    carrinho.setSegDesbloqueioItens(ParametrosSistema.SEGUNDOS_BLOQUEIO_PRODUTO);
                    request.getSession().setAttribute("carrinhoCompra", carrinho);
                    //Toda vez que o item for adicionado, um AJAX fará a requisição para saber os minutos de bloqueio que deverão ser contados
                    return itemReservado;
                }
            }
        }
        // A ideia é retornar o item para reservar
        return null;
    }

    private ItemReservado removerUnidadeItemCarrinho(HttpServletRequest request){
        //Recuperar carrinho e itens do carrinho
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");

        List<Livro> livrosCatalogo = (List<Livro>) request.getSession().getAttribute("livrosCatalogo");

        long idProduto = Long.parseLong(request.getParameter("idProduto"));
        ItemReservado itemReservado = new ItemReservado();

        List<ItemPedido> itensPedido = carrinho.getItens();

        for(ItemPedido item : itensPedido){
            if(item.getProduto().getId() == idProduto){
                //Liberando as quantidades
                itemReservado.setQtde(1);
                itemReservado.setProduto(item.getProduto());
                item.setQtde(item.getQtde() - 1);
                item.setPreco((item.getProduto().getItemEstoque().getValorVenda()) * item.getQtde()); //Valor total do Item x Qtde
                carrinho.setValorTotal(carrinho.getValorTotal() - ((item.getProduto().getItemEstoque().getValorVenda())));

                if (item.getQtde() == 0){
                    carrinho.setQtdeItens(carrinho.getQtdeItens() - 1);
                    itensPedido.remove(item);
                }
                break;
            }
        }

        request.getSession().setAttribute("carrinhoCompra", carrinho);

        if(carrinho.getQtdeItens() == 0){
            carrinho.setSegDesbloqueioItens(-1);
        }

        return itemReservado;
    }

    @Override
    public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd = null;

        if(operacao.equals("REMOVER_ITEM_CARRINHO") ||  operacao.equals("DEL_UNIDADE_ITEM")){
            response.sendRedirect("/ellentex-livrariales/carrinhoCompra.jsp");
        }

        else if(operacao.equals("ADD_UNIDADE_ITEM") ||operacao.equals("ADD_ITEM_CARRINHO")){
            if(obj == null){
                String msg = "Quantidade Indisponível do Item";
                request.getSession().setAttribute("erro", msg);
            }

            response.sendRedirect("/ellentex-livrariales/carrinhoCompra.jsp");
        }

        else if(operacao.equals("CHECKOUT")){
            ComboBoxUtil comboBoxUtil = new ComboBoxUtil();
            request.getSession().setAttribute("bandeiraCartao", comboBoxUtil.carregarComboBox("BandeiraCartao"));
            //response.sendRedirect("/ellentex-livrariales/checkout.jsp");
            rd = request.getRequestDispatcher("/checkout.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("TEMPO_SESSAO")){
            Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");
            response.setContentType("text/plain");
            response.getWriter().write(String.valueOf(carrinho.getSegDesbloqueioItens())); //se devolver -1, o carrinho está vazio
        }
    }
}
