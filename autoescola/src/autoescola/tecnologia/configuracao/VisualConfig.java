package autoescola.tecnologia.configuracao;

import java.io.Console;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

import autoescola.tecnologia.tipos.TpAlinhamento;
import autoescola.tecnologia.tipos.TpEscape;
import autoescola.tecnologia.tipos.TpMensagem;

public class VisualConfig {
	// ATRIBUTOS
		public static int tamanhoTela = 70;
		public static Scanner scanner = new Scanner(System.in);
		public static Console console = System.console();
		
		
		
		// CORES
		private static final String VERMELHO = "\033[91m";
		private static final String VERDE = "\033[92m";
		private static final String AMARELO = "\033[93m";
		private static final String RESET = "\033[00m";
		
		
		
		// GETTERS E SETTERS DO MENU
		public static int getTamanhoTela() {
			return tamanhoTela;
		}
		public static void setTamanhoTela(int tamanhoTela) {
			VisualConfig.tamanhoTela = tamanhoTela;
		}
		
		
		
		// METODOS DE SAIDA DE DADOS
		public static void mensagem(String prMsg, TpMensagem prTipoMensagem) {
			linhaSeparacao();
			textoAlinhado(prMsg, TpAlinhamento.CENTRO, prTipoMensagem);
			linhaSeparacao();
			retornaTexto("DIGITE ENTER PARA CONTINUAR...");
		}
		
		public static void textoAlinhado(String prTexto, TpAlinhamento prTpAlinhaTexto) {
			textoAlinhado(prTexto, prTpAlinhaTexto, TpMensagem.NORMAL);
		}
		
		public static void opcaoEscape(TpEscape... prOpcoesEscape) {
			VisualConfig.linhaEspaco();
			for (int i = 0; i < prOpcoesEscape.length; i++) {
				textoAlinhado(Integer.toString(i+21) + " - " + prOpcoesEscape[i], TpAlinhamento.ESQUERDA);
			}		
		}
		
		public static void textoAlinhado(String prTexto, TpAlinhamento prTpAlinhaTexto, TpMensagem prTpMensagem) {
			String textoFinal = "";
			String cor;
			
			switch (prTpMensagem) {
			case NORMAL: {
				cor = RESET;
				break;
			}
			
			case ERRO: {
				cor = VERMELHO;
				break;
			}
			
			case SUCESSO: {
				cor = VERDE;
				break;
			}
			
			case ADVERTENCIA: {
				cor = AMARELO;
				break;
			}
			
			default:
				cor = RESET;
			}
			
			textoFinal += "| " + cor;
			
			switch (prTpAlinhaTexto) {
			case DIREITA: {
				
				break;
			}
			
			case ESQUERDA: {
				int tamanhoTexto = prTexto.length();
				int espaco = tamanhoTela - tamanhoTexto;
				
				textoFinal += prTexto;
				
				for (int i = 0; i < espaco -1; i++) {
					textoFinal += " ";
				}
				
				textoFinal += RESET + " |";
				
				break;
			}
			
			case CENTRO: {
				int tamanhoTexto = prTexto.length();
				int espaco = tamanhoTela - tamanhoTexto;
				
				for (int i = 0; i < espaco / 2; i++) {
					textoFinal += " ";
				}
				
				textoFinal += prTexto;
				
				for (int i = 0; i < espaco / 2; i++) {
					textoFinal += " ";
				}
				textoFinal += RESET;
				
				if (espaco % 2 == 0) {
					textoFinal += "|";
				} else {
					textoFinal += " |";
				}
				
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + prTpAlinhaTexto);
			}
			
			System.out.println(textoFinal);
		}
		
		public static void titulo(String prTexto) {
			VisualConfig.linhaSeparacao();
			VisualConfig.textoAlinhado(prTexto, TpAlinhamento.CENTRO);
			VisualConfig.linhaSeparacao();
		}
		
		public static void limparTela() {
			try {
	            // Configurar PrintWriter com UTF-8
	            PrintWriter writer = new PrintWriter(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));

	            // ENVIAR SEQUENCIA DE CONTROLE PARA LIMPAR O CONSOLEnviar sequência de controle para limpar o console
	            writer.print("\033[H\033[2J");
	            writer.flush();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
		}
		
		public static void linhaSeparacao() {
			String linha = "+";
			for (int i = 0; i <= tamanhoTela; i++) {
				linha += "-";
			}
			linha += "+";
			System.out.println(linha);
		}
		
		public static void linhaEspaco() {
			String textoFinal = "|";
			for (int i = 0; i <= tamanhoTela; i++) {
				textoFinal += " ";
			}
			
			textoFinal += "|";
			
			System.out.println(textoFinal);
		}
		
		
		
		// METODOS DE ENTRADA DE DADOS
		public static String retornaTexto(String prTexto) {
			if (prTexto.trim().equals("")) {
				System.out.print(prTexto);
				return scanner.nextLine();
			} else {
				if (console == null) {
					System.out.print("| " + prTexto);
					
					return scanner.nextLine();
				} else {
					String msg = ("| " + prTexto);
					return console.readLine(msg);
				}
			}
		}
		
		public static String retornaPassword(String prTexto) {
			if (console == null) {
				System.out.print("| " + prTexto);
				return scanner.nextLine();
			} else {
				return new String(console.readPassword("| " + prTexto));
			}
		}
		
		public static int retornaInt(String prTexto) {
			System.out.print("| " + prTexto);
			String num = scanner.nextLine();
			return (StringUtils.isStrictlyNumeric(num) ? Integer.parseInt(num) : 0);
		}
		
		public static void fecharScanner() {
			scanner.close();
		}
}
