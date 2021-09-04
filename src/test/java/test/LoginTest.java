package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginAdminPage;
import pages.LoginClientePage;
import pages.PageObject;

/**
 * @author EllenTex
 */
public class LoginTest {

    private LoginClientePage loginClientePage;
    private LoginAdminPage loginAdminPage;
    private HomePage homePage;

    @AfterEach
    public void afterEach() {
        PageObject.quit();
    }

    @Test
    @DisplayName("Login cliente com dados validos")
    public void efetuarLoginValidoCliente(){
        loginClientePage = new LoginClientePage(null);
        loginClientePage.efetuarLogin("jorge@jorge.com", "Etdsand20*");
        Assertions.assertFalse(loginClientePage.isPaginaLogin());
        Assertions.assertEquals("Olá Ellen", loginClientePage.getNomeUsuarioLogado());
    }

    @Test
    @DisplayName("Login administrador com dados validos")
    public void efetuarLoginValidoAdmin(){
        loginAdminPage = new LoginAdminPage(null);
        loginAdminPage.efetuarLogin("admin", "admin");
        Assertions.assertFalse(loginAdminPage.isPaginaLogin());
        Assertions.assertEquals("USER LOGADO: admin", loginAdminPage.getNomeUsuarioLogado());
    }
}
