package br.com.sramos.comercio.cadastro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sramos.comercio.categoria.Categoria;
import br.com.sramos.comercio.categoria.CategoriaDAO;
import br.com.sramos.comercio.produto.Produto;
import br.com.sramos.comercio.produto.ProdutoDAO;

public class Cadastro {
	
	public static void main(String[] args) {
		Cadastro cadastro = new Cadastro();
		cadastro.cadastraProdutos();
		cadastro.cadastraCategorias();
		System.out.println("Cadastros gerados com sucesso!");
	}

	public void cadastraProdutos(){
		String descricao[] = {"Bicicleta", "Televisão", "DVD"};
		Double preco[] = {356.83, 19.99, 195.60};
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = null;
		
		for(int i =0 ; i < 3 ; i++){
			produto = new Produto();
			produto.setDescricao(descricao[i]);
			produto.setPreco(preco[i]);
			produtoDAO.salvar(produto);
		}
	}
	
	public void cadastraCategorias(){
		String descricao[] = {"Utilidades", "Geral"};
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = null;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Set<Produto> produtos = new HashSet<Produto>();
		List<Produto> produtosListagem = produtoDAO.listar();
		
		for(int i = 0 ; i < produtosListagem.size() ; i++){
			produtos.add(produtosListagem.get(i));
		}
		
		for(int i = 0 ; i < 2 ; i++){
			categoria = new Categoria();
			categoria.setDescricao(descricao[i]);
			categoria.setProdutos(produtos);
			categoriaDAO.salvar(categoria);
		}
	}
}
