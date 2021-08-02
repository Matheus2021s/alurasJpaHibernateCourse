package br.com.mariah.lojajpahibernate.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mariah.lojajpahibernate.dao.CategoriaDAO;
import br.com.mariah.lojajpahibernate.dao.ClienteDAO;
import br.com.mariah.lojajpahibernate.dao.PedidoDAO;
import br.com.mariah.lojajpahibernate.dao.ProdutoDAO;
import br.com.mariah.lojajpahibernate.model.Categoria;
import br.com.mariah.lojajpahibernate.model.Cliente;
import br.com.mariah.lojajpahibernate.model.ItemPedido;
import br.com.mariah.lojajpahibernate.model.Pedido;
import br.com.mariah.lojajpahibernate.model.Produto;
import br.com.mariah.lojajpahibernate.util.JPAUtil;

public class CadastroDePedido {


	public static void main(String[] args) {
		cadastrarProduto();
		
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		Produto produto = produtoDAO.buscarPorId(1L);

		
		entityManager.getTransaction().begin();
		ClienteDAO clienteDAO = new ClienteDAO(entityManager);
		
		Cliente cliente = new Cliente("MATHEUS MARTINS", "12343543");
		clienteDAO.cadastrar(cliente);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
	
		PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
		pedidoDAO.cadastrar(pedido);
		
		
		entityManager.getTransaction().commit();
		
		BigDecimal valorTotalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("valor total vendido: " + valorTotalVendido);

		
		List<Object[]> relatorioDeVendas = pedidoDAO.relatorioDeVendas();
		for (Object[] objects : relatorioDeVendas) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);

		}
		entityManager.close();
	}

	private static void cadastrarProduto() {

		Categoria celurares = new Categoria("CELULARES");
		Produto celular = new Produto("XIAOMI", "MOBILE", new BigDecimal(1000), celurares);

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
