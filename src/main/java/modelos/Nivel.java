package modelos;

public enum Nivel {
	JUNIOR(1), PLENO(2), SENIOR(3);
	
	private int valor;
	
	private Nivel(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public static Nivel getByValor(int valor) {
		switch (valor) {
		case 1:
			return JUNIOR;
		case 2:
			return PLENO;
		case 3:
			return SENIOR;
		default:
			return null;
		}
	}
}
