package br.com.mariah.lojajpahibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mariah.lojajpahibernate.model.Pedido;

public class PedidoDAO {

	private EntityManager entityManager;

	public PedidoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Pedido pedido) {
		this.entityManager.persist(pedido);
	}

	public void atualizar(Pedido pedido) {
		this.entityManager.merge(pedido);
	}

	public void remover(Pedido pedido) {
		pedido = this.entityManager.merge(pedido);
		this.entityManager.remove(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return this.entityManager.find(Pedido.class, id);
	}

	public List<Pedido> buscarTodos() {
		String jpql = "SELECT p FROM Pedido p";
		return this.entityManager.createQuery(jpql, Pedido.class).getResultList();
	}
	
}
