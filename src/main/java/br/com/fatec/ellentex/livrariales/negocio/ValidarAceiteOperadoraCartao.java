package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;

import java.util.Random;

/**
 * @author EllenTex
 */

public class ValidarAceiteOperadoraCartao implements IStrategy{

    @Override
    public Object processar(EntidadeDominio entidade) {
        if(entidade instanceof PedidoCompra) {
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;

            Random random = new Random();
            int numero = random.nextInt(1);

        }

        return null;
    }
}
