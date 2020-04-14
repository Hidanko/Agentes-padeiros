package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import main.Main;
import modelos.ListaFuncionarios;
import modelos.Tarefa;
import modelos.TarefaStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Testador extends Agent {
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private String nome;

    public Testador() {

    }

    protected void setup() {
        nome = (String)getArguments()[0];
        ACLMessage tarefaMsg = new ACLMessage(ACLMessage.INFORM);

        for (Programador p : ListaFuncionarios.getProgramadores()){
            tarefaMsg.addReceiver(p.getAID());
        }
        tarefaMsg.setLanguage("Português");
        tarefaMsg.setOntology("Aviso");
        tarefaMsg.setContent("Novo Testador");


        addBehaviour(new TickerBehaviour(this, Main.delay) {

            public void onTick() {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    System.out.println(nome +" recebeu nova tarefa");
                    try {
                        if (Tarefa.class.isInstance(msg.getContentObject())) {
                            Tarefa p = (Tarefa) msg.getContentObject();
                            tarefas.add(p);
                            System.out.println("Testador "+nome+" recebeu uma nova tarefa!");
                        } else{
                            System.out.println("Nao era tarefa");
                        }
                    } catch (UnreadableException e) {
                        System.out.println("Falha ao ler objeto serializado");
                        e.printStackTrace();
                    }
                }

                if (tarefas.size() > 0) {

                    tarefas.get(0).setTempoTeste(tarefas.get(0).getTempoTeste() + 1);
                    System.out.println("Testador "+nome + " trabalhou por uma hora na tarefa "+tarefas.get(0).getId()  + " restante: "+ (tarefas.get(0).getDuracaoTeste() -tarefas.get(0).getTempoTeste()));

                    if (tarefas.get(0).getDuracaoTeste() >= tarefas.get(0).getTempoTeste()) {
                        Random r = new Random();
                        if (!r.nextBoolean()) {
                            System.out.println("Testador "+nome+" validou a tarefa "+tarefas.get(0).getId()+" mas havia erros pendentes");
                            tarefas.get(0).setStatus(TarefaStatus.EM_DESENVOLVIMENTO);
                            tarefas.get(0).setTempoGasto(0);
                            // Retornando tarefa para programador
                            ACLMessage tarefaMsg = new ACLMessage(ACLMessage.INFORM);
                            tarefaMsg.addReceiver(tarefas.get(0).getProgramador().getAID());
                            tarefaMsg.setLanguage("Português");
                            tarefaMsg.setOntology("Aviso");
                            tarefaMsg.setContent("Nova Tarefa");
                            try {
                                tarefaMsg.setContentObject(tarefas.get(0));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            send(tarefaMsg);
                        }else{
                            System.out.println("Testador "+nome+" validou a tarefa "+tarefas.get(0).getId()+ " e ela foi finalizada");
                        }
                        tarefas.remove(0);
                    }
                }
            }
        });
        ListaFuncionarios.cadastraTestador(this);
    }
    protected int getTempoTotalTarefas() {
        AtomicInteger duracao = new AtomicInteger();
        for (Tarefa tarefa : tarefas) {
            duracao.addAndGet(tarefa.getDuracaoTeste() - tarefa.getTempoGasto());
        }
        return duracao.get();
    }

}
