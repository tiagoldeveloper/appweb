package br.com.appweb.mb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.appweb.comum.FacesUtil;
import br.com.appweb.entity.ProdutoEntity;
import br.com.appweb.facade.ProdutoFacade;
import br.com.appweb.types.EntradaSaidaProduto;

@ViewScoped
@Named
public class ManutencaoProdutoManMB implements Serializable {

	private static final long serialVersionUID = 3819230534860340809L;

	@Inject
	private ProdutoFacade produtoFacade;

	@Inject
	private ProdutoEntity produto;

	private String entradaSaidaProdutoStr;

	private String codigoStr;

	public void grava() {
		try {
			produtoFacade.entradaSaidaProduto(produto, EntradaSaidaProduto.valueOf(entradaSaidaProdutoStr));
			codigoStr = null;
			entradaSaidaProdutoStr = null;
			produto = new ProdutoEntity();
		} catch (Exception e) {
			FacesUtil.addInfoMessage("Ocorreu erro ao gravar "+e.toString());
		}
	}
	
	public List<ProdutoEntity> completarProduto(String descricao) {
		return produtoFacade.recuperaValores(descricao);
	}
	
	@SuppressWarnings("rawtypes")
	public void produtoSelecionado(SelectEvent event) {
		produto = (ProdutoEntity) event.getObject();
	}

	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
		PrimeFaces.current().dialog().openDynamic("produtoselecao", opcoes, null);
	}
	
	public void selecionar(ProdutoEntity produto) {
		PrimeFaces.current().dialog().closeDynamic(produto);
	}
	
	public EntradaSaidaProduto[] getEntradaSaidaProduto() {
		return EntradaSaidaProduto.values();
	}

	public void pesquisaProduto() {
		produto = produtoFacade.recuperaValor(Long.valueOf(codigoStr));
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public String getEntradaSaidaProdutoStr() {
		return entradaSaidaProdutoStr;
	}

	public void setEntradaSaidaProdutoStr(String entradaSaidaProdutoStr) {
		this.entradaSaidaProdutoStr = entradaSaidaProdutoStr;
	}

	public String getCodigoStr() {
		return codigoStr;
	}

	public void setCodigoStr(String codigoStr) {
		this.codigoStr = codigoStr;
	}

}
