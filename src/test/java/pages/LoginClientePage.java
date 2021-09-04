package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * @author EllenTex
 */
public class LoginClientePage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/ellentex-livrariales/formLogin.jsp";

    public LoginClientePage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_LOGIN);
    }

    public HomePage efetuarLogin(String usuario, String senha){
        browser.findElement(By.id("email")).sendKeys(usuario);
        browser.findElement(By.id("senha")).sendKeys(senha);
        submeterForm();
        return new HomePage(browser);
    }

    public boolean isPaginaLogin(){
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public Object getNomeUsuarioLogado(){
        try{
            return browser.findElement(By.id("usuarioLogadoNav")).getText();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public void efetuarLogoffCliente(){
        browser.findElement(By.id("usuarioLogadoNav")).click();
        browser.findElement(By.linkText("Deslogar")).click();
    }

    public boolean isMensagemLogin(String mensagem){
        String msg = browser.findElement(By.id("mensagemlogin")).getText();
        return msg.contains(mensagem);
    }

    public CadastroClientePage abrirPaginaCadastrarCliente(){
        return new CadastroClientePage(browser);
    }
}
