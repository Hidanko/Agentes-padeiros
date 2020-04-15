package modelos;

import agentes.Atendente;

import java.io.Serializable;

public class Pao implements Serializable {
    private Enum nome;
    private int tempo;
    private int quantidade;
    private Atendente atendente;


    public Pao(Enum nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Enum getNome() {
        return nome;
    }

    public void setNome(Enum nome) {
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

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
