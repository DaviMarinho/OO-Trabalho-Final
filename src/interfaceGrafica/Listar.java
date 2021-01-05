package interfaceGrafica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dados.Paciente;
import utilitario.PessoaDAO;
import utilitario.Servicos;

public class Listar extends JFrame {

	private static final long serialVersionUID = 425840095344531344L;

	public Listar() throws SQLException {
		JFrame lista = new JFrame();
		String coluna[] = { "ID", "NOME", "GENERO", "IDADE", "SAUDE" };

		ArrayList<Paciente> pessoas = new PessoaDAO().listarPessoas();
		String[][] pessoasStr = Servicos.converterArrayPessoas(pessoas);
		JTable tabela = new JTable(pessoasStr, coluna);
		tabela.setBounds(30, 40, 200, 300);
		JScrollPane painel = new JScrollPane(tabela);
		lista.add(painel);
		lista.setSize(600, 600);
		lista.setVisible(true);
		lista.setLocationRelativeTo(null);
	}
}
