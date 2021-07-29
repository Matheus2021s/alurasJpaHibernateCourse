package br.com.mariah.lojajpahibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mariah.lojajpahibernate.model.Cliente;

public class ClienteDAO {

	private EntityManager entityManager;

	public ClienteDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = this.entityManager.merge(cliente);
		this.entityManager.remove(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return this.entityManager.find(Cliente.class, id);
	}

	public List<Cliente> buscarTodos() {
		String jpql = "SELECT p FROM Cliente p";
		return this.entityManager.createQuery(jpql, Cliente.class).getResultList();
	}
}
