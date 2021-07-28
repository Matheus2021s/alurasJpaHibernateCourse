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
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		Categoria celurares = new Categoria("CELULARES");		
		Produto celular = new Produto("XIAOMI","MOBILE",new BigDecimal(1000), celurares);
		
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		categoriaDAO.cadastrar(celurares);
		produtoDAO.cadastrar(celular);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();

	
	}
}
