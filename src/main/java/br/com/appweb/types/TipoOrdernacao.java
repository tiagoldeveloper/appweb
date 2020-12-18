package br.com.appweb.types;

public enum TipoOrdernacao {

	ASC, DESC;

	
	public boolean isAscOrder() {
		return ASC.equals(this);
	}
}