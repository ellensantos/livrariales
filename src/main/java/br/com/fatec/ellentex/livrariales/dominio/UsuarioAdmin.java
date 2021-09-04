package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author EllenTex
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UsuarioAdmin extends Usuario {

    public UsuarioAdmin(String email, String senha) {
        super(email, senha);
    }

    public UsuarioAdmin() {

    }

}

