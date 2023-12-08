package autoescola.tecnologia.configuracao;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import autoescola.tecnologia.tipos.TpMensagem;

public class Utils {
	// CONVERTER DATA PARA BRASIL
	public static String converterDataParaBrasil(String prDataBanco) {
		try {
			LocalDate data = LocalDate.parse(prDataBanco);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			return data.format(dtf);
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CONVERTER DATA", TpMensagem.ERRO);
			return null;
		}
	}
		
	// CONVERTER DATA PARA BANCO
	public static String converterDataParaBanco(String prDataBrasil) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate data = LocalDate.parse(prDataBrasil, dtf);
			
			return data.toString();
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CONVERTER DATA", TpMensagem.ERRO);
			return null;
		}
	}
		
		
	// GERAR UMA SENHA ALEATORIA NO SISTEMA
	public static String gerarSenhaAleatoria(int tamanho) {
		final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
		
		SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();
        
        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(index));
        }
		
		return senha.toString();
	}
}
