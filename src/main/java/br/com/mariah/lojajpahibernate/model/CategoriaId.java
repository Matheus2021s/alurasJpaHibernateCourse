package br.com.mariah.lojajpahibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId  implements Serializable[
	
	private static final long serialVersionUID = 1L;



	private String nome;
	private String tipo;

	public CategoriaId(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public CategoriaId() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
