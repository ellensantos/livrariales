package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author EllenTex
 */
public class PedidoClientePage extends PageObject{

    private static final String URL_PAGINA_CONSULTAR_PEDIDOS = "http://localhost:8080/ellentex-livrariales/Cliente?operacao=CONSULTAR_PEDIDOS";
    private static final String URL_PAGINA_DETALHES_PEDIDO = "http://localhost:8080/ellentex-livrariales/Pedido?operacao=PEDIDO_DETALHES_CLIENTE&idPedido=";

    public PedidoClientePage(WebDriver browser) {
        super(browser);
    }

    public boolean isPaginaPedidos(){
        return browser.getCurrentUrl().equals(URL_PAGINA_CONSULTAR_PEDIDOS);
    }

    public void consultarPedidoRealizado(String numeroPedido){
        browser.findElement(By.linkText("#" + numeroPedido)).click();
        //browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public boolean isStatusPedido(String status){
        return browser.findElement(By.id("statusPedido")).getText().contains(status);
    }

    public boolean isPaginaConsultarPedido(String numPedido){
        return browser.getCurrentUrl().equals(URL_PAGINA_DETALHES_PEDIDO + numPedido);
    }

    public boolean isCupomPromocional(String codigoCupom){
        WebElement tabela = browser.findElement(By.cssSelector("#tabelaCupomDesconto"));
        return tabela.getText().contains(codigoCupom);
    }

    public boolean isCupomTroca (String valorCupomSelecionado){
        WebElement tabela = browser.findElement(By.cssSelector("#tabelaCupomTroca"));
        return tabela.getText().contains(valorCupomSelecionado.replace(".", ","));
    }

    public void solicitarTroca(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#tabelaItens tbody tr:first-child"));
        WebElement colunaTroca = linhaTabela.findElement(By.cssSelector("th:nth-child(6)"));
        WebElement link = colunaTroca.findElement(By.cssSelector("a"));
        link.click();
        browser.switchTo().alert().accept();
        System.out.println("TELA " + browser.getWindowHandle() + " CLIENTE " + idJanelaOrigem);
        browser.switchTo().window(idJanelaOrigem);
    }

    public boolean isCupomLiberado(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#tabelaItens tbody tr:last-child"));
        String codigoCupom = linhaTabela.findElement(By.cssSelector("th:nth-child(5)")).getText();
        return !codigoCupom.equals("Não liberado");
    }



}
