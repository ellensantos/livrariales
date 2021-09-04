package br.com.fatec.ellentex.livrariales.quartz;

import br.com.fatec.ellentex.livrariales.controle.web.SessionListener;
import br.com.fatec.ellentex.livrariales.dominio.Carrinho;
import br.com.fatec.ellentex.livrariales.dominio.ItemPedido;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioComum;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioLogado;
import br.com.fatec.ellentex.livrariales.negocio.DesbloquearItemPedido;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import javax.servlet.http.HttpSession;

public class RemoverItemCarrinhoJOB implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        for (HttpSession session : SessionListener.listaSessao) {
            UsuarioLogado usuarioLogado = (UsuarioLogado) session.getAttribute("usuarioLogado");

            if (usuarioLogado != null) {
                if (usuarioLogado.getUsuario() instanceof UsuarioComum) { //é cliente
                    //System.out.println("Usuário logado = " + usuarioLogado.getUsuario().getEmail());
                    Carrinho carrinho = (Carrinho) session.getAttribute("carrinhoCompra");

                    if (carrinho.getQtdeItens() > 0) {
                        carrinho.setSegDesbloqueioItens(carrinho.getSegDesbloqueioItens() - 1);
                        //System.out.println("Segundos restantes = " + carrinho.getSegDesbloqueioItens());

                        if (carrinho.getSegDesbloqueioItens() <= 0 && carrinho.getQtdeItens() > 0) {
                            System.out.println("Removendo itens do carrinho ... ");
                            for (ItemPedido itemPedido : carrinho.getItens()) {
                                DesbloquearItemPedido desbloquearItemPedido = new DesbloquearItemPedido();
                                desbloquearItemPedido.processar(itemPedido);
                                carrinho.setValorTotal(carrinho.getValorTotal() - itemPedido.getPreco());
                                carrinho.setQtdeItens(carrinho.getQtdeItens() - 1);

                                carrinho.getItensRemovidos().add(itemPedido);
                                carrinho.getItens().remove(itemPedido);
                                carrinho.setSegDesbloqueioItens(-1);

                                if (carrinho.getQtdeItens() == 0)
                                    break;
                            }
                        }
                        session.setAttribute("carrinhoCompra", carrinho);
                    }

                    //Devo fazer um strategy para liberar item do pedido se atingir o valor do parâmetro
                }
            }
        }
    }
}
