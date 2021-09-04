package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author EllenTex
 */
public class HomePage extends PageObject {

    private static final String URL_HOME_PAGE = "http://localhost:8080/ellentex-livrariales/index.jsp";


    public HomePage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_HOME_PAGE);
    }

    public void abrirHomePage(){
        browser.navigate().to(URL_HOME_PAGE);
    }

    public LoginClientePage abrirPaginaLogin() {
        browser.findElement(By.linkText("Entre ou Cadastre-se")).click();
        return new LoginClientePage(browser);
    }

    public DetalhesLivroPage conferirLivro(int numero){
        browser.findElement((By.id("conferirLivro" + numero))).click();
        return new DetalhesLivroPage(browser);
    }

    public LoginAdminPage abrirPaginaLoginAdmin() {
        return new LoginAdminPage(browser);
    }

    public boolean isHomePage(){
        return browser.getCurrentUrl().equals(URL_HOME_PAGE);
    }

    public void efetuarLogoffCliente(){
        browser.findElement(By.id("usuarioLogadoNav")).click();
        browser.findElement(By.linkText("Deslogar")).click();
    }

    public AreaClientePage abrirPaginaAreaCliente(){
        browser.findElement(By.id("usuarioLogadoNav")).click();
        browser.findElement(By.linkText("Dados Pessoais")).click();
        return new AreaClientePage(browser);
    }



}
