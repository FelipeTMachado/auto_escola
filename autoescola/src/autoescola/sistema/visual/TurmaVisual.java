package autoescola.sistema.visual;

import java.util.ArrayList;

import autoescola.sistema.modelo.PessoaModelo;
import autoescola.sistema.modelo.TurmaModelo;
import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpAlinhamento;
import autoescola.tecnologia.tipos.TpEscape;
import autoescola.tecnologia.tipos.TpMensagem;

public class TurmaVisual extends Visual{
	private int campoHandle;
	private String campoDataInicio;
	private PessoaModelo campoInstrutor;
	private String campoCategoria;
	private String campoHorario;
	private ArrayList<PessoaModelo> campoAlunos = new ArrayList<PessoaModelo>();
	private ArrayList<TurmaModelo> campoListaTurmas = new ArrayList<TurmaModelo>();
	
	
	// METODOS
	public int gerenciamentoTurma() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("GERENCIAMENTO DE TURMAS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("1 - NOVA", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("2 - ALTERAR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("3 - EXCLUIR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("4 - LISTAR TURMA", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("5 - ALUNOS", TpAlinhamento.ESQUERDA);
		
		VisualConfig.opcaoEscape(TpEscape.VOLTAR, TpEscape.SAIR);
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("DIGITE SUA OPCAO: ");
		return retorno;
	}
	
	public int listarTurmas() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("LISTANDO TURMAS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.linhaSeparacao();
		VisualConfig.textoAlinhado("DATA INICIO | HORARIO  | CATEGORIA | INSTRUTOR", TpAlinhamento.ESQUERDA);
		VisualConfig.linhaSeparacao();
		
		for (TurmaModelo turma: campoListaTurmas) {
			VisualConfig.textoAlinhado(turma.getDataInicio() + "  | " +
					turma.getHorario() + " | " +
					(turma.getCategoria().trim().length() == 1 ? turma.getCategoria().trim() + " " : turma.getCategoria().trim()) + "        | " +
					turma.getInstrutor().getNome(), TpAlinhamento.ESQUERDA);
		}
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("SELECIONAR TURMA OU [0] PARA VOLTAR: ");
		return retorno; 
	}
	
	public void mostraCamposDigitados() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("VERIFIQUE OS CAMPOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		
		VisualConfig.textoAlinhado("DATA INICIO: ............ " + getCampoDataInicio(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("CATEGORIA: .............. " + getCampoCategoria(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("HORA INICIO: ............ " + getCampoHorario(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("INSTRUTOR ............... " + getCampoInstrutor().getNome(), TpAlinhamento.ESQUERDA);
		
		VisualConfig.linhaSeparacao();
	}
	
	public boolean novaTurma() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("NOVA TURMA", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		setCampoDataInicio(VisualConfig.retornaTexto("DATA INICIO: "));
		setCampoCategoria(VisualConfig.retornaTexto("CATEGORIA: "));
		setCampoHorario(VisualConfig.retornaTexto("HORA DE INICIO: "));
		
		boolean ehSair = false;
		PessoaModelo instrutor;
		
		
		
		do {
			instrutor = new PessoaModelo();
			instrutor.buscarInstrutor(VisualConfig.retornaTexto("CPF DO INSTRUTOR: "));
			
			if (instrutor.getCpf() == null) {
				VisualConfig.limparTela();
				VisualConfig.linhaSeparacao();
				VisualConfig.textoAlinhado("NAO ENCONTROU O INSTRUTOR SELECIONADO", TpAlinhamento.CENTRO, TpMensagem.ADVERTENCIA);
				VisualConfig.linhaSeparacao();
				ehSair = VisualConfig.retornaInt("[1] DIGITAR NOVAMENTE O INSTRUTOR OU [2] SAIR: ") == 2;
			} else {
				setCampoInstrutor(instrutor);
				return true;
			}
		} while (!ehSair);
		
		return false;
	}
	
	public String minhasTurmas() {	
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("MINHAS TURMAS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.linhaSeparacao();
		VisualConfig.textoAlinhado("DATA INICIO | HORARIO  | CATEGORIA ", TpAlinhamento.ESQUERDA);
		VisualConfig.linhaSeparacao();
		
		for (TurmaModelo turma: campoListaTurmas) {
			VisualConfig.textoAlinhado(turma.getDataInicio() + "  | " +
					turma.getHorario() + " | " +
					(turma.getCategoria().trim().length() == 1 ? turma.getCategoria().trim() + " " : turma.getCategoria().trim()),
					TpAlinhamento.ESQUERDA);
		}
		VisualConfig.linhaSeparacao();
		String retorno = VisualConfig.retornaTexto("DIGITE A DATA PARA SELECIONAR TURMA OU [0] PARA VOLTAR: ");
		
		
		return retorno; 	
	} 

	public void adicionarAlunosTurma() {
		
	}
	
	// GETTERS AND SETTERS
	public void setListaTurmas(ArrayList<TurmaModelo> campoListaTurmas) {
		this.campoListaTurmas = campoListaTurmas;
	}
	public int getCampoHandle() {
		return campoHandle;
	}
	public void setCampoHandle(int campoHandle) {
		this.campoHandle = campoHandle;
	}
	public String getCampoDataInicio() {
		return campoDataInicio;
	}
	public void setCampoDataInicio(String campoDataInicio) {
		this.campoDataInicio = campoDataInicio;
	}
	public PessoaModelo getCampoInstrutor() {
		return campoInstrutor;
	}
	public void setCampoInstrutor(PessoaModelo campoInstrutor) {
		this.campoInstrutor = campoInstrutor;
	}
	public String getCampoCategoria() {
		return campoCategoria;
	}
	public void setCampoCategoria(String campoCategoria) {
		this.campoCategoria = campoCategoria;
	}
	public String getCampoHorario() {
		return campoHorario;
	}
	public void setCampoHorario(String campoHorario) {
		this.campoHorario = campoHorario;
	}
	public ArrayList<PessoaModelo> getCampoAlunos() {
		return campoAlunos;
	}
	public void setCampoAlunos(ArrayList<PessoaModelo> campoAlunos) {
		this.campoAlunos = campoAlunos;
	}
	public ArrayList<TurmaModelo> getCampoListaTurmas() {
		return campoListaTurmas;
	}
	public void setCampoListaTurmas(ArrayList<TurmaModelo> campoListaTurmas) {
		this.campoListaTurmas = campoListaTurmas;
	}

	
}
