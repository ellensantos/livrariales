package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author EllenTex
 */
@Entity
public class Estoque extends EntidadeDominio {
    @OneToMany(mappedBy = "estoque", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ItemEstoque> itens;

    public Estoque() {
    }

    public Estoque(List<ItemEstoque> itens) {
        this.itens = itens;
    }

    public List<ItemEstoque> getItens() {
        return itens;
    }

    public void setItens(List<ItemEstoque> itens) {
        this.itens = itens;
    }
}
