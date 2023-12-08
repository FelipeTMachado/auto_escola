package autoescola.sistema.controle;

import autoescola.sistema.modelo.VeiculoModelo;
import autoescola.sistema.visual.VeiculoVisual;

public class VeiculoControle {
	private VeiculoModelo modelo = new VeiculoModelo();
	private VeiculoVisual visual = new VeiculoVisual();
	
	
	
	// METODOS
	public void gerenciamentoVeiculo() {
		boolean ehSair = false;
		
		while(!ehSair) {
			switch (visual.gerenciamentoVeiculo()) {
			case 1: {
				
				break;
			}
			
			case 2: {
				
				break;
			}
			
			case 3: {
				
				break;
			}
			
			case 4: {
				
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
	
	
	
	// GETTERS AND SETTERS
		public VeiculoModelo getModelo() {
			return modelo;
		}
		public void setModelo(VeiculoModelo modelo) {
			this.modelo = modelo;
		}
		public VeiculoVisual getVisual() {
			return visual;
		}
		public void setVisual(VeiculoVisual visual) {
			this.visual = visual;
		}
	
		
		
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VeiculoControle [modelo=");
		builder.append(modelo);
		builder.append(", visual=");
		builder.append(visual);
		builder.append("]");
		return builder.toString();
	}	
}
