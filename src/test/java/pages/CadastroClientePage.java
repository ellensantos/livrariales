package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author EllenTex
 */
public class CadastroClientePage extends PageObject{

    private static final String URL_CADASTRO_CLIENTE = "http://localhost:8080/ellentex-livrariales/cadastrarCliente.jsp";

    public CadastroClientePage(WebDriver browser) {
        super(browser);
        this.browser.navigate().to(URL_CADASTRO_CLIENTE);
    }

    public boolean isPaginaCadastroCliente(){
        return browser.getCurrentUrl().equals(URL_CADASTRO_CLIENTE);
    }

    public void cadastrarCliente(String nome, String dataNasc, int indexGen, String cpf, int indexTipoTel,
                                             String ddd, String telefone, String email, String senha, int qtdeEndereco,
                                             String descricaoEnd, int indexTipoResidencia, int indexTipoLogradouro, String logradouro,
                                             String numero, String bairro, String cep, int indexPais, int indexEstado, int indexCidade,
                                             String observacao){

        browser.findElement(By.name("nomeCliente")).sendKeys(nome);
        browser.findElement(By.name("dataNascimento")).sendKeys(dataNasc);
        new Select(browser.findElement(By.name("generoCliente"))).selectByIndex(indexGen);
        browser.findElement(By.name("cpfCliente")).sendKeys(cpf);

        new Select(browser.findElement(By.name("tipoTelefone"))).selectByIndex(indexTipoTel);
        browser.findElement(By.name("dddTelefone")).sendKeys(ddd);
        browser.findElement(By.name("numTelefone")).sendKeys(telefone);

        browser.findElement(By.name("email")).sendKeys(email);
        browser.findElement(By.name("senha")).sendKeys(senha);
        browser.findElement(By.name("confirmaSenha")).sendKeys(senha);

        preencherDadosEndereco(qtdeEndereco, descricaoEnd, indexTipoResidencia, indexTipoLogradouro,
        logradouro, numero, bairro, cep, indexPais,
        indexEstado, indexCidade, observacao);

        submeterForm();
    }

    private void preencherDadosEndereco(int qtdeEndereco, String descricaoEnd, int indexTipoResidencia, int indexTipoLogradouro,
                                        String logradouro, String numero, String bairro, String cep, int indexPais,
                                        int indexEstado, int indexCidade, String observacao){

        for(int i= 1; i <= qtdeEndereco; i++){

            browser.findElement(By.name("entrega" + i)).click();
            browser.findElement(By.name("cobranca" + i)).click();
            browser.findElement(By.name("descricaoEnd" + i)).sendKeys(descricaoEnd);
            new Select(browser.findElement(By.name("tipoResidencia" + i))).selectByIndex(indexTipoResidencia);
            new Select(browser.findElement(By.name("tipoLogradouro" + i))).selectByIndex(indexTipoLogradouro);
            browser.findElement(By.name("logradouro" + i)).sendKeys(logradouro);
            browser.findElement(By.name("numero" + i)).sendKeys(numero);
            browser.findElement(By.name("bairro" + i)).sendKeys(bairro);
            browser.findElement(By.name("cep" + i)).sendKeys(cep);
            new Select(browser.findElement(By.name("pais" + i))).selectByIndex(indexPais);
            new Select(browser.findElement(By.name("estado" + i))).selectByIndex(indexEstado);
            new Select(browser.findElement(By.name("cidade" + i))).selectByIndex(indexCidade);
            browser.findElement(By.name("observacao" + i)).sendKeys(observacao);

            if(i < qtdeEndereco)
            browser.findElement(By.id("adicionarNovoEndereco")).click();
        }

    }

    public boolean isSucesso(){
        Alert alert = browser.switchTo().alert();
        String textoAlerta = alert.getText();
        alert.accept();
        System.out.println(textoAlerta);
        return textoAlerta.equals("Cadastro realizado com sucesso!");
    }
}
