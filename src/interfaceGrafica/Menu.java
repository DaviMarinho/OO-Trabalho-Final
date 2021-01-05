package interfaceGrafica;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import saida.Visao;

public class Menu extends JFrame {

	private static final long serialVersionUID = -6927269310739569460L;
	private JPanel menu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		menu = new JPanel();
		menu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(menu);
		menu.setLayout(null);

		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(163, 60, 117, 25);
		menu.add(cadastrar);
		cadastrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Cadastro().setVisible(true);
			}
		});

		JButton listar = new JButton("Listar");
		listar.setBounds(163, 100, 117, 25);
		menu.add(listar);
		listar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					new Listar().setVisible(true);
				} catch (SQLException e) {
					Visao.mostraMensagemErro("Ocorreu um erro:" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

		JButton consultar = new JButton("Consultar");
		consultar.setBounds(163, 140, 117, 25);
		menu.add(consultar);
		consultar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					new Consultar().setVisible(true);
				} catch (SQLException e) {
					Visao.mostraMensagemErro("Ocorreu um erro:" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

		JButton pesquisar = new JButton("Pesquisar");
		pesquisar.setBounds(163, 180, 117, 25);
		menu.add(pesquisar);
		pesquisar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Pesquisar().setVisible(true);
			}
		});

		JButton encerrar = new JButton("Encerrar");
		encerrar.setBounds(163, 220, 117, 25);
		menu.add(encerrar);
		encerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Visao.mensagem("Obrigado por utilizar meu programa, volte sempre!", "OBRIGADO");
				try {
					Visao.mostrarRelatorioFinal();
					System.exit(0);
				} catch (SQLException e) {
					Visao.mostraMensagemErro("Ocorreu um erro:" + e.getMessage());
					e.printStackTrace();
				}
			}
		});

		JLabel texto = new JLabel("Por favor clique na opção que deseja executar:");
		texto.setBounds(60, 12, 360, 25);
		menu.add(texto);
	}
}
