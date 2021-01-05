package interfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import saida.Visao;

public class Cadastro extends JFrame {
	
	private static final long serialVersionUID = -9170444077501901955L;
	public static JPanel cadastro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro painel = new Cadastro();
					painel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		cadastro = new JPanel();
		cadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastro);
		cadastro.setLayout(null);

		JButton cadastrarSaudavel = new JButton("Cadastrar Saudavel");
		cadastrarSaudavel.setBounds(12, 187, 187, 25);
		cadastro.add(cadastrarSaudavel);
		cadastrarSaudavel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					new CadastraSaudavel().setVisible(true);
				} catch (Exception e) {
					Visao.mostraMensagemErro("Ocorreu um erro:" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

		JButton cadastrarDoente = new JButton("Cadastrar Doente");
		cadastrarDoente.setBounds(251, 187, 187, 25);
		cadastro.add(cadastrarDoente);
		cadastrarDoente.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					new CadastrarDoente().setVisible(true);
				} catch (Exception e) {
					Visao.mostraMensagemErro("Ocorreu um erro:" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

		JLabel selecioneAOpcao = new JLabel("Selecione a opcao que deseja cadastrar:");
		selecioneAOpcao.setBounds(76, 48, 298, 15);
		cadastro.add(selecioneAOpcao);

		JButton voltar = new JButton("Voltar");
		voltar.setBounds(171, 224, 117, 25);
		cadastro.add(voltar);
		voltar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		});
	}
}
