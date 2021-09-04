package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author EllenTex
 */
public class CarrinhoPage extends PageObject {

    private static final String URL_CARRINHO_COMPRAS = "http://localhost:8080/ellentex-livrariales/carrinhoCompra.jsp";

    public CarrinhoPage(WebDriver browser) {
        super(browser);
    }

    public HomePage continuarComprando(){
        browser.findElement(By.id("btnContinuarComprando")).click();
        return new HomePage(browser);
    }

    public CheckoutPage finalizarCompra(){
        browser.findElement(By.id("btnFinalizarCompra")).click();
        return new CheckoutPage(browser);
    }

    public boolean validarLivroInserido(String nomeLivro, String qtdeInserida){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#tabelaCarrinho tbody tr:last-child"));
        WebElement colunaNomeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(2)"));
        WebElement colunaQtdeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(4)"));
        WebElement inputQtde = colunaQtdeItem.findElement(By.cssSelector("input[type=text]"));
        return colunaNomeItem.getText().equals(nomeLivro) && inputQtde.getAttribute("value").equals(qtdeInserida);
    }

    public void removerItem(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#tabelaCarrinho tbody tr:last-child"));
        WebElement colunaNomeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(2)"));
        WebElement colunaLixeira = linhaTabela.findElement(By.cssSelector("th:nth-child(6)"));
        WebElement lixeira = colunaLixeira.findElement(By.cssSelector("a"));
        lixeira.click();
    }

    public boolean isPaginaCarrinho(){
        return browser.getCurrentUrl().equals(URL_CARRINHO_COMPRAS);
    }

    public boolean isCarrinhoVazio(){
        return browser.getPageSource().contains("Seu carrinho de compras ainda está vazio.");
    }

    public void adicionarUnidadeItem(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#tabelaCarrinho tbody tr:last-child"));
        WebElement colunaNomeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(2)"));
        WebElement colunaLixeira = linhaTabela.findElement(By.cssSelector("th:nth-child(4)"));
        List<WebElement> lixeira = colunaLixeira.findElements(By.cssSelector("a"));
        lixeira.get(1).click();
    }

    public void removerUnidadeItem(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#tabelaCarrinho tbody tr:last-child"));
        WebElement colunaNomeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(2)"));
        WebElement colunaLixeira = linhaTabela.findElement(By.cssSelector("th:nth-child(4)"));
        List<WebElement> lixeira = colunaLixeira.findElements(By.cssSelector("a"));
        lixeira.get(0).click();
    }


}
