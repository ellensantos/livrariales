package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Usuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author EllenTex
 */
public class CriptografarSenhaUsuario implements IStrategy {

    public Object processar(EntidadeDominio entidade) {

        if (entidade instanceof Usuario) {
            Usuario usuario = (Usuario) entidade;

            MessageDigest algoritmo = null;

            try {
                algoritmo = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return "Erro ao criptografar a senha!";
            }
            byte digestMessage[] = new byte[0];
            try {
                digestMessage = algoritmo.digest(usuario.getSenha().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return "Erro ao criptografar a senha!";
            }
            StringBuilder hexPassword = new StringBuilder();

            for (byte aByte : digestMessage) {
                hexPassword.append(String.format("%02X", 0xFF & aByte));
            }

            // Só vai setar a senha criptografada se a entidade não tiver ID
            if(usuario.getId() == 0){
                usuario.setSenha(hexPassword.toString());
                return null;
            }
        }
        return null;
    }
}
