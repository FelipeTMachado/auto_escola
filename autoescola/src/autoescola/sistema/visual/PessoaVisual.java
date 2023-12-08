package autoescola.sistema.visual;

import java.util.ArrayList;

import autoescola.sistema.modelo.ParcelasModelo;
import autoescola.sistema.modelo.PessoaModelo;
import autoescola.tecnologia.configuracao.VisualConfig;
import autoescola.tecnologia.tipos.TpAlinhamento;
import autoescola.tecnologia.tipos.TpEscape;
import autoescola.tecnologia.tipos.TpInsercao;
import autoescola.tecnologia.tipos.TpMensagem;

public class PessoaVisual extends Visual {
	// ATRIBUTOS
	private String campoNome;
	private String campoCpf;
	private String campoDataNascimento;
	private String campoTelefone = "NULL";
	private String campoEmail;
	private int campoTipoPessoa;
	
	private int campoQuantParcelas;
	private float campoValorParcelas;
	
	private float campoNotaPratica;
	private float campoNotaTeorica;
	
	private String campoUsuario;
	private String campoSenha;
	
	
	
	// METODOS
	public void login() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("LOGIN", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		setCampoUsuario(VisualConfig.retornaTexto("USUARIO: "));
		setCampoSenha(VisualConfig.retornaPassword("SENHA: "));
	}
	
	public int funcionario(String prNome) {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("FUNCIONARIO: " + prNome, TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("1 - ALUNOS",       TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("2 - FUNCIONARIOS", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("3 - VEICULOS",     TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("4 - TURMAS" ,      TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("5 - MINHAS TURMAS", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("6 - EMITIR CERTIFICADO", TpAlinhamento.ESQUERDA);
		
		VisualConfig.opcaoEscape(TpEscape.RELOGAR, TpEscape.SAIR);
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("DIGITE SUA OPCAO: ");
		return retorno;
	}
	
	public int gerenciamentoFuncionario() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("GERENCIAMENTO FUNCIONARIOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("1 - NOVO", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("2 - ALTERAR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("3 - EXCLUIR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("4 - MEU PERFIL", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("5 - LISTAR FUNCIONARIOS", TpAlinhamento.ESQUERDA);
		
		VisualConfig.opcaoEscape(TpEscape.VOLTAR, TpEscape.SAIR);
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("DIGITE SUA OPCAO: ");
		return retorno;
	}
	
	public void mostrarCamposDigitados() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("VERIFIQUE OS CAMPOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("NOME .................... " + getCampoNome(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("CPF ..................... " + getCampoCpf(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("DATA NASCIMENTO ......... " + getCampoDataNascimento(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("TELEFONE ................ " + getCampoTelefone(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("EMAIL ................... " + getCampoEmail(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("USUARIO ................. " + getCampoUsuario(), TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("SENHA ................... " + getCampoSenha(), TpAlinhamento.ESQUERDA);
		
		VisualConfig.linhaSeparacao();
	}
	
	public void progresso() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("PROGRESSO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.linhaSeparacao();
		VisualConfig.textoAlinhado("NOTA TEORICA | NOTA PRATICA | SITUACAO ", TpAlinhamento.ESQUERDA);
		VisualConfig.linhaSeparacao();
		VisualConfig.textoAlinhado(getCampoNotaTeorica() + "          | " + 
		                           getCampoNotaPratica() + "          |" + 
				                   (((getCampoNotaPratica() >= 7) && (getCampoNotaTeorica() >= 7)) ? " APROVADO": " REPROVADO" ), TpAlinhamento.ESQUERDA);
		VisualConfig.linhaSeparacao();
		VisualConfig.retornaInt("DIGITE ENTER PARA SAIR...");
	}
	
	public int gerenciamentoAluno() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("GERENCIAMENTO ALUNOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("1 - NOVO", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("2 - ALTERAR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("3 - EXCLUIR", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("4 - LISTAR ALUNOS", TpAlinhamento.ESQUERDA);
		
		VisualConfig.opcaoEscape(TpEscape.VOLTAR, TpEscape.SAIR);
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("DIGITE SUA OPCAO: ");
		return retorno;
	}
	
	public void listarAlunos(ArrayList<PessoaModelo> prAlunos) {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("LISTA DE ALUNOS", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		getlinhaSeparadorTabela(20);
		VisualConfig.textoAlinhado("CPF                | NOME", TpAlinhamento.ESQUERDA);
		
		getlinhaSeparadorTabela(20);
		for (PessoaModelo aluno: prAlunos) {
			VisualConfig.textoAlinhado(aluno.getCpf() + "        | " + aluno.getNome(), TpAlinhamento.ESQUERDA);
		}
		getlinhaSeparadorTabela(20);
		
		VisualConfig.retornaTexto("DIGITE ENTER PARA CONTINUAR...");
	}
	
	public int aluno(String prNome) {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("ALUNO: " + prNome, TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.textoAlinhado("1 - PROGRESSO", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("2 - PAGAMENTOS", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("3 - ALTERAR DADOS", TpAlinhamento.ESQUERDA);
		VisualConfig.textoAlinhado("4 - EMITIR CERTIFICADO", TpAlinhamento.ESQUERDA);
		
		VisualConfig.opcaoEscape(TpEscape.RELOGAR, TpEscape.SAIR);
		VisualConfig.linhaSeparacao();
		int retorno = VisualConfig.retornaInt("DIGITE SUA OPCAO: ");
		return retorno;
	}
	
	public boolean verificarPagamentos(ArrayList<ParcelasModelo> parcelas) {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("ALUNO: " + getCampoNome(), TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		VisualConfig.linhaSeparacao();
		VisualConfig.textoAlinhado("NUMERO | DATA            | STATUS ", TpAlinhamento.ESQUERDA);
		VisualConfig.linhaSeparacao();
		
		for (ParcelasModelo parcela : parcelas) {
			VisualConfig.textoAlinhado(String.format("%s", parcela.getNumeroParcela()) + "      | " + 
					                   parcela.getDataParcela() + "      |" +
					                   (parcela.getPago() == 1 ? " Pago": " Pendente"), TpAlinhamento.ESQUERDA);
		}
		
		VisualConfig.linhaSeparacao();
		int numero = VisualConfig.retornaInt("[0..9] PAGAR PARCELA OU [99] SAIR: ");
		
		for (ParcelasModelo parcela : parcelas) {
			if (parcela.getNumeroParcela() == numero) {
				if (parcela.getPago() == 0) {
					if (parcela.pagar()) {
						VisualConfig.mensagem(String.format("PAGAMENTO DA PARCELA %s REALIZADO", parcela.getDataParcela()), TpMensagem.SUCESSO);
						return false;
					} else {
						VisualConfig.mensagem("NAO FOI POSSIVEL PAGAR", TpMensagem.SUCESSO);
					}
				} else {
					VisualConfig.mensagem("PARCELA NAO ESTA PENDENTE", TpMensagem.ADVERTENCIA);
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void alunoAlterar() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("ALTERACAO DO ALUNO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
	}
	
	public void alterar() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("ALTERACAO DO ALUNO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		setCampoCpf(VisualConfig.retornaTexto("DIGITE O CPF DO ALUNO: "));
	}
	
	public void excluir() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("EXCLUSAO DO ALUNO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		setCampoCpf(VisualConfig.retornaTexto("DIGITE O CPF DO ALUNO: "));
	}
	
	public void digitarAluno(TpInsercao prTipoInsercao) {
		if ((prTipoInsercao == TpInsercao.NOVO)) {
			VisualConfig.limparTela();
		}
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("DIGITANDO DADOS DO ALUNO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		setCampoNome(VisualConfig.retornaTexto("NOME: "));
		if (prTipoInsercao == TpInsercao.NOVO) {
			setCampoCpf(VisualConfig.retornaTexto("CPF: "));
		}
		setCampoDataNascimento(VisualConfig.retornaTexto("DATA NASCIMENTO: "));
		setCampoTelefone(VisualConfig.retornaTexto("TELEFONE: "));
		setCampoEmail(VisualConfig.retornaTexto("EMAIL: "));
		setCampoUsuario(VisualConfig.retornaTexto("USUARIO: "));
		setCampoSenha(VisualConfig.retornaPassword("SENHA: "));
	}
	
	public void advertenciaAutenticacao() {
		VisualConfig.limparTela();
		VisualConfig.mensagem("USUARIO OU SENHA INVÁLIDOS", TpMensagem.ADVERTENCIA);
	}
	
	public void emitirCertificado() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("EMISSAO DE CERTIFICADO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		setCampoCpf(VisualConfig.retornaTexto("DIGITE O CPF: "));
	}
	
	public void certificado() {
		VisualConfig.limparTela();
		VisualConfig.titulo("SISTEMA AUTOESCOLA");
		VisualConfig.textoAlinhado("CERTIFICADO", TpAlinhamento.CENTRO);
		VisualConfig.linhaEspaco();
		
		String texto = String.format("O(A) ALUNO(A) %s COM CPF %s ", campoNome, campoCpf);
		
		if (getCampoTipoPessoa() == 1) {
			if (getCampoNotaPratica() >= 7 && getCampoNotaTeorica() >= 7) {
				VisualConfig.textoAlinhado(texto, TpAlinhamento.CENTRO);
				VisualConfig.textoAlinhado("CONCLUIU O TREINAMENTO DE AUTOESCOLA", TpAlinhamento.CENTRO);
				VisualConfig.textoAlinhado("E ESTA APTO A DIRIGIR VEICULOS DENTRO DO", TpAlinhamento.CENTRO);
				VisualConfig.textoAlinhado("TERRITORIO BRASILEIRO ATE A DATA DE 24/01/2028", TpAlinhamento.CENTRO);
				VisualConfig.linhaSeparacao();
				VisualConfig.retornaInt("DIGITE ENTER PARA CONTINUAR...");
			} else {
				VisualConfig.mensagem(texto + " NAO ESTA APTO", TpMensagem.ADVERTENCIA);
			}
		} else {
			VisualConfig.mensagem("ALUNO DIGITADO NAO EXISTE", TpMensagem.ADVERTENCIA);
		}
	}
	

	
	// GETTERS AND SETTERS
	public String getCampoUsuario() {
		return campoUsuario;
	}
	public void setCampoUsuario(String campoUsuario) {
		this.campoUsuario = campoUsuario;
	}
	public String getCampoSenha() {
		return campoSenha;
	}
	public void setCampoSenha(String campoSenha) {
		this.campoSenha = campoSenha;
	}
	public String getCampoNome() {
		return campoNome;
	}
	public void setCampoNome(String campoNome) {
		this.campoNome = campoNome;
	}
	public String getCampoCpf() {
		return campoCpf;
	}
	public void setCampoCpf(String campoCpf) {
		this.campoCpf = campoCpf;
	}
	public String getCampoDataNascimento() {
		return campoDataNascimento;
	}
	public void setCampoDataNascimento(String campoDataNascimento) {
		this.campoDataNascimento = campoDataNascimento;
	}
	public String getCampoTelefone() {
		return campoTelefone;
	}
	public void setCampoTelefone(String campoTelefone) {
		this.campoTelefone = campoTelefone;
	}
	public String getCampoEmail() {
		return campoEmail;
	}
	public void setCampoEmail(String campoEmail) {
		this.campoEmail = campoEmail;
	}
	public int getCampoTipoPessoa() {
		return campoTipoPessoa;
	}
	public void setCampoTipoPessoa(int campoTipoPessoa) {
		this.campoTipoPessoa = campoTipoPessoa;
	}
	public int getCampoQuantParcelas() {
		return campoQuantParcelas;
	}
	public void setCampoQuantParcelas(int campoQuantParcelas) {
		this.campoQuantParcelas = campoQuantParcelas;
	}
	public float getCampoValorParcelas() {
		return campoValorParcelas;
	}
	public void setCampoValorParcelas(float campoValorParcelas) {
		this.campoValorParcelas = campoValorParcelas;
	}
	public float getCampoNotaPratica() {
		return campoNotaPratica;
	}
	public void setCampoNotaPratica(float campoNotaPratica) {
		this.campoNotaPratica = campoNotaPratica;
	}
	public float getCampoNotaTeorica() {
		return campoNotaTeorica;
	}
	public void setCampoNotaTeorica(float campoNotaTeorica) {
		this.campoNotaTeorica = campoNotaTeorica;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PessoaVisual [campoUsuario=");
		builder.append(campoUsuario);
		builder.append(", campoSenha=");
		builder.append(campoSenha);
		builder.append("]");
		return builder.toString();
	}

	
}
