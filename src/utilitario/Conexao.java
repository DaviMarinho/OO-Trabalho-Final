package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection con = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19", "Davi", "davi123");
			System.out.println("Nova conexão com sucesso!");
		} catch (SQLException e) {
			System.out.println("Sem conectar!\n" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Falha na Conexão!\n" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {
		if (con != null)
			try {
				System.out.println("Conexão fechada com sucesso!");
				con.close();
				con = null;
			} catch (SQLException e) {
				System.out.print("Falha ao fechar." + e.getMessage());
			}

	}
}