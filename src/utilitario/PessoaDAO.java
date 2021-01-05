package utilitario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Doente;
import dados.Paciente;
import dados.Saudavel;

public class PessoaDAO {

	public void insereBanco(Paciente pessoa) throws SQLException {

		Connection con = null;
		PreparedStatement stm = null;
		StringBuilder sql = new StringBuilder("INSERT pessoa (nome, genero, saude, idade) VALUES (?,?,?,?)");
		int i = 1;

		try {
			con = Conexao.getConnection();
			stm = con.prepareStatement(sql.toString());
			stm.setString(i++, pessoa.getNomeCompleto().toString());
			stm.setString(i++, pessoa.getGenero().toString());
			if (pessoa instanceof Doente) {
				Doente doente = (Doente) pessoa;
				stm.setString(i++, doente.getEstadoDeSaude().toString());
				stm.setNull(i++, java.sql.Types.INTEGER);
			} else {
				Saudavel saudavel = (Saudavel) pessoa;
				stm.setNull(i++, java.sql.Types.VARCHAR);
				stm.setInt(i++, saudavel.getIdade());
			}

			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				stm.close();
			}
			Conexao.closeConnection();
		}
	}

	public ArrayList<Paciente> listarPessoas() throws SQLException {

		ArrayList<Paciente> pessoas = new ArrayList<Paciente>();
		Connection con = null;
		PreparedStatement stm = null;
		StringBuilder sql = new StringBuilder("SELECT idPessoa, nome, genero, saude, idade FROM pessoa");

		try {
			con = Conexao.getConnection();
			stm = con.prepareStatement(sql.toString());
			ResultSet rs = stm.executeQuery();

			while (rs.next())
				pessoas.add(Servicos.retornaPessoa(rs));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				stm.close();
			}
			Conexao.closeConnection();
		}

		return pessoas;
	}

	public Paciente consultaId(int idValido) throws SQLException {

		Connection con = null;
		PreparedStatement stm = null;
		StringBuilder sql = new StringBuilder("SELECT * FROM pessoa WHERE idPessoa = ?");

		try {
			con = Conexao.getConnection();
			stm = con.prepareStatement(sql.toString());
			stm.setInt(1, idValido);
			ResultSet rs = stm.executeQuery();

			if (rs.next())
				return Servicos.retornaPessoa(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				stm.close();
			}
			Conexao.closeConnection();
		}

		return null;
	}

	public ArrayList<Paciente> pesquisarPessoas(String nomeValido) throws SQLException {

		ArrayList<Paciente> pessoas = new ArrayList<Paciente>();
		Connection con = null;
		PreparedStatement stm = null;
		StringBuilder sql = new StringBuilder("SELECT * FROM pessoa WHERE pessoa.nome LIKE ? ORDER BY nome");

		try {
			con = Conexao.getConnection();
			stm = con.prepareStatement(sql.toString());
			stm.setString(1, '%' + nomeValido + '%');
			ResultSet rs = stm.executeQuery();

			while (rs.next())
				pessoas.add(Servicos.retornaPessoa(rs));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				stm.close();
			}
			Conexao.closeConnection();
		}

		return pessoas;
	}
}
