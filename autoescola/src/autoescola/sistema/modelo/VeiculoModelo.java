package autoescola.sistema.modelo;

import autoescola.sistema.interfaces.ModeloInterface;

public class VeiculoModelo implements ModeloInterface{
	private String marca;
	private String modelo;
	private int numeroPortas;
	
	
	
	// METODOS
	@Override
	public boolean alterar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean salvar() {
		return true;
	}
	
	public boolean buscar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean verificarExistencia() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	// GETTERS AND SETTERS
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getNumeroPortas() {
		return numeroPortas;
	}
	public void setNumeroPortas(int numeroPortas) {
		this.numeroPortas = numeroPortas;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VeiculoModelo [marca=");
		builder.append(marca);
		builder.append(", modelo=");
		builder.append(modelo);
		builder.append(", numeroPortas=");
		builder.append(numeroPortas);
		builder.append("]");
		return builder.toString();
	}
}
