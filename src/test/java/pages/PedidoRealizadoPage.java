package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * @author EllenTex
 */
public class PedidoRealizadoPage extends PageObject {

    private static final String URL_PEDIDO_REALIZADO = "http://localhost:8080/ellentex-livrariales/cliente/pedidoRealizado.jsp";

    public PedidoRealizadoPage(WebDriver browser) {
        super(browser);
    }

    public boolean isPaginaPedidoRealizado(){
        return browser.getCurrentUrl().equals(URL_PEDIDO_REALIZADO);
    }

    public boolean validarPedidoEfetuado(){
        String pageSource = browser.getPageSource();
        return pageSource.contains("Seu pedido de compra foi recebido.");
    }

    public PedidoClientePage consultarPedidos(){
        browser.findElement(By.linkText("Consultar Pedidos.")).click();
        return new PedidoClientePage(browser);
    }

    public String recuperarNumeroPedidoCompra(){
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String numeroPedido = browser.findElement(By.id("numeroPedido")).getText();
        numeroPedido = numeroPedido.replace("Pedido de Compra #", "");
        numeroPedido = numeroPedido.replace(" recebido","");
        //System.out.println("NUM PEDIDO = " + numeroPedido);

        return numeroPedido;
    }

    public String recuperarNumeroPedidoTroca(){
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String numeroPedido = browser.findElement(By.id("numeroPedido")).getText();
        numeroPedido = numeroPedido.replace("Pedido de Troca #", "");
        numeroPedido = numeroPedido.replace(" recebido","");
        System.out.println("NUM PEDIDO = " + numeroPedido);
        return numeroPedido;
    }


}
