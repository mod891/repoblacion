package repoblacion.modelo;

public class Posicion {
	private double x;
	private double y;
	
	public Posicion(double x,double y) {
		setX(x);
		setY(y);
	}
	public Posicion(Posicion posicion) {
		if (posicion == null) 
			throw new NullPointerException("ERROR: No se puede copiar una posición nula.");
		this.setX(posicion.getX());
		this.setY(posicion.getY());
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double distancia(Posicion posicion) {
		if (posicion == null) 
			throw new NullPointerException("ERROR: No se puede calcular la distancia a una posición nula.");
				
		double distancia = Math.sqrt(Math.pow((posicion.getX()-this.getX()),2)+Math.pow((posicion.getY()-this.getY()),2));
		return distancia; 		 
	}
	@Override
	public String toString() {
		return "x=" + String.format("%5.3f",x) + ", y=" + String.format("%5.3f",y);
	}
	
}
