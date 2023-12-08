package autoescola.tecnologia.configuracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	// ATRIBUTOS
	private static String banco = "";
	private static String local = "localhost";
	private static String usuario = "root";
	private static String senha = "315865";
	private static int porta = 3306;
	private static Connection conexao;
	private static boolean mostrarMsgConexao = false;
	

	// CONSTRUCTOR
	public Conexao() {
		// TODO Auto-generated constructor stub
	}
	
	// GETTERS AND SETTERS
	public static String getBanco() {
		return banco;
	}
	public static boolean isMostrarMsgConexao() {
		return mostrarMsgConexao;
	}
	public static void setMostrarMsgConexao(boolean mostrarMsgConexao) {
		Conexao.mostrarMsgConexao = mostrarMsgConexao;
	}
	public static Connection getConexao() {
		return conexao;
	}
	public static void setConexao(Connection conexao) {
		Conexao.conexao = conexao;
	}
	public static void setBanco(String banco) {
		Conexao.banco = banco;
	}
	public static String getLocal() {
		return local;
	}
	public static void setLocal(String local) {
		Conexao.local = local;
	}
	public static String getUsuario() {
		return usuario;
	}
	public static void setUsuario(String usuario) {
		Conexao.usuario = usuario;
	}
	public static String getSenha() {
		return senha;
	}
	public static void setSenha(String senha) {
		Conexao.senha = senha;
	}
	public static int getPorta() {
		return porta;
	}
	public static void setPorta(int porta) {
		Conexao.porta = porta;
	}



	// METODOS
	public static boolean conectar() {
		String stringConexao = "jdbc:mysql://";
		
		stringConexao += local;
		stringConexao += ":" + String.format("%d", porta);
		
		if (!banco.trim().equals("")) {
			stringConexao += "/" + banco;
		}
		
		try {
			//System.out.println(stringConexao);
			conexao = DriverManager.getConnection(stringConexao, usuario, senha);
			
			if (mostrarMsgConexao) {
				System.out.println("CONECTADO");
			}
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao conectar no banco de dados\nErro: " + e.getMessage());
			return false;
		}
	}
	
	
	public static boolean desconectar() {
		try {
			conexao.close();
			if (mostrarMsgConexao) {
				System.out.println("DESCONECTADO");
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	
}
