package br.com.appweb.types;

public enum Pagina {
	
	DASHBOARD("dashboard", true),
	PESSOAMAN("pessoaman", true),
	PESSOACON("pessoacon", true),
	FUNCIONARIOMAN("funcionarioman", false),
	FUNCIONARIOCON("funcionariocon", false),
	PRODUTOMAN("produtoman", true),
	PRODUTOCON("produtocon", true),
	ENTRADAPRODUTOMAN("entradaprodutoman", true),
	USUARIOMAN("usuarioman", false),
	USUARIOCON("usuariocon", false);
	
	private String pagina;
	private boolean permissao; 
	
	private Pagina(String pagina, boolean permissao) {
		this.pagina = pagina;
		this.permissao = permissao;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
	public boolean isPermissao() {
		return permissao;
	}

	public void setPermissao(boolean permissao) {
		this.permissao = permissao;
	}

	public static boolean ehPaginaMenu(String pagina) {
		for(Pagina umaPagina : Pagina.values()) {
			if (umaPagina.pagina.equals(pagina)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean usuarioTemPermissao(String pagina) {
		for(Pagina umaPagina : Pagina.values()) {
			if (umaPagina.pagina.equals(pagina)) {
				if (umaPagina.isPermissao()) {
					return true;
				}
			}
		}
		return false;
	}
	
}
