package br.com.appweb.facade;

import java.util.List;

import br.com.appweb.entity.PessoaEntity;

public interface PessoaFacade {
	public void grava(PessoaEntity pessoa) throws Exception;
	public List<PessoaEntity> recuperaValores(String valor);
	public void remove(PessoaEntity pessoa);
	public PessoaEntity recuperaValor(Long id);
}
