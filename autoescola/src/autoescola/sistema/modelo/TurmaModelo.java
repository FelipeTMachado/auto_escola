package autoescola.sistema.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import autoescola.sistema.interfaces.ModeloInterface;
import autoescola.tecnologia.configuracao.Conexao;
import autoescola.tecnologia.configuracao.Utils;
import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpMensagem;
import autoescola.tecnologia.tipos.TpPessoa;

public class TurmaModelo implements ModeloInterface {
	private int handle;
	private String dataInicio;
	private PessoaModelo instrutor;
	private String categoria;
	private String horario;
	private ArrayList<PessoaModelo> alunos = new ArrayList<PessoaModelo>();
	
	private ArrayList<TurmaModelo> listaTurmas = new ArrayList<TurmaModelo>();
	
	
	// METODOS
	@Override
	public boolean alterar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean buscar() {
		
		return false;
	}
	
	public boolean carregarTurmasProfessor(int prHandleInstrutor) {
		String sql = "SELECT * FROM TURMA WHERE INSTRUTOR = " + String.format("%s", prHandleInstrutor);
		
		try(PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			try (ResultSet vQuery = statement.executeQuery()){
				while (vQuery.next()) {
					TurmaModelo turma = new TurmaModelo();
					turma.setHandle(vQuery.getInt("handle"));
					turma.carregarAlunosTurma();
					turma.setCategoria(vQuery.getString("categoria"));
					turma.setDataInicio(Utils.converterDataParaBrasil(vQuery.getString("datainicio")));
					turma.setHorario(vQuery.getString("horarioaulas"));
					
					listaTurmas.add(turma);
				}
			} catch (Exception e) {
				VisualConfig.mensagem("ERRO AO REALIZAR CONSULTA", TpMensagem.ERRO);
			}
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CARREGAR O STATEMENT", TpMensagem.ERRO);
		}
		
		return true;
	}
	
	public boolean carregarAlunosTurmaProfessor(int prHandleTurma) {
		String sql = "SELECT * FROM TURMAALUNO WHERE TURMA = " + prHandleTurma;
		
		setListaTurmas(new ArrayList<TurmaModelo>());
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			try (ResultSet vQuery = statement.executeQuery()){
				while (vQuery.next()) {
					PessoaModelo aluno = new PessoaModelo();
					
					aluno.setNome(vQuery.getString("nome"));
					aluno.setCpf(vQuery.getString("cpf"));
					aluno.setDataNascimento(vQuery.getString("datanascimento"));
					aluno.setTelefone(vQuery.getString("telefone"));
					aluno.setEmail(vQuery.getString("email"));
					aluno.setUsuario(vQuery.getString("usuario"));
					aluno.setSenha(vQuery.getString("senha"));
					
					if (vQuery.getInt("tipopessoa") == 1) {
						aluno.setTipoPessoa(TpPessoa.ALUNO);
					} 
					
					if (vQuery.getInt("tipopessoa") == 2) {
						aluno.setTipoPessoa(TpPessoa.FUNCIONARIO);
					}
					
					alunos.add(aluno);
					return true;
				}
			} catch (Exception e) {
				VisualConfig.mensagem("ERRO AO REALIZAR CONSULTA", TpMensagem.ERRO);
			}
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CARREGAR O STATEMENT", TpMensagem.ERRO);
		}
		
		return false;
	}
	
	public boolean carregarAlunosTurma() {
		String sql = "SELECT * FROM TURMAALUNO WHERE TURMA = " + String.format("%d", handle);
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			try (ResultSet vQuery = statement.executeQuery()){
				while (vQuery.next()) {
					PessoaModelo aluno = new PessoaModelo();
					
					aluno.setNome(vQuery.getString("nome"));
					aluno.setCpf(vQuery.getString("cpf"));
					aluno.setDataNascimento(vQuery.getString("datanascimento"));
					aluno.setTelefone(vQuery.getString("telefone"));
					aluno.setEmail(vQuery.getString("email"));
					aluno.setUsuario(vQuery.getString("usuario"));
					aluno.setSenha(vQuery.getString("senha"));
					
					if (vQuery.getInt("tipopessoa") == 1) {
						aluno.setTipoPessoa(TpPessoa.ALUNO);
					} 
					
					if (vQuery.getInt("tipopessoa") == 2) {
						aluno.setTipoPessoa(TpPessoa.FUNCIONARIO);
					}
					
					alunos.add(aluno);
				}
			} catch (Exception e) {
				VisualConfig.mensagem("ERRO AO REALIZAR CONSULTA", TpMensagem.ERRO);
			}
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CARREGAR O STATEMENT", TpMensagem.ERRO);
		}
		
		return true;
	}
	
	public boolean carregarListaTurmas() {
		String sql = "SELECT T.*, P.CPF CPF FROM TURMA T JOIN PESSOA P ON P.HANDLE = T.INSTRUTOR";
		
		try(PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			try (ResultSet vQuery = statement.executeQuery()){
				while (vQuery.next()) {
					TurmaModelo turma = new TurmaModelo();
					turma.setHandle(vQuery.getInt("handle"));
					turma.carregarAlunosTurma();
					turma.setCategoria(vQuery.getString("categoria"));
					turma.setInstrutor((new PessoaModelo()).buscarInstrutor(vQuery.getString("cpf")));
					turma.setDataInicio(Utils.converterDataParaBrasil(vQuery.getString("datainicio")));
					turma.setHorario(vQuery.getString("horarioaulas"));
					
					listaTurmas.add(turma);
				}
			} catch (Exception e) {
				VisualConfig.mensagem("ERRO AO REALIZAR CONSULTA", TpMensagem.ERRO);
			}
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CARREGAR O STATEMENT", TpMensagem.ERRO);
		}
		
		return true;
	}
	
	@Override
	public boolean salvar() {
		String sql = "INSERT INTO TURMA "
				   + "		 (CATEGORIA, DATAINICIO, HORARIOAULAS, INSTRUTOR) "
				   + "VALUES "
				   + "       (?, ?, ?, ?)";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {			
			statement.setString(1, getCategoria());
			statement.setString(2, Utils.converterDataParaBanco(getDataInicio()));
			statement.setString(3, getHorario());
			statement.setInt(4, getInstrutor().getHandle());
			
			statement.execute();
			return true;
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CARREGAR O STATEMENT", TpMensagem.ERRO);
		}
		
		return false;
	}
	
	@Override
	public boolean verificarExistencia() {
		
		
		return false;
	}
	
	
	
	// GETTERS AND SETTERS
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public ArrayList<PessoaModelo> getAlunos() {
		return alunos;
	}
	public void setAlunos(ArrayList<PessoaModelo> alunos) {
		this.alunos = alunos;
	}
	public PessoaModelo getInstrutor() {
		return instrutor;
	}
	public void setInstrutor(PessoaModelo instrutor) {
		this.instrutor = instrutor;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public ArrayList<TurmaModelo> getListaTurmas() {
		return listaTurmas;
	}
	public void setListaTurmas(ArrayList<TurmaModelo> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}
	public int getHandle() {
		return handle;
	}
	public void setHandle(int handle) {
		this.handle = handle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TurmaModelo [dataInicio=");
		builder.append(dataInicio);
		builder.append(", alunos=");
		builder.append(alunos);
		builder.append(", instrutor=");
		builder.append(instrutor);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", horario=");
		builder.append(horario);
		builder.append("]");
		return builder.toString();
	}
}
