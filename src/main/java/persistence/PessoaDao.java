package persistence;

import java.sql.SQLException;

import model.Pessoa;

public class PessoaDao implements IPessoaDao {

	private GenericDao gDao;

	public PessoaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String iudPessoa(String op, Pessoa p) throws SQLException, ClassNotFoundException {
		return null;
	}

}
