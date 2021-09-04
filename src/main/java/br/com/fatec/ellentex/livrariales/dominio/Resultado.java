package br.com.fatec.ellentex.livrariales.dominio;

import java.util.List;
import java.util.Map;

public class Resultado extends EntidadeDominio {
    private Map<String, List<EntidadeDominio>> mapResultado;

    public Resultado(Map<String, List<EntidadeDominio>> mapResultado) {
        this.mapResultado = mapResultado;
    }

    public Map<String, List<EntidadeDominio>> getMapResultado() {
        return mapResultado;
    }
}
