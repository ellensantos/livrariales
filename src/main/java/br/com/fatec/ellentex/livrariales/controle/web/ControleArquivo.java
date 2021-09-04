package br.com.fatec.ellentex.livrariales.controle.web;

/**
 * @author EllenTex
 */

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/ControleArquivo", "/admin/EnviarArquivo"})
public class ControleArquivo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ENTREI NA SERVLET DO UPLOAD!!");

        String nomeArqEnviado = null;
        String caminhoArq = null;

        /*Identifica se o formulario é do tipo multipart/form-data*/
//        if (ServletFileUpload.isMultipartContent(request)) {
        try {
            /*Faz o parse da request*/
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            System.out.println("PART = " + multiparts);

            /*Escreve a o arquivo na pasta images*/

            //Recuperando o input com o nome do arquivo submetido
            for (FileItem item : multiparts) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("nomeArquivo")) {
                        nomeArqEnviado = item.getString();
                    }
                }
            }
            //Salvando a imagem no diretório
            for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                    item.write(new File(request.getServletContext().getRealPath("images")+ File.separator + nomeArqEnviado));
                    caminhoArq = request.getServletContext().getRealPath("images")+ File.separator + nomeArqEnviado;
                    System.out.println("NOME ARQ ENVIADO= " + nomeArqEnviado);
                    System.out.println("CAMINHO ARQ= " + caminhoArq);
                }
            }
            request.setAttribute("msgUploadArquivo", "Arquivo carregado com sucesso");
            request.setAttribute("nomeImagemProduto", nomeArqEnviado); //Mensagem
        } catch (Exception ex) {
            request.setAttribute("msgUploadArquivo", "Upload de arquivo falhou. Tente novamente.");
            System.out.println("Exceção do Upload = " + ex);

        }

      /*  } else {
            request.setAttribute("message","Desculpe este Servlet lida apenas com pedido de upload de arquivos");
        }*/

        //request.getRequestDispatcher("/testeUpload.jsp").forward(request, response);

        request.getRequestDispatcher("/admin/Livro?operacao=CADASTRAR_LIVRO").forward(request, response);

    }
}
