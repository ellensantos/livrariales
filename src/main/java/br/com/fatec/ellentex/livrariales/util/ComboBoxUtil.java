package br.com.fatec.ellentex.livrariales.util;

import br.com.fatec.ellentex.livrariales.dao.*;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EllenTex
 */
public class ComboBoxUtil {
    private Map<String, IDAO> daos;

    public ComboBoxUtil() {
        daos = new HashMap<String, IDAO>();
        daos.put("Autor", new AutorDAO());
        daos.put("BandeiraCartao", new BandeiraCartaoDAO());
        daos.put("CategoriaAtivacao", new CategoriaAtivacaoDAO());
        daos.put("CategoriaInativacao", new CategoriaInativacaoDAO());
        daos.put("CategoriaLivro", new CategoriaLivroDAO());
        daos.put("Editora", new EditoraDAO());
        daos.put("Precificacao", new PrecificacaoDAO());
        daos.put("Livro", new LivroDAO());
        daos.put("Fornecedor", new FornecedorDAO());
    }

    public List<EntidadeDominio> carregarComboBox(String nomeClasse) {
        IDAO dao = daos.get(nomeClasse);

        List<EntidadeDominio> listaConsulta = listaConsulta = dao.listar();

        if(listaConsulta != null){
            return listaConsulta;
        }
        else{
            List<EntidadeDominio> listaVazia = new ArrayList<>();
            return listaVazia;
        }

    }

}
