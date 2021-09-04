package br.com.fatec.ellentex.livrariales.controle.web; /**
 * @author EllenTex
 */

import br.com.fatec.ellentex.livrariales.util.ComboBoxUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FilterIndex", urlPatterns = "/index.jsp")
public class FilterIndex implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ComboBoxUtil comboBoxUtil = new ComboBoxUtil();
        HttpServletRequest req = (HttpServletRequest) request;
        req.getSession().setAttribute("livrosCatalogo", comboBoxUtil.carregarComboBox("Livro"));
        chain.doFilter(request, response);
    }
}
