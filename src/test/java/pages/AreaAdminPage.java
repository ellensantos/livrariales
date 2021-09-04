package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * @author EllenTex
 */
public class AreaAdminPage extends PageObject{

    private static final String URL_AREA_ADMIN = "http://localhost:8080/ellentex-livrariales/admin/indexAdmin.jsp";
    private static final String URL_GRAFICO_LIVRO = "http://localhost:8080/ellentex-livrariales/admin/Pesquisa";

    public AreaAdminPage(WebDriver browser) {
        super(browser);
    }

    public void acessarPedidosRecebidos(){
        browser.findElement(By.id("pedidoRecebido")).click();
    }

    public void aprovarPedido(String numeroPedido){
        browser.findElement(By.id("aprovarPedido" + numeroPedido)).click();
    }

    public void liberarPedidoParaEntrega(String numeroPedido){
        browser.findElement(By.id("liberarEntregaPedido" + numeroPedido)).click();
    }

    public void marcarPedidoComoEntregue(String numeroPedido){
        browser.findElement(By.id("entreguePedido" + numeroPedido)).click();
    }

    public void autorizarTroca(String numeroPedido){
        browser.findElement(By.id("pedidoEmTroca")).click();
        browser.findElement(By.id("autorizarTroca" + numeroPedido)).click();
    }

    public void marcarPedidoComoRecebidoReentrada(String numeroPedido){
        browser.findElement(By.id("pedidoTrocaAutorizada")).click();
        browser.findElement(By.id("produtoRecebido" + numeroPedido)).click();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        browser.switchTo().alert().accept();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public void marcarPedidoComoRecebidoSemReentrada(String numeroPedido){
        browser.findElement(By.id("pedidoTrocaAutorizada")).click();
        browser.findElement(By.id("produtoRecebido" + numeroPedido)).click();
        browser.switchTo().alert().dismiss();
    }

    public CadastroLivroPage abrirCadastroLivro(){
        browser.findElement(By.id("cadastrarLivro")).click();
        return new CadastroLivroPage(browser);
    }

    public void abrirListagemEstoque(){
        browser.findElement(By.id("listarEstoqueLivro")).click();
    }

    public String recuperarNomeUltimoItemEstoque(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#mytable tbody tr:last-child"));
        WebElement colunaNomeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(3)"));
        return colunaNomeItem.getText();
    }

    public String recuperarQtdeUltimoItemEstoque(){
        WebElement linhaTabela = browser.findElement(By.cssSelector("#mytable tbody tr:last-child"));
        WebElement colunaQtdeItem = linhaTabela.findElement(By.cssSelector("th:nth-child(4)"));
        return colunaQtdeItem.getText();
    }

    public EntradaItemEstoquePage abrirEntradaItemEstoque(){
        return new EntradaItemEstoquePage(browser);
    }

    public void consultarLivrosVendidos(String dataInicio, String dataFim){
        browser.findElement(By.id("livroMaisVendido")).click();
        browser.findElement(By.id("dataInicio")).sendKeys(dataInicio);
        browser.findElement(By.id("dataFim")).sendKeys(dataFim);
        browser.findElement(By.name("operacao")).click();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public boolean verificarGrafico(){
        browser.findElement(By.id("canvas")).isDisplayed();
        browser.getPageSource().contains("Livros Vendidos - Período:");
        return browser.findElement(By.id("canvas")).isDisplayed() && browser.getPageSource().contains("Livros Vendidos - Período:")
                && (!browser.getPageSource().isEmpty()) && browser.getCurrentUrl().equals(URL_GRAFICO_LIVRO);
    }
}
