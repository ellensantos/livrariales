package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 *
 */

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Endereco extends EntidadeDominio {

	private String descricao;
	private String tipoResidencia;
	private String tipoLogradouro;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cep;

	@ManyToOne
	private Cidade cidade;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<TipoEndereco> tipoEndereco;

	private String observacao;


	public Endereco() {
		super();
	}

	public Endereco(String descricao, String tipoResidencia, String tipoLogradouro, String logradouro, int numero,
			String bairro, String cep, Cidade cidade, List<TipoEndereco> tipoEndereco, String observacao) {
		super();
		this.descricao = descricao;
		this.tipoResidencia = tipoResidencia;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.tipoEndereco = tipoEndereco;
		this.observacao = observacao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<TipoEndereco> getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(List<TipoEndereco> tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
