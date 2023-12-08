package autoescola.sistema.controle;

import java.util.ArrayList;

import autoescola.sistema.modelo.PessoaModelo;
import autoescola.sistema.visual.PessoaVisual;
import autoescola.tecnologia.configuracao.Utils;
import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpAlinhamento;
import autoescola.tecnologia.tipos.TpInsercao;
import autoescola.tecnologia.tipos.TpMensagem;
import autoescola.tecnologia.tipos.TpPessoa;

public class PessoaControle {
	private PessoaModelo modelo = new PessoaModelo();
	private PessoaVisual visual = new PessoaVisual();
	private VeiculoControle controleVeiculos = new VeiculoControle();
	private TurmaControle controleTurma = new TurmaControle();
	
	
	
	// METODOS
	public void login() {
		boolean ehSair = false;
		
		while (!ehSair) {
			visual.login();
			
			if (modelo.autenticarLogin(visual.getCampoUsuario(), visual.getCampoSenha())) {
				if (modelo.getTipoPessoa() == TpPessoa.FUNCIONARIO) {
					funcionario();
				}
				
				if (modelo.getTipoPessoa() == TpPessoa.ALUNO) {
					aluno();
				}
			} else {
				visual.advertenciaAutenticacao();
			}
		}
	}
	
	public void funcionario() {
		boolean ehSair = false;
		
		while(!ehSair) {
			switch (visual.funcionario(modelo.getNome())) {
			case 1: {
				gerenciamentoAluno();
	
				break;
			}
			
			case 2: {
				gerenciamentoFuncionario();
	
				break;
			}
			
			case 3: {
				controleVeiculos.gerenciamentoVeiculo();
				
				break;
			}
			
			case 4: {
				controleTurma.gerenciamentoTurma();
				
				break;
			}
			
			case 5: {
				controleTurma.getModelo().carregarTurmasProfessor(modelo.getHandle());
				controleTurma.getVisual().setCampoListaTurmas(controleTurma.getModelo().getListaTurmas());
				controleTurma.getVisual().minhasTurmas();
				
				
				break;
			}
			
			case 6: {
				PessoaControle pessoa = new PessoaControle();
				
				pessoa.visual = new PessoaVisual();
				pessoa.modelo = new PessoaModelo();
				
				pessoa.visual.emitirCertificado();
				pessoa.modelo.setCpf(pessoa.visual.getCampoCpf());
				pessoa.modelo.buscarAluno();
				
				pessoa.visual.setCampoTipoPessoa(pessoa.modelo.getTipoPessoa() == TpPessoa.ALUNO ? 1 : 2);
				pessoa.visual.setCampoNotaPratica(pessoa.modelo.getNotaPratica());
				pessoa.visual.setCampoNotaTeorica(pessoa.modelo.getNotaTeorica());
				pessoa.visual.setCampoNome(pessoa.modelo.getNome());
				pessoa.visual.certificado();
				
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
	
	public void gerenciamentoAluno() {
		boolean ehSair = false;
		
		while(!ehSair) {
			switch (visual.gerenciamentoAluno()) {
			
			case 1: {
				novoAluno();
				
				break;
			}
			
			case 2: {
				alterarAluno();
				
				break;
			}
			
			case 3: {
				excluirAluno();
				
				break;
			}
			
			case 4: {
				modelo.carregarListaAlunos();
				visual.listarAlunos(modelo.getListaAlunos());
				
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
	
	public void excluirAluno() {
		boolean ehSair = false;
		
		PessoaControle aluno = new PessoaControle();
		
		while (!ehSair) {
			aluno.visual.excluir();
			VisualConfig.linhaSeparacao();
			
			aluno.modelo.setCpf(aluno.visual.getCampoCpf());
			
			if (aluno.getModelo().buscarAluno()) {
				if (VisualConfig.retornaInt("[1] EXCLUIR OU [2] VOLTAR [1 / 2]: ") == 1) {
					if (aluno.getModelo().excluir()) {
						VisualConfig.mensagem("ALUNO EXCLUIDO COM SUCESSO", TpMensagem.SUCESSO);
						ehSair = true;
						
						VisualConfig.limparTela();
						VisualConfig.linhaSeparacao();
						if (VisualConfig.retornaInt("[1] EXCLUIR OUTRO ALUNO OU [2] VOLTAR [1 / 2]: ") == 1) {
							excluirAluno();
							ehSair = true;
						}
					}
				} else {
					ehSair = true;
				}
			} else {
				VisualConfig.mensagem("NAO ENCONTROU O ALUNO ESPECIFICADO", TpMensagem.ADVERTENCIA);
				VisualConfig.linhaSeparacao();
				
				if (VisualConfig.retornaInt("[1] EXCLUIR OUTRO ALUNO OU [2] VOLTAR [1 / 2]: ") == 1) {
					excluirAluno();
					ehSair = true;
				} else {
					ehSair = true;
				}
			}
		}
	}
	
	public void alterarAluno() {
		boolean ehSair = false;
		int voltar = 0;
		PessoaControle aluno = new PessoaControle();
		
		while (!ehSair) {
			aluno.visual.alterar();
			aluno.modelo.setCpf(aluno.visual.getCampoCpf());
			
			if (aluno.modelo.buscarAluno()) {
				aluno.visual.setCampoCpf(aluno.modelo.getCpf());
				aluno.visual.setCampoNome(aluno.modelo.getNome());
				aluno.visual.setCampoDataNascimento(Utils.converterDataParaBrasil(aluno.modelo.getDataNascimento()));
				aluno.visual.setCampoTelefone(aluno.modelo.getTelefone());
				aluno.visual.setCampoEmail(aluno.modelo.getEmail());
				aluno.visual.setCampoUsuario(aluno.modelo.getUsuario());
				aluno.visual.setCampoSenha(aluno.modelo.getSenha());
				
				VisualConfig.textoAlinhado("ALTERACAO DE ALUNOS", TpAlinhamento.CENTRO);
				VisualConfig.linhaSeparacao();
				aluno.visual.mostrarCamposDigitados();
				VisualConfig.textoAlinhado("[1] DIGITAR DADOS ALTERADOS", TpAlinhamento.ESQUERDA);
				VisualConfig.textoAlinhado("[2] SELECIONAR OUTRO USUARIO", TpAlinhamento.ESQUERDA);
				VisualConfig.textoAlinhado("[?] VOLTAR GERENCIAMENTO DE ALUNOS", TpAlinhamento.ESQUERDA);
				VisualConfig.linhaSeparacao();
				
				switch (VisualConfig.retornaInt("DIGITE SUA OPÇÃO: ") ) {
				case 1: {
					VisualConfig.limparTela();
					aluno.visual.mostrarCamposDigitados();
					aluno.visual.digitarAluno(TpInsercao.ALTERAR);
					
					boolean habilitado = true;
					ArrayList<String> campos = new ArrayList<String>();
					
					if (aluno.visual.getCampoNome().equals("")) {
						campos.add("NOME");
						habilitado = habilitado && false;
					}
					
					if (aluno.visual.getCampoCpf().equals("")) {
						campos.add("CPF");
						habilitado = habilitado && false;
					}
					
					if (aluno.visual.getCampoDataNascimento().equals("")) {
						campos.add("DATA NASCIMENTO");
						habilitado = habilitado && false;
					}
					 
					if (aluno.visual.getCampoTelefone().equals("")) {
						campos.add("TELEFONE");
						habilitado = habilitado && false;
					}
					
					if (aluno.visual.getCampoEmail().equals("")) {
						campos.add("EMAIL");
						habilitado = habilitado && false;
					}
					
					if (aluno.visual.getCampoUsuario().equals("")) {
						campos.add("USUARIO");
						habilitado = habilitado && false;
					}
					
					if (aluno.visual.getCampoSenha().equals("")) {
						campos.add("SENHA");
						habilitado = habilitado && false;
					}

					
					if (!habilitado) {
						aluno.visual.getMensagemPreenchimentos(campos.toArray(new String[campos.size()]));
						VisualConfig.linhaSeparacao();
						voltar = VisualConfig.retornaInt("[1] DIGITAR NOVAMENTE OU [2] VOLTAR [1 / 2]: ");
					} else {
						aluno.visual.mostrarCamposDigitados();
						voltar = VisualConfig.retornaInt("[1] SALVAR ou [2] DESCARTAR ALTERACOES: [1 / 2]: ");
					}
					
					if ((habilitado) && (voltar == 1)) {
						aluno.modelo.setCpf(aluno.visual.getCampoCpf());
						aluno.modelo.setNome(aluno.visual.getCampoNome());
						aluno.modelo.setDataNascimento(aluno.visual.getCampoDataNascimento());
						aluno.modelo.setEmail(aluno.visual.getCampoEmail());
						aluno.modelo.setTelefone(aluno.visual.getCampoTelefone());
						
						aluno.modelo.setUsuario(aluno.visual.getCampoUsuario());
						aluno.modelo.setSenha(aluno.visual.getCampoSenha());
						
						if (aluno.modelo.alterar()) {
							VisualConfig.mensagem("SUCESSO AO ALTERAR ALUNO", TpMensagem.SUCESSO);
							ehSair = true;
						} else {
							VisualConfig.mensagem("NAO FOI POSSIVEL ALTERAR O ALUNO", TpMensagem.ERRO);
						}
					}
					
					if (voltar == 2) {
						ehSair = true;
					}
					
					break;
				}
				
				case 2: {
					
					break;
				}
				
				default:
					ehSair = true;
				}
			} else {
				VisualConfig.mensagem("NÃO ENCONTROU O ALUNO COM O CPF ESPECIFICADO", TpMensagem.ADVERTENCIA);
			}
			
			
		}
	}
	
	public void alunoAlterarAluno() {
		boolean ehSair = false;
		int voltar = 0;
		
		while (!ehSair) {
			visual.alunoAlterar();
			
			if (modelo.buscarAluno()) {
				visual.setCampoCpf(modelo.getCpf());
				visual.setCampoNome(modelo.getNome());
				visual.setCampoDataNascimento(Utils.converterDataParaBrasil(modelo.getDataNascimento()));
				visual.setCampoTelefone(modelo.getTelefone());
				visual.setCampoEmail(modelo.getEmail());
				visual.setCampoUsuario(modelo.getUsuario());
				visual.setCampoSenha(modelo.getSenha());
				
				VisualConfig.textoAlinhado("ALTERACAO DE ALUNOS", TpAlinhamento.CENTRO);
				VisualConfig.linhaSeparacao();
				visual.mostrarCamposDigitados();
				VisualConfig.textoAlinhado("[1] DIGITAR DADOS ALTERADOS", TpAlinhamento.ESQUERDA);
				VisualConfig.textoAlinhado("[?] VOLTAR GERENCIAMENTO DE ALUNOS", TpAlinhamento.ESQUERDA);
				VisualConfig.linhaSeparacao();
				
				switch (VisualConfig.retornaInt("DIGITE SUA OPÇÃO: ") ) {
				case 1: {
					VisualConfig.limparTela();
					visual.mostrarCamposDigitados();
					visual.digitarAluno(TpInsercao.ALTERAR);
					
					boolean habilitado = true;
					ArrayList<String> campos = new ArrayList<String>();
					
					if (visual.getCampoNome().equals("")) {
						campos.add("NOME");
						habilitado = habilitado && false;
					}
					
					if (visual.getCampoCpf().equals("")) {
						campos.add("CPF");
						habilitado = habilitado && false;
					}
					
					if (visual.getCampoDataNascimento().equals("")) {
						campos.add("DATA NASCIMENTO");
						habilitado = habilitado && false;
					}
					 
					if (visual.getCampoTelefone().equals("")) {
						campos.add("TELEFONE");
						habilitado = habilitado && false;
					}
					
					if (visual.getCampoEmail().equals("")) {
						campos.add("EMAIL");
						habilitado = habilitado && false;
					}
					
					if (visual.getCampoUsuario().equals("")) {
						campos.add("USUARIO");
						habilitado = habilitado && false;
					}
					
					if (visual.getCampoSenha().equals("")) {
						campos.add("SENHA");
						habilitado = habilitado && false;
					}

					
					if (!habilitado) {
						visual.getMensagemPreenchimentos(campos.toArray(new String[campos.size()]));
						VisualConfig.linhaSeparacao();
						voltar = VisualConfig.retornaInt("[1] DIGITAR NOVAMENTE OU [2] VOLTAR [1 / 2]: ");
					} else {
						visual.mostrarCamposDigitados();
						voltar = VisualConfig.retornaInt("[1] SALVAR ou [2] DESCARTAR ALTERACOES: [1 / 2]: ");
					}
					
					if ((habilitado) && (voltar == 1)) {
						modelo.setCpf(visual.getCampoCpf());
						modelo.setNome(visual.getCampoNome());
						modelo.setDataNascimento(visual.getCampoDataNascimento());
						modelo.setEmail(visual.getCampoEmail());
						modelo.setTelefone(visual.getCampoTelefone());
						
						modelo.setUsuario(visual.getCampoUsuario());
						modelo.setSenha(visual.getCampoSenha());
						
						if (modelo.alterar()) {
							VisualConfig.mensagem("SUCESSO AO ALTERAR ALUNO", TpMensagem.SUCESSO);
							ehSair = true;
						} else {
							VisualConfig.mensagem("NAO FOI POSSIVEL ALTERAR O ALUNO", TpMensagem.ERRO);
						}
					}
					
					if (voltar == 2) {
						ehSair = true;
					}
					
					break;
				}
				
				default:
					ehSair = true;
				}
			} else {
				VisualConfig.mensagem("NÃO ENCONTROU O ALUNO COM O CPF ESPECIFICADO", TpMensagem.ADVERTENCIA);
			}
		}
	}
	
	public void novoAluno() {
		boolean ehSair = false;
		int voltar = 2;
		
		
		PessoaControle aluno = new PessoaControle();
		
		while (!ehSair) {
			aluno.visual.digitarAluno(TpInsercao.NOVO);
			
			boolean habilitado = true;
			ArrayList<String> campos = new ArrayList<String>();
			
			if (aluno.visual.getCampoNome().equals("")) {
				campos.add("NOME");
				habilitado = habilitado && false;
			}
			
			if (aluno.visual.getCampoCpf().equals("")) {
				campos.add("CPF");
				habilitado = habilitado && false;
			}
			
			if (aluno.visual.getCampoDataNascimento().equals("")) {
				campos.add("DATA NASCIMENTO");
				habilitado = habilitado && false;
			}
			 
			if (aluno.visual.getCampoTelefone().equals("")) {
				campos.add("TELEFONE");
				habilitado = habilitado && false;
			}
			
			if (aluno.visual.getCampoEmail().equals("")) {
				campos.add("EMAIL");
				habilitado = habilitado && false;
			}
			
			if (aluno.visual.getCampoUsuario().equals("")) {
				campos.add("USUARIO");
				habilitado = habilitado && false;
			}
			
			if (aluno.visual.getCampoSenha().equals("")) {
				campos.add("SENHA");
				habilitado = habilitado && false;
			}
			
			
			if (!habilitado) {
				aluno.visual.getMensagemPreenchimentos(campos.toArray(new String[campos.size()]));
				VisualConfig.linhaSeparacao();
				voltar = VisualConfig.retornaInt("[1] DIGITAR NOVAMENTE OU [2] VOLTAR [1 / 2]: ");
			} else {
				aluno.visual.mostrarCamposDigitados();
				voltar = VisualConfig.retornaInt("[1] SALVAR ou [2] DESCARTAR ALTERACOES: [1 / 2]: ");
			}
			
			ehSair = habilitado;
			
			if (voltar == 2) {
				ehSair = true;
			}
		}
		
		if (voltar == 1) {
			aluno.modelo.setCpf(aluno.visual.getCampoCpf());
			aluno.modelo.setNome(aluno.visual.getCampoNome());
			aluno.modelo.setDataNascimento(aluno.visual.getCampoDataNascimento());
			aluno.modelo.setTelefone(aluno.visual.getCampoTelefone());
			aluno.modelo.setEmail(aluno.visual.getCampoEmail());
			aluno.modelo.setUsuario(aluno.visual.getCampoUsuario());
			aluno.modelo.setSenha(aluno.visual.getCampoSenha());
			aluno.modelo.setTipoPessoa(TpPessoa.ALUNO);
			aluno.modelo.setValor(200);
			aluno.modelo.setQuantParcelas(10);
			
			
			if (!aluno.modelo.verificarExistencia()) {
				if (!aluno.modelo.salvar()) {
					VisualConfig.mensagem("ERRO AO CADASTRAR ALUNO! ",  TpMensagem.ERRO);
				} else {
					VisualConfig.mensagem("ALUNO CADATRADO COM SUCESSO ",  TpMensagem.SUCESSO);
					
					VisualConfig.limparTela();
					VisualConfig.linhaSeparacao();
					if (VisualConfig.retornaTexto("CADASTRAR NOVO ALUNO? [S / N]: ").equals("S")) {
						this.novoAluno();
					}
				}
			} else {
				VisualConfig.mensagem("ALUNO JA CADASTRADO NO SISTEMA!", TpMensagem.ADVERTENCIA);
			}
		}
	}
	
	public void gerenciamentoFuncionario() {
		boolean ehSair = false;
		
		while(!ehSair) {
			switch (visual.gerenciamentoFuncionario()) {
			
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
	
	public void aluno() {
		boolean ehSair = false;
		
		while(!ehSair) {
			switch (visual.aluno(modelo.getNome())) {
			
			case 1: {
				progresso();
				
				break;
			}
			
			case 2: {
				verificarPagamentos();	
				
				break;
			}
			
			case 3: {
				alunoAlterarAluno();
				
				break;
			}
			
			case 4: {
				emitirCertificado();
				
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
	
	
	
	private void emitirCertificado() {
		PessoaControle pessoa = new PessoaControle();
		
		pessoa.visual = new PessoaVisual();
		pessoa.modelo = new PessoaModelo();
		
		pessoa.modelo.setCpf(modelo.getCpf());
		
		pessoa.visual.setCampoTipoPessoa(modelo.getTipoPessoa() == TpPessoa.ALUNO ? 1 : 2);
		pessoa.visual.setCampoNotaPratica(modelo.getNotaPratica());
		pessoa.visual.setCampoNotaTeorica(modelo.getNotaTeorica());
		pessoa.visual.setCampoCpf(modelo.getCpf());
		pessoa.visual.setCampoNome(modelo.getNome());
		pessoa.visual.certificado();
	}

	private void progresso() {
		visual.setCampoNotaPratica(modelo.getNotaPratica());
		visual.setCampoNotaTeorica(modelo.getNotaTeorica());
		
		visual.progresso();
	}

	private void verificarPagamentos() {
		boolean ehSair = false;
		
		while (!ehSair) {
			visual.setCampoNome(modelo.getNome());
			visual.setCampoCpf(modelo.getCpf());
			visual.setCampoQuantParcelas(modelo.getQuantParcelas());
			visual.setCampoValorParcelas(modelo.getValor());
			
			modelo.carregarListaParcelas();
			
			
			ehSair = visual.verificarPagamentos(modelo.getListaParcelas());
		}
	}

	// GETTERS AND SETTERS
	public PessoaModelo getModelo() {
		return modelo;
	}
	public void setModelo(PessoaModelo modelo) {
		this.modelo = modelo;
	}
	public PessoaVisual getVisual() {
		return visual;
	}
	public void setVisual(PessoaVisual visual) {
		this.visual = visual;
	}
	
		
		
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PessoaControle [modelo=");
		builder.append(modelo);
		builder.append(", visual=");
		builder.append(visual);
		builder.append("]");
		return builder.toString();
	}	
	
	
//	TODO MELHORAR ESSE CODIGO PARA PODER NO FUTURO FAZER CAMPOS OBRIGATORIO E NAO OBRIGATORIOS NO METODO NOVO
//	Class<?> classe = visual.getClass();
//	Field[] campos = classe.getDeclaredFields();
//	
//	 for (Field campo : campos) {
//		campo.setAccessible(true);
//		 
//		if (campo.getType().equals(String.class)) {
//			try {
//				if (campo.get(visual).toString().trim().equals("")) {
//					String nomeCampo = campo.getName();
//					String[] partes = nomeCampo.split("campo");
//					
//					
//					visual.getMensagemPreenchimentos(partes[1].toUpperCase());
//					habilitado = habilitado && false;
//				}
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
}
