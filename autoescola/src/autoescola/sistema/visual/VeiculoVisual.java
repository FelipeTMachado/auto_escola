package autoescola.sistema.visual;

import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpAlinhamento;
import autoescola.tecnologia.tipos.TpEscape;

public class VeiculoVisual extends Visual {
	public int gerenciamentoVeiculo() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("GERENCIAMENTO DE VEICULOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("1 - NOVO", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("2 - ALTERAR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("3 - EXCLUIR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("4 - LISTAR VEICULOS", TpAlinhamento.ESQUERDA);
		
		VisualConfig.opcaoEscape(TpEscape.VOLTAR, TpEscape.SAIR);
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("DIGITE SUA OPCAO: ");
		return retorno;
	}
	
	
	
}
