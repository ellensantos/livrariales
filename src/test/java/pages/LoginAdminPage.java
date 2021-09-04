package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author EllenTex
 */
public class LoginAdminPage extends PageObject {

    private static final String URL_LOGIN_ADMIN = "http://localhost:8080/ellentex-livrariales/formLoginAdmin.jsp";

    public LoginAdminPage(WebDriver browser) {
        super(browser);

        if(browser != null) {
            abrirNovaJanelaComum();
        }

        this.browser.navigate().to(URL_LOGIN_ADMIN);
    }

    public boolean isPaginaLogin(){
        return browser.getCurrentUrl().equals(URL_LOGIN_ADMIN);
    }

    public void acessarPaginaLogin(){
        browser.navigate().to(URL_LOGIN_ADMIN);
    }

    public AreaAdminPage efetuarLogin(String usuario, String senha){
        browser.findElement(By.id("email")).sendKeys(usuario);
        browser.findElement(By.id("senha")).sendKeys(senha);
        submeterForm();
        return new AreaAdminPage(browser);
    }

    public Object getNomeUsuarioLogado(){
        try{
            return browser.findElement(By.id("usuarioLogadoNav")).getText();
        }catch (NoSuchFieldError e){
            return null;
        }
    }



}
