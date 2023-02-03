package repoblacion.modelo;

import java.util.Random;

public class Bosque {
	public static int MAX_ALTURA = 500;
	public static int MINIMO = 10;
	public static int MAX_ANCHURA = 1000;
	public static int MAX_ESPECIES = 4;
	private Arbol arbolMasAlejado;
	private Arbol arbolMasCentrado;
	public Random generador;
	private int ancho;
	private int alto;
	private Arbol[] arboles;

	public Bosque(int ancho, int alto, int poblacion) {
		if (ancho < MINIMO || ancho > MAX_ANCHURA)
			throw new IllegalArgumentException("ERROR: Anchura no válida.");
		if (alto < MINIMO || alto > MAX_ALTURA)
			throw new IllegalArgumentException("ERROR: Altura no válida.");
		if (poblacion < 0)
			throw new IllegalArgumentException("ERROR: La población debe ser mayor que cero.");
		if (poblacion > (2*(ancho+alto) ))
			throw new IllegalArgumentException("ERROR: La población no puede superar el perímetro del bosque.");
	
		
		setAncho(ancho);
		setAlto(alto);
		arboles = new Arbol[poblacion];
		
		repoblar();
	}
	private void repoblar() {

		this.generador = new Random();
		int tamanio = arboles.length;
		int totalUtilizados = 0;
		int totalEspeciesUtilizadas = 0;
		double nuevoX;
		double nuevoY;
		Arbol nuevoArbol;
		Especie[] especiesUtilizadas = new Especie[MAX_ESPECIES];
		Especie nuevaEspecie;
		Especie anteriorEspecie;
		boolean especieInvalida = false;
		boolean existeEspecie = false;
		
		while (totalEspeciesUtilizadas != MAX_ESPECIES) { // las especies que forman el bosque se calculan primero
			if (totalEspeciesUtilizadas == 0) {
				especiesUtilizadas[totalEspeciesUtilizadas] = Especie.values()[this.generador.nextInt(Especie.values().length)];
				totalEspeciesUtilizadas++;
			}			
			nuevaEspecie = Especie.values()[this.generador.nextInt(Especie.values().length)];
			
			for (int i=totalEspeciesUtilizadas-1; i >= 0 && !existeEspecie; i--) {
				if (especiesUtilizadas[i] == nuevaEspecie) 
					existeEspecie = true;
			}
			if (!existeEspecie) {
				especiesUtilizadas[totalEspeciesUtilizadas] = nuevaEspecie;
				totalEspeciesUtilizadas++;
			}
			existeEspecie = false;
		}
		// inicialización del primer arbol 
		nuevoX = this.generador.nextDouble(-this.getAncho()/2, this.getAncho()/2);
		nuevoY = this.generador.nextDouble(-this.getAlto()/2,this.getAlto()/2);
		nuevaEspecie = especiesUtilizadas[this.generador.nextInt(MAX_ESPECIES)];
		nuevoArbol = new Arbol(nuevaEspecie,new Posicion(nuevoX,nuevoY));
		arboles[totalUtilizados] = nuevoArbol;
		totalUtilizados++;

		while (totalUtilizados != tamanio) {
			nuevaEspecie = especiesUtilizadas[this.generador.nextInt(MAX_ESPECIES)];
			anteriorEspecie = arboles[totalUtilizados-1].getEspecie();
			
			if (nuevaEspecie == Especie.ALAMO && ( (anteriorEspecie == Especie.CASTANO) || (anteriorEspecie == Especie.CIPRES) || (anteriorEspecie == Especie.OLIVO) ) ) {
				especieInvalida = true;
			} else if (nuevaEspecie == Especie.OLIVO && ( (anteriorEspecie == Especie.ALAMO) || (anteriorEspecie == Especie.CASTANO) ) ) {
				especieInvalida = true;
			}
			if (!especieInvalida) {
				nuevoX = this.generador.nextDouble(-this.getAncho()/2, this.getAncho()/2);
				nuevoY = this.generador.nextDouble(-this.getAlto()/2,this.getAlto()/2);
				nuevoArbol = new Arbol(nuevaEspecie,new Posicion(nuevoX,nuevoY));
				arboles[totalUtilizados] = nuevoArbol;
				totalUtilizados++;
			}
			especieInvalida = false;
		}
	}
	public void realizarCalculos() {
	
		Arbol masCercano=null, masLejano=null, centro;
		double distancia, distanciaMenor=9999.9, distanciaMayor=0;
		centro = new Arbol(Especie.ENCINA,new Posicion(0.0,0.0));

		for (int i=0; i<arboles.length; i++) {
			distancia = arboles[i].getPosicion().distancia(centro.getPosicion());
			if (distancia > distanciaMayor) {
				masLejano = arboles[i];
				distanciaMayor = distancia;
			}
			if (distancia < distanciaMenor) {
				distanciaMenor = distancia;
				masCercano = arboles[i];
			}
		}
		this.arbolMasAlejado = new Arbol(masLejano);
		this.arbolMasCentrado = new Arbol(masCercano);
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public Arbol getArbolMasAlejado() {
		return arbolMasAlejado;
	}
	public Arbol getArbolMasCentrado() {
		return arbolMasCentrado;
	}
}
