package saida;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dados.Doente;
import dados.Paciente;
import dados.Saudavel;
import utilitario.PessoaDAO;

public class Visao {

	public static void mensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
	}

	public static void mostraMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void limpaTela(int linhas) {
		for (int i = 0; i < linhas; i++)
			System.out.println();
	}

	public static void mostrarRelatorioFinal() throws SQLException {
		ArrayList<Paciente> pacientes = new PessoaDAO().listarPessoas();
		DecimalFormat mascara = new DecimalFormat("00");
		String espacos = "                    ";
		int naoConta = 0, contaTrat = 0, contaCurados = 0, mulherContaFalecidas = 0, homensContaFalecidos = 0;

		for (int cont = 0; cont < pacientes.size(); cont++) {
			if (pacientes.get(cont) instanceof Saudavel) {
				naoConta++;
			}
			if (pacientes.get(cont) instanceof Doente) {
				Doente doente = (Doente) pacientes.get(cont);
				if (doente.getEstadoDeSaude() == 'E')
					contaTrat++;
				if (doente.getEstadoDeSaude() == 'C')
					contaCurados++;
				if (doente.getGenero() == 'F' && doente.getEstadoDeSaude() == 'F')
					mulherContaFalecidas++;
				if (doente.getGenero() == 'M' && doente.getEstadoDeSaude() == 'F')
					homensContaFalecidos++;
			}
		}
		limpaTela(30);
		System.out.println(espacos + mascara.format(naoConta) + " = NÃO CONTAMINADAS");
		System.out.println(espacos + mascara.format(contaTrat) + " = CONTAMINADAS EM TRATAMENTO");
		System.out.println(espacos + mascara.format(contaCurados) + " = CONTAMINADAS CURADAS");
		System.out.println(espacos + mascara.format(mulherContaFalecidas) + " = MULHERES CONTAMINADAS FALECIDAS");
		System.out.println(espacos + mascara.format(homensContaFalecidos) + " = HOMENS CONTAMINADOS FALECIDOS");
		System.err.println(espacos + mascara.format(pacientes.size()) + " = TOTAL DE PESSOAS CADASTRADAS");
		limpaTela(5);
	}
}
