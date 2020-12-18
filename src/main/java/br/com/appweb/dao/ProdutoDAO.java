package br.com.appweb.dao;

import java.io.Serializable;
import javax.persistence.Query;
import br.com.appweb.entity.ProdutoEntity;

public class ProdutoDAO extends AppDAO implements Serializable {

	private static final long serialVersionUID = 6598565497105720265L;
	
	public void entradaProduto(ProdutoEntity produto) {
		String sql = "update ProdutoEntity p set p.quantidade = p.quantidade + :novaquantidade where p.id = :idproduto";
		Query query = getManager().createQuery(sql);
		query.setParameter("novaquantidade", produto.getQuantidade());
		query.setParameter("idproduto", produto.getId());
		query.executeUpdate();
	}

	public void saidaProduto(ProdutoEntity produto) {
		String sql = "update ProdutoEntity p set p.quantidade = p.quantidade - :novaquantidade where p.id = :idproduto";
		Query query = getManager().createQuery(sql);
		query.setParameter("novaquantidade", produto.getQuantidade());
		query.setParameter("idproduto", produto.getId());
		query.executeUpdate();
	}
	
}
