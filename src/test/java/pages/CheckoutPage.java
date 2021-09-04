package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;

/**
 * @author EllenTex
 */
public class CheckoutPage extends PageObject {

    private static final String URL_CHECKOUT = "http://localhost:8080/ellentex-livrariales/checkout.jsp";

    public CheckoutPage(WebDriver browser) {
        super(browser);
    }

    public void abrirCheckOut(){
        browser.navigate().to(URL_CHECKOUT);
    }

    public void selecionarEndEntregaExistente() {
        Select select = new Select(browser.findElement(By.id("idEndEntregaSelecionado")));
        select.selectByIndex(1);
    }

    public void selecionarEndCobranca(){
        Select select = new Select(browser.findElement(By.id("idEndCobrancaSelecionado")));
        select.selectByIndex(1);
    }

    public void pagarCartaoExistente(int indexCartao, int cartao, String valorPagamento){
        Select select = new Select(browser.findElement(By.id("idCartaoSelecionado" + indexCartao)));
        select.selectByIndex(cartao);
        browser.findElement(By.id("valorPag" + indexCartao)).sendKeys(valorPagamento);
    }

    public void pagarNovoCartao(int numPosicaoCartao, String numCartao, String nomeCartao, int indexBandeira, String cvv, int indexMes, int indexAno, String valorPagamento){
        browser.findElement(By.name("botaoAddCartao")).click();
        browser.findElement(By.id("numeroCartao" + numPosicaoCartao)).sendKeys(numCartao);
        browser.findElement(By.id("nomeCartao" + numPosicaoCartao)).sendKeys(nomeCartao);
        Select select = new Select(browser.findElement(By.id("bandeiraCartao" + numPosicaoCartao)));
        select.selectByIndex(indexBandeira);
        browser.findElement(By.id("cvv" + numPosicaoCartao)).sendKeys(cvv);
        select = new Select(browser.findElement(By.id("mesVencimento" + numPosicaoCartao)));
        select.selectByIndex(indexMes);
        select = new Select(browser.findElement(By.id("anoVencimento" + numPosicaoCartao)));
        select.selectByIndex(indexAno);
        browser.findElement(By.id("valorPag" + numPosicaoCartao)).sendKeys(valorPagamento);
    }

    public String selecionarCupomTroca(){
        String valorCupom = "0.00";
        String qtdeCupomTroca = browser.findElement(By.id("qtdeCupomTroca")).getAttribute("value");
        int qtdeCupom = Integer.parseInt(qtdeCupomTroca);
            for (int i = 1; i <= qtdeCupom; i++) {
                try {
                    browser.findElement(By.id("cupomTroca" + i)).click();
                    valorCupom = browser.findElement(By.id("valorCupomTroca" + i)).getAttribute("value");
                    break;
                } catch (NoSuchElementException e) {

                }
            }

        System.out.println("VALOR = " + valorCupom);
        return valorCupom;
    }

    public void inserirCupomDesconto(String codigoCupom){
        browser.findElement(By.id("cupomDesconto")).sendKeys(codigoCupom);
        browser.findElement(By.id("validarCupomDesconto")).click();
    }

    public String recuperarTotalPagar(String valorCartaoExiste){
        String totalPagar = browser.findElement(By.id("totalPagar")).getText();
        totalPagar = totalPagar.replace("R$ ", "");
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("#.00");
        double total = Double.parseDouble(totalPagar) - Double.parseDouble(valorCartaoExiste);
        return String.valueOf(decimalFormat.format(total));
    }

    public PedidoRealizadoPage submeterFormulario(){
        browser.findElement(By.name("operacao")).click();
/*        browser.findElement(By.id("sidebarCheckOutItens")).click();
        WebElement element = browser.findElement(By.id("operacao"));
        Actions actions = new Actions(browser);
        actions.moveToElement(element).click().perform();*/
        return new PedidoRealizadoPage(browser);
    }

    public void definirNovoEnderecoEntrega(String descricaoEnd, int indexTipoResidencia, int indexTipoLogradouro, String logradouro,
                                           String numero, String bairro, String cep, int indexPais, int indexEstado, int indexCidade, String observacao){

        browser.findElement(By.id("AddEndEntrega")).click();
        browser.findElement(By.name("descricaoEndEntrega1")).sendKeys(descricaoEnd);
        new Select(browser.findElement(By.name("tipoResidenciaEndEntrega1"))).selectByIndex(indexTipoResidencia);
        new Select(browser.findElement(By.name("tipoLogradouroEndEntrega1"))).selectByIndex(indexTipoLogradouro);
        browser.findElement(By.name("logradouroEndEntrega1")).sendKeys(descricaoEnd);
        browser.findElement(By.name("numeroEndEntrega1")).sendKeys(numero);
        browser.findElement(By.name("bairroEndEntrega1")).sendKeys(bairro);
        browser.findElement(By.name("cepEndEntrega1")).sendKeys(cep);
        new Select(browser.findElement(By.name("paisEndEntrega1"))).selectByIndex(indexPais);
        new Select(browser.findElement(By.name("estadoEndEntrega1"))).selectByIndex(indexEstado);
        new Select(browser.findElement(By.name("cidadeEndEntrega1"))).selectByIndex(indexCidade);
        browser.findElement(By.name("observacaoEndEntrega1")).sendKeys(observacao);
    }

    public void salvarNovoEnderecoEntrega(){
        browser.findElement(By.id("salvarEndEntrega1")).click();
    }

    public void salvarNovoCartaoCadastro(){
        //browser.findElement(By.id("salvarCartao2")).click();
        browser.findElement(By.xpath("//div[@id='cardCartaoCheckOut']/div[4]/label/input")).click();
    }

    public void teste(){
        browser.findElement(By.name("botaoAddCartao")).click();
        salvarNovoCartaoCadastro();
    }

}
