package br.com.appweb.mb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.appweb.comum.FacesUtil;
import br.com.appweb.entity.PessoaEntity;
import br.com.appweb.facade.PessoaFacade;
import br.com.appweb.types.TipoPessoa;

@ViewScoped
@Named
public class PessoaConMB implements Serializable {

	private static final long serialVersionUID = 3819230534860340809L;

	@Inject
	private PessoaFacade pessoaFacade;

	@Inject
	private PessoaEntity pessoa;

	private String pesquisa;

	private String tipoPessoaStr;

	private List<PessoaEntity> pessoas;

	@PostConstruct
	public void start() {
		carregaPessoas();
	}

	public void carregaPessoas() {
		pessoas = pessoaFacade.recuperaValores(pesquisa);
	}

	public void remove() {
		pessoaFacade.remove(pessoa);
		FacesUtil.addInfoMessage("Registro removido com sucesso!");
		carregaPessoas();
	}

	public TipoPessoa[] getTipoPessoas() {
		return TipoPessoa.values();
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getTipoPessoaStr() {
		return tipoPessoaStr;
	}

	public void setTipoPessoaStr(String tipoPessoaStr) {
		this.tipoPessoaStr = tipoPessoaStr;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public List<PessoaEntity> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaEntity> pessoas) {
		this.pessoas = pessoas;
	}

}
