package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ClienteDAO;
import br.com.fatec.ellentex.livrariales.dominio.Cliente;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class ValidarExistenciaCliente implements IStrategy{

    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof Cliente) {
            System.out.println("Validando existência do cliente ... ");
            Cliente cliente = (Cliente) entidade;

            if(cliente.getId() > 0){
                return null; //Quando for alteração
            }

            ClienteDAO dao = new ClienteDAO();
            List<EntidadeDominio> listaCliente = dao.listar();

            for(EntidadeDominio l : listaCliente){
                Cliente cli = (Cliente) l;
                if(cli.getCpf().equals(cliente.getCpf())){
                    return "CPF já cadastrado! ";
                }

                if(cli.getUsuario().getEmail().equals(cliente.getUsuario().getEmail())){
                    return "E-mail já cadastrado! ";
                }
            }
        }
        return null;
    }
}
