package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * @author EllenTex
 */
public class CadastroLivroPage extends PageObject {
    private static final String URL_CADASTRAR_LIVRO = "http://localhost:8080/ellentex-livrariales/admin/Livro?operacao=CADASTRAR_LIVRO";

    public CadastroLivroPage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_CADASTRAR_LIVRO);
    }

    public boolean isPaginaCadastroLivro(){
        return browser.getCurrentUrl().equals(URL_CADASTRAR_LIVRO);
    }

    public void cadastrarLivro(String caminhoImagem){
        browser.findElement(By.id("capaLivro")).click();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        for(String str : browser.getWindowHandles()){
            System.out.println(str);
        }

        browser.switchTo().activeElement();
        //browser.findElement(By.id("file")).sendKeys("C://Teste");

        browser.findElement(By.xpath("//*[@id=\"file\"]")).sendKeys("HHHHH");
        browser.findElement(By.id("enviarImagem")).click();


    }
}
