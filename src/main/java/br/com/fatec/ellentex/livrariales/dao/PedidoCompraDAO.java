package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroPedidoCompra;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author EllenTex
 */
public class PedidoCompraDAO extends AbstractDAO{

    public PedidoCompraDAO() {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        PedidoCompra pedidoCompra = (PedidoCompra) entidade;

        try {
            getEntityManager().clear();

            FiltroPedidoCompra filtroPedido = new FiltroPedidoCompra();
            TypedQuery<EntidadeDominio> query = filtroPedido.criarQuery(pedidoCompra, getEntityManager());

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        PedidoCompra pedidoCompra = (PedidoCompra) entidade;

        //DAOS
        ClienteDAO clienteDAO = new ClienteDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        CartaoDAO cartaoDAO = new CartaoDAO();
        CupomTrocaDAO cupomTrocaDAO= new CupomTrocaDAO();
        List<Pagamento> listPagamento = pedidoCompra.getPagamento();

        try{

            //Endereço Cobrança
            if(pedidoCompra.getEnderecoCobranca().getId() > 0){
                pedidoCompra.setEnderecoCobranca(getEntityManager().find(Endereco.class, pedidoCompra.getEnderecoCobranca().getId()));
            }

            //Endereço Entrega
            if(pedidoCompra.getEnderecoEntrega().getId() > 0){
                pedidoCompra.setEnderecoEntrega(getEntityManager().find(Endereco.class, pedidoCompra.getEnderecoEntrega().getId()));
            }

            if(pedidoCompra.getCupomDesconto()!= null && pedidoCompra.getCupomDesconto().getId() > 0){
                pedidoCompra.setCupomDesconto(getEntityManager().find(CupomDesconto.class,pedidoCompra.getCupomDesconto().getId()));
                pedidoCompra.setCupomDesconto(getEntityManager().merge(pedidoCompra.getCupomDesconto()));
            }

            //Formas de Pagamento
            for(Pagamento pag : listPagamento){
                Cartao cartao = (Cartao) pag.getFormaPagamento();
                if(cartao.getId() <= 0)
                    cartaoDAO.salvar(cartao);
            }

            //Endereços
            enderecoDAO.alterar(pedidoCompra.getEnderecoEntrega());
            enderecoDAO.alterar(pedidoCompra.getEnderecoCobranca());

            //Cupom Troca
            if(pedidoCompra.getCliente().getCupom() != null){
                for(CupomTroca cup : pedidoCompra.getCliente().getCupom()){
                    cupomTrocaDAO.alterar(cup);
                }
            }

            getEntityManager().persist(pedidoCompra);

            //PEDIDO - CLIENTE
            pedidoCompra.getCliente().getPedido().add(pedidoCompra);
            clienteDAO.alterar(pedidoCompra.getCliente());

            //commit em cliente;

        }catch (Exception e){
            System.out.println("Erro ao Salvar Pedido!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            pedidoCompra = null;
        }

        return pedidoCompra;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        PedidoCompra pedidoCompra = (PedidoCompra) entidade;
        ItemEstoqueDAO itemEstoqueDAO = new ItemEstoqueDAO();
        List<ItemPedido> listItemPedido = pedidoCompra.getItens();
        ClienteDAO clienteDAO = new ClienteDAO();

        try{

            for(ItemPedido item : listItemPedido){
                getEntityManager().merge(item.getProduto().getItemEstoque());
                getEntityManager().merge(item.getProduto());
            }

            PedidoCompra pedidoRecebido = pedidoCompra;

            pedidoCompra = getEntityManager().merge(pedidoCompra);

            clienteDAO.alterar(pedidoRecebido.getCliente());

            ctrlTransacao = false;


        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Pedido!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return pedidoCompra;
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
