package br.com.appweb.impl;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import br.com.appweb.dao.UsuarioDAO;
import br.com.appweb.entity.UsuarioEntity;
import br.com.appweb.facade.UsuarioFacade;

@ApplicationScoped
public class UsuarioImpl implements Serializable, UsuarioFacade {

	private static final long serialVersionUID = 2101599288749513604L;

	@Inject
	private UsuarioDAO UsuarioDAO;

	@Override
	public UsuarioEntity logar(UsuarioEntity usuario) {
		return UsuarioDAO.logar(usuario);
	}

	@Override
	public List<UsuarioEntity> recuperaValores(String valor) {
		return null;
	}

	@Override
	public void grava(UsuarioEntity usuario) {

	}

	@Override
	public void remove(UsuarioEntity usuario) {

	}

}
