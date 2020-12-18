package br.com.appweb.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.appweb.types.TipoOrdernacao;

public interface AppFacadeDAO {
	public <T> T merge(T entity);
	public void grava(Object entity);
	public void remove(Object entity);
	public <T> T recuperaValor(Class<T> classe, Long id);
	public <T> T recuperaValor(Class<T> classe, Long id, Map<String, Object> propriedades);
	public <T> List<T> recuperaValor(Class<T> classe);
	public <T> List<Long> recuperaListaIds(Class<T> classe);
	public <T> List<T> recuperaValores(Class<T> classe, Long valor, String propriedade);
	public <T> List<T> recuperaItensOrdenado(Class<T> classe, String propriedade, TipoOrdernacao tipoOrdernacao);
	public <T> List<T> recuperaIds(Class<T> classe, List<Long> listaIds);
	public <T> List<T> recuperaValores(Class<T> classe, String valor, String propriedade);
	public <T> List<T> recuperaValores(Class<T> classe, Date inicio, Date fim, String propriedade);
	public <T> boolean existeRegistro(Class<T> classe, String propriedade, String valor);
	public void refresh(Object entity);
	public void clear();
}
