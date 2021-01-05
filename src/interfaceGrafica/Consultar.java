package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import saida.Visao;
import utilitario.Servicos;

public class Consultar extends JFrame {

	private static final long serialVersionUID = -6289764672800073985L;
	private JPanel consultar;
	private JTextField idProcurado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consultar frame = new Consultar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Consultar() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		consultar = new JPanel();
		consultar.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(consultar);
		consultar.setLayout(null);

		JLabel texto = new JLabel("Digite o id da pessoa que você deseja pesquisar:");
		texto.setBounds(38, 25, 400, 46);
		consultar.add(texto);

		idProcurado = new JTextField();
		idProcurado.setBounds(38, 63, 114, 19);
		consultar.add(idProcurado);
		idProcurado.setColumns(10);

		JButton cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelar.setBounds(40, 205, 117, 25);
		consultar.add(cancelar);

		JButton confirmar = new JButton("CONFIRMAR");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!Servicos.consultaPessoa(idProcurado.getText()))
						throw new Exception("o id digitado deve ser um inteiro valido e já existir no sistema");
					confirmar.setVisible(false);
					dispose();
				} catch (Exception e) {
					Visao.mostraMensagemErro("Ocorreu um erro: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		confirmar.setBounds(221, 205, 117, 25);
		consultar.add(confirmar);
	}
}
