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

public class CadastrarDoente extends JFrame {

	private static final long serialVersionUID = -5814764459412123434L;
	private JPanel cadastraDoente;
	private JTextField txtNome;
	private ButtonGroup sexos, situacoesSaude;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarDoente frame = new CadastrarDoente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastrarDoente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		cadastraDoente = new JPanel();
		cadastraDoente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastraDoente);
		cadastraDoente.setLayout(null);

		JLabel textoInformativo = new JLabel("Preencha os dados do paciente:");
		textoInformativo.setBounds(40, 10, 249, 15);
		cadastraDoente.add(textoInformativo);
		
		txtNome = new JTextField();
		txtNome.setBounds(40, 60, 298, 19);
		cadastraDoente.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel nome = new JLabel("Nome:");
		nome.setBounds(40, 40, 70, 15);
		cadastraDoente.add(nome);

		JLabel sexo = new JLabel("Sexo:");
		sexo.setBounds(40, 85, 70, 15);
		cadastraDoente.add(sexo);

		sexos = new ButtonGroup();

		JRadioButton masculino = new JRadioButton("Masculino");
		masculino.setBounds(40, 105, 149, 23);
		cadastraDoente.add(masculino);

		JRadioButton feminino = new JRadioButton("Feminino");
		feminino.setBounds(200, 105, 149, 23);
		cadastraDoente.add(feminino);

		sexos.add(masculino);
		sexos.add(feminino);

		situacoesSaude = new ButtonGroup();

		JLabel situacaoDeSaude = new JLabel("Situação de Saúde:");
		situacaoDeSaude.setBounds(40, 116, 164, 47);
		cadastraDoente.add(situacaoDeSaude);

		JRadioButton tratamento = new JRadioButton("Em tratamento");
		tratamento.setBounds(40, 147, 149, 23);
		cadastraDoente.add(tratamento);

		JRadioButton falecido = new JRadioButton("Falecido");
		falecido.setBounds(200, 147, 149, 23);
		cadastraDoente.add(falecido);

		JRadioButton curado = new JRadioButton("Curado");
		curado.setBounds(40, 174, 149, 23);
		cadastraDoente.add(curado);

		situacoesSaude.add(tratamento);
		situacoesSaude.add(falecido);
		situacoesSaude.add(curado);

		JButton cancelar = new JButton("CANCELAR");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelar.setBounds(40, 205, 117, 25);
		cadastraDoente.add(cancelar);

		JButton confirmar = new JButton("CONFIRMAR");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PessoaBO.salvarPessoaDoente(txtNome.getText().trim(), masculino, feminino, tratamento,
							falecido, curado);
						Visao.mensagem("Cadastro efetuado com sucesso", "CADASTRO");
						dispose();
				} catch (Exception e) {
					Visao.mostraMensagemErro("Ocorreu um erro: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		confirmar.setBounds(221, 205, 117, 25);
		cadastraDoente.add(confirmar);
	}
}
