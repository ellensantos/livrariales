package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author EllenTex
 */
public class DadosLogonPage extends PageObject{
    private static final String URL_PAGINA_ALTERAR_DADOS_LOGON = "http://localhost:8080/ellentex-livrariales/Cliente?operacao=ALTERAR_SENHA";

    public DadosLogonPage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_PAGINA_ALTERAR_DADOS_LOGON);
    }

    public boolean isPaginaAlterarDadosLogon(){return browser.getCurrentUrl().equals(URL_PAGINA_ALTERAR_DADOS_LOGON);}

    public void alterarSenha(String senha){
        browser.findElement(By.name("senha")).sendKeys(senha);
        browser.findElement(By.name("confirmaSenha")).sendKeys(senha);
        submeterForm();
    }
}
