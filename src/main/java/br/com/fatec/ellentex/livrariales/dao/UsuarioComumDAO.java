package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioComum;
import br.com.fatec.ellentex.livrariales.negocio.CriptografarSenhaUsuario;

import java.util.List;

/**
 * @author EllenTex
 */
public class UsuarioComumDAO extends AbstractDAO{

    public UsuarioComumDAO() {
        getEntityManager();
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        UsuarioComum usuario = (UsuarioComum) entidade;

        try{
            getEntityManager().persist(usuario);
        }catch (Exception e){
            System.out.println("Erro ao salvar o Usuário!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return usuario;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        UsuarioComum usuario = (UsuarioComum) entidade;

        try{
            UsuarioComum usuarioPesquisado = getEntityManager().find(UsuarioComum.class,usuario.getId());

            //Criptografar a senha novamente apenas se ela foi alterada!!!
            if(!usuario.getSenha().equals(usuarioPesquisado.getSenha())){
                UsuarioComum user = new UsuarioComum();
                user.setSenha(usuario.getSenha());
                CriptografarSenhaUsuario cript = new CriptografarSenhaUsuario();
                cript.processar(user);
                usuario.setSenha(user.getSenha());
            }

            usuario = getEntityManager().merge(usuario);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR USER!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return usuario;
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
