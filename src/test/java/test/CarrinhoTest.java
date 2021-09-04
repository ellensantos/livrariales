package test;

import org.junit.jupiter.api.*;
import pages.CarrinhoPage;
import pages.DetalhesLivroPage;
import pages.HomePage;
import pages.LoginClientePage;

/**
 * @author EllenTex
 */
public class CarrinhoTest {

    private LoginClientePage loginClientePage;
    private HomePage homePage;
    private DetalhesLivroPage detalhesLivroPage;
    private String nomeLivro;
    private CarrinhoPage carrinhoPage;
    private int qtdeItem;

    @BeforeEach
    public void beforeEach(){
        loginClientePage = new LoginClientePage(null);
        homePage = loginClientePage.efetuarLogin("ellen@ellen.com", "Etdsand20*");
        qtdeItem = 3;
        detalhesLivroPage = homePage.conferirLivro(1);
        nomeLivro = detalhesLivroPage.recuperarNomeLivro();
        carrinhoPage = detalhesLivroPage.adicionarLivroCarrinho(qtdeItem);
    }

    @AfterEach
    public void afterEach(){
        loginClientePage.quit();
    }

    @Test
    @DisplayName("Inserir Item no Carrinho de Compras")
    public void inserirItemCarrinhoCompra(){

        Assertions.assertTrue(carrinhoPage.isPaginaCarrinho());
        Assertions.assertTrue(carrinhoPage.validarLivroInserido(nomeLivro, String.valueOf(qtdeItem)));
    }

    @Test
    @DisplayName("Remover Item do Carrinho de Compras")
    public void removerItemCarrinhoCompra(){
        carrinhoPage.removerItem();
        Assertions.assertTrue(carrinhoPage.isPaginaCarrinho());
        Assertions.assertTrue(carrinhoPage.isCarrinhoVazio());
    }

    @Test
    @DisplayName("Adicionar Unidade do Item Através do Carrinho de Compras")
    public void adicionarUnidadeItemCarrinhoCompra(){
        carrinhoPage.adicionarUnidadeItem();
        Assertions.assertTrue(carrinhoPage.isPaginaCarrinho());
        Assertions.assertTrue(carrinhoPage.validarLivroInserido(nomeLivro, String.valueOf(qtdeItem + 1)));
    }

    @Test
    @DisplayName("Subtrair Unidade do Item Através do Carrinho de Compras")
    public void removerUnidadeItemCarrinhoCompra(){
        carrinhoPage.removerUnidadeItem();
        Assertions.assertTrue(carrinhoPage.isPaginaCarrinho());
        Assertions.assertTrue(carrinhoPage.validarLivroInserido(nomeLivro, String.valueOf(qtdeItem - 1)));
    }




}
