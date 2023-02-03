package repoblacion.modelo;

public enum Especie {

	ALAMO("álamo"),
	ENCINA("encina"),
	CASTANO("castaño"),
	CIPRES("ciprés"),
	PINO("pino"),
	ROBLE("roble"),
	OLIVO("olivo");
	
	private String cadenaAMostrar;
	
	private Especie(String cadenaAMostrar) {
		this.cadenaAMostrar = cadenaAMostrar;
	}
	
	public String toString() {
		return this.cadenaAMostrar;
	}
	
}
