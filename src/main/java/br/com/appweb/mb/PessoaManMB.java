package br.com.appweb.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import br.com.appweb.comum.FacesUtil;
import br.com.appweb.entity.PessoaEntity;
import br.com.appweb.facade.PessoaFacade;
import br.com.appweb.types.TipoPessoa;

@ViewScoped
@Named
public class PessoaManMB implements Serializable {

	private static final long serialVersionUID = 3819230534860340809L;

	@Inject
	private PessoaFacade pessoaFacade;

	@Inject
	private PessoaEntity pessoa;

	private String pesquisa;

	private List<PessoaEntity> pessoas;

	private String tipoPessoa;

	@PostConstruct
	public void start() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (StringUtils.isNotEmpty(id) && StringUtils.isNotBlank(id)) {
			this.setPessoa(pessoaFacade.recuperaValor(Long.valueOf(id)));
			setTipoPessoa(String.valueOf(getPessoa().getTipoPessoa()));
		}
	}

	public void grava() {
		try {
			pessoa.setTipoPessoa(TipoPessoa.valueOf(tipoPessoa));
			pessoaFacade.grava(getPessoa());
			FacesUtil.addInfoMessage("Registro gravado com sucesso!");
			novaInstacia();
		} catch (Exception e) {
			FacesUtil.addInfoMessage("Ocorreu erro ao gravar. "+ e.getMessage());
		}
	}

	public void novaInstacia() {
		setPessoa(new PessoaEntity());
		setTipoPessoa(null);
		PrimeFaces.current().resetInputs("frm");
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public List<PessoaEntity> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaEntity> pessoas) {
		this.pessoas = pessoas;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}
