package br.com.mariah.lojajpahibernate.dao;

import javax.persistence.EntityManager;

import br.com.mariah.lojajpahibernate.model.Produto;

public class ProdutoDAO {

	private EntityManager entityManager;

	public ProdutoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}
	
}
