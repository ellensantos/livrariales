package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.Cupom;
import br.com.fatec.ellentex.livrariales.dominio.CupomDesconto;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author EllenTex
 */
public class VhCupom implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        System.out.println("Operação no VH CUPOM = " + operacao);

        if(operacao.equals("VALIDAR_CUPOM_DESCONTO")){
            CupomDesconto cupomDesconto = new CupomDesconto();
            cupomDesconto.setCodigo(request.getParameter("cupomDesconto"));
            System.out.println("CUPOM = " + cupomDesconto.getCodigo());
            return cupomDesconto;
        }
        return null;
    }



    @Override
    public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");

        if(operacao.equals("VALIDAR_CUPOM_DESCONTO")){
            System.out.println("OBJETO = " + obj);

            if(obj == null){
                System.out.println("CUPOM NÃO EXISTE!");
                response.setContentType("text/plain");
                response.getWriter().write("cupom invalido");
            }

            else{
               List<EntidadeDominio> lista = (List<EntidadeDominio>) obj;
                Cupom cp = (Cupom) lista.get(0);
                System.out.println("Cupom válido!!! Código = " + cp.getCodigo());
                response.setContentType("text/plain");
                response.getWriter().write(String.valueOf(((Cupom) lista.get(0)).getValor()));
            }
        }
    }
}
