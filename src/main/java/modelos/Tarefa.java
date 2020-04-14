package modelos;

import java.io.Serializable;
import java.util.Random;

import agentes.Programador;
import agentes.Testador;
import main.Main;

public class Tarefa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int duracao;
	private int prioridade;
	private Nivel nivel;
	private Programador programador;
	private Testador testador;
	private TarefaStatus status;
	private int tempoGasto;
	private int tempoTeste;
	private int duracaoTeste;
	
	public Tarefa() {
		this.id = Main.getTarefaId();
		Random random = new Random();
		this.nivel = Nivel.getByValor(random.nextInt(3)+1);
		this.duracao = (random.nextInt(4) * nivel.getValor())+2;
		this.duracaoTeste = duracao /2;
		this.prioridade = random.nextInt(5)+1 ;
		this.programador = null;
		this.testador = null;
		this.status = TarefaStatus.PENDENTE;
		this.tempoGasto = 0;
		this.tempoTeste = 0;

	}
	
	public int getDuracao() {
		return duracao;
	}

	public int getDuracaoTeste(){ return duracaoTeste;}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Programador getProgramador() {
		return programador;
	}

	public void setProgramador(Programador programador) {
		this.programador = programador;
	}
	
	public Testador getTestador() {
		return testador;
	}

	public void setTestador(Testador testador) {
		this.testador = testador;
	}

	public TarefaStatus getStatus() {
		return status;
	}

	public void setStatus(TarefaStatus status) {
		System.out.println("Tarefa "+ id+ "  de status "+ this.status + " alterado para "+ status);
		this.status = status;
	}

	public int getTempoGasto() {
		return tempoGasto;
	}

	public void setTempoGasto(int tempoGasto) {
		this.tempoGasto = tempoGasto;
	}

	public int getTempoTeste() {
		return tempoTeste;
	}

	public void setTempoTeste(int tempoTeste) {
		this.tempoTeste = tempoTeste;
	}

	public int getId(){ return this.id;	}
}
