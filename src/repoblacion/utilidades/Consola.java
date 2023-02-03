package repoblacion.utilidades;

import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {}
	
	public static int leerAnchura() {
		int anchura;
		System.out.println("Introduce la anchura del bosque:");
		anchura = Entrada.entero();
		return anchura;
	}
	
	public static int leerAltura() {
		int altura;
		System.out.println("Introduce la altura del bosque:");
		altura = Entrada.entero();
		return altura;
	}
	
	public static int leePoblacion() {
		int poblacion;
		System.out.println("Introduce la población de especies:");
		poblacion = Entrada.entero();
		return poblacion;
	}
}
