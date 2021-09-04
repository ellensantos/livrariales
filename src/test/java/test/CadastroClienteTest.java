package test;

import org.junit.jupiter.api.*;
import pages.CadastroClientePage;
import pages.LoginClientePage;

/**
 * @author EllenTex
 */
public class CadastroClienteTest {

    private CadastroClientePage cadastroClientePage;
    private LoginClientePage loginClientePage;

    @BeforeEach
    public void beforeEach(){
        loginClientePage = new LoginClientePage(null);
        cadastroClientePage = loginClientePage.abrirPaginaCadastrarCliente();
    }

    @AfterEach
    public void afterEach(){
        cadastroClientePage.quit();
    }
    @Test
    @DisplayName("Cadastrar Novo Cliente")
    public void cadastrarCliente(){
            cadastroClientePage.cadastrarCliente("Jorge Silva", "2001-07-04", 1, "40831276543", 1, "11",
                "976544221", "jorge@jorge.com", "Etdsand20*", 1, "Casa", 2, 1,
                "Rua 12", "34", "Bairro 10","08722431",0,1,1,"Teste");

        Assertions.assertTrue(cadastroClientePage.isSucesso());
        Assertions.assertTrue(loginClientePage.isPaginaLogin());
    }

    @Test
    @DisplayName("Cadastrar Cliente Existente")
    public void cadastrarClienteExistente(){
        cadastroClientePage.cadastrarCliente("Helena Silva", "1995-04-23", 1, "42313276543", 1, "11",
                "976544221", "helena@helena.com", "Etdsand20*", 1, "Casa", 2, 1,
                "Rua 10", "34", "Bairro 10","08722431",0,1,1,"Teste");

        Assertions.assertTrue(loginClientePage.isPaginaLogin());
        Assertions.assertTrue(loginClientePage.isMensagemLogin("CPF já cadastrado!"));
    }



}
