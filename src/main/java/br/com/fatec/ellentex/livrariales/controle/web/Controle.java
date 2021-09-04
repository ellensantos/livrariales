package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.command.*;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Log;
import br.com.fatec.ellentex.livrariales.dominio.UsuarioLogado;
import br.com.fatec.ellentex.livrariales.negocio.GerarLog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * @author EllenTex
 */

@WebServlet(urlPatterns = {"/Cliente", "/CadCliente", "/Cupom", "/Pedido", "/Checkout",
		"/Frete", "/Carrinho", "/admin/Pedido", "/admin/Estoque", "/admin/ItemLivro",
		"/admin/Livro", "/admin/Cliente", "/admin/Login", "/admin/Pesquisa", "/Livro"})

public class Controle extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String operacao = null;
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;

	public Controle() {
		definirCommands();
		definirViewHelpers();
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		operacao = request.getParameter("operacao");
		String uri = request.getRequestURI();
		System.out.println("OPERAÇÃO CONTROLLER = " + operacao);
		System.out.println("URL CONTROLLER = " + uri);

		// Saber qual view helper atende a requisição de acordo com a URI que a servlet foi chamada
		IViewHelper vh = vhs.get(uri);

		EntidadeDominio entidade = vh.getEntidade(request);

		if(entidade != null){
			ICommand cmd = commands.get(operacao); // Descobrindo a operação

			if(cmd != null){
				Object obj = cmd.execute(entidade); // Objeto do comando execute do command correspondente na fachada.

				//Gerando Log
				Log log = new Log((UsuarioLogado) request.getSession().getAttribute("usuarioLogado"), obj, cmd.getClass().getSimpleName());
				GerarLog gerarLog = new GerarLog();
				gerarLog.processar(log);
				vh.setView(obj, request, response); // Passar objeto para setar na sessão
			}
		}else{
			vh.setView(null, request, response); // Passar objeto para setar na sessão
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	private void definirCommands(){
		commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("LISTAR", new ConsultarCommand());
		commands.put("SALVAR_DADOS_PESSOAIS", new AlterarCommand());
		commands.put("SALVAR_ENDERECO", new AlterarCommand());
		commands.put("SALVAR_SENHA", new AlterarCommand());
		commands.put("SALVAR_CARTAO", new AlterarCommand());
		commands.put("ALTERAR_CARTAO", new ConsultarCommand());
		commands.put("LISTAR_CLIENTES", new ConsultarCommand());
		commands.put("PERFIL_CLIENTE", new ConsultarCommand());
		commands.put("PESQUISAR_CLIENTE", new ConsultarCommand());
		commands.put("PESQUISAR_CLIENTE_NOME", new ConsultarCommand());
		commands.put("ATIVAR_CLIENTE", new ConsultarCommand());
		commands.put("HABILITAR_CLIENTE", new AlterarCommand());
		commands.put("DESATIVAR_CLIENTE", new ConsultarCommand());
		commands.put("DESABILITAR_CLIENTE", new AlterarCommand());
		commands.put("ALTERAR_ENDERECO", new ConsultarCommand());
		commands.put("ALTERAR_DADOS_PESSOAIS", new ConsultarCommand());
		commands.put("CONSULTAR_PEDIDOS", new ConsultarCommand());
		commands.put("CONSULTAR_DADOS", new ConsultarCommand());
		commands.put("CONSULTAR_MENSAGENS", new ConsultarCommand());
		commands.put("CONSULTAR_CUPONS", new ConsultarCommand());
		commands.put("CADASTRAR_LIVRO", new ConsultarCommand());
		commands.put("LISTAR_LIVROS", new ConsultarCommand());
		commands.put("PESQUISAR_LIVRO", new ConsultarCommand());
		commands.put("PERFIL_LIVRO", new ConsultarCommand());
		commands.put("ATIVAR_LIVRO", new ConsultarCommand());
		commands.put("HABILITAR_LIVRO", new AlterarCommand());
		commands.put("DESATIVAR_LIVRO", new ConsultarCommand());
		commands.put("PESQUISAR_LIVRO_TITULO", new ConsultarCommand());
		commands.put("ENTRADA_ESTOQUE", new ConsultarCommand());
		commands.put("ADD_ITEM_CARRINHO", new SalvarCommand());
		commands.put("ADD_UNIDADE_ITEM", new SalvarCommand());
		commands.put("REMOVER_ITEM_CARRINHO", new ExcluirCommand());
		commands.put("DEL_UNIDADE_ITEM", new ExcluirCommand());
		commands.put("VALIDAR_CUPOM_TROCA", new ConsultarCommand());
		commands.put("VALIDAR_CUPOM_DESCONTO", new ConsultarCommand());
		commands.put("PEDIDO_DETALHES_CLIENTE", new ConsultarCommand());
		commands.put("LISTAR_PEDIDOS", new ConsultarCommand());
		commands.put("PEDIDO_DETALHES_ADMIN", new ConsultarCommand());
		commands.put("CONSULTAR_STATUS", new ConsultarCommand());
		commands.put("SALVAR_TROCA", new SalvarCommand());
		commands.put("CONSULTAR_PENDENTES", new ConsultarCommand());
		commands.put("NEGAR_VALOR", new AlterarCommand());
		commands.put("APROVAR_VALOR", new AlterarCommand());
		commands.put("LISTAR_PEDIDO_COMPRA", new ConsultarCommand());
		commands.put("LISTAR_PEDIDO_TROCA", new ConsultarCommand());
		commands.put("RELATORIO_LIVRO", new SalvarCommand());
		commands.put("CONFERIR_LIVRO", new ConsultarCommand());
	}

	private void definirViewHelpers(){
		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/ellentex-livrariales/CadCliente", new VhCliente());
		vhs.put("/ellentex-livrariales/Cliente", new VhCliente());
		vhs.put("/ellentex-livrariales/Cupom", new VhCupom());
		vhs.put("/ellentex-livrariales/Pedido", new VhPedido());
		vhs.put("/ellentex-livrariales/Checkout", new VhCarrinho());
		vhs.put("/ellentex-livrariales/Frete", new VhFrete());
		vhs.put("/ellentex-livrariales/Carrinho", new VhCarrinho());
		vhs.put("/ellentex-livrariales/Livro", new VhCarrinho());
		vhs.put("/ellentex-livrariales/admin/Pedido", new VhPedido());
		vhs.put("/ellentex-livrariales/admin/Estoque", new VhEstoque());
		vhs.put("/ellentex-livrariales/admin/Cliente", new VhCliente());
		vhs.put("/ellentex-livrariales/admin/Livro", new VhLivro());
		vhs.put("/ellentex-livrariales/admin/ItemLivro", new VhItemLivro());
		vhs.put("/ellentex-livrariales/admin/Pesquisa", new VhPesquisa());
	}
}
