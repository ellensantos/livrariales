package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.util.ComboBoxUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author EllenTex
 *
 */
public class VhCliente implements IViewHelper {

	private Cliente definirCliente(HttpServletRequest request) {
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

		String nome = request.getParameter("nomeCliente");
		Date dataNascimento = new Date();
		String dataNasc = request.getParameter("dataNascimento");

		if(!dataNasc.isEmpty()) {
			try {
				dataNascimento = formatoData.parse(dataNasc);
			} catch (ParseException e) {
				dataNascimento = null;
				e.printStackTrace();
			}
		}else{
			dataNascimento = null;
		}

		String genero = request.getParameter("generoCliente");
		String cpf = request.getParameter("cpfCliente");
		String tipoTelefone = request.getParameter("tipoTelefone");
		String numTelefone = request.getParameter("numTelefone");
		int dddTelefone = (Integer.parseInt(request.getParameter("dddTelefone")));
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		// Telefone
		Telefone telefone = new Telefone(dddTelefone, numTelefone, tipoTelefone);

		// Usuario
		UsuarioComum usuario = new UsuarioComum(email, senha);
		usuario.setStatus(true);

		// Endereço
		int qtdeEndereco = (Integer.parseInt(request.getParameter("qtdeEndereco")));

		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		List<TipoEndereco> listaTipoEndereco = new ArrayList<TipoEndereco>();

		if(qtdeEndereco > 0) {
			for (int i = 1; i <= qtdeEndereco; i++) {

				// Verificando se a descrição está nula, pois significa que o input foi removido
				if (request.getParameter("descricaoEnd" + i) != null) {


					if (request.getParameter("entrega" + i) != null) {
						TipoEndereco tipoEnd1 = new TipoEndereco();
						int idTip1 = (Integer.parseInt(request.getParameter("entrega" + i)));
						tipoEnd1.setId(idTip1);
						listaTipoEndereco.add(tipoEnd1);
					}

					if (request.getParameter("cobranca" + i) != null) {
						TipoEndereco tipoEnd2 = new TipoEndereco();
						int idTip2 = (Integer.parseInt(request.getParameter("cobranca" + i)));
						tipoEnd2.setId(idTip2);
						listaTipoEndereco.add(tipoEnd2);
					}

					String descricaoEnd = request.getParameter("descricaoEnd" + i);
					String tipoResidencia = request.getParameter("tipoResidencia" + i);
					String tipoLogradouro = request.getParameter("tipoLogradouro" + i);
					String logradouro = request.getParameter("logradouro" + i);
					int numero = (Integer.parseInt(request.getParameter("numero" + i)));
					String bairro = request.getParameter("bairro" + i);
					String cep = request.getParameter("cep" + i);
					int idpais = (Integer.parseInt(request.getParameter("pais" + i)));
					int idestado = (Integer.parseInt(request.getParameter("estado" + i)));
					int idcidade = (Integer.parseInt(request.getParameter("cidade" + i)));
					String observacao = request.getParameter("observacao" + i);

					Pais pais = new Pais();
					pais.setId(idpais);

					Estado estado = new Estado();
					estado.setId(idestado);

					Cidade cidade = new Cidade(estado);
					cidade.setId(idcidade);

					Endereco endereco = new Endereco(descricaoEnd, tipoResidencia, tipoLogradouro, logradouro, numero,
							bairro, cep, cidade, listaTipoEndereco, observacao);

					listaEndereco.add(endereco);
				}
			}
		}

		Cliente cliente = new Cliente(nome, cpf, genero, dataNascimento, listaEndereco, telefone, usuario, null,
				new Ranking(CategoriaRanking.BRONZE), null);

		usuario.setCliente(cliente);

		return cliente;
	}

	private Cliente definirDadosPessoais(HttpServletRequest request){

		UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
		Cliente cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();

		SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

		String nome = request.getParameter("nomeCliente");

		Date dataNascimento = new Date();
		String dataNasc = request.getParameter("dataNascimento");
		//System.out.println("Data Alteração = " + dataNascimento);

		try {
			dataNascimento = formatoData.parse(dataNasc);

			//System.out.println("Data Formatada = " + dataNascimento);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		String genero = request.getParameter("generoCliente");
		String cpf = request.getParameter("cpfCliente");
		String tipoTelefone = request.getParameter("tipoTelefone");
		String numTelefone = request.getParameter("numTelefone");
		int dddTelefone = (Integer.parseInt(request.getParameter("dddTelefone")));

		cliente.setNome(nome);
		cliente.setDtNascimento(dataNascimento);
		cliente.setGenero(genero);
		cliente.setCpf(cpf);
		cliente.getTelefone().setTipoTelefone(tipoTelefone);
		cliente.getTelefone().setDdd(dddTelefone);
		cliente.getTelefone().setTelefone(numTelefone);

		return cliente;
	}

	private Cliente definirEndereco(HttpServletRequest request){

		UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
		Cliente cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();

		// Endereço
		int qtdEnd = (Integer.parseInt(request.getParameter("qtdeEndereco")));

		List<Endereco> listaEndereco = new ArrayList<Endereco>();


		for (int i = 1; i <= qtdEnd; i++) {

			List<TipoEndereco> listaTipoEndereco = new ArrayList<TipoEndereco>();

			// Verificando se a descrição está nula, pois significa que o input foi removido
			if(request.getParameter("descricaoEnd" + i) != null){
				//if ((!request.getParameter("descricaoEnd" + i).trim().equals(""))) {

				String flgEntrega = request.getParameter("entrega" + i);
				String flgCobranca = request.getParameter("cobranca" + i);

				if (flgEntrega != null) {
					TipoEndereco tipoEnd1 = new TipoEndereco();
					tipoEnd1.setId(1L);
					listaTipoEndereco.add(tipoEnd1);
				}

				if (flgCobranca != null) {
					TipoEndereco tipoEnd2 = new TipoEndereco();
					tipoEnd2.setId(2L);
					listaTipoEndereco.add(tipoEnd2);
				}

				String descricaoEnd = request.getParameter("descricaoEnd" + i);
				String tipoResidencia = request.getParameter("tipoResidencia" + i);
				String tipoLogradouro = request.getParameter("tipoLogradouro" + i);
				String logradouro = request.getParameter("logradouro" + i);
				int numero = (Integer.parseInt(request.getParameter("numero" + i)));
				String bairro = request.getParameter("bairro" + i);
				String cep = request.getParameter("cep" + i);
				int idpais = (Integer.parseInt(request.getParameter("pais" + i)));
				int idestado = (Integer.parseInt(request.getParameter("estado" + i)));
				int idcidade = (Integer.parseInt(request.getParameter("cidade" + i)));
				String observacao = request.getParameter("observacao" + i);
				int idEndereco = (Integer.parseInt(request.getParameter("idEndereco" + i)));

				Pais pais = new Pais();
				pais.setId(idpais);

				Estado estado = new Estado();
				estado.setId(idestado);

				Cidade cidade = new Cidade(estado);
				cidade.setId(idcidade);

				Endereco endereco = new Endereco(descricaoEnd, tipoResidencia, tipoLogradouro, logradouro, numero,
						bairro, cep, cidade, listaTipoEndereco, observacao);

				if(idEndereco > 0){
					endereco.setId(idEndereco);
				}

				listaEndereco.add(endereco);
			}
		}
		//}
		cliente.setEndereco(listaEndereco);
		return cliente;
	}

	private Cliente definirSenha(HttpServletRequest request){
		UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
		Cliente cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();
		String senha = request.getParameter("senha");
		cliente.getUsuario().setSenha(senha);

		return cliente;
	}

	private Cliente definirCartao(HttpServletRequest request){
		UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
		Cliente cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();
		int qtdeCartao = (Integer.parseInt(request.getParameter("qtdeCartao")));

		if(qtdeCartao == 0){
			cliente.setCartao(null);
		}

		else{
			List<Cartao> listaCartao = new ArrayList<Cartao>();
			// Verificando se o número do cartão é nulo, pois significa que o input foi removido
			for (int i = 1; i <= qtdeCartao; i++) {
				if (request.getParameter("numeroCartao" + i) != null) {

					String numeroCartao = request.getParameter("numeroCartao" + i);
					String nomeImpresso = request.getParameter("nomeCartao" + i);
					long idBandeira = (Long.parseLong(request.getParameter("bandeiraCartao" + i)));
					String cvv = request.getParameter("cvv" + i);
					int mesVencimento = Integer.parseInt(request.getParameter("mesVencimento" + i));
					int anoVencimento = Integer.parseInt(request.getParameter("anoVencimento" + i));


					int idCartao = (Integer.parseInt(request.getParameter("idCartao" + i)));
					System.out.println(request.getParameter("Bandeira Cartão = " + "bandeiraCartao" + i));

					BandeiraCartao bandeiraCartao = new BandeiraCartao();
					bandeiraCartao.setId(idBandeira);

					Cartao cartao = new Cartao(numeroCartao,nomeImpresso, bandeiraCartao, cvv, mesVencimento, anoVencimento);

					if(idCartao > 0){
						cartao.setId(idCartao);
					}

					listaCartao.add(cartao);
				}
			}

			cliente.setCartao(listaCartao);
		}
		return cliente;
	}

	public EntidadeDominio getEntidade(HttpServletRequest request) {

		Cliente cliente = null;

		String operacao = request.getParameter("operacao");
		System.out.println("Operacão no VH Cliente = " + operacao);

		if(operacao.equals("SALVAR")){
			return definirCliente(request);
		}

		else if(operacao.equals("SALVAR_DADOS_PESSOAIS")){
			return definirDadosPessoais(request);
		}

		else if(operacao.equals("SALVAR_ENDERECO")){
			return definirEndereco(request);
		}

		else if(operacao.equals("SALVAR_SENHA")){
			return definirSenha(request);
		}

		else if(operacao.equals("ALTERAR_CARTAO") || operacao.equals("LISTAR_CLIENTES")){
			cliente = new Cliente();
		}

		else if(operacao.equals("SALVAR_CARTAO")){
			return definirCartao(request);
		}

		else if(operacao.equals("ALTERAR_ENDERECO") || operacao.equals("ALTERAR_DADOS_PESSOAIS") ||
				operacao.equals("ALTERAR_DADOS_PESSOAIS") || operacao.equals("CONSULTAR_PEDIDOS") ||
				operacao.equals("CONSULTAR_DADOS") || operacao.equals("CONSULTAR_MENSAGENS") ||
				operacao.equals("ATUALIZAR_CLI_LOGADO") || operacao.equals("CONSULTAR_CUPONS")){
			UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
			cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();

		}

		else if(operacao.equals("EXCLUIR")){
			UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
			cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();
			cliente.getUsuario().setStatus(false);
			System.out.println("To na VH excluir");
		}

		else if(operacao.equals("PERFIL_CLIENTE") || operacao.equals("ATIVAR_CLIENTE") || operacao.equals("DESATIVAR_CLIENTE")){
			cliente = new Cliente();
			cliente.setId(Long.parseLong(request.getParameter("idCliente")));
		}

		else if(operacao.equals("PESQUISAR_CLIENTE_NOME")){
			cliente = new Cliente();
			String nome = request.getParameter("consultaNomeCliente");
			cliente.setNome(nome);
		}

		else if(operacao.equals("PESQUISAR_CLIENTE")){
			return definirCliente(request);
		}

		else if(operacao.equals("HABILITAR_CLIENTE")){
			List<EntidadeDominio> list = (List<EntidadeDominio>) request.getSession().getAttribute("clienteAtivar");
			cliente = (Cliente) list.get(0);
			cliente.getUsuario().setStatus(true);
		}

		else if(operacao.equals("DESABILITAR_CLIENTE")){
			List<EntidadeDominio> list = (List<EntidadeDominio>) request.getSession().getAttribute("clienteInativar");
			cliente = (Cliente) list.get(0);
			cliente.getUsuario().setStatus(false);
		}

		else if(operacao.equals("ATUALIZAR_CLI_LOGADO")){
			cliente = new Cliente();
			UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
			cliente.setId(((UsuarioComum) usuarioLogado.getUsuario()).getCliente().getId());
		}

		return cliente;
	}

	public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operacao = request.getParameter("operacao");
		RequestDispatcher rd;
		String mensagemVH;
		UsuarioLogado usuarioLogado = new UsuarioLogado();


		if(operacao.equals("SALVAR")){
			if(obj instanceof Cliente) {
				mensagemVH = "sucesso";
				request.getSession().setAttribute("msg", mensagemVH);
				response.sendRedirect("formLogin.jsp");
			}
			else{
				request.getSession().setAttribute("msg", obj);
				response.sendRedirect("/ellentex-livrariales/cliente/cadastrarCliente.jsp");
			}
		}

		else if (operacao.equals("ALTERAR_DADOS_PESSOAIS")){
			if(obj instanceof Cliente){
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			rd = request.getRequestDispatcher("/cliente/editarCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("SALVAR_DADOS_PESSOAIS")){
			if (!(obj instanceof Cliente)) request.getSession().setAttribute("msg", obj);
			else {
				mensagemVH = "sucesso";
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("msg", mensagemVH);
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			response.sendRedirect("Cliente?operacao=ALTERAR_DADOS_PESSOAIS");
		}

		else if(operacao.equals("ALTERAR_ENDERECO")){
			if(obj instanceof Cliente){
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			rd = request.getRequestDispatcher("/cliente/editarEnderecoCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("SALVAR_ENDERECO")){
			if(obj instanceof Cliente){
				mensagemVH = "sucesso";
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("msg", mensagemVH);
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			else{
				request.getSession().setAttribute("msg", obj);
			}
			response.sendRedirect("Cliente?operacao=ALTERAR_ENDERECO");
		}

		else if(operacao.equals("ALTERAR_SENHA")){
			if(obj instanceof Cliente){
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			rd = request.getRequestDispatcher("/cliente/editarSenhaCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("SALVAR_SENHA")){
			if(obj instanceof Cliente){
				mensagemVH = "sucesso";
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("msg", mensagemVH);
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			else{
				request.getSession().setAttribute("msg", obj);
			}
			response.sendRedirect("Cliente?operacao=ALTERAR_SENHA");
		}

		else if(operacao.equals("ALTERAR_CARTAO")){
			ComboBoxUtil comboBoxUtil = new ComboBoxUtil();
			request.getSession().setAttribute("bandeiraCartao", comboBoxUtil.carregarComboBox("BandeiraCartao"));
			rd = request.getRequestDispatcher("/cliente/editarCartaoCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("SALVAR_CARTAO")){
			if(obj instanceof Cliente){
				mensagemVH = "sucesso";
				usuarioLogado.setUsuario(((Cliente) obj).getUsuario());
				request.getSession().setAttribute("msg", mensagemVH);
				request.getSession().setAttribute("usuarioLogado", (usuarioLogado));
			}
			else{
				request.getSession().setAttribute("msg", obj);
			}
			//response.sendRedirect("/ellentex-livrariales/cliente/editarCartaoCliente.jsp");
			response.sendRedirect("Cliente?operacao=ALTERAR_CARTAO");
		}

		else if(operacao.equals("CONSULTAR_CUPONS")){
			if(obj != null){
				List<EntidadeDominio> list = (List<EntidadeDominio>) obj;
				Cliente clienteEncontrado = (Cliente) list.get(0);
				usuarioLogado.setUsuario(clienteEncontrado.getUsuario());
				request.getSession().setAttribute("usuarioLogado", usuarioLogado);
			}
			rd = request.getRequestDispatcher("/cliente/cuponsTrocaCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("CONSULTAR_PEDIDOS")){
			if(obj != null){
				List<EntidadeDominio> list = (List<EntidadeDominio>) obj;
				Cliente clienteEncontrado = (Cliente) list.get(0);
				usuarioLogado.setUsuario(clienteEncontrado.getUsuario());
				request.getSession().setAttribute("usuarioLogado", usuarioLogado);
			}
			rd = request.getRequestDispatcher("/cliente/pedidosCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("CONSULTAR_DADOS")){
			if(obj != null){
				List<EntidadeDominio> list = (List<EntidadeDominio>) obj;
				Cliente clienteEncontrado = (Cliente) list.get(0);
				usuarioLogado.setUsuario(clienteEncontrado.getUsuario());
				request.getSession().setAttribute("usuarioLogado", usuarioLogado);
			}
			rd = request.getRequestDispatcher("/cliente/editarCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("CONSULTAR_MENSAGENS")){
			if(obj != null){
				List<EntidadeDominio> list = (List<EntidadeDominio>) obj;
				Cliente clienteEncontrado = (Cliente) list.get(0);
				usuarioLogado.setUsuario(clienteEncontrado.getUsuario());
				request.getSession().setAttribute("usuarioLogado", usuarioLogado);
			}
			rd = request.getRequestDispatcher("/cliente/mensagensCliente.jsp");
			rd.forward(request,response);
		}

		else if(operacao.equals("EXCLUIR")){
			if(obj == null){
				mensagemVH = "sucesso";
				request.getSession().setAttribute("msg", mensagemVH);
				request.getSession().invalidate();
			}
			else{
				request.getSession().setAttribute("msg", obj);
			}
			response.sendRedirect("/ellentex-livrariales/index.jsp");
		}

		else if(operacao.equals("LISTAR_CLIENTES") || operacao.equals("PESQUISAR_CLIENTE_NOME") || operacao.equals("PESQUISAR_CLIENTE")){
			if(obj != null) {
				request.getSession().setAttribute("listaCliente", obj);
			}else{
				obj = "Tente novamente mais tarde.";
				request.getSession().setAttribute("msg", obj);
			}
			rd = request.getRequestDispatcher("/admin/listarCliente.jsp");
			rd.forward(request,response);
		}
		//VIEW DO PERFIL
		else if(operacao.equals("PERFIL_CLIENTE")){
			request.getSession().setAttribute("clientePerfil", obj);
			rd = request.getRequestDispatcher("/admin/perfilCliente.jsp");
			rd.forward(request,response);
		}

		//Cliente a ser ativado
		else if(operacao.equals("ATIVAR_CLIENTE")){
			if(obj != null){
				request.getSession().setAttribute("clienteAtivar", obj);
				rd = request.getRequestDispatcher("Cliente?operacao=HABILITAR_CLIENTE");
				rd.forward(request,response);
			}
			else{
				obj = "Tente novamente mais tarde.";
				request.getSession().setAttribute("msg", obj);
				response.sendRedirect("Cliente?operacao=LISTAR_CLIENTES");
			}
		}

		//Cliente a ser desativado
		else if(operacao.equals("DESATIVAR_CLIENTE")){
			if(obj != null){
				request.getSession().setAttribute("clienteInativar", obj);
				rd = request.getRequestDispatcher("Cliente?operacao=DESABILITAR_CLIENTE");
				rd.forward(request,response);
			}
			else{
				obj = "Tente novamente mais tarde.";
				request.getSession().setAttribute("msg", obj);
				response.sendRedirect("Cliente?operacao=LISTAR_CLIENTES");
			}
		}

		else if (operacao.equals("HABILITAR_CLIENTE") || operacao.equals("DESABILITAR_CLIENTE")){
			if (obj instanceof Cliente) {
				mensagemVH = "sucesso";
				request.getSession().setAttribute("msg", mensagemVH);
			}
			else{
				request.getSession().setAttribute("msg", obj);
			}
			response.sendRedirect("Cliente?operacao=LISTAR_CLIENTES");
		}

		else if (operacao.equals("ATUALIZAR_CLI_LOGADO")){
			if(obj instanceof Cliente){
				List<EntidadeDominio> list = (List<EntidadeDominio>) obj;
				Cliente clienteEncontrado = (Cliente) list.get(0);
				usuarioLogado.setUsuario(clienteEncontrado.getUsuario());
				request.getSession().setAttribute("usuarioLogado", usuarioLogado);
			}
		}
	}
}
