package br.com.mariah.lojajpahibernate.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.mariah.lojajpahibernate.dao.ProdutoDAO;
import br.com.mariah.lojajpahibernate.model.Produto;
import br.com.mariah.lojajpahibernate.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("xiaomi");
		celular.setDescricao("mobile");
		celular.setPreco(new BigDecimal(1000));
		
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		produtoDAO.cadastrar(celular);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();

	
	}
}
