package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author EllenTex
 */
public class DadosPessoaisPage extends PageObject {

    private static final String URL_PAGINA_ALTERAR_DADOS_PESSOAIS = "http://localhost:8080/ellentex-livrariales/Cliente?operacao=ALTERAR_DADOS_PESSOAIS";

    public DadosPessoaisPage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_PAGINA_ALTERAR_DADOS_PESSOAIS);
    }

    public boolean isPaginaAlterarDadosPessoais(){return browser.getCurrentUrl().equals(URL_PAGINA_ALTERAR_DADOS_PESSOAIS);}

    public void alterarDadosPessoais(String nome, int indexGeneroCliente, String cpfCliente,
                                     int indexTipoTelefone, String dddTelefone, String numTelefone){

        browser.findElement(By.name("nomeCliente")).clear();
        browser.findElement(By.name("nomeCliente")).sendKeys(nome);
        new Select(browser.findElement(By.name("generoCliente"))).selectByIndex(indexGeneroCliente);
        browser.findElement(By.name("cpfCliente")).clear();
        browser.findElement(By.name("cpfCliente")).sendKeys(cpfCliente);
        new Select(browser.findElement(By.name("tipoTelefone"))).selectByIndex(indexTipoTelefone);
        browser.findElement(By.name("dddTelefone")).clear();
        browser.findElement(By.name("dddTelefone")).sendKeys(dddTelefone);
        browser.findElement(By.name("numTelefone")).clear();
        browser.findElement(By.name("numTelefone")).sendKeys(numTelefone);
        submeterForm();

    }

    public void submeterForm(){
        browser.findElement(By.name("operacao")).click();
    }


}
