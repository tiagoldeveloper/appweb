package br.com.appweb.impl;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import br.com.appweb.bo.PessoaBO;
import br.com.appweb.dao.PessoaDAO;
import br.com.appweb.entity.PessoaEntity;
import br.com.appweb.facade.PessoaFacade;
import br.com.appweb.jpa.Transactional;

@ApplicationScoped
public class PessoaImpl implements Serializable, PessoaFacade {

	private static final long serialVersionUID = 2101599288749513604L;

	@Inject
	private PessoaBO pessoaBO;

	@Inject
	private PessoaDAO pessoaDAO;

	@Transactional
	@Override
	public void grava(PessoaEntity pessoa) throws Exception {
		pessoaBO.grava(pessoa);
	}

	@Transactional
	@Override
	public void remove(PessoaEntity pessoa) {
		pessoaDAO.remove(pessoa);
	}

	@Override
	public List<PessoaEntity> recuperaValores(String valor) {
		return pessoaBO.recuperaPessoas(valor);
	}

	@Override
	public PessoaEntity recuperaValor(Long id) {
		return pessoaDAO.recuperaValor(PessoaEntity.class, id);
	}

}
