package br.com.fatec.ellentex.livrariales.controle.web;

/*
 * @author EllenTex
 */

import br.com.fatec.ellentex.livrariales.dao.IDAO;
import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.negocio.LimparCarrinhoCompra;
import br.com.fatec.ellentex.livrariales.negocio.ValidarCredenciais;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/Login", "/Logout", "/admin/Logout", "/LoginAdmin", "/cliente/Logout"})
public class Autenticacao extends HttpServlet {

    private Map<String, IDAO> daos;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario usuario = new Usuario(email,senha);
        UsuarioLogado usuarioLogado = new UsuarioLogado();

        String uri = request.getRequestURI();
        //System.out.println("URI Autenticação = " + uri);

        if(operacao.equals("LOGAR")) {
            ValidarCredenciais validarCredenciais = new ValidarCredenciais();
            Object obj = validarCredenciais.processar(usuario);

            if(obj instanceof Usuario) {
                usuarioLogado.setUsuario((Usuario) obj);

                if (obj instanceof UsuarioComum && !uri.contains("LoginAdmin")) {
                    request.getSession().setAttribute("usuarioLogado", usuarioLogado);
                    //Inserir um carrinho na sessão
                    request.getSession().setAttribute("carrinhoCompra", new Carrinho(((UsuarioComum) usuarioLogado.getUsuario()).getCliente()));
                    response.sendRedirect("index.jsp");
                }

                else if(obj instanceof UsuarioComum && uri.contains("LoginAdmin")){
                    String msg = "usuario não existe";
                    request.getSession().setAttribute("msg", msg);
                    response.sendRedirect("formLoginAdmin.jsp");
                }

                else if(obj instanceof UsuarioAdmin && !uri.contains("LoginAdmin")){
                    String msg = "usuario não existe";
                    request.getSession().setAttribute("msg", msg);
                    response.sendRedirect("formLogin.jsp");
                }

                else if (obj instanceof UsuarioAdmin && uri.contains("LoginAdmin")) {
                    request.getSession().setAttribute("usuarioLogado", usuarioLogado);
                    response.sendRedirect(request.getContextPath() + "/admin/Pedido?operacao=LISTAR_PEDIDO_COMPRA");
                }
            }

            else if(obj instanceof String){
                if(uri.contains("LoginAdmin")){
                    request.getSession().setAttribute("msg", obj);
                    response.sendRedirect("formLoginAdmin.jsp");
                }

                else{
                    request.getSession().setAttribute("msg", obj);
                    response.sendRedirect("formLogin.jsp");
                }
            }
        }

        else if(operacao.equals("DESLOGAR")) {
            Usuario user = ((UsuarioLogado) request.getSession().getAttribute("usuarioLogado")).getUsuario();

            if(user instanceof UsuarioComum){
                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");
                LimparCarrinhoCompra limparCarrinhoCliente = new LimparCarrinhoCompra();
                limparCarrinhoCliente.processar(carrinho);
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            }

            else if (user instanceof UsuarioAdmin){
                request.getSession().invalidate();
                response.sendRedirect("formLoginAdmin.jsp");
            }
        }
    }
}

