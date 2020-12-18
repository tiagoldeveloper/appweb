package br.com.appweb.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import br.com.appweb.entity.UsuarioEntity;

public class UsuarioDAO extends AppDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public UsuarioEntity logar(UsuarioEntity usuario) {
		UsuarioEntity usuarioLogado = null;
		try {
			String sql = "select p from UsuarioEntity p where p.nome =:nome and p.senha =:senha";
			Query query = getManager().createQuery(sql);  
			query.setParameter("nome", usuario.getNome());
			query.setParameter("senha", usuario.getSenha());
			usuarioLogado = (UsuarioEntity) query.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
		return usuarioLogado;
	}
	
	public List<UsuarioEntity> recuperaUsuarios(String valor) {
		StringBuilder sql = new StringBuilder();
		sql.append("select usu from UsuarioEntity usu ");
		sql.append(" inner join usu.pessoa pes ");

		if (StringUtils.isNotBlank(valor) && !StringUtils.isNumeric(valor)) {
			sql.append(" where upper (usu.login) like :login ");
		}

		if (StringUtils.isNotBlank(valor) && StringUtils.isNumeric(valor)) {
			sql.append(" where usu.id = :id ");
		}

		Query query = getManager().createQuery(sql.toString());

		if (StringUtils.isNotBlank(valor) && !StringUtils.isNumeric(valor)) {
			query.setParameter("login", "%" + valor.toUpperCase() + "%");
		}

		if (StringUtils.isNotBlank(valor) && StringUtils.isNumeric(valor)) {
			query.setParameter("id", Long.valueOf(valor));
		}

		return query.getResultList();
	}


}
