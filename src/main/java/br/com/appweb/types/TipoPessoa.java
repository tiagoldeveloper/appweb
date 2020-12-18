package br.com.appweb.types;

public enum TipoPessoa {

	PESSOAFISICA("Pessoa fisica"),
	PESSOAJURIDICA("Pessoa juridica"),
	FUNCIONARIO("Funcionário");

	private String label;

	private TipoPessoa(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
