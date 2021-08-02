package br.com.mariah.lojajpahibernate.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.mariah.lojajpahibernate.model.Produto;

public class ProdutoDAO {

	private EntityManager entityManager;

	public ProdutoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.entityManager.merge(produto);
	}

	public void remover(Produto produto) {
		produto = this.entityManager.merge(produto);
		this.entityManager.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.entityManager.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return this.entityManager.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return this.entityManager.createQuery(jpql, Produto.class)
				.setParameter( "nome", nome )
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return this.entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome )
				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoPeloNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return this.entityManager.createQuery(jpql,BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	
	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> from = criteriaQuery.from(Produto.class);
		Predicate filtros = criteriaBuilder.and();

		
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome));
		}
		if (preco != null) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"), preco));
		}
		if (dataCadastro != null) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
		}
		
		criteriaQuery.where(filtros);
		
		return entityManager.createQuery(criteriaQuery).getResultList();

	}
}
