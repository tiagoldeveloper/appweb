package br.com.appweb.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.appweb.entity.UsuarioEntity;
import br.com.appweb.types.Pagina;

@WebFilter(urlPatterns = { "*.xhtml", "*.jsf" }) 
public class AppFiltro implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		controle(request, response, chain);
	}

	@Override
	public void destroy() {
	}

	private void controle(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
		String pagina = replacePagina(req.getServletPath());

		if (usuario == null && Pagina.ehPaginaMenu(pagina)) {
			redireciona("/appweb/login.xhtml", response);
		} else {
			if (Pagina.ehPaginaMenu(pagina)) {
				if (usuario != null && usuario.isFuncionario() && !Pagina.usuarioTemPermissao((pagina))) {
					redireciona("/appweb/dashboard.xhtml", response);
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	private static String replacePagina(String pagina) {
		String paginaStr = "";
		if (pagina.contains(".xhtml")) {
			paginaStr = pagina.replace("/", "").replace(".xhtml", "");
		} else if (pagina.contains(".jsf")) {
			paginaStr = pagina.replace("/", "").replace(".jsf", "");
		}
		return paginaStr;
	}

	private void redireciona(String url, ServletResponse response) throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}

}
