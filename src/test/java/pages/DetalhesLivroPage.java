package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author EllenTex
 */
public class DetalhesLivroPage extends PageObject {

    private static final String URL_DETALHES_LIVRO = "http://localhost:8080/ellentex-livrariales/detalhesLivroCompra.jsp";

    public DetalhesLivroPage(WebDriver browser) {
        super(browser);
    }


    public CarrinhoPage adicionarLivroCarrinho(int qtde){
        int quantidade = qtde;

        while (quantidade > 1){
            browser.findElement(By.id("addLivroSelecionado00")).click();
            quantidade --;
        }

        browser.findElement(By.name("operacao")).click();
        return new CarrinhoPage(browser);
    }

    public String recuperarNomeLivro(){
        return browser.findElement(By.id("nomeLivro")).getText();
    }

}
