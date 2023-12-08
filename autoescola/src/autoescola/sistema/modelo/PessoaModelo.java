package autoescola.sistema.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import autoescola.sistema.interfaces.ModeloInterface;
import autoescola.tecnologia.configuracao.Conexao;
import autoescola.tecnologia.configuracao.Utils;
import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpMensagem;
import autoescola.tecnologia.tipos.TpPessoa;

public class PessoaModelo implements ModeloInterface{
	// ATRIBUTOS
	private int handle;
	
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String telefone;
	private String email;
	private String usuario;
	private String senha;
	private int quantParcelas;
	private float valorParcela;
	private TpPessoa tipoPessoa;	
	private float notaPratica;
	private float notaTeorica;
	private ArrayList<PessoaModelo> listaAlunos = new ArrayList<PessoaModelo>();
	private ArrayList<ParcelasModelo> listaParcelas = new ArrayList<ParcelasModelo>();
	
	public PessoaModelo() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean excluir() {
		String sql = "DELETE FROM PESSOA WHERE CPF = ?";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			statement.setString(1, getCpf());
			
			statement.execute();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	// METODOS
	@Override
	public boolean alterar() {
		String sql =  " UPDATE PESSOA"
				      + "  SET NOME           = ?,"
				      + "      TELEFONE       = ?,"
				      + "      DATANASCIMENTO = ?,"
				      + "      EMAIL          = ?,"
				      + "      USUARIO        = ?,"
				      + "      SENHA          = ?"
				      + "WHERE CPF = ?";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			statement.setString(1, getNome());
			statement.setString(2, getTelefone());
			statement.setString(3, Utils.converterDataParaBanco(getDataNascimento()));
			statement.setString(4, getEmail());
			statement.setString(5, getUsuario());
			statement.setString(6, getSenha());
			
			statement.setString(7, getCpf());
			
			statement.execute();
			return true;
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO SALVAR ALTERACAO", TpMensagem.ERRO);
			return false;
		}		
	}
	
	public PessoaModelo buscarInstrutor(String prCpf) {
		String sql = "SELECT * FROM PESSOA WHERE CPF = ? AND TIPOPESSOA = 2";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			statement.setString(1, prCpf);
			
			try (ResultSet vQuery = statement.executeQuery()) {
				if (vQuery.next()) {
					setHandle(vQuery.getInt("handle"));
					setNome(vQuery.getString("nome"));
					setCpf(vQuery.getString("cpf"));
					setDataNascimento(vQuery.getString("datanascimento"));
					setTelefone(vQuery.getString("telefone"));
					setEmail(vQuery.getString("email"));
					setUsuario(vQuery.getString("usuario"));
					setSenha(vQuery.getString("senha"));
					
					if (vQuery.getInt("tipopessoa") == 1) {
						setTipoPessoa(TpPessoa.ALUNO);
					} 
					
					if (vQuery.getInt("tipopessoa") == 2) {
						setTipoPessoa(TpPessoa.FUNCIONARIO);
					}
					
					return this;
				} else {
					VisualConfig.mensagem("NAO ENCONTROU O INSTRUTOR SELECIONADO", TpMensagem.ADVERTENCIA);
				}
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CRIAR O STATEMENT", TpMensagem.ERRO);
		}
		
		return null;
	}
	
	public boolean buscarAluno() {
		String sql = "SELECT * FROM PESSOA WHERE CPF = ? AND TIPOPESSOA = 1";
		
		try(PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			statement.setString(1, getCpf());
			
			try (ResultSet vQuery = statement.executeQuery()){
				if (vQuery.next()) {
					setNome(vQuery.getString("nome"));
					setCpf(vQuery.getString("cpf"));
					setDataNascimento(vQuery.getString("datanascimento"));
					setTelefone(vQuery.getString("telefone"));
					setEmail(vQuery.getString("email"));
					setUsuario(vQuery.getString("usuario"));
					setSenha(vQuery.getString("senha"));
					setNotaPratica(vQuery.getFloat("notapratica"));
					setNotaTeorica(vQuery.getFloat("notateorica"));
					
					if (vQuery.getInt("tipopessoa") == 1) {
						setTipoPessoa(TpPessoa.ALUNO);
					} 
					
					if (vQuery.getInt("tipopessoa") == 2) {
						setTipoPessoa(TpPessoa.FUNCIONARIO);
					}
				} else {
					return false;
				}
			} catch (Exception e) {
				VisualConfig.mensagem("ERRO AO EXECUTAR A QUERY", TpMensagem.ERRO);
			}
		} catch (Exception e) {
			VisualConfig.mensagem("ERRO AO CRIAR O STATEMENT", TpMensagem.ERRO);
		}
	
		return true;
	}
	
	public boolean cadastrarNotaTeorica(float prNota) {
		String sql = "UPDATE PESSOA "
				   + "SET NOTATEORICA = ? "
				   + "WHERE HANDLE = ? ";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			statement.setFloat(1, prNota);
			statement.setInt(2, getHandle());
			
			statement.execute();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public boolean cadastrarNotaPratica(float prNota) {
		String sql = "UPDATE PESSOA "
				   + "SET NOTAPRATICA = ? "
				   + "WHERE HANDLE = ? ";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			statement.setFloat(1, prNota);
			statement.setInt(2, getHandle());
			
			statement.execute();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public void cadastrarParcelas() {
		LocalDate dataAtual = LocalDate.now();
		
		for (int i = 0; i < getQuantParcelas(); i++) {
			String sql = "INSERT INTO PARCELAS"
				       + "    (NUMEROPARCELA, DATAPARCELA, PESSOA, PAGO)"
					   + "VALUES "
					   + "    (?, ?, (SELECT HANDLE FROM PESSOA WHERE CPF = ?), ?) ";
			
			try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
				statement.setInt(1, i);
				statement.setString(2, dataAtual.plusMonths(i).with(TemporalAdjusters.firstDayOfNextMonth()).toString());
				statement.setString(3, getCpf());
				statement.setInt(4, 0);
				
				statement.execute();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Override
	public boolean salvar() {
		String sql = "INSERT INTO PESSOA "
				   + "    (NOME, CPF, DATANASCIMENTO, TELEFONE, EMAIL, USUARIO, SENHA, TIPOPESSOA)"
				   + "VALUES "
				   + "    (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			statement.setString(1, getNome());
			statement.setString(2, getCpf());
			statement.setString(3, Utils.converterDataParaBanco(getDataNascimento()));
			statement.setString(4, getTelefone());
			statement.setString(5, getEmail());
			statement.setString(6, getUsuario());
			statement.setString(7, getSenha());
			
			if (getTipoPessoa() == TpPessoa.ALUNO) {
				statement.setInt(8, 1);
			} 
			
			if (getTipoPessoa() == TpPessoa.FUNCIONARIO) {
				statement.setInt(8, 2);
			}
			
			statement.execute();
			cadastrarParcelas();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void carregarListaParcelas() {
		String sql = "SELECT * FROM PARCELAS WHERE PESSOA = ?";
		
		listaParcelas = new ArrayList<ParcelasModelo>();
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
			statement.setInt(1, getHandle());
			
			try (ResultSet vQuery = statement.executeQuery()) {
				while (vQuery.next()) {
					ParcelasModelo parcela = new ParcelasModelo();
					
					parcela.setHandlePessoa(vQuery.getInt("pessoa"));
					parcela.setDataParcela(Utils.converterDataParaBrasil(vQuery.getString("dataparcela")));
					parcela.setNumeroParcela(vQuery.getInt("numeroparcela"));
					parcela.setPago(vQuery.getInt("pago"));
					
					listaParcelas.add(parcela);
				}
			}
			
		} catch (Exception e) {
			VisualConfig.mensagem("STATEMENT NAO CARREGADO", null);
		}
	}
	
	public void carregarListaAlunos() {
		String sql = "SELECT * FROM PESSOA WHERE TIPOPESSOA = 1";
		
		listaAlunos = new ArrayList<PessoaModelo>();
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			try(ResultSet vQuery = statement.executeQuery()){
				while(vQuery.next()) {
					PessoaModelo aluno = new PessoaModelo();
					
					aluno.setNome(vQuery.getString("nome"));
					aluno.setCpf(vQuery.getString("cpf"));
					aluno.setDataNascimento(vQuery.getString("datanascimento"));
					aluno.setTelefone(vQuery.getString("telefone"));
					aluno.setEmail("email");
					aluno.setUsuario(vQuery.getString("usuario"));
					aluno.setSenha(vQuery.getString("senha"));
					aluno.setValor(vQuery.getFloat("valorparcela"));
					aluno.setQuantParcelas(vQuery.getInt("quantparcelas"));
					aluno.setTipoPessoa(TpPessoa.ALUNO);
					
					listaAlunos.add(aluno);
				}
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public boolean verificarExistencia() {
		String sql = "SELECT * FROM PESSOA WHERE CPF = ?";
		
		boolean existe = true;
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			statement.setString(1, getCpf()); 
			
			try(ResultSet vQuery = statement.executeQuery()){
				if (vQuery.next()) {
					existe = true;
				} else {
					existe = false;
				}
			} catch (Exception e) {
				existe = true;
			}
		} catch (Exception e) {
			existe = true;
		}
		
		return existe;
	}
	
	public boolean autenticarLogin(String prUsuario, String prSenha) {
		boolean retorno = false;
		
		String sql = "SELECT              "
				   + "    *               "
				   + "FROM                "
				   + "    PESSOA         "
				   + "WHERE               "
				   + "    USUARIO = ? AND "
				   + "    SENHA   = ?     ";
		
		try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)){
			statement.setString(1, prUsuario);
			statement.setString(2, prSenha);
				
			try (ResultSet vQuery = statement.executeQuery()) {
				if (vQuery.next()) {
					setHandle(vQuery.getInt("handle"));
					setCpf(vQuery.getString("cpf"));
					setNome(vQuery.getString("nome"));
					setDataNascimento(vQuery.getString("datanascimento"));
					setTelefone(vQuery.getString("telefone"));
					setEmail(vQuery.getString("email"));
					setQuantParcelas(vQuery.getInt("quantparcelas"));
					setValor(vQuery.getFloat("valorparcela"));
					setNotaPratica(vQuery.getFloat("notapratica"));
					setNotaTeorica(vQuery.getFloat("notateorica"));
					
					setTipoPessoa(vQuery.getInt("tipopessoa") == 1 ? TpPessoa.ALUNO : TpPessoa.FUNCIONARIO);
					
					setUsuario(vQuery.getString("usuario"));
					setSenha(vQuery.getString("senha"));
					
					retorno = true;
				}
			} catch (Exception e) {
				retorno = false;
				System.err.print(e.getMessage());
			}
		} catch (SQLException e) {
			retorno = false;
			System.err.print(e.getMessage());	
		}
		
		return retorno;
	}
	
	
	
	// GETTER AND SETTERS
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TpPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TpPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public ArrayList<PessoaModelo> getListaAlunos() {
		return listaAlunos;
	}
	public void setListaAlunos(ArrayList<PessoaModelo> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	public int getHandle() {
		return handle;
	}
	public void setHandle(int handle) {
		this.handle = handle;
	}
	public int getQuantParcelas() {
		return quantParcelas;
	}
	public void setQuantParcelas(int quantParcelas) {
		this.quantParcelas = quantParcelas;
	}
	public float getValor() {
		return valorParcela;
	}
	public void setValor(float valor) {
		this.valorParcela = valor;
	}
	public float getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(float valorParcela) {
		this.valorParcela = valorParcela;
	}
	public ArrayList<ParcelasModelo> getListaParcelas() {
		return listaParcelas;
	}
	public void setListaParcelas(ArrayList<ParcelasModelo> listaParcelas) {
		this.listaParcelas = listaParcelas;
	}
	public float getNotaPratica() {
		return notaPratica;
	}
	public void setNotaPratica(float notaPratica) {
		this.notaPratica = notaPratica;
	}
	public float getNotaTeorica() {
		return notaTeorica;
	}
	public void setNotaTeorica(float notaTeorica) {
		this.notaTeorica = notaTeorica;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PessoaModelo [handle=");
		builder.append(handle);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cpf=");
		builder.append(cpf);
		builder.append(", dataNascimento=");
		builder.append(dataNascimento);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", senha=");
		builder.append(senha);
		builder.append(", quantParcelas=");
		builder.append(quantParcelas);
		builder.append(", valorParcela=");
		builder.append(valorParcela);
		builder.append(", tipoPessoa=");
		builder.append(tipoPessoa);
		builder.append(", notaPratica=");
		builder.append(notaPratica);
		builder.append(", notaTeorica=");
		builder.append(notaTeorica);
		builder.append(", listaAlunos=");
		builder.append(listaAlunos);
		builder.append(", listaParcelas=");
		builder.append(listaParcelas);
		builder.append("]");
		return builder.toString();
	}
}
