package autoescola.sistema.visual;

import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpAlinhamento;
import autoescola.tecnologia.tipos.TpMensagem;

public class Visual {
	public void getMensagemOpcaoInvalida() {
		VisualConfig.mensagem("OPCAO INVALIDA", TpMensagem.ADVERTENCIA);
	}
	
	public void getMensagemPreenchimentos(String[] prCampo) {
		VisualConfig.limparTela();
		VisualConfig.linhaSeparacao();
		VisualConfig.textoAlinhado("CAMPOS OBRIGATORIOS NAO PREENCHIDOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		for (String campo: prCampo) {
			VisualConfig.textoAlinhado(campo, TpAlinhamento.ESQUERDA, TpMensagem.ADVERTENCIA);
		}
		
		VisualConfig.linhaSeparacao();
		VisualConfig.retornaTexto("DIGITE ENTER PARA CONTINUAR...");
	}
	
	
	public void getlinhaSeparadorTabela(int prLocal) {
		String linha = "+";
		for (int i = 0; i <= VisualConfig.getTamanhoTela(); i++) {
			if (i == prLocal) {
				linha += "+";
			} else {
				linha += "-";
			}
		}
		
		linha += "+";
		System.out.println(linha);
	}
}
