package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

/**
 * @author EllenTex
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CupomTroca extends Cupom {

    @Column(columnDefinition = "BOOLEAN")
    private boolean status;

    public CupomTroca() {
    }

    public CupomTroca(String codigo, double valor, Date validade) {
        super(codigo, valor, validade);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
