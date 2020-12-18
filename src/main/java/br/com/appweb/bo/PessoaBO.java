package br.com.appweb.bo;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import br.com.appweb.dao.PessoaDAO;
import br.com.appweb.entity.PessoaEntity;
import br.com.appweb.types.TipoPessoa;

@ApplicationScoped
public class PessoaBO implements Serializable {

	private static final long serialVersionUID = 3609128568971127754L;

	@Inject
	private PessoaDAO pessoaDAO;

	public void grava(PessoaEntity pessoa) throws Exception {
		if (StringUtils.isNotBlank(pessoa.getCpf()) || StringUtils.isNotBlank(pessoa.getCnpj())) {
			String cpfCpng = StringUtils.isNotBlank(pessoa.getCpf()) ? pessoa.getCpf() : pessoa.getCnpj();
			String isCpfOuCpng = StringUtils.isNotBlank(pessoa.getCpf()) ? "cpf" : "cnpj";
			boolean existePessoa = pessoaDAO.existeRegistro(PessoaEntity.class, cpfCpng, isCpfOuCpng);
			if (existePessoa) {
				if (pessoa.getId() == null) {
					if (pessoa.getTipoPessoa().equals(TipoPessoa.PESSOAFISICA)) {
						throw new Exception("Cpf já existe.");
					} else {
						throw new Exception("Cnpj já existe.");
					}
				}
			}
		}
		pessoaDAO.merge(pessoa);
	}

	public List<PessoaEntity> recuperaPessoas(String valor) {
		if (StringUtils.isNumeric(valor)) {
			return pessoaDAO.recuperaValores(PessoaEntity.class, Long.valueOf(valor), "id");
		}
		return pessoaDAO.recuperaValores(PessoaEntity.class, valor, "nome");
	}

}
