package br.com.appweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "TB_PEDIDO")
@Entity
//@SequenceGenerator(name = "PED_SEQ", sequenceName = "TB_PEDIDO_SEC", initialValue = 1, allocationSize = 1)
public class PedidoEntity extends AppBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PED_ID")
	private Long id;

	@Column(name = "PED_VALOR_TOTAL")
	private BigDecimal valorTotal;

	@ManyToOne(targetEntity = PessoaEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PED_PESID")
	private PessoaEntity pessoa;

	@OneToMany(targetEntity = ItemPedidoEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pedido")
	private List<ItemPedidoEntity> pedidos;
	
	public PedidoEntity() {
	}
	
	public PedidoEntity(Long id) {
		this.id = id;
	}

	public PedidoEntity(Long id, BigDecimal valorTotal, PessoaEntity pessoa, List<ItemPedidoEntity> pedidos) {
		super();
		this.id = id;
		this.valorTotal = valorTotal;
		this.pessoa = pessoa;
		this.pedidos = pedidos;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public List<ItemPedidoEntity> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<ItemPedidoEntity> pedidos) {
		this.pedidos = pedidos;
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
		PedidoEntity other = (PedidoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
