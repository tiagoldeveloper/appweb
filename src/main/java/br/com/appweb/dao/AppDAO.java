package br.com.appweb.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import br.com.appweb.facade.AppFacadeDAO;
import br.com.appweb.types.TipoOrdernacao;

public class AppDAO implements AppFacadeDAO {

	@Inject
	private EntityManager manager;

	@Override
	public <T> T merge(T entity) {
		return getManager().merge(entity);
	}

	@Override
	public void grava(Object entity) {
		getManager().persist(entity);
	}

	@Override
	public void remove(Object entity) {
		getManager().remove(getManager().merge(entity));
		getManager().flush();
	}

	@Override
	public <T> T recuperaValor(Class<T> classe, Long id) {
		return getManager().find(classe, id);
	}

	@Override
	public <T> T recuperaValor(Class<T> classe, Long id, Map<String, Object> propriedades) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		propriedades.forEach((chave, valor) -> {
			sql.append(propriedades.get(chave)).append(", ");
		});
		sql.append("from ");
		sql.append(classe.getName());
		sql.append(" p where p.id =:id");
		Query query = getManager().createQuery(sql.toString());
		query.setParameter("id", id);
		return (T) query.getResultList();
	}

	@Override
	public <T> List<T> recuperaValor(Class<T> classe) {
		return getManager().createQuery("from " + classe.getName()).getResultList();
	}

	@Override
	public <T> List<Long> recuperaListaIds(Class<T> classe) {
		return getManager().createQuery("select p.id from " + classe.getName() + " p").getResultList();
	}

	@Override
	public <T> List<T> recuperaItensOrdenado(Class<T> classe, String propriedade, TipoOrdernacao tipoOrdernacao) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p from ").append(classe.getName());
		sql.append(" as p order by p.");
		sql.append(propriedade).append(" ");
		sql.append(tipoOrdernacao != null ? tipoOrdernacao : TipoOrdernacao.ASC);
		return getManager().createQuery(sql.toString()).getResultList();
	}

	@Override
	public <T> List<T> recuperaIds(Class<T> classe, List<Long> listaIds) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id from ");
		sql.append(classe.getName()).append(" p");
		sql.append(" where p.id in :ids ");
		return getManager().createQuery(sql.toString()).setParameter("ids", listaIds).getResultList();
	}

	@Override
	public <T> List<T> recuperaValores(Class<T> classe, String valor, String propriedade) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p from ");
		sql.append(classe.getName()).append(" p ");
		if(StringUtils.isNotBlank(valor)) {
			sql.append("where upper (p.").append(propriedade).append(") ");
			sql.append("like :propriedade ");
		}
		Query query = getManager().createQuery(sql.toString());
		if(StringUtils.isNotBlank(valor)) {
			query.setParameter("propriedade", "%" + valor.toUpperCase() + "%");
		}
		return query.getResultList();
	}

	@Override
	public <T> List<T> recuperaValores(Class<T> classe, Long valor, String propriedade) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p from ");
		sql.append(classe.getName()).append(" p ");
		sql.append(" where p. ").append(propriedade);
		sql.append(" =:propriedade ");
		Query query = getManager().createQuery(sql.toString());
		query.setParameter("propriedade", valor);
		return query.getResultList();
	}

	@Override
	public <T> List<T> recuperaValores(Class<T> classe, Date start, Date end, String propriedade) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p from ");
		sql.append(classe.getName());
		sql.append(" p where p." + propriedade);
		sql.append("between :dinicio and :dfim");
		Query query = getManager().createQuery(sql.toString());
		query.setParameter("dinicio", start);
		query.setParameter("dfim", end);
		return query.getResultList();
	}

	@Override
	public void refresh(Object entity) {
		getManager().refresh(entity);
	}

	@Override
	public void clear() {
		getManager().clear();
	}

	@Override
	public <T> boolean existeRegistro(Class<T> classe, String propriedade, String valor) {
		boolean encontrouRegistro = false;
		try {
			if (StringUtils.isNotBlank(propriedade) && StringUtils.isNotBlank(valor)) {
				StringBuilder sql = new StringBuilder();
				sql.append("select count(p.id) from ");
				sql.append(classe.getName());
				sql.append(" as p ");
				sql.append(" where p.");
				sql.append(propriedade);
				sql.append(" = :parametro ");
				Query query = getManager().createQuery(sql.toString());
				query.setParameter("parametro", valor);
				Long retorno = (Long) query.getSingleResult();
				if (retorno != null && retorno > 0) {
					encontrouRegistro = true;
				}
			}
		} catch (Exception e) {
			encontrouRegistro = false;
		}
		return encontrouRegistro;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
