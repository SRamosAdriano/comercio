package br.com.sramos.comercio.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sramos.comercio.util.HibernateUtil;

public class ProdutoDAO {
	
	private Session sessao;
	
	private Transaction transacao;
	
	public void salvar(Produto produto){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.save(produto);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel inserir o produto. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public void atualizar(Produto produto){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.update(produto);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel alterar o produto. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de atualização. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public void excluir(Produto produto){
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			this.sessao.delete(produto);
			
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possivel excluir o produto. Erro: " + e.getMessage());
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem" + e2.getMessage());
			}
		}
	}
	
	public Produto buscarCategoria(Integer codigo){
		Produto produto = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			filtro.add(Restrictions.eq("produto", codigo));
			produto = (Produto) filtro.uniqueResult();
			
			this.transacao.commit();
		} catch (Throwable e) {
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de busca. Mensagem" + e2.getMessage());
			}
		}
		return produto;
	}
	
	public List<Produto> listar(){
		List<Produto> produtos = null;
		
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			produtos = filtro.list();
			
			this.transacao.commit();
		} catch (Throwable e) {
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
		}finally{
			try {
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			} catch (Throwable e2) {
				System.out.println("Erro ao fechar operação de listagem. Mensagem" + e2.getMessage());
			}
		}
		return produtos;
	}
}
