package agentes;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import main.Main;
import modelos.Pao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Padeiro extends Agent {

    List<Pao> fila = new ArrayList<Pao>();

    protected void setup() {
        // Task para ver se houve pedido novo do atendente
        addBehaviour(new TickerBehaviour(this, Main.delay) {
            public void onTick() {
                // Escuta se há nova mensagem
                ACLMessage msg = myAgent.receive();
                // Se houver
                if (msg != null) {
                    try {
                        // Tenta converter a mensagem para o objeto Pao
                        // (caso ele receba uma mensagem que não devia, ela vai cair nessa excessão)
                        Pao p = (Pao) msg.getContentObject();
                        // Essa mensagem só vai ser impressa caso a linha anterior for executada
                        fila.add(p);
                        System.out.println("Nova requisição para o padeiro!");
                    } catch (UnreadableException e) {
                        System.out.println("Falha ao ler pão");
                        e.printStackTrace();
                    }
                }
            }
        });

        // Task para trabalhar nos pedidos
        addBehaviour(new TickerBehaviour(this, Main.delay) {
            public void onTick() {
                // Se houver altuma coisa para fazer
                if (fila.size() < 0) {
                    fila.get(0).diminuirTempo(1);

                    // Testar se o pedido esta pronto
                    if (fila.get(0).getTempo() <= 0) {
                        // Devolver para o Atendente
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(fila.get(0).getAtendente().getAID());
                        msg.setLanguage("Português");
                        msg.setOntology("Aviso");
                        msg.setContent("Pedido pronto");
                        try {
                            // Adiciona pedido pronto na mensagem
                            msg.setContentObject(fila.get(0));
                            // Remove pedido da fila de pedidos do Padeiro
                            fila.remove(0);
                            send(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }
}
