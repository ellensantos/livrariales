package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 */
public enum CategoriaRanking {
    MASTER (30),
    DIAMANTE(20),
    PLATINA(15),
    OURO (10),
    PRATA(5),
    BRONZE(1);

private final Integer qtde;

    CategoriaRanking(int qtde){
        this.qtde = qtde;
    }

    public static CategoriaRanking getCategoria(Integer qtde){
        for(CategoriaRanking categ : CategoriaRanking.values()){
            if(categ.qtde <= qtde){
                return categ;
            }
        }
        return null;
    }

}
