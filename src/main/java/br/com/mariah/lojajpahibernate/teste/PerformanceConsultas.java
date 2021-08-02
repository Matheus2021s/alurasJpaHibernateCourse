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
import br.com.mariah.lojajpahibernate.vo.RelatorioDeVendasVo;

public class PerformanceConsultas {


	public static void main(String[] args) {
		popularBancoDeDados();
		
		EntityManager entityManager = JPAUtil.getEntityManager();
	
		Pedido pedido = entityManager.find(Pedido.class, 1L);
		
		System.out.println( pedido.getItens().size() );
	}

	private static void popularBancoDeDados() {
		cadastrarProduto();
		
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		Produto produto1 = produtoDAO.buscarPorId(1L);
		Produto produto2 = produtoDAO.buscarPorId(2L);


		ClienteDAO clienteDAO = new ClienteDAO(entityManager);
		PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		
		Cliente cliente = new Cliente("MATHEUS MARTINS", "12343543");
		clienteDAO.cadastrar(cliente);
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(10, pedido1, produto1));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(10, pedido2, produto1));	
		pedido2.adicionarItem(new ItemPedido(10, pedido2, produto2));	

		pedidoDAO.cadastrar(pedido1);
		
		pedidoDAO.cadastrar(pedido2);

		entityManager.getTransaction().commit();
		
		BigDecimal valorTotalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("valor total vendido: " + valorTotalVendido);

		
		List<RelatorioDeVendasVo> relatorioDeVendas = pedidoDAO.relatorioDeVendas();
		for (RelatorioDeVendasVo rel : relatorioDeVendas) {
			System.out.println(rel.toString());


		}
		entityManager.close();
	}

	private static void cadastrarProduto() {

		Categoria celurares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");

		Produto celular = new Produto("XIAOMI", "MOBILE", new BigDecimal(1000), celurares);
		Produto videogame =new Produto("PLAYSTATION5", "VIEOGAME MUITO CARO", new  BigDecimal(5000), videogames);

		EntityManager entityManager = JPAUtil.getEntityManager();

		CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

		entityManager.getTransaction().begin();

		categoriaDAO.cadastrar(celurares);
		categoriaDAO.cadastrar(videogames);
		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(videogame);

		entityManager.getTransaction().commit();

		entityManager.close();
	}

}
