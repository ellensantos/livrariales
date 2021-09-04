package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 */
public enum StatusPedido {
    RECUSADO("RECUSADO"),
    EM_PROCESSAMENTO("EM_PROCESSAMENTO"),
    APROVADO("APROVADO"),
    REPROVADO("REPROVADO"),
    EM_TRANSPORTE("EM_TRANSPORTE"),
    ENTREGUE("ENTREGUE"),
    EM_TROCA("EM_TROCA"),
    TROCA_AUTORIZADA("TROCA_AUTORIZADA"),
    PRODUTO_RECEBIDO("PRODUTO_RECEBIDO"),
    REENTRAR_ESTOQUE("REENTRAR_ESTOQUE");

    private final String status;

    StatusPedido(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public static StatusPedido getStatusPedido(String status){
        for(StatusPedido sts : StatusPedido.values()){
            if(sts.status.equals(status)){
                return sts;
            }
        }
        return null;
    }
}
