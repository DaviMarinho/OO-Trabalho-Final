package validacao;

import javax.swing.JRadioButton;

import dados.Doente;
import dados.Saudavel;
import utilitario.PessoaDAO;


public class PessoaBO {

	public final static int MAXIMO = 130;
	public final static int MINIMO_NOME = 3;

	public static int validaInt(String recebido) {
		int valor = 0;

		try {
			valor = Integer.parseInt(recebido);
		} catch (NumberFormatException e) {
			valor = -1;
		}

		if (valor < 0) 
			valor = -1;
		
		return valor;
	}
	
	public static boolean validaNome(String nome, int tamanho) {
		boolean cadastro = true;

		if (nome.isEmpty() || nome.length() < tamanho || nome.length() > 90)
			cadastro = false;

		return cadastro;
	}
	
	public static boolean validaSexo(char sexo) {
		if (sexo == ' ')  {
			return false;
		}
		if (sexo != 'M' && sexo != 'F') {
			return false;
		}
		return true;
	}

	public static boolean validaIdade(String idadeTexto) {
		int idade;
		try {
			idade = Integer.parseInt(idadeTexto);
		} catch (NumberFormatException e) {
			return false;
		}
		if (idade < 0 || idade > MAXIMO) {
			return false;
		}
		return true;
	}
	
	public static boolean validaSaude(char estadoSaude) {
		if (estadoSaude == ' ')  {
			return false;
		}
		if (estadoSaude != 'E' && estadoSaude != 'F' && estadoSaude != 'C') {
			return false;
		}
		return true;
	}

	public static boolean salvarPessoaDoente(String nome, JRadioButton masculino, JRadioButton feminino,
			JRadioButton tratamento, JRadioButton falecido, JRadioButton curado) throws Exception {
		boolean cadastro = false;
		int identificador = 0;
		char sexo = 0;
		char situacaoSaude = 0;
		StringBuilder nomeCompleto = new StringBuilder();

		if (masculino.isSelected())
			sexo = 'M';
		if (feminino.isSelected())
			sexo = 'F';
		if (tratamento.isSelected())
			situacaoSaude = 'E';
		if (falecido.isSelected())
			situacaoSaude = 'F';
		if (curado.isSelected())
			situacaoSaude = 'C';
		
		if (!validaNome(nome, MINIMO_NOME)) 
			throw new Exception("Nome deve ser preenchido e conter entre " + MINIMO_NOME + " e 90 caracteres");
		
		if (!validaSexo(sexo)) 
			throw new Exception("Sexo deve ser selecionado");
		
		if(!validaSaude(situacaoSaude))
			throw new Exception("Situação de saúde deve ser selecionados");
			
			nomeCompleto.append(nome);
			
			Doente doente = new Doente(identificador, nomeCompleto, sexo, situacaoSaude);
			new PessoaDAO().insereBanco(doente);

			cadastro = true;
			return cadastro;
	}

	public static void salvarPessoaSaudavel(String nome, JRadioButton masculino, JRadioButton feminino,
			String idadeRecebido) throws Exception {

		int identificador = 0;
		char sexo = 0;
		int idade = 0;
		StringBuilder nomeCompleto = new StringBuilder();

		idade = validaInt(idadeRecebido);
		if (masculino.isSelected())
			sexo = 'M';
		if (feminino.isSelected())
			sexo = 'F';

		if (!validaNome(nome, MINIMO_NOME)) 
			throw new Exception("Nome deve ser preenchido e conter entre " + MINIMO_NOME + " e 90 caracteres");
		
		if (!validaSexo(sexo)) 
			throw new Exception("Sexo deve ser selecionado");
		
		if (!validaIdade(idadeRecebido)) 
			throw new Exception("Idade de ser um valor inteiro entre 0 e " + MAXIMO);
		
		
		idade = Integer.parseInt(idadeRecebido);
		nomeCompleto.append(nome);
		Saudavel saudavel = new Saudavel(identificador, nomeCompleto, sexo, idade);
		new PessoaDAO().insereBanco(saudavel);

	}
}
