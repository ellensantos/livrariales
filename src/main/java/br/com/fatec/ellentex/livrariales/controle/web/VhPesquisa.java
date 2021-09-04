package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PeriodoConsulta;
import br.com.fatec.ellentex.livrariales.dominio.Resultado;
import br.com.fatec.ellentex.livrariales.negocio.ConsultarLivrosVendidosPeriodo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VhPesquisa implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Resultado resultado = null;

        if(operacao.equals("RELATORIO_LIVRO")){
            PeriodoConsulta periodoConsulta = new PeriodoConsulta(request.getParameter("dataInicio"),request.getParameter("dataFim"));
            ConsultarLivrosVendidosPeriodo livrosMaisVendidosPeriodo = new ConsultarLivrosVendidosPeriodo();
            resultado = new Resultado((Map<String, List<EntidadeDominio>>) livrosMaisVendidosPeriodo.processar(periodoConsulta));
        }

        return resultado;
    }

    @Override
    public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd;

        if(operacao.equals("PESQUISA_PERIODO")){
            rd = request.getRequestDispatcher("/admin/graficoPeriodo.jsp");
            rd.forward(request,response);
        }

        else if(operacao.equals("RELATORIO_LIVRO")){
            if(obj != null){
                Resultado resultado = (Resultado) obj;
                ObjectMapper objectMapper = new ObjectMapper();
                try{
                    String json = objectMapper.writeValueAsString(resultado.getMapResultado());
                    System.out.println("JSON = " + json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
