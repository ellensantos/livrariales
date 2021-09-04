package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author EllenTex
 */
public class EntradaItemEstoquePage extends PageObject {

    private static final String URL_ENTRADA_ITEM_ESTOQUE = "http://localhost:8080/ellentex-livrariales/admin/ItemLivro?operacao=ENTRADA_ESTOQUE";

    public EntradaItemEstoquePage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_ENTRADA_ITEM_ESTOQUE);
    }

    public void registrarItemEstoque(String nomeLivro, String qtde, String valorCusto, int indexFornecedor, String data){
        new Select(browser.findElement(By.name("idProduto"))).selectByVisibleText(nomeLivro);
        browser.findElement(By.name("qtdeProduto")).sendKeys(qtde);
        browser.findElement(By.name("valorCustoProduto")).sendKeys(valorCusto);
        new Select(browser.findElement(By.name("idFornecedor"))).selectByIndex(indexFornecedor);
        browser.findElement(By.id("dataEntrada"));
        submeterForm();
    }

}
