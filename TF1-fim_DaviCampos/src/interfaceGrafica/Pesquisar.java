package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dados.Paciente;
import saida.Visao;
import utilitario.PessoaDAO;
import utilitario.Servicos;
import validacao.PessoaBO;

public class Pesquisar extends JFrame {

	private static final long serialVersionUID = 7975455627986520301L;
	private JPanel pesquisar;
	private JTextField nome;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisar frame = new Pesquisar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pesquisar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		pesquisar = new JPanel();
		pesquisar.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pesquisar);
		pesquisar.setLayout(null);

		JButton cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelar.setBounds(40, 205, 117, 25);
		pesquisar.add(cancelar);

		nome = new JTextField();
		nome.setBounds(40, 60, 361, 19);
		pesquisar.add(nome);
		nome.setColumns(10);

		JLabel lbldigiteONome = new JLabel("Digite o nome ou parte do nome da pessoa");
		lbldigiteONome.setBounds(42, 15, 448, 15);
		pesquisar.add(lbldigiteONome);

		JLabel lblPessoaQueDeseja = new JLabel("que deseja procurar:");
		lblPessoaQueDeseja.setBounds(42, 35, 238, 15);
		pesquisar.add(lblPessoaQueDeseja);

		JButton confirmar = new JButton("CONFIRMAR");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						if (!PessoaBO.validaNome(nome.getText(), 1)) 
							throw new Exception("Nome deve ser preenchido e conter entre " + 1 + " e 90 caracteres");
						criaTabela(nome.getText());
						dispose();
					} catch (Exception e) {
						Visao.mostraMensagemErro("Ocorreu um erro:" + e.getMessage());
						e.printStackTrace();
					}
			}			
		});
		confirmar.setBounds(284, 205, 117, 25);
		pesquisar.add(confirmar);

	}

	private void criaTabela(String nomeValido) throws SQLException {
		JFrame lista = new JFrame();
		String coluna[] = { "ID", "NOME", "GENERO", "IDADE", "SAUDE" };

		ArrayList<Paciente> pessoas = new PessoaDAO().pesquisarPessoas(nomeValido);
		if (pessoas.size() == 0) {
			Visao.mostraMensagemErro("Não existe ninguém com esse nome cadastrado!");
		} else {
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
}
