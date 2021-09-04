package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * @author EllenTex
 */
public class PageObject {

    protected static WebDriver browser;
    protected static String idJanelaOrigem;
    protected static String idJanelaNovaJanela;


    public PageObject(WebDriver browser) {
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        //ChromeOptions options = new ChromeOptions();
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--start-maximized");
        //options.addArguments("--incognito");
        options.addArguments("-private");
        if(browser != null){
            this.browser = browser;
        }else{
            //this.browser = new ChromeDriver(options);
            this.browser = new FirefoxDriver(options);
            this.browser.manage().window().maximize();
/*            this.browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));*/
            this.browser.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
            this.idJanelaOrigem = this.browser.getWindowHandle();
        }

    }

    public void abrirNovaJanelaComum() {
        this.browser.switchTo().newWindow(WindowType.WINDOW);
        this.browser.manage().window().maximize();
        idJanelaNovaJanela = browser.getWindowHandle();
    }

    public void alternarParaJaneladeOrigem(){
        this.browser.switchTo().window(idJanelaOrigem);

    }

    public void alternarParaNovaJanelaAberta(){
        browser.switchTo().window(idJanelaNovaJanela);
    }

    public static void quit(){
        browser.quit();
    }

    public void atualizarTela(){
        browser.navigate().refresh();
    }

    public boolean isSucessoOperacao(){
        String msgRetorno = browser.findElement(By.id("msgRetorno")).getText();
        return msgRetorno.contains("SUCESSO!");
    }

    public boolean isFalhaOperacao(){
        String msgRetorno = browser.findElement(By.id("msgRetorno")).getText();
        return msgRetorno.contains("FALHA!");
    }

    public void submeterForm(){
        browser.findElement(By.name("operacao")).click();
    }

}
