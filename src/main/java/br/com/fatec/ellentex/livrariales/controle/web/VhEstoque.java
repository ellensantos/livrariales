package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemEstoque;
import br.com.fatec.ellentex.livrariales.dominio.ItemLivro;
import br.com.fatec.ellentex.livrariales.dominio.Livro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author EllenTex
 */
public class VhEstoque implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        ItemEstoque itemEstoque = null;

        String operacao = request.getParameter("operacao");

        if(operacao.equals("LISTAR_LIVROS")){
            itemEstoque = new ItemEstoque();
        }

        else if(operacao.equals("ALTERAR")){
            itemEstoque = new ItemEstoque();
            long idItemEstoque = Long.parseLong(request.getParameter("idItemEstoque"));
            itemEstoque.setId(idItemEstoque);
            String tiraVirgulaValor = request.getParameter("novoValorVendaProduto").replace(",", ".");
            itemEstoque.setValorVenda(Double.parseDouble(tiraVirgulaValor));
        }

        return itemEstoque;
    }

    @Override
    public void setView(Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd;
        String mensagemVH;

        if(operacao.equals("LISTAR_LIVROS")){
            request.getSession().setAttribute("listaItemEstoque", obj);
            rd = request.getRequestDispatcher("/admin/listarEstoqueLivro.jsp");
            rd.forward(request,response);
        }

        if(operacao.equals("ALTERAR")){
            if(obj instanceof ItemEstoque) {
                mensagemVH = "sucesso";
                request.getSession().setAttribute("msg", mensagemVH);
            }else{
                request.getSession().setAttribute("msg", obj);
            }
            response.sendRedirect("Estoque?operacao=LISTAR_LIVROS");
        }
    }
}
