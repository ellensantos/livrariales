package pages;

import org.openqa.selenium.WebDriver;

/**
 * @author EllenTex
 */
public class AreaClientePage extends PageObject {

    private static final String URL_HOME_PAGE = "http://localhost:8080/ellentex-livrariales/index.jsp";

    public AreaClientePage(WebDriver browser) {
        super(browser);
    }

    public PedidoClientePage abrirPaginaPedidos(){
        return new PedidoClientePage(browser);
    }

    public DadosPessoaisPage abrirPaginaDadosPessoais(){
        return new DadosPessoaisPage(browser);
    }

    public DadosLogonPage abrirPaginaDadosLogon(){return new DadosLogonPage(browser);}

    public DadosCartaoPage abrirPaginaDadosPagamento(){return new DadosCartaoPage(browser);}

    public DadosEnderecoPage abrirPaginaDadosEndereco(){return new DadosEnderecoPage(browser);}

    public boolean isPaginaInicial(){return browser.getCurrentUrl().equals(URL_HOME_PAGE);}

}
