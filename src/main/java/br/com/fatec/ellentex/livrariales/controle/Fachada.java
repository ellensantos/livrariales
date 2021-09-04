package br.com.fatec.ellentex.livrariales.controle;

import br.com.fatec.ellentex.livrariales.dao.*;
import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.negocio.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EllenTex
 */
public class Fachada implements IFachada {

	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> rngs;
	private StringBuilder mensagemErro = new StringBuilder();
	private List<java.lang.Object> retornoStrategy = new ArrayList<>();

	//Construtor
	public Fachada(){

		definirDAOS();

		List<IStrategy> rngCliente = new ArrayList<>();
		List<IStrategy> rngLivro = new ArrayList<>();
		List<IStrategy> rngPedido = new ArrayList<>();
		List<IStrategy> rngItemLivro = new ArrayList<>();
		rngs = new HashMap<>();

		//Objetos da RNG - Cliente
		IStrategy validarExistenciaCliente = new ValidarExistenciaCliente();
		IStrategy criptografarSenhaCliente = new CriptografarSenhaCliente();
		IStrategy definirRankingCliente = new DefinirRankingCliente();

		//Objetos da RNG - Livro
		IStrategy validarExistenciaLivro = new ValidarExistenciaLivro();

		//Objetos da RNG - Pedido
		IStrategy validarCupomDesconto = new ValidarCupomDescontoPedido();
		IStrategy validarCupomTrocaPedido = new ValidarCupomTrocaPedido();
		IStrategy validarPagamentoCartao = new ValidarDadosPagamentoCartao();
		IStrategy calcularFrete = new CalcularFrete();
		IStrategy validarPagamentoPedido = new ValidarPagamentoPedido();
 		IStrategy gerenciarStatusPedido = new GerenciarStatusPedido();

		//Objetos da RNG - ItemLivro
		IStrategy calcularValorVendaItemLivro = new CalcularValorVendaItemLivro();
		IStrategy validarEntradaItemLivroEstoque = new GerenciarEntradaItemLivroEstoque();

		//Adicionando na lista de RNG - Cliente
		rngCliente.add(validarExistenciaCliente);
		rngCliente.add(criptografarSenhaCliente);
		rngCliente.add(definirRankingCliente);

		//Adicionando na lista de RNG - Livro
		rngLivro.add(validarExistenciaLivro);

		//Adicionando na lista de RNG - Pedido
		rngPedido.add(validarCupomDesconto);
		rngPedido.add(validarCupomTrocaPedido);
		rngPedido.add(validarPagamentoCartao);
		rngPedido.add(calcularFrete);
		rngPedido.add(validarPagamentoPedido);
		rngPedido.add(gerenciarStatusPedido);

		//Adicionando na lista de RNG - ItemLivro
		rngItemLivro.add(calcularValorVendaItemLivro);
		rngItemLivro.add(validarEntradaItemLivroEstoque);

		// Adicionando no map de regras de negócio
		rngs.put(Cliente.class.getName(), rngCliente);
		rngs.put(Livro.class.getName(), rngLivro);
		rngs.put(PedidoCompra.class.getName(),rngPedido);
		rngs.put(ItemLivro.class.getName(), rngItemLivro);
		rngs.put(PedidoTroca.class.getName(),rngPedido);
	}

	@Override
	public java.lang.Object salvar(EntidadeDominio entidade) {
		//Retorna mensagem de erro ou a entidade.

		String nomeClasse = entidade.getClass().getName();
		// Limpar mensagens de erro
		mensagemErro.setLength(0);
		String msgs = executarRng(entidade);
		System.out.println("Mensagens FACHADA = " + msgs);

		if (msgs == null) {
			IDAO dao = daos.get(nomeClasse);
			if(dao == null){//Entidade recebida não tem um DAO.
				return entidade;
			}

			EntidadeDominio entid = dao.salvar(entidade);

			if(entid != null){
				return entid;
			}
			else{
				return "falha";
			}

			//IStrategy gerarLog = new GerarLog();
			//gerarLog.processar(entidade);
		} else {
			return msgs;
		}
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nomeClasse);

		if(dao == null){//Entidade recebida não tem um DAO.
			return null;
		}
		else{
			return dao.excluir(entidade);
		}
	}

	@Override
	public java.lang.Object alterar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		// Limpar mensagens de erro
		mensagemErro.setLength(0);

		String msgs = executarRng(entidade);

		System.out.println("Mensagens FACHADA = " + msgs);

		if (msgs == null) {
			IDAO dao = daos.get(nomeClasse);
			EntidadeDominio entid = dao.alterar(entidade);
			//IStrategy gerarLog = new GerarLog();
			//gerarLog.processar(entidade);
			if(entid != null){
				return entid;
			}else{
				return "falha";
			}
		}else {
			return msgs;
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nomeClasse);

		if(dao == null){//Entidade recebida não tem um DAO.
			return null;
		}

		List<EntidadeDominio> listaConsulta = dao.consultar(entidade);

		if(listaConsulta != null && !listaConsulta.isEmpty()){
			return listaConsulta;
		}
		else{
			return null;
		}
	}

	private void definirDAOS() {
		daos = new HashMap<String, IDAO>();
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Livro.class.getName(), new LivroDAO());
		daos.put(ItemLivro.class.getName(), new ItemLivroDAO());
		daos.put(ItemReservado.class.getName(), new ItemReservadoDAO());
		daos.put(CupomDesconto.class.getName(), new CupomDescontoDAO());
		daos.put(CupomTroca.class.getName(), new CupomDAO());
		daos.put(PedidoCompra.class.getName(), new PedidoCompraDAO());
		daos.put(Pedido.class.getName(), new PedidoDAO());
		daos.put(PedidoTroca.class.getName(), new PedidoTrocaDAO());
		daos.put(Estoque.class.getName(), new EstoqueDAO());
		daos.put(ItemEstoque.class.getName(), new ItemEstoqueDAO());
	}

	// Execução das regras de negócio
	private String executarRng(EntidadeDominio entidade) {

		String nmClasse = entidade.getClass().getName();
		List<IStrategy> rngEntidade = rngs.get(nmClasse);

		// Verificar se existe regra para a entidade
		if (rngEntidade != null) {
			for (IStrategy strategy : rngEntidade) {
				java.lang.Object msg = strategy.processar(entidade);
				if (msg != null) {
					mensagemErro.append(msg).append("\n");
				}
			}
		}

		if (mensagemErro.length() > 0)
			return mensagemErro.toString();
		else
			return null;
	}
}

