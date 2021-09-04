package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.Cidade;
import br.com.fatec.ellentex.livrariales.dominio.Endereco;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Estado;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class EnderecoDAO extends AbstractDAO{

    public EnderecoDAO() {
        getEntityManager();
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        //Implementado apenas para buscar o endereço pelo ID.
        Endereco endereco = (Endereco) entidade;
        List<EntidadeDominio> listaEnt = new ArrayList<>();

        try {
            endereco = getEntityManager().find(Endereco.class, endereco.getId());
            listaEnt.add(endereco);

        }catch (Exception e) {

        }finally {
            if(ctrlTransacao){
                getEntityManager().close();
            }
        }

        return listaEnt;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        Endereco endereco = (Endereco) entidade;

        //Tratar se endereço já existe
        try{
            //Recuperar o objeto Estado pois apenas setei o ID no VH
            //EMF não está atualizando o contexto.
            Cidade cidade = getEntityManager().find(Cidade.class, endereco.getCidade().getId());
            Estado estado = getEntityManager().find(Estado.class, endereco.getCidade().getEstado().getId());
            cidade.setEstado(estado);
            endereco.setCidade(cidade);

            getEntityManager().persist(endereco);

        }catch (Exception e){
            System.out.println("Erro ao ADD Endereço!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return endereco;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {

        Endereco endereco = (Endereco) entidade;

        try{
            if(endereco.getId() <= 0){
               endereco = (Endereco) salvar(endereco);
            }

            endereco = getEntityManager().merge(endereco);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Endereço!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return endereco;
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
