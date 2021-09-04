package br.com.fatec.ellentex.livrariales.controle.web; /**
 * @author EllenTex
 */

import br.com.fatec.ellentex.livrariales.dominio.Usuario;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioAdmin;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioComum;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioLogado;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "FilterAutorizacao", urlPatterns = {"/AdicionarItem", "/Carrinho", "/cliente/*", "/admin/*"})
public class FilterAutorizacao implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        //System.out.println("URI Autorização" + uri);

        HttpSession sessao = request.getSession();

        boolean userNaoLogado = (sessao.getAttribute("usuarioLogado") == null);

        if(userNaoLogado){
            if(uri.contains("admin")) {
                response.sendRedirect(request.getContextPath() + "/formLoginAdmin.jsp");
            }
            else{
                response.sendRedirect(request.getContextPath() + "/formLogin.jsp");
            }
            return;
        }

        else {
            Usuario usuario = ((UsuarioLogado) sessao.getAttribute("usuarioLogado")).getUsuario();

            if(uri.contains("admin") && usuario instanceof UsuarioComum){
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/formLoginAdmin.jsp");
            }
            else if(!uri.contains("admin") && usuario instanceof UsuarioAdmin){
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/formLogin.jsp");
            }
        }
        chain.doFilter(request, response);
    }
}
