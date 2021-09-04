package br.com.fatec.ellentex.livrariales.dominio;

public class PeriodoConsulta extends EntidadeDominio {
    private String dataInicio;
    private String dataFim;

    public PeriodoConsulta(String dataInicio, String dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
