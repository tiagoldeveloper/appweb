package br.com.appweb.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.appweb.comum.FacesUtil;
import br.com.appweb.entity.ProdutoEntity;
import br.com.appweb.facade.ProdutoFacade;

@ViewScoped
@Named
public class ProdutoManMB implements Serializable {

	private static final long serialVersionUID = 3819230534860340809L;

	@Inject
	private ProdutoFacade produtoFacade;

	@Inject
	private ProdutoEntity produto;

	@PostConstruct
	public void inicia() {
		recuperaProduto();
	}

	public void grava() {
		try {
			produtoFacade.grava(produto);
			FacesUtil.addInfoMessage("Registro gravado com sucesso!");
			novaInstancia();
		} catch (Exception e) {
			FacesUtil.addInfoMessage("Ocorreu erro ao gravar " + e.toString());
		}
	}

	public void novaInstancia() {
		produto = new ProdutoEntity();
	}

	private void recuperaProduto() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (StringUtils.isNotEmpty(id) && StringUtils.isNotBlank(id)) {
			this.produto = produtoFacade.recuperaValor(Long.valueOf(id));
		}
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

}
