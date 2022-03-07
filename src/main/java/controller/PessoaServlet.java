package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;
import persistence.GenericDao;
import persistence.PessoaDao;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PessoaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String telFixo = request.getParameter("telFixo");
		String telCelular = request.getParameter("telCelular");
		String botao = request.getParameter("botao");
		String op = botao.substring(0, 1);
		if (op.equals("A")) {
			op = "U";
		}
		
		Pessoa p = new Pessoa();
		p = validaPessoa(id, nome, telFixo, telCelular);
		String erro = "";
		String saida = "";
		try {
			if (p == null) {
				erro = "Preencha os campos corretamente";
			} else {
				GenericDao gDao = new GenericDao();
				PessoaDao pDao = new PessoaDao(gDao);
				saida = pDao.iudPessoa(op, p);
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			rd.forward(request, response);
		}
	}

	private Pessoa validaPessoa(String id, String nome, String telFixo, String telCelular) {
		Pessoa p = new Pessoa();
		if (id.equals("") || nome.equals("") || telFixo.equals("") || telCelular.equals("")) {
			p = null;
		} else {
			p.setId(Integer.parseInt(id));
			p.setNome(nome);
			p.setTelFixo(telFixo);
			p.setTelCelular(telCelular);
		}
		return p;
	}

}
