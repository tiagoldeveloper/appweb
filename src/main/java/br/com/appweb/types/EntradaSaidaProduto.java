package br.com.appweb.types;

public enum EntradaSaidaProduto {

	ENTRADA("Entrada"), 
	SAIDA("Saida");

	private String label;

	private EntradaSaidaProduto(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
