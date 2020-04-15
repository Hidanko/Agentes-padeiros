package main;


import agentes.Atendente;
import jade.Boot;

public class Main {

	public static int delay = 5000;
	private static int idTarefa = 1;
	private static Atendente atendete;
	public static void main(String[] args) {


		
		String[] param = new String[2];
		param[0] = "-gui";
		param[1] = "  a:agentes.Atendente;p:agentes.Padeiro;";
//		param[2] = "";
		Boot.main(param);
		
	}

	public static Atendente getAtendete() {
		return atendete;
	}
}
