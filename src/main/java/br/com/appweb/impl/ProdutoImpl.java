package br.com.appweb.impl;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import br.com.appweb.as.ProdutoAS;
import br.com.appweb.dao.ProdutoDAO;
import br.com.appweb.entity.ProdutoEntity;
import br.com.appweb.facade.ProdutoFacade;
import br.com.appweb.jpa.Transactional;
import br.com.appweb.types.EntradaSaidaProduto;

@ApplicationScoped
public class ProdutoImpl implements Serializable, ProdutoFacade {

	private static final long serialVersionUID = 2101599288749513604L;

	@Inject
	private ProdutoAS produtoAS;

	@Inject
	private ProdutoDAO produtoDAO;

	@Transactional
	@Override
	public void grava(ProdutoEntity produto) {
		produtoDAO.merge(produto);
	}

	@Transactional
	@Override
	public void remove(ProdutoEntity produto) {
		produtoDAO.remove(produto);
	}

	@Override
	public ProdutoEntity recuperaValor(Long id) {
		return produtoDAO.recuperaValor(ProdutoEntity.class, id);
	}

	@Override
	public List<ProdutoEntity> recuperaValores() {
		return produtoDAO.recuperaValor(ProdutoEntity.class);
	}

	@Override
	public List<ProdutoEntity> recuperaValores(String valor) {
		return produtoAS.recuperaProdutos(valor);
	}

	@Transactional
	@Override
	public void entradaSaidaProduto(ProdutoEntity produto, EntradaSaidaProduto entradaSaidaProduto) {
		produtoAS.entradaSaidaProduto(produto, entradaSaidaProduto);
	}

}
