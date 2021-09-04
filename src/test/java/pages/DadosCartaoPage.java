package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author EllenTex
 */
public class DadosCartaoPage extends PageObject{
    private static final String URL_PAGINA_ALTERAR_CARTAO = "http://localhost:8080/ellentex-livrariales/Cliente?operacao=ALTERAR_CARTAO";


    public DadosCartaoPage(WebDriver browser) {
        super(browser);
    }

    public boolean isPaginaAlterarCartao(){return browser.getCurrentUrl().equals(URL_PAGINA_ALTERAR_CARTAO);}

    public void alterarCartao(String numeroCartao, String nomeImpresso, int indexBandeira, String cvv, int indexMes, int indexAno){
        browser.findElement(By.name("numeroCartao1")).clear();
        browser.findElement(By.name("numeroCartao1")).sendKeys(numeroCartao);
        browser.findElement(By.name("nomeCartao1")).clear();
        browser.findElement(By.name("nomeCartao1")).sendKeys(nomeImpresso);
        new Select(browser.findElement(By.name("bandeiraCartao1"))).selectByIndex(indexBandeira);
        browser.findElement(By.name("cvv1")).clear();
        browser.findElement(By.name("cvv1")).sendKeys(cvv);
        new Select(browser.findElement(By.name("mesVencimento1"))).selectByIndex(indexMes);
        new Select(browser.findElement(By.name("anoVencimento1"))).selectByIndex(indexAno);
        submeterForm();
    }

    public boolean validarExistencia(String numeroCartao, String nomeImpresso){
        List<WebElement> elements = browser.findElements(By.cssSelector("input[type=text]"));
        for(WebElement element : elements){
            System.out.println(element.getAttribute("value"));
            if(element.getAttribute("value").equals(numeroCartao) || element.getAttribute("value").equals(nomeImpresso));
            return true;
        }
        return false;
    }


}
