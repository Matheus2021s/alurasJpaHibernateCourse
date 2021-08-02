package br.com.mariah.lojajpahibernate.model;

import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {
	private String nome;
	private String cpf;
	
	public DadosPessoais() {
	}

	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public DadosPessoais(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
