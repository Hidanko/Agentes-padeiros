package modelos;

import agentes.Atendente;

import java.io.Serializable;

public class Pao implements Serializable {
    private String nome;
    private int tempo;
    private Atendente atendente;

    public Pao(String nome, int tempo, Atendente atendente) {
        this.nome = nome;
        this.tempo = tempo;
        this.atendente = atendente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public void diminuirTempo(int quantidade){
        this.tempo = this.tempo - quantidade;
    }
}
