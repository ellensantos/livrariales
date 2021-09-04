package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Usuario;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroUsuario;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author EllenTex
 */
public class UsuarioDAO extends AbstractDAO{

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

/*        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }*/

        Usuario usuario = (Usuario) entidade;

        try{
            getEntityManager().clear();

            FiltroUsuario filtroUsuario = new FiltroUsuario();
            TypedQuery<EntidadeDominio> query = filtroUsuario.criarQuery(usuario,getEntityManager());
            return query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(ctrlTransacao) {
                getEntityManager().close();
            }
        }

        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar() {
        return null;
    }


}
