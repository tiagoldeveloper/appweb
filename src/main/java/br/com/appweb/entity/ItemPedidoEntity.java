package br.com.appweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "TB_ITEM_PEDIDO")
@Entity
//@SequenceGenerator(name = "IPE_SEQ", sequenceName = "TB_ITEM_PEDIDO_SEC", initialValue = 1, allocationSize = 1)
public class ItemPedidoEntity extends AppBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IPE_ID")
	private Long id;

	@Column(name = "IPE_DESCRICAO", length = 100)
	private String descricao;

	@Column(name = "IPE_QUANTIDADE")
	private Integer quantidade;

	@Column(name = "IPE_VALOR")
	private BigDecimal valor;

	@Column(name = "IPE_DESCONTO")
	private BigDecimal desconto;

	@ManyToOne(targetEntity = ProdutoEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name =  "IPE_PROID")
	private ProdutoEntity produto;
	
	@ManyToOne(targetEntity = PedidoEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "IPE_PEDID")
	private PedidoEntity pedido;

	public ItemPedidoEntity() {
	}
	
	public ItemPedidoEntity(Long id) {
		this.id = id;
	}

	public ItemPedidoEntity(Long id, String descricao, Integer quantidade, BigDecimal valor, BigDecimal desconto,
			ProdutoEntity produto, PedidoEntity pedido) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.desconto = desconto;
		this.produto = produto;
		this.pedido = pedido;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
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
		ItemPedidoEntity other = (ItemPedidoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

}
