package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.util.ComboBoxUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author EllenTex
 */
public class VhItemLivro implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        ItemLivro itemLivro = null;

        String operacao = request.getParameter("operacao");

        if(operacao.equals("SALVAR")){
            itemLivro = definirItemLivro(request);
        }
        //preparar view
        else if(operacao.equals("ENTRADA_ESTOQUE")){
            itemLivro = new ItemLivro();
        }

        //Será usado como consulta para os itens pendentes de aprovação do valor de venda
        else if(operacao.equals("CONSULTAR_PENDENTES")){
            itemLivro = new ItemLivro();
            itemLivro.setStatus(StatusItemLivro.PENDENTE_APROVACAO);
        }

        else if(operacao.equals("CONSULTAR")){
            itemLivro = new ItemLivro();
        }

        else if(operacao.equals("APROVAR_VALOR")){
            itemLivro = new ItemLivro();
            long idItemLivro = Long.parseLong(request.getParameter("idItemLivro"));
            itemLivro.setId(idItemLivro);
            itemLivro.setStatus(StatusItemLivro.APROVADO);
        }

        else if(operacao.equals("NEGAR_VALOR")){
            itemLivro = new ItemLivro();
            long idItemLivro = Long.parseLong(request.getParameter("idItemLivro"));
            itemLivro.setId(idItemLivro);
            itemLivro.setStatus(StatusItemLivro.REPROVADO);
        }

        else if(operacao.equals("ALTERAR")){
            itemLivro = new ItemLivro();
            long idItemLivro = Long.parseLong(request.getParameter("idItemLivro"));
            itemLivro.setId(idItemLivro);
            String tiraVirgulaValor = request.getParameter("novoValorVendaProduto").replace(",", ".");
            itemLivro.setValorVenda(Double.parseDouble(tiraVirgulaValor));
            long idProduto = Long.parseLong(request.getParameter("idProduto"));
            Livro livro = new Livro();
            livro.setId(idProduto);
            itemLivro.setLivro(livro);
        }

        return itemLivro;
    }

    private ItemLivro definirItemLivro(HttpServletRequest request){
        long idProduto = Long.parseLong(request.getParameter("idProduto"));
        int qtdeProduto = Integer.parseInt(request.getParameter("qtdeProduto"));
        String tiraVirgulaValor = request.getParameter("valorCustoProduto").replace(",", ".");
        double valorCusto = Double.parseDouble(tiraVirgulaValor);
        long idFornecedor = Long.parseLong(request.getParameter("idFornecedor"));
        double valorVendaProduto = 0;

        if(request.getParameter("valorVendaProduto")!= null){
            tiraVirgulaValor = request.getParameter("valorVendaProduto");
            valorVendaProduto = Double.parseDouble(tiraVirgulaValor);
        }

        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

        Date dataEntrada = new Date();
        String data = request.getParameter("dataEntrada");

        try {
            dataEntrada = formatoData.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Livro livro = new Livro();
        livro.setId(idProduto);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(idFornecedor);

        ItemLivro itemLivro = new ItemLivro(livro, fornecedor,qtdeProduto,dataEntrada, valorCusto, valorVendaProduto);

        return itemLivro;
    }

    @Override
    public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd = null;
        String mensagemVH = null;
        ComboBoxUtil comboBoxUtil = new ComboBoxUtil();

        if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")){
            if(obj instanceof ItemLivro) {
                mensagemVH = "sucesso";
                request.getSession().setAttribute("msg", mensagemVH);
            }else{
                request.getSession().setAttribute("msg", obj);
            }
            response.sendRedirect("Estoque?operacao=LISTAR_LIVROS");

        }
        else if(operacao.equals("ENTRADA_ESTOQUE")){
            //Preencher combobox para entrada de itens no estoque
            request.getSession().setAttribute("livros", comboBoxUtil.carregarComboBox("Livro"));
            request.getSession().setAttribute("fornecedores", comboBoxUtil.carregarComboBox("Fornecedor"));
            rd = request.getRequestDispatcher("/admin/entradaItemEstoque.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("CONSULTAR_PENDENTES")){
            request.getSession().setAttribute("listaItemLivroPendente", obj);
            rd = request.getRequestDispatcher("/admin/listarLivrosPendenteValorVenda.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("CONSULTAR")){
            request.getSession().setAttribute("listaEntradaItem", obj);
            rd = request.getRequestDispatcher("/admin/listarEntradaItemEstoque.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("APROVAR_VALOR") || operacao.equals("NEGAR_VALOR")){
            if(obj instanceof ItemLivro) {
                mensagemVH = "sucesso";
                request.getSession().setAttribute("msg", mensagemVH);
            }else{
                request.getSession().setAttribute("msg", obj);
            }
            response.sendRedirect("ItemLivro?operacao=CONSULTAR_PENDENTES");
        }



    }
}
