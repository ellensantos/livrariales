package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.util.ComboBoxUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class VhLivro implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Livro livro = null;

        String operacao = request.getParameter("operacao");

        if(operacao.equals("SALVAR")){
            livro = definirLivro(request);
        }
        else if(operacao.equals(("CADASTRAR_LIVRO")) || operacao.equals(("PESQUISAR_LIVRO"))){
            livro = new Livro();
        }
        else if(operacao.equals(("CONSULTAR"))){
            livro = definirLivroPesquisa(request);
        }
        else if(operacao.equals("LISTAR_LIVROS") || operacao.equals("TESTE_LISTAR_LIVROS")){
            livro = new Livro();
        }
        else if(operacao.equals("PERFIL_LIVRO") || operacao.equals("ATIVAR_LIVRO") || operacao.equals("DESATIVAR_LIVRO")){
            livro = new Livro();
            livro.setId(Long.parseLong(request.getParameter("idLivro")));
        }
        else if(operacao.equals("PESQUISAR_LIVRO_TITULO")){
            livro = new Livro();
            livro.setTitulo(request.getParameter("consultaTituloLivro"));
        }
        else if(operacao.equals("ALTERAR")){
            livro = definirLivro(request);
            livro.setId(Long.parseLong(request.getParameter("idLivroPerfil")));
            livro.getDimensao().setId(Long.parseLong(request.getParameter("idDimensaoLivroPerfil")));
        }
        else if(operacao.equals("HABILITAR_LIVRO")){
            List<EntidadeDominio> list = (List<EntidadeDominio>) request.getSession().getAttribute("livroAtivar");
            livro = (Livro) list.get(0);
            livro.setStatus(true);
            livro.setJustificativaStatus(request.getParameter("justificativaStatus"));
            CategoriaAtivacao categoriaAtivacao = new CategoriaAtivacao();
            categoriaAtivacao.setId(Long.parseLong(request.getParameter("idCategoriaAtivacao")));
            livro.setCategoriaAtivacao(categoriaAtivacao);
            livro.setCategoriaInativacao(null);
        }

        else if(operacao.equals("EXCLUIR")){
            List<EntidadeDominio> list = (List<EntidadeDominio>) request.getSession().getAttribute("livroInativar");
            livro = (Livro) list.get(0);

            livro.setStatus(false);
            livro.setJustificativaStatus(request.getParameter("justificativaStatus"));
            CategoriaInativacao categoriaInativacao = new CategoriaInativacao();
            categoriaInativacao.setId(Long.parseLong(request.getParameter("idCategoriaInativacao")));
            livro.setCategoriaInativacao(categoriaInativacao);
            livro.setCategoriaAtivacao(null);
        }

        else if(operacao.equals("CONFERIR_LIVRO")){
            livro = new Livro();
            livro.setId(Long.parseLong(request.getParameter("idLivro")));
        }

        return livro;
    }

    private Livro definirLivroPesquisa(HttpServletRequest request) {
        String tiraVirgulaValor = null;
        //Tratando as vírgulas para o campo do tipo number do form
        tiraVirgulaValor = request.getParameter("numeroEdicao").replace(",", ".");
        double edicao = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("altura").replace(",", ".");
        double altura = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("largura").replace(",", ".");
        double largura = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("comprimento").replace(",", ".");
        double comprimento = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("peso").replace(",", ".");
        double peso = Double.parseDouble(tiraVirgulaValor);
        int status = (Integer.parseInt(request.getParameter("statusLivro")));
        String titulo = request.getParameter("tituloLivro");
        String isbn = request.getParameter("isbn");
        int qtdePagina = Integer.parseInt(request.getParameter("qtdePaginas"));
        int anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
        int idAutor = Integer.parseInt(request.getParameter("idAutor"));
        int idEditora = Integer.parseInt(request.getParameter("idEditora"));
        String sinopse = request.getParameter("sinopse");
        int idPrecificacao = Integer.parseInt(request.getParameter("idPrecificacao"));
        String codBarras = request.getParameter("codigoDeBarras");
        int qtdeCategoria = Integer.parseInt(request.getParameter("qtdeCategoria"));

        Autor autor = new Autor();
        autor.setId(idAutor);

        Editora editora = new Editora();
        editora.setId(idEditora);

        Precificacao precificacao = new Precificacao();
        precificacao.setId(idPrecificacao);

        Dimensao dimensao = new Dimensao(altura,largura,comprimento,peso);


        List<CategoriaLivro> listaCategoriaLivro = new ArrayList<CategoriaLivro>();

        if(qtdeCategoria > 0) {
            for (int i = 1; i <= qtdeCategoria; i++) {
                if (request.getParameter("categoria" + i) != null && !request.getParameter("categoria" + i).equals("")) {
                    int idCategoria = Integer.parseInt(request.getParameter("categoria" + i));
                    CategoriaLivro categoriaLivro = new CategoriaLivro();
                    categoriaLivro.setId(idCategoria);
                    listaCategoriaLivro.add(categoriaLivro);
                }
            }
        }else{
            listaCategoriaLivro = null;
        }

        Livro livro = new Livro(titulo, anoLancamento, edicao, isbn, qtdePagina, sinopse,
                null, autor, editora, null,
                null, listaCategoriaLivro, precificacao,
                dimensao);

        livro.setCodBarras(codBarras);


        return livro;
    }


    private Livro definirLivro(HttpServletRequest request){

        String tiraVirgulaValor = null;
        //Tratando as vírgulas para o campo do tipo number do form
        tiraVirgulaValor = request.getParameter("numeroEdicao").replace(",", ".");
        double edicao = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("altura").replace(",", ".");
        double altura = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("largura").replace(",", ".");
        double largura = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("comprimento").replace(",", ".");
        double comprimento = Double.parseDouble(tiraVirgulaValor);
        tiraVirgulaValor = request.getParameter("peso").replace(",", ".");
        double peso = Double.parseDouble(tiraVirgulaValor);
        int status = (Integer.parseInt(request.getParameter("statusLivro")));
        String titulo = request.getParameter("tituloLivro");
        String isbn = request.getParameter("isbn");
        int qtdePagina = Integer.parseInt(request.getParameter("qtdePaginas"));
        int anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
        int idAutor = Integer.parseInt(request.getParameter("idAutor"));
        int idEditora = Integer.parseInt(request.getParameter("idEditora"));
        String nomeImagem = request.getParameter("nomeImagemProduto");
        String sinopse = request.getParameter("sinopse");
        int idPrecificacao = Integer.parseInt(request.getParameter("idPrecificacao"));
        String codBarras = request.getParameter("codigoDeBarras");
        //double valor = Double.parseDouble(request.getParameter("valorLivro"));
        int qtdeCategoria = Integer.parseInt(request.getParameter("qtdeCategoria"));
        String justificativaStatus = request.getParameter("justificativaStatus");
        int idCategoriaAtivacao = Integer.parseInt(request.getParameter("idCategoriaAtivacao"));
        int idCategoriaInativacao = Integer.parseInt(request.getParameter("idCategoriaInativacao"));
        int idDimensao = Integer.parseInt(request.getParameter("idDimensao"));

        System.out.println("NOME IMAGEM = " + nomeImagem);

        Autor autor = new Autor();
        autor.setId(idAutor);

        Editora editora = new Editora();
        editora.setId(idEditora);

        Precificacao precificacao = new Precificacao();
        precificacao.setId(idPrecificacao);

        Dimensao dimensao = new Dimensao(altura,largura,comprimento,peso);

        if(idDimensao > 0){
            dimensao.setId(idDimensao);
        }

        CategoriaAtivacao categoriaAtivacao = new CategoriaAtivacao();
        if(idCategoriaAtivacao == 0){
            categoriaAtivacao = null;
        }
        else if(idCategoriaAtivacao != 0){
            categoriaAtivacao.setId(idCategoriaAtivacao);
        }

        CategoriaInativacao categoriaInativacao = new CategoriaInativacao();
        if(idCategoriaInativacao == 0){
            categoriaInativacao = null;
        }
        else if(idCategoriaInativacao != 0){
            categoriaInativacao.setId(idCategoriaInativacao);
        }

        List<CategoriaLivro> listaCategoriaLivro = new ArrayList<CategoriaLivro>();

        if(qtdeCategoria > 0) {
            for (int i = 1; i <= qtdeCategoria; i++) {
                if (request.getParameter("categoria" + i) != null) {
                    int idCategoria = Integer.parseInt(request.getParameter("categoria" + i));
                    CategoriaLivro categoriaLivro = new CategoriaLivro();
                    categoriaLivro.setId(idCategoria);
                    listaCategoriaLivro.add(categoriaLivro);
                }
            }
        }else{
            listaCategoriaLivro = null;
        }

        Livro livro = new Livro(titulo, anoLancamento, edicao, isbn, qtdePagina, sinopse,
                justificativaStatus, autor, editora, categoriaInativacao,
                categoriaAtivacao, listaCategoriaLivro, precificacao,
                dimensao);

        livro.setCodBarras(codBarras);
        //livro.setValor(valor);
        livro.setFoto(nomeImagem);
        livro.setTipo(livro.getClass().getSimpleName());
        livro.setJustificativaStatus(justificativaStatus);

        if(status == 1) {
            livro.setStatus(true);
        }else{
            livro.setStatus(false);
        }

        return livro;
    }


    @Override
    public void setView(Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd = null;
        String mensagemVH = null;
        ComboBoxUtil comboBoxUtil = new ComboBoxUtil();


        if(operacao.equals("SALVAR")){
            if(obj instanceof Livro) {
                mensagemVH = "sucesso";
                request.getSession().setAttribute("msg", mensagemVH);
                //response.sendRedirect("ListarLivros?operacao=LISTAR_LIVROS");
                rd = request.getRequestDispatcher("/admin/Livro?operacao=LISTAR_LIVROS");
                rd.forward(request, response);
            }else{
                request.getSession().setAttribute("msg", obj);
                response.sendRedirect("Livro?operacao=CADASTRAR_LIVRO");
                //response.sendRedirect(request.getRequestURI());
                //rd = request.getRequestDispatcher("/erro.jsp");
            }
            //rd.forward(request, response);
        }

        else if(operacao.equals(("CADASTRAR_LIVRO"))){
            request.getSession().setAttribute("autores", comboBoxUtil.carregarComboBox("Autor"));
            request.getSession().setAttribute("editoras", comboBoxUtil.carregarComboBox("Editora"));
            request.getSession().setAttribute("categoriasLivro", comboBoxUtil.carregarComboBox("CategoriaLivro"));
            request.getSession().setAttribute("precificacoes", comboBoxUtil.carregarComboBox("Precificacao"));
            rd = request.getRequestDispatcher("/admin/cadastrarLivro.jsp");
            rd.forward(request, response);
        }

        else if(operacao.equals(("PESQUISAR_LIVRO"))){
            request.getSession().setAttribute("autores", comboBoxUtil.carregarComboBox("Autor"));
            request.getSession().setAttribute("editoras", comboBoxUtil.carregarComboBox("Editora"));
            request.getSession().setAttribute("categoriasLivro", comboBoxUtil.carregarComboBox("CategoriaLivro"));
            request.getSession().setAttribute("precificacoes", comboBoxUtil.carregarComboBox("Precificacao"));
            rd = request.getRequestDispatcher("/admin/pesquisarLivro.jsp");
            rd.forward(request, response);
        }

        else if(operacao.equals(("CONSULTAR")) || operacao.equals("LISTAR_LIVROS") || operacao.equals("PESQUISAR_LIVRO_TITULO")){
            if(obj != null){
                request.getSession().setAttribute("listaLivrosConsultados", obj);
            } else{
                request.getSession().setAttribute("msg", obj);
            }
            rd = request.getRequestDispatcher("/admin/listarLivros.jsp");
            rd.forward(request, response);
        }

        else if(operacao.equals(("PERFIL_LIVRO"))){
            request.getSession().setAttribute("autores", comboBoxUtil.carregarComboBox("Autor"));
            request.getSession().setAttribute("editoras", comboBoxUtil.carregarComboBox("Editora"));
            request.getSession().setAttribute("categoriasLivro", comboBoxUtil.carregarComboBox("CategoriaLivro"));
            request.getSession().setAttribute("precificacoes", comboBoxUtil.carregarComboBox("Precificacao"));
            request.getSession().setAttribute("livroPerfil", obj);
            rd = request.getRequestDispatcher("/admin/perfilLivro.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("ALTERAR") || operacao.equals("EXCLUIR") || operacao.equals("HABILITAR_LIVRO")){
            if(obj instanceof Livro){
                mensagemVH = "sucesso";
                request.getSession().setAttribute("msg", mensagemVH);
            }else{
                request.getSession().setAttribute("msg", obj);
            }
            response.sendRedirect("Livro?operacao=LISTAR_LIVROS");
        }

        //View para ativar um livro
        else if(operacao.equals("ATIVAR_LIVRO")){
            request.getSession().setAttribute("categoriasAtivacao", comboBoxUtil.carregarComboBox("CategoriaAtivacao"));
            request.getSession().setAttribute("livroAtivar", obj);
            rd = request.getRequestDispatcher("/admin/ativarLivro.jsp");
            rd.forward(request,response);
        }

        //View para desativar um livro
        else if(operacao.equals("DESATIVAR_LIVRO")){
            request.getSession().setAttribute("categoriasInativacao", comboBoxUtil.carregarComboBox("CategoriaInativacao"));
            request.getSession().setAttribute("livroInativar", obj);
            rd = request.getRequestDispatcher("/admin/desativarLivro.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("CONFERIR_LIVRO")){
            if(obj == null){
                mensagemVH = "Falha ao conferir livro.";
                request.getSession().setAttribute("msg", mensagemVH);
            }

            else{

                request.getSession().setAttribute("livros", ((List<EntidadeDominio>) obj).get(0));
            }

            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request,response);
        }



    }
}
