package main;


import agentes.Atendente;
import jade.Boot;
import jade.core.AID;

public class Main {

	public static int delay = 5000;
	private static int idTarefa = 1;
	private static Atendente atendete;
	public static void main(String[] args) {


		
		String[] param = new String[2];
		param[0] = "-gui";
		param[1] = "  a:agentes.Atendente;p:agentes.Padeiro;cliente:agentes.Cliente";
//		param[2] = "";
		Boot.main(param);
		
	}

	public static AID getAtendete() {
		if(atendete != null){
			return(atendete.getAID());
		}
		return(null);
	}

	public static void setAtendente(Atendente a){ Main.atendete = a;}
}
