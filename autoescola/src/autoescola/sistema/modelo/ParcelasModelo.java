package autoescola.sistema.modelo;

import java.sql.PreparedStatement;

import autoescola.tecnologia.configuracao.Conexao;

public class ParcelasModelo {
	private int numeroParcela;
	private String dataParcela;
	private int pago;
	private int handlePessoa;
	
	
	
	// METODOS
	public boolean pagar() {
		String sql = "UPDATE PARCELAS "
				   + "SET PAGO = 1 " +
				     "WHERE NUMEROPARCELA = ? AND PESSOA = ?";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			statement.setInt(1, getNumeroParcela());
			statement.setInt(2, getHandlePessoa());
			
			statement.execute();
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	// GETTERS AND SETTERS
	public int getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public String getDataParcela() {
		return dataParcela;
	}
	public void setDataParcela(String dataParcela) {
		this.dataParcela = dataParcela;
	}
	public int getPago() {
		return pago;
	}
	public void setPago(int pago) {
		this.pago = pago;
	}
	public int getHandlePessoa() {
		return handlePessoa;
	}
	public void setHandlePessoa(int handlePessoa) {
		this.handlePessoa = handlePessoa;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParcelasModelo [numeroParcela=");
		builder.append(numeroParcela);
		builder.append(", dataParcela=");
		builder.append(dataParcela);
		builder.append(", pago=");
		builder.append(pago);
		builder.append("]");
		return builder.toString();
	}
}
