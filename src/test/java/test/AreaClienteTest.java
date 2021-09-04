package test;

import org.junit.jupiter.api.*;
import pages.*;

/**
 * @author EllenTex
 */
public class AreaClienteTest {
    private LoginClientePage loginClientePage;
    private HomePage homePage;
    private AreaClientePage areaClientePage;

    @BeforeEach
    public void beforeEach(){
        loginClientePage = new LoginClientePage(null);
        homePage = loginClientePage.efetuarLogin("ellen@ellen.com", "Etdsand20*");
        areaClientePage = homePage.abrirPaginaAreaCliente();
    }

    @AfterEach
    public void afterEach(){
        loginClientePage.quit();
    }

    @Test
    @DisplayName("Alterar Dados Pessoais")
    public void alterarDadosPessoais(){
        DadosPessoaisPage dadosPessoaisPage = areaClientePage.abrirPaginaDadosPessoais();
        dadosPessoaisPage.alterarDadosPessoais("Ellen Teixeira Domingo dos Santos",0,"41216637832",2,"13", "975234563");
        Assertions.assertTrue(dadosPessoaisPage.isSucessoOperacao());
        Assertions.assertTrue(dadosPessoaisPage.isPaginaAlterarDadosPessoais());
    }

    @Test
    @DisplayName("Alterar Dados de Endereço")
    public void alterarDadosEndereco(){
        DadosEnderecoPage dadosEnderecoPage = areaClientePage.abrirPaginaDadosEndereco();
        dadosEnderecoPage.alterarEndereco("Casa Ellen – LES",2,3,
            "Fatec Mogi das Cruzes ", "12", "Fatec", "08655987", 0,3,4, "Casa Ellen – LES");
        Assertions.assertTrue(dadosEnderecoPage.isSucessoOperacao());
        Assertions.assertTrue(dadosEnderecoPage.isPaginaAlterarEndereco());
    }

    @Test
    @DisplayName("Alterar Senha")
    public void alterarDadosLogon(){
        DadosLogonPage dadosLogonPage = areaClientePage.abrirPaginaDadosLogon();
        dadosLogonPage.alterarSenha("Laboratorio123*");
        Assertions.assertTrue(dadosLogonPage.isSucessoOperacao());
        Assertions.assertTrue(dadosLogonPage.isPaginaAlterarDadosLogon());
    }



}
