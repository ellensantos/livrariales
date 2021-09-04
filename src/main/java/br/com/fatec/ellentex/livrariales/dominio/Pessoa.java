package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class Pessoa extends EntidadeDominio {

	private String nome;
	private String cpf;
	private String genero;
	private Date dtNascimento;
	
	public Pessoa() {};

	public Pessoa(String nome, String cpf, String genero, Date dtNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.genero = genero;
		this.dtNascimento = dtNascimento;
	}

	public Pessoa(String nome){
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

}
