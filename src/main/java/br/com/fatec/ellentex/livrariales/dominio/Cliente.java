package br.com.fatec.ellentex.livrariales.dominio;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

	@OneToMany(fetch = FetchType.EAGER)
	private List<Endereco> endereco;

	@JoinColumn(unique = true)
	@OneToOne
	private Telefone telefone;

	@JoinColumn(unique = true)
	@OneToOne
	private UsuarioComum usuario;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Pedido> pedido;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<CupomTroca> cupom;

	@JoinColumn(unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Ranking ranking;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Cartao> cartao;

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Mensagem> mensagem;

	public Cliente() {

	}

	public Cliente(String nome, String cpf, String genero, Date dtNascimento, List<Endereco> endereco,
				   Telefone telefone, UsuarioComum usuario, List<Pedido> pedido, Ranking ranking, List<Cartao> cartao) {
		super(nome, cpf, genero, dtNascimento);
		this.endereco = endereco;
		this.telefone = telefone;
		this.usuario = usuario;
		this.pedido = pedido;
		this.ranking = ranking;
		this.cartao = cartao;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public UsuarioComum getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioComum usuario) {
		this.usuario = usuario;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public List<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedidoCompra) {
		this.pedido = pedidoCompra;
	}

	public List<CupomTroca> getCupom() {
		return cupom;
	}

	public void setCupom(List<CupomTroca> cupom) {
		this.cupom = cupom;
	}

	public List<Mensagem> getMensagem() {
		return mensagem;
	}

	public void setMensagem(List<Mensagem> mensagem) {
		this.mensagem = mensagem;
	}
}
