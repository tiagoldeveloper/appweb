package br.com.appweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO")
//@SequenceGenerator(name = "PRO_SEQ", sequenceName = "TB_PRODUTO_SEC", initialValue = 1, allocationSize = 1)
public class ProdutoEntity extends AppBaseEntity implements Serializable {

	private static final long serialVersionUID = 1503752441249011654L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRO_ID")
	private Long id;

	@Column(name = "PRO_DESCRICAO", length = 100)
	private String descricao;

	@Column(name = "PRO_QUANTIDADE")
	private Integer quantidade;

	@Column(name = "PRO_PRECO")
	private BigDecimal preco;

	@Column(name = "PRO_DATA")
	private Date data = Calendar.getInstance().getTime();

	public ProdutoEntity() {
	}
	
	public ProdutoEntity(Long id) {
		this.id =  id;
	}

	public ProdutoEntity(Long id, String descricao, Integer quantidade, Date data) {
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.data = data;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		ProdutoEntity other = (ProdutoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
