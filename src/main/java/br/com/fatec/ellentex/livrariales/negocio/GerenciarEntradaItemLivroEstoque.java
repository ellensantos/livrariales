package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemEstoqueDAO;
import br.com.fatec.ellentex.livrariales.dao.ItemLivroDAO;
import br.com.fatec.ellentex.livrariales.dominio.*;

import java.util.List;

/**
 * @author EllenTex
 */
public class GerenciarEntradaItemLivroEstoque implements IStrategy{
    //Método para ajustar a quantidade e valor de custo do Item de Livro

    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof ItemLivro){
            System.out.println("Gerenciando entrada de item de livro no estoque ... ");
            ItemLivro itemLivro = (ItemLivro) entidade;
            ItemEstoque itemEstoque = new ItemEstoque();
            ItemEstoqueDAO dao = new ItemEstoqueDAO();

            System.out.println("ID " + itemLivro.getId());

            if(itemLivro.getId() > 0){
                ItemLivroDAO itemLivroDAO = new ItemLivroDAO();
                List<EntidadeDominio> listEntidadeDominio = itemLivroDAO.consultar(itemLivro);

                System.out.println(listEntidadeDominio);
                System.out.println(listEntidadeDominio.size());
                StatusItemLivro status = itemLivro.getStatus();

                // Recebo NULL -> itemLivro = (ItemLivro) listEntidadeDominio.get(0);

                //Refazendo Obj
                ItemLivro itemLivroEncontrado = (ItemLivro) listEntidadeDominio.get(0);
                itemLivro.setItemEstoque(itemLivroEncontrado.getItemEstoque());
                itemLivro.setLivro(itemLivroEncontrado.getLivro());
                itemLivro.setValorVenda(itemLivroEncontrado.getValorVenda());
                itemLivro.setValorCusto(itemLivroEncontrado.getValorCusto());
                itemLivro.setDataEntrada(itemLivroEncontrado.getDataEntrada());
                itemLivro.setFornecedor(itemLivroEncontrado.getFornecedor());
                itemLivro.setQuantidade(itemLivroEncontrado.getQuantidade());
                itemLivro.setStatus(status);
            }

            itemEstoque.setProduto(itemLivro.getLivro());
            List<EntidadeDominio> listEntidadeDominio = dao.consultar(itemEstoque);

            //Entrada no estoque, pois na alteração não altero a quantidade.
            if(itemLivro.getStatus().equals(StatusItemLivro.REGISTRADO) || itemLivro.getStatus().equals(StatusItemLivro.PENDENTE_APROVACAO)){
                itemEstoque.setQuantidade(itemLivro.getQuantidade());
            }

            itemLivro.getLivro().setStatus(true);
            itemLivro.getLivro().setCategoriaInativacao(null);
            itemLivro.getLivro().setCategoriaAtivacao(null);
            itemEstoque.setProduto(itemLivro.getLivro());
            itemEstoque.setValorVenda(itemLivro.getValorVenda());

            if(!listEntidadeDominio.isEmpty()) {
                ItemEstoque itemEncontrado = (ItemEstoque) listEntidadeDominio.get(0);
                int qtdeItensEntrada = itemEstoque.getQuantidade();
                itemEstoque.setQuantidade(qtdeItensEntrada + itemEncontrado.getQuantidade()); //entrada
                itemEstoque.setId(itemEncontrado.getId());
                itemEstoque.setEstoque(itemEncontrado.getEstoque());

                //Se não for aprovado, o valor será substituído se for inferior ao valor de venda do item do estoque
                if(!itemLivro.getStatus().equals(StatusItemLivro.APROVADO)) {
                    if ((itemEstoque.getValorVenda() < itemEncontrado.getValorVenda())) {
                        itemEstoque.setValorVenda(itemEncontrado.getValorVenda());
                    }
                }
            }

            else {
                itemEstoque.setEstoque(new Estoque());
            }

            itemLivro.setItemEstoque(itemEstoque);
        }

        return null;
    }
}
