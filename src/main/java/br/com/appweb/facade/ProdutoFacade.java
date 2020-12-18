package br.com.appweb.facade;

import java.util.List;

import br.com.appweb.entity.ProdutoEntity;
import br.com.appweb.types.EntradaSaidaProduto;

public interface ProdutoFacade {
	public void grava(ProdutoEntity produto);
	public List<ProdutoEntity> recuperaValores();
	public void remove(ProdutoEntity produto);
	public List<ProdutoEntity> recuperaValores(String valor);
	public ProdutoEntity recuperaValor(Long id);
	public void entradaSaidaProduto(ProdutoEntity produto, EntradaSaidaProduto entradaSaidaProduto);
}
