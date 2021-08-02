package br.com.mariah.lojajpahibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mariah.lojajpahibernate.model.Pedido;
import br.com.mariah.lojajpahibernate.vo.RelatorioDeVendasVo;

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
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p ";
		return this.entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new "
				+ " br.com.mariah.lojajpahibernate.vo.RelatorioDeVendasVo("
				+ " produto.nome ,"
				+ " sum( item.quantidade ),"
				+ " max( pedido.data ) "
				+ " )"
				+ " FROM Pedido pedido "
				+ " JOIN pedido.itens item"
				+ " JOIN item.produto produto "
				+ " GROUP BY produto.nome "
				+ " ORDER BY item.quantidade DESC ";	
		return this.entityManager.createQuery(jpql, RelatorioDeVendasVo.class ).getResultList();
	}
	
}
