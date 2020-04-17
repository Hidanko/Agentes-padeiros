package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import main.Main;
import modelos.Pao;
import modelos.PaoTipo;
import modelos.Pedido;

import java.io.IOException;
import java.util.Random;

public class Cliente extends Agent {
    Random random = new Random();
    private boolean recebeu = false;
    AID atendente;
    PaoTipo t;
    int q;
    protected void setup() {

       atendente = Main.getAtendete();
        // gerando pedido aleatório
        PaoTipo[] tipos = PaoTipo.values();
        random.nextInt(2);
        t = tipos[random.nextInt(2) + 1];
        q = random.nextInt(20) + 1;
        final Pedido p = new Pedido();
        p.addLista((new Pao(t, q)));


        addBehaviour(new OneShotBehaviour() {

            public void action() {
                block(Main.delay);
                System.out.println("Cliente chegou");
                System.out.println("Pedido: " + q + " " + t.toString());
                if (atendente == null){
                    atendente = Main.getAtendete();
                }
                try {
                    // Adiciona pedido pronto na mensagem
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(atendente);
                    msg.setLanguage("Português");
                    msg.setOntology("Aviso");
                    msg.setContent("Pedido pronto");
                    msg.setContentObject(p);
                    send(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }
}