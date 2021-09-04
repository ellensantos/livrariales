package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Pedido;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroPedido;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author EllenTex
 */
public class PedidoDAO extends AbstractDAO{
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Pedido pedido = (Pedido) entidade;

        try {
            getEntityManager().clear();

            FiltroPedido filtroPedido = new FiltroPedido();
            TypedQuery<EntidadeDominio> query = filtroPedido.criarQuery(pedido, getEntityManager());

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        return null;
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
