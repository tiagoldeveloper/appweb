package br.com.appweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.appweb.types.Role;

@Entity
@Table(name = "TB_USUARIO")
//@SequenceGenerator(name = "USU_SEQ", sequenceName = "TB_USUARIO_SEC", initialValue = 1, allocationSize = 1)
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = -2094929034363252346L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USU_ID")
	private Long id;

	@Column(name = "USU_NOME", length = 100)
	private String nome;

	@Column(name = "USU_SENHA", length = 100)
	private String senha;

	@Column(name = "USU_ROLE", length = 11)
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne(targetEntity = PessoaEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "USU_PESID")
	private PessoaEntity pessoa;

	public UsuarioEntity() {
	}

	public UsuarioEntity(Long id) {
		this.id = id;
	}

	public UsuarioEntity(Long id, String nome, String senha, Role role, PessoaEntity pessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.role = role;
		this.pessoa = pessoa;
	}

	public boolean isAdmin() {
		return Role.ADMIN.equals(role);
	}

	public boolean isFuncionario() {
		return Role.FUNCIONARIO.equals(role);
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
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
		UsuarioEntity other = (UsuarioEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
