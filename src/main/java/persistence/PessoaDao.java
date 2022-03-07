package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import model.Pessoa;

public class PessoaDao implements IPessoaDao {

	private GenericDao gDao;

	public PessoaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String iudPessoa(String op, Pessoa p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "{CALL sp_pessoa (?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, op);
		cs.setInt(2, p.getId());
		cs.setString(3, p.getNome());
		cs.setString(4, p.getTelFixo());
		cs.setString(5, p.getTelCelular());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		cs.close();
		c.close();
		
		return saida;
	}

}
