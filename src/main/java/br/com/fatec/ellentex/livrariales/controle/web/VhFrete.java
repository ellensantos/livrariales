package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.Carrinho;
import br.com.fatec.ellentex.livrariales.dominio.Endereco;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;
import br.com.fatec.ellentex.livrariales.negocio.CalcularFrete;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author EllenTex
 */
public class VhFrete implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        PedidoCompra pedidoCompra = null;
        String operacao = request.getParameter("operacao");

        System.out.println("VH FRETE!!!");

        return null;
    }

    double calcularFrete(HttpServletRequest request){

        PedidoCompra pedidoCompra = new PedidoCompra();
        Endereco endereco = new Endereco();
        String cep = request.getParameter("cepEndEntrega1");
        String idEndEntrega = request.getParameter("idEndEntregaSelecionado");
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");
        pedidoCompra.setQtdeItens(carrinho.getQtdeItens());

        if(idEndEntrega != null && idEndEntrega != ""){
            endereco.setId(Long.parseLong(request.getParameter("idEndEntregaSelecionado")));
        }

        else if(cep != null){
            endereco.setCep(request.getParameter("cepEndEntrega1"));
        }

        pedidoCompra.setEnderecoEntrega(endereco);
        CalcularFrete calcularFrete = new CalcularFrete();
        calcularFrete.processar(pedidoCompra);

        return pedidoCompra.getFrete();
    }

    @Override
    public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd = null;

        if(operacao.equals("CALCULAR_FRETE")){
            double valorFrete = calcularFrete(request);
            response.setContentType("text/plain");
            if(valorFrete > 0){
                response.getWriter().write(String.valueOf(calcularFrete(request)));
            } else{
                response.getWriter().write("null");
            }
        }
    }
}
