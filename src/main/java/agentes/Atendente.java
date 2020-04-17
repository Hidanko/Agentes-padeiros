package agentes;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import main.Main;
import modelos.Estoque;
import modelos.Pao;
import modelos.PaoTipo;
import modelos.Pedido;

import java.io.IOException;

public class Atendente extends Agent {

    private boolean atendendo;

    protected void setup() {
        Main.setAtendente(this);
        addBehaviour(new TickerBehaviour(this, Main.delay) {
            @Override
            protected void onTick() {
                // Escuta se há nova mensagem
                ACLMessage msg = myAgent.receive();
                // Se houver
                if (msg != null) {
                    try {
                        // Tenta converter a mensagem para o objeto Pao
                        // (caso ele receba uma mensagem que não devia, ela vai cair nessa excessão)
                        Pedido pedido = (Pedido) msg.getContentObject();
                        // Essa mensagem só vai ser impressa caso a linha anterior for executada
                        System.out.println("Nova requisição para o atendente!");

                        // Buscar todos os pães do pedido
                        for (Pao p: pedido.getLista()) {
                        try{
                            System.out.println(p.getNome() +" - " + p.getQuantidade());
                            Estoque e = Estoque.getInstance();
                            if (e.temNoEstoque((PaoTipo) p.getNome(), p.getQuantidade())){
                                e.tiraDoEstoque((PaoTipo) p.getNome(), p.getQuantidade());
                                System.out.println("Pedido entregue");
                            }
                        }catch (Exception e){
                            System.out.println("Sem estoque, enviando pedido para padeiro");
                            try {
                                // Adiciona pedido pronto na mensagem
                                ACLMessage m = new ACLMessage(ACLMessage.INFORM);
                                m.setLanguage("Português");
                                m.setOntology("Aviso");
                                m.setContent("Produzir mais pão");
                                m.setContentObject(p);
                                send(m);
                            } catch (IOException exep) {
                                exep.printStackTrace();
                            }
                        }
                        }
                    } catch (UnreadableException e) {
                        System.out.println("Falha ao ler pão");
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public boolean isAtendendo() {
        return atendendo;
    }

    public void setAtendendo(boolean atendendo) {
        this.atendendo = atendendo;
    }

}
