package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.Cliente;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author EllenTex
 */
public class CriptografarSenhaCliente implements IStrategy {

    public Object processar(EntidadeDominio entidade) {

        if (entidade instanceof Cliente) {
            System.out.println("Criptografando senha cliente ... ");
            Cliente cliente = (Cliente) entidade;
            MessageDigest algoritmo = null;

            try {
                algoritmo = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return "Erro ao criptografar a senha!";
            }
            byte digestMessage[] = new byte[0];
            try {
                digestMessage = algoritmo.digest(cliente.getUsuario().getSenha().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return "Erro ao criptografar a senha!";
            }
            StringBuilder hexPassword = new StringBuilder();

            for (byte aByte : digestMessage) {
                hexPassword.append(String.format("%02X", 0xFF & aByte));
            }

            // Só vai setar a senha criptografada se a entidade não tiver ID
            if(cliente.getId() == 0){
                cliente.getUsuario().setSenha(hexPassword.toString());
                return null;
            }
        }
        return null;
    }
}
