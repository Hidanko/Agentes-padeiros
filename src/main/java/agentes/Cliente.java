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
    AID atendente = Main.getAtendete().getAID();
    Random random = new Random();
    private boolean recebeu = false;

    protected void setup() {

        // gerando pedido aleatório
        PaoTipo[] tipos = PaoTipo.values();
        random.nextInt(2);
        PaoTipo t = tipos[random.nextInt(2) + 1];
        int q = random.nextInt(20) + 1;
        final Pedido p = new Pedido();
        p.addLista((new Pao(t, q)));


        addBehaviour(new OneShotBehaviour() {

            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(atendente);
                msg.setLanguage("Português");
                msg.setOntology("Aviso");
                msg.setContent("Pedido pronto");
                try {
                    // Adiciona pedido pronto na mensagem
                    msg.setContentObject(p);
                    send(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }
}