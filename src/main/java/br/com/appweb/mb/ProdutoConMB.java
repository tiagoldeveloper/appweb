package br.com.appweb.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.appweb.comum.FacesUtil;
import br.com.appweb.entity.ProdutoEntity;
import br.com.appweb.facade.ProdutoFacade;

@ViewScoped
@Named
public class ProdutoConMB implements Serializable {

	private static final long serialVersionUID = 3819230534860340809L;

	@Inject
	private ProdutoFacade produtoFacade;

	@Inject
	private ProdutoEntity produto;

	private String pesquisa;

	private List<ProdutoEntity> produtos;

	@PostConstruct
	public void start() {
		recuperaProdutos();
	}

	public void recuperaProdutos() {
		if (StringUtils.isNotEmpty(pesquisa)) {
			produtos = new ArrayList<ProdutoEntity>();
			produtos = produtoFacade.recuperaValores(pesquisa);
		} else {
			produtos = produtoFacade.recuperaValores();
		}
	}

	public void remove() {
		produtoFacade.remove(produto);
		FacesUtil.addInfoMessage("Registro removido com sucesso!");
		produtos = produtoFacade.recuperaValores();
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public List<ProdutoEntity> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoEntity> produtos) {
		this.produtos = produtos;
	}

}
