package repoblacion;

import repoblacion.modelo.Bosque;
import repoblacion.utilidades.Consola;

public class MainApp {
	private static Bosque bosque;
	
	public static void main(String args[]) {
		
		int anchura, altura, poblacion, perimetro;

		anchura = Consola.leerAnchura();
		altura = Consola.leerAltura();
		perimetro = 2 * (anchura + altura);
		do {
			poblacion = Consola.leePoblacion();
			if (poblacion > perimetro) 
				System.out.println("ERROR: La población no puede superar el perímetro del bosque.");
		} while (poblacion > perimetro);
		try {
			bosque = new Bosque(anchura,altura,poblacion);
			bosque.realizarCalculos();
			System.out.println("Árbol más cercano: "+bosque.getArbolMasCentrado().toString());
			System.out.println("Árbol más alejado: "+bosque.getArbolMasAlejado().toString());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
}