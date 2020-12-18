package br.com.appweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.appweb.types.TipoPessoa;

@Entity
@Table(name = "TB_PESSOA")
//@SequenceGenerator(name = "PES_SEQ", sequenceName = "TB_PESSOA_SEC", initialValue = 1, allocationSize = 1)
public class PessoaEntity extends AppBaseEntity implements Serializable {

	private static final long serialVersionUID = 1503752441249011654L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PES_ID")
	private Long id;

	@Column(name = "PES_NOME", length = 100)
	private String nome;

	@Column(name = "PES_CPF", length = 14)
	private String cpf;

	@Column(name = "PES_CNPJ", length = 20)
	private String cnpj;

	@Column(name = "PES_TELEFONE", length = 20)
	private String telefone;

	@Column(name = "PES_CELULAR", length = 20)
	private String celular;

	@Column(name = "PES_EMAIL", length = 100)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "PES_TIPOPESSOA", length = 15)
	private TipoPessoa tipoPessoa = TipoPessoa.PESSOAFISICA;

	public PessoaEntity() {
	}
	
	public PessoaEntity(Long id) {
		this.id =  id;
	}

	public PessoaEntity(Long id, String nome, String cpf, String cnpj, String telefone, String celular, String email,
			TipoPessoa tipoPessoa) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.tipoPessoa = tipoPessoa;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public TipoPessoa getTipoPessoa() {
		if (tipoPessoa == null) {
			return TipoPessoa.PESSOAFISICA;
		}
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaEntity other = (PessoaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
