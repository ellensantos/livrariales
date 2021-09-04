package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroCliente;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 *
 */
public class ClienteDAO extends AbstractDAO {

	public ClienteDAO() {
		getEntityManager();
		//getSessionHibernate();
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		Cliente cliente = (Cliente) entidade;

		try{
			getEntityManager().clear();
			List<EntidadeDominio> listaCliente = new ArrayList<EntidadeDominio>();
			FiltroCliente filtroCliente = new FiltroCliente();
			TypedQuery<EntidadeDominio> query = filtroCliente.criarQuery(cliente, getEntityManager());
			return query.getResultList();

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(ctrlTransacao){
				getEntityManager().close();
			}
		}

		return null;
	}

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {

		if(getEntityManager().getTransaction().isActive()){
			ctrlTransacao = false;
		}
		else{
			getEntityManager().getTransaction().begin();
		}

		Cliente cliente = (Cliente) entidade;
		List<Endereco> enderecos = cliente.getEndereco();
		List<Endereco> novosEnd = new ArrayList<Endereco>();

		//DAOS
		UsuarioComumDAO daoUser = new UsuarioComumDAO();
		EnderecoDAO daoEnd = new EnderecoDAO();
		TelefoneDAO daoTel = new TelefoneDAO();

		try{

			for(Endereco end : enderecos){
				System.out.println("ADD os endereços!");
				daoEnd.salvar(end);
			}

			daoTel.salvar(cliente.getTelefone());
			daoUser.salvar(cliente.getUsuario());
			getEntityManager().persist(cliente);

			ctrlTransacao = true;

		}catch (Exception e){
			System.out.println("Erro ao salvar Cliente!");
			getEntityManager().getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(ctrlTransacao){
				getEntityManager().getTransaction().commit();
				getEntityManager().close();
			}
		}

		return cliente;
	}

	@Override
	public EntidadeDominio alterar(EntidadeDominio entidade) {

		if(getEntityManager().getTransaction().isActive()){
			ctrlTransacao = false;
		}
		else{
			getEntityManager().getTransaction().begin();
		}

		Cliente cliente = (Cliente) entidade;
		List<Endereco> enderecos = cliente.getEndereco();
		List<Cartao> cartoes = cliente.getCartao();
		List<CupomTroca> cupons = cliente.getCupom();
		List<Mensagem> mensagens = cliente.getMensagem();

		EnderecoDAO daoEnd = new EnderecoDAO();
		CartaoDAO daoCartao = new CartaoDAO();
		UsuarioComumDAO daoUser = new UsuarioComumDAO();
		TelefoneDAO daoTel = new TelefoneDAO();
		CupomTrocaDAO daoCupom = new CupomTrocaDAO();
		RankingDAO daoRanking = new RankingDAO();
		MensagemDAO daoMensagem = new MensagemDAO();

		List<Endereco> novosEnderecos = new ArrayList<>();
		List<Cartao> novosCartoes = new ArrayList<>();
		List<CupomTroca> novosCupons = new ArrayList<>();
		List<Mensagem> novasMsg = new ArrayList<>();

		try {
			for(Endereco end : enderecos){
				novosEnderecos.add((Endereco) daoEnd.alterar(end));
			}

			if(!cartoes.isEmpty()){
				for(Cartao cartao : cartoes){
					novosCartoes.add((Cartao) daoCartao.alterar(cartao));
				}
			}

			if(cupons != null){
				for(CupomTroca cupom : cupons){
					novosCupons.add((CupomTroca) daoCupom.alterar(cupom));
				}
			}

			if(cliente.getRanking() != null){
				daoRanking.alterar(cliente.getRanking());
			}

			if(cliente.getMensagem() != null){
				for(Mensagem msg : mensagens)
					novasMsg.add((Mensagem) daoMensagem.alterar(msg));
			}

			cliente.setEndereco(novosEnderecos);
			cliente.setCartao(novosCartoes);
			cliente.setCupom(novosCupons);
			cliente.setMensagem(novasMsg);

			daoTel.alterar(cliente.getTelefone());
			daoUser.alterar(cliente.getUsuario());

			cliente = getEntityManager().merge(cliente);

			System.out.println("Alterei o cliente!");

			ctrlTransacao = true;

		}catch (Exception e){
			System.out.println("Erro ao alterar Cliente!");
			getEntityManager().getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(ctrlTransacao){
				System.out.println("Commit em Cliente!");
				getEntityManager().getTransaction().commit();
				getEntityManager().close();
			}
		}

		return cliente;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {

		if(getEntityManager().getTransaction().isActive()){
			ctrlTransacao = false;
		}
		else{
			getEntityManager().getTransaction().begin();
		}

		Cliente cliente = (Cliente) entidade;

		try{
			Cliente clienteConsultado = getEntityManager().find(Cliente.class,cliente.getId());
			clienteConsultado.getUsuario().setStatus(false);
			getEntityManager().merge(cliente);

		}catch (Exception e){
			getEntityManager().getTransaction().rollback();
			e.printStackTrace();
			return ("Falha ao desativar conta");
		}finally {
			if(ctrlTransacao){
				getEntityManager().getTransaction().commit();
				getEntityManager().close();
			}
		}

		return null;
	}

	@Override
	public List<EntidadeDominio> listar() {
		try{
			getEntityManager().clear();
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
			Root<Cliente> root = cQuery.from(Cliente.class);
			CriteriaQuery<EntidadeDominio> all = cQuery.select(root);
			TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
			return query.getResultList();

		}catch (Exception e) {
			return null;
		}
	}


}
