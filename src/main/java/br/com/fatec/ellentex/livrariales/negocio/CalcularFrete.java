package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.EnderecoDAO;
import br.com.fatec.ellentex.livrariales.dominio.Endereco;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;

/**
 * @author EllenTex
 */
public class CalcularFrete implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {
        if (entidade instanceof PedidoCompra) {
            System.out.println("Calculando frete ... ");
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;

            if (pedidoCompra.getId() <= 0) {
                Endereco endereco = pedidoCompra.getEnderecoEntrega();

                if (endereco.getId() > 0) {
                    EnderecoDAO dao = new EnderecoDAO();
                    endereco = (Endereco) dao.consultar(endereco).get(0);
                }

                if (endereco.getCep() == null || endereco.getCep().length() < 7) {
                    pedidoCompra.setFrete(0);
                    return "cep invalido";
                }

                int ultimoDig = Integer.parseInt(String.valueOf(endereco.getCep().charAt(7)));
                double valorFrete;

                // Adicional de 0.50 por item
                if (ultimoDig <= 3) {
                    //15.00
                    valorFrete = 15.00;
                    pedidoCompra.setFrete(valorFrete + (0.50 * pedidoCompra.getQtdeItens()));
                } else if (ultimoDig >= 4 && ultimoDig < 7) {
                    //12.00
                    valorFrete = 12.00;
                } else {
                    //10.00
                    valorFrete = 10.00;
                }
                pedidoCompra.setFrete(valorFrete + (0.50 * pedidoCompra.getQtdeItens()));
                pedidoCompra.setValorTotal((pedidoCompra.getValorTotal() + pedidoCompra.getFrete()));
            }
        }

        return null;
    }
}
