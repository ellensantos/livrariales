package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author EllenTex
 */
public class DadosEnderecoPage extends PageObject{
    private static final String URL_PAGINA_ALTERAR_ENDERECO = "http://localhost:8080/ellentex-livrariales/Cliente?operacao=ALTERAR_ENDERECO";

    public DadosEnderecoPage(WebDriver browser) {
        super(browser);
        browser.navigate().to(URL_PAGINA_ALTERAR_ENDERECO);
    }

    public boolean isPaginaAlterarEndereco(){return browser.getCurrentUrl().equals(URL_PAGINA_ALTERAR_ENDERECO);}

    public void alterarEndereco(String descricaoEnd, int indexTipoResidencia, int indexTipoLogradouro, String logradouro,
                                String numero, String bairro, String cep, int indexPais, int indexEstado, int indexCidade, String observacao){

        browser.findElement(By.name("descricaoEnd1")).clear();
        browser.findElement(By.name("descricaoEnd1")).sendKeys(descricaoEnd);

        new Select(browser.findElement(By.name("tipoResidencia1"))).selectByIndex(indexTipoResidencia);
        new Select(browser.findElement(By.name("tipoLogradouro1"))).selectByIndex(indexTipoLogradouro);

        browser.findElement(By.name("logradouro1")).clear();
        browser.findElement(By.name("logradouro1")).sendKeys(descricaoEnd);

        browser.findElement(By.name("numero1")).clear();
        browser.findElement(By.name("numero1")).sendKeys(numero);

        browser.findElement(By.name("bairro1")).clear();
        browser.findElement(By.name("bairro1")).sendKeys(bairro);

        browser.findElement(By.name("cep1")).clear();
        browser.findElement(By.name("cep1")).sendKeys(cep);

        new Select(browser.findElement(By.name("pais1"))).selectByIndex(indexPais);
        new Select(browser.findElement(By.name("estado1"))).selectByIndex(indexEstado);
        new Select(browser.findElement(By.name("cidade1"))).selectByIndex(indexCidade);

        browser.findElement(By.name("observacao1")).clear();
        browser.findElement(By.name("observacao1")).sendKeys(observacao);

        submeterForm();

    }


    public boolean validarExistencia(String apelidoEndereco, String logradouro){
        List<WebElement> elements = browser.findElements(By.cssSelector("input[type=text]"));
        for(WebElement element : elements){
            System.out.println(element.getAttribute("value"));
            if(element.getAttribute("value").equals(apelidoEndereco) || element.getAttribute("value").equals(logradouro));
            return true;
        }
        return false;
    }
}
