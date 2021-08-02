package br.com.mariah.lojajpahibernate.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {
	}

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public Cliente(String nome) {
		this.dadosPessoais = new DadosPessoais(nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public String getNome() {
		return getDadosPessoais().getNome();
	}
	
	public String getCpf() {
		return getDadosPessoais().getCpf();
	}

}
