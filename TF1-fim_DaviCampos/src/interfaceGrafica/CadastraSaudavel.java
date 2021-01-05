package interfaceGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import saida.Visao;
import validacao.PessoaBO;

public class CadastraSaudavel extends JFrame {

	private static final long serialVersionUID = -6766775048028473139L;
	private JPanel cadastraSaudavel;
	private JTextField txtNome;
	private JTextField txtIdade;
	private ButtonGroup sexos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraSaudavel frame = new CadastraSaudavel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastraSaudavel() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		cadastraSaudavel = new JPanel();
		cadastraSaudavel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastraSaudavel);
		cadastraSaudavel.setLayout(null);

		JLabel textoInformativo = new JLabel("Preencha os dados do paciente:");
		textoInformativo.setBounds(40, 10, 249, 15);
		cadastraSaudavel.add(textoInformativo);
		
		JLabel nome = new JLabel("Nome:");
		nome.setBounds(40, 60, 70, 15);
		cadastraSaudavel.add(nome);

		JLabel sexo = new JLabel("Sexo:");
		sexo.setBounds(40, 100, 70, 15);
		cadastraSaudavel.add(sexo);

		JLabel idade = new JLabel("Idade:");
		idade.setBounds(40, 132, 70, 34);
		cadastraSaudavel.add(idade);
		
		txtNome = new JTextField();
		txtNome.setBounds(40, 75, 298, 19);
		cadastraSaudavel.add(txtNome);
		txtNome.setColumns(10);

		JRadioButton masculino = new JRadioButton("Masculino");
		masculino.setBounds(40, 115, 149, 23);
		cadastraSaudavel.add(masculino);

		JRadioButton feminino = new JRadioButton("Feminino");
		feminino.setBounds(200, 115, 149, 23);
		cadastraSaudavel.add(feminino);

		
		sexos = new ButtonGroup();
		
		sexos.add(masculino);
		sexos.add(feminino);

		txtIdade = new JTextField();
		txtIdade.setBounds(40, 161, 114, 19);
		cadastraSaudavel.add(txtIdade);
		txtIdade.setColumns(10);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(40, 205, 117, 25);
		cadastraSaudavel.add(btnCancelar);

		JButton confirmar = new JButton("CONFIRMAR");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PessoaBO.salvarPessoaSaudavel(txtNome.getText().trim(), masculino, feminino,
							txtIdade.getText());
					Visao.mensagem("Cadastro efetuado com sucesso", "CADASTRO");
					dispose();
				} catch (Exception e) {
					Visao.mostraMensagemErro("Ocorreu um erro: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		confirmar.setBounds(221, 205, 117, 25);
		cadastraSaudavel.add(confirmar);
	}
}
