package utilitario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Doente;
import dados.Paciente;
import dados.Saudavel;
import saida.Visao;
import validacao.PessoaBO;

public class Servicos {
	public static String[][] converterArrayPessoas(ArrayList<Paciente> pessoasRecebidas) {

		int i = pessoasRecebidas.size();
		String[][] pessoa = new String[i][5];
		for (int j = 0; j < i; j++) {
			pessoa[j][0] = pessoasRecebidas.get(j).getIdentificador().toString();
			pessoa[j][1] = pessoasRecebidas.get(j).getNomeCompleto().toString().toUpperCase();
			if (pessoasRecebidas.get(j).getGenero() == 'M')
				pessoa[j][2] = "MASCULINO";
			if (pessoasRecebidas.get(j).getGenero() == 'F')
				pessoa[j][2] = "FEMININO";
			if (pessoasRecebidas.get(j) instanceof Saudavel) {
				Saudavel saudavel = (Saudavel) pessoasRecebidas.get(j);
				pessoa[j][3] = saudavel.getIdade().toString();
				pessoa[j][4] = "";
			}
			if (pessoasRecebidas.get(j) instanceof Doente) {
				Doente doente = (Doente) pessoasRecebidas.get(j);
				pessoa[j][3] = "";
				if (doente.getEstadoDeSaude() == 'C')
					pessoa[j][4] = "CURADO";
				if (doente.getEstadoDeSaude() == 'E')
					pessoa[j][4] = "EM TRATAMENTO";
				if (doente.getEstadoDeSaude() == 'F')
					pessoa[j][4] = "FALECIDO";
			}
		}
		return pessoa;
	}

	public static Paciente retornaPessoa(ResultSet rs) throws SQLException {

		if (rs.getString("saude") == null) {
			Saudavel pessoa = new Saudavel(rs.getInt("idPessoa"), 
										   new StringBuilder(rs.getString("nome")),
										   rs.getString("genero").charAt(0), 
										   rs.getInt("idade"));
			return pessoa;
		} else {
			Doente pessoa = new Doente(rs.getInt("idPessoa"), 
									   new StringBuilder(rs.getString("nome")),
									   rs.getString("genero").charAt(0), 
									   rs.getString("saude").charAt(0));
			return pessoa;
		}
	}

	public static boolean consultaPessoa(String idProcurado) throws SQLException {
		boolean consulta = true;
		String genero = "", saude = "";
		int idValido = 0;
		idValido = PessoaBO.validaInt(idProcurado);
		Paciente pessoa = new PessoaDAO().consultaId(idValido);
		if (pessoa != null) {
			if (pessoa instanceof Doente) {
				Doente doente = (Doente) pessoa;
				if (doente.getGenero() == 'M')
					genero = "Masculino";
				if (doente.getGenero() == 'F')
					genero = "Feminino";
				if (doente.getEstadoDeSaude() == 'C')
					saude = "Curado";
				if (doente.getEstadoDeSaude() == 'E')
					saude = "Em tratamento";
				if (doente.getEstadoDeSaude() == 'F')
					saude = "Falecido";
				Visao.mensagem(
						"Nome: " + doente.getNomeCompleto() + "\nGenero: " + genero + "\nEstado de Saude: " + saude,
						"CONSULTA");
			}
			if (pessoa instanceof Saudavel) {
				Saudavel saudavel = (Saudavel) pessoa;
				if (saudavel.getGenero() == 'M')
					genero = "Masculino";
				if (saudavel.getGenero() == 'F')
					genero = "Feminino";
				Visao.mensagem("Nome: " + saudavel.getNomeCompleto() + "\nGenero: " + genero + "\nIdade: "
						+ saudavel.getIdade(), "CONSULTA");
			}
		} else {
			consulta = false;
		}
		return consulta;
	}
}
