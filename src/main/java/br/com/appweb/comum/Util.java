package br.com.appweb.comum;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class Util {
	
	public static void redirecionarPagina(String pagina) {
		FacesContext context = FacesContext.getCurrentInstance();
		String url = context.getExternalContext().getRequestContextPath();
		try {
			context.getExternalContext().redirect(url + "/" + pagina);
		} catch (IOException ex) {
			Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void redireciona(String url, ServletResponse response)
			throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}
}
