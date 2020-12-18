package br.com.appweb.facade;

import java.util.List;

import br.com.appweb.entity.UsuarioEntity;

public interface UsuarioFacade {
	public UsuarioEntity logar(UsuarioEntity usuario);
	public List<UsuarioEntity> recuperaValores(String valor);
	public void grava(UsuarioEntity usuario);
	public void remove(UsuarioEntity usuario);
}
