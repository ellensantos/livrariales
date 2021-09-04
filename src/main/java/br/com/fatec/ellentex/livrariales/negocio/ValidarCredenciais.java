package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.UsuarioDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Usuario;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioComum;

import java.util.List;

/**
 * @author EllenTex
 */
public class ValidarCredenciais implements IStrategy{

    @Override
    public Object processar(EntidadeDominio entidade) {

        if (entidade instanceof Usuario) {
            System.out.println("Validando credenciais ... ");
            Usuario usuario = (Usuario) entidade;
            UsuarioDAO dao = new UsuarioDAO();

            List<EntidadeDominio> listUsuario = dao.consultar(usuario);
            //System.out.println("SENHA RECEBIDA = " + usuario.getSenha());

            if (listUsuario != null) {
                if (!listUsuario.isEmpty()) {
                    //Tenho usuário com o e-mail passado
                    Usuario usuarioEncontrado = (Usuario) listUsuario.get(0);
                    if (usuarioEncontrado.isStatus()) {
                        if(usuarioEncontrado instanceof UsuarioComum){
                            CriptografarSenhaUsuario cript = new CriptografarSenhaUsuario();
                            cript.processar(usuario);
                        }
                        if (usuario.getSenha().equals(usuarioEncontrado.getSenha())) {
                            return usuarioEncontrado;
                        } else {
                            return "senha inválida";
                        }
                    }
                    return "usuário desativado";
                }
            }
            return "usuário não existe";
        }
        return null;
    }


}
