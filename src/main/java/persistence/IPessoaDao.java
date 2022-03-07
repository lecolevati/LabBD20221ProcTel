package persistence;

import java.sql.SQLException;

import model.Pessoa;

public interface IPessoaDao {
	
	public String iudPessoa(String op, Pessoa p) throws SQLException, ClassNotFoundException;

}
