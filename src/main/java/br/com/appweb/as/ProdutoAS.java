package br.com.appweb.as;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import br.com.appweb.dao.ProdutoDAO;
import br.com.appweb.entity.ProdutoEntity;
import br.com.appweb.types.EntradaSaidaProduto;

@ApplicationScoped
public class ProdutoAS implements Serializable {

	private static final long serialVersionUID = -2096894550279202049L;

	@Inject
	private ProdutoDAO produtoDAO;

	public void entradaSaidaProduto(ProdutoEntity produto, EntradaSaidaProduto entradaSaidaProduto) {
		if (EntradaSaidaProduto.ENTRADA.equals(entradaSaidaProduto)) {
			produtoDAO.entradaProduto(produto);
		}
		if (EntradaSaidaProduto.SAIDA.equals(entradaSaidaProduto)) {
			produtoDAO.saidaProduto(produto);
		}
	}

	public List<ProdutoEntity> recuperaProdutos(String valor) {
		if (StringUtils.isNumeric(valor)) {
			return produtoDAO.recuperaValores(ProdutoEntity.class, Long.valueOf(valor), "id");
		}
		return produtoDAO.recuperaValores(ProdutoEntity.class, valor, "descricao");
	}

}