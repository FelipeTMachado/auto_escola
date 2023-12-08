package autoescola.sistema.controle;

import java.util.ArrayList;

import autoescola.sistema.modelo.TurmaModelo;
import autoescola.sistema.visual.TurmaVisual;
import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpMensagem;

public class TurmaControle {
	// ATRIBUTOS
	private TurmaModelo modelo = new TurmaModelo();
	private TurmaVisual visual = new TurmaVisual();
	
	
	
	// METODOS
	public void gerenciamentoTurma() {
		boolean ehSair = false;
		
		
		while(!ehSair) {
			switch (visual.gerenciamentoTurma()) {
			case 1: {
				novaTurma();
				
				break;
			}
			
			case 2: {
				alterarTurma();
				
				break;
			}
			
			case 4: {
				listarTurmas();
				
				break;
			}
			
			case 21: {
				ehSair = true;
				
				break;
			}
		
			case 22: {
				System.exit(1);
				
				break;
			}
			
			default:
				visual.getMensagemOpcaoInvalida();
			}
		}
	}
	private void alterarTurma() {
		
	}
	
	private void novaTurma() {
		if (visual.novaTurma()) {
			modelo.setCategoria(visual.getCampoCategoria());
			modelo.setDataInicio(visual.getCampoDataInicio());
			modelo.setHorario(visual.getCampoHorario());
			modelo.setInstrutor(visual.getCampoInstrutor());
			
			VisualConfig.limparTela();
			visual.mostraCamposDigitados();
			

			if (VisualConfig.retornaInt("[1] SALVAR ou [2] DESCARTAR ALTERACOES: [1 / 2]: ") == 1) {
				if (modelo.salvar()) {
					VisualConfig.mensagem("SUCESSO AO CADASTRAR TURMA", TpMensagem.SUCESSO);
				} else {
					VisualConfig.mensagem("TURMA NAO CADASTRADA", TpMensagem.ADVERTENCIA);
				}
			}
		}
	}


	public void listarTurmas() {
		modelo.setListaTurmas(new ArrayList<TurmaModelo>());
		visual.setListaTurmas(new ArrayList<TurmaModelo>());
		
		modelo.carregarListaTurmas();
		visual.setListaTurmas(modelo.getListaTurmas());
		visual.listarTurmas();
	}
	
	// GETTERS AND SETTERS
	public TurmaModelo getModelo() {
		return modelo;
	}
	public void setModelo(TurmaModelo modelo) {
		this.modelo = modelo;
	}
	public TurmaVisual getVisual() {
		return visual;
	}
	public void setVisual(TurmaVisual visual) {
		this.visual = visual;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TurmaVisual [modelo=");
		builder.append(modelo);
		builder.append(", visual=");
		builder.append(visual);
		builder.append("]");
		return builder.toString();
	}
}
