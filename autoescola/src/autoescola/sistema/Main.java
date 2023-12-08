package autoescola.sistema;

import autoescola.sistema.controle.PessoaControle;
import autoescola.tecnologia.configuracao.Conexao;
import autoescola.tecnologia.configuracao.Constantes;

public class Main {
	public static void main(String[] args) {
		try {
			Conexao.setBanco("AUTO_ESCOLA");
			Conexao.conectar();
			
			Constantes.controlePessoa = new PessoaControle();
			Constantes.controlePessoa.login();
		} finally {
			Conexao.desconectar();	
		}
	}
}
