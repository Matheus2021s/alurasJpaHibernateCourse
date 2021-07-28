package br.com.mariah.lojajpahibernate.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.mariah.lojajpahibernate.dao.CategoriaDAO;
import br.com.mariah.lojajpahibernate.dao.ProdutoDAO;
import br.com.mariah.lojajpahibernate.model.Categoria;
import br.com.mariah.lojajpahibernate.model.Produto;
import br.com.mariah.lojajpahibernate.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		Long id = 1L;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		Produto produto = produtoDAO.buscarPorId(id);
		System.out.println(produto);
		
		for (Produto produto2 : produtoDAO.buscarTodos()) {
			System.out.println(produto2);
		}
	}

	private static void cadastrarProduto() {
		
		Categoria celurares = new Categoria("CELULARES");		
		Produto celular = new Produto("XIAOMI","MOBILE",new BigDecimal(1000), celurares);
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		categoriaDAO.cadastrar(celurares);
		produtoDAO.cadastrar(celular);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
}
