package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
    List<Pao> lista = new ArrayList<Pao>();

    public Pedido(List<Pao> lista) {
        this.lista = lista;
    }

    public Pedido() {

    }

    public List<Pao> getLista() {
        return lista;
    }

    public void setLista(List<Pao> lista) {
        this.lista = lista;
    }

    public void addLista(Pao pao){
        if(lista == null){
            lista = new ArrayList<Pao>();
        }
        lista.add(pao);
    }
}
