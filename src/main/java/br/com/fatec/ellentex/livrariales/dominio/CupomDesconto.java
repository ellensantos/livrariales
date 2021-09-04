package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

/**
 * @author EllenTex
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CupomDesconto extends Cupom {

    public CupomDesconto() {
    }

    public CupomDesconto(String codigo, double valor, Date validade) {
        super(codigo, valor, validade);
    }
}
