package br.com.appweb.comum;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import br.com.appweb.entity.UsuarioEntity;

public class SessionUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public SessionUtil() {}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	public static UsuarioEntity getUsuario() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
			return usuario !=null ? usuario :null;
		} catch (Exception e) {
			return null;
		}
	}

	public static HttpSession getSession() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return sessao;
	}
	
	public static void setUsuario(UsuarioEntity usuario) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("usuario", usuario);
	}
	
	public static void setParam(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getParam(String key) {
		return getSession().getAttribute(key);
	}

	public static void removeSessao(String key) {
		getSession().removeAttribute(key);
	}

	public static void invalidate() {
		getSession().invalidate();
	}
	
}
