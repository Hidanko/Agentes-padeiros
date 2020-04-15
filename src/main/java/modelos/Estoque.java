package modelos;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private static Estoque instancia;

    public static Estoque getInstance() {
        if(instancia == null){
            instancia = new Estoque();
            estoque.add(new Pao(PaoTipo.FRANCES, 50));
            estoque.add(new Pao(PaoTipo.FORMA, 50));
            estoque.add(new Pao(PaoTipo.BAGUETTE, 50));
        }
        return instancia;
    }

    public static List<Pao> estoque = new ArrayList<Pao>();

    public  boolean temNoEstoque(PaoTipo tipo, int quantidade) throws Exception {
        for (Pao p: estoque ) {
            if(p.getNome() == tipo){
                if(quantidade < p.getQuantidade()){
                    return true;
                }
                return false;
            }
        }
        throw new Exception("Sem pão no estoque");
    }

    public void tiraDoEstoque(PaoTipo tipo, int quantidade){
        for (Pao p: estoque ) {
            if(p.getNome() == tipo){
                p.setQuantidade(p.getQuantidade()-quantidade);
            }
        }
    }
}
