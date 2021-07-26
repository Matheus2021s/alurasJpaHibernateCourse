package br.com.mariah.lojajpahibernate.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.mariah.lojajpahibernate.model.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("xiaomi");
		celular.setDescricao("mobile");
		celular.setPreco(new BigDecimal(1000));
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("loja");		
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		entityManager.persist(celular);
		transaction.commit();
		
		entityManager.close();

	
	}
}
