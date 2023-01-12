package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class BlueBlock extends Object {
	// Propiedades privadas de cada monstruo
	private String nombre; // Nombre que recibe el monstruo
	
	//Propiedades estáticas de esta clase
	public static String IMAGEN_BICHO_0 = "bicho0.gif";
	
	/**
	 * Constructor sin argumentos de entrada
	 */
	public BlueBlock() {
		super();
	}

	/**
	 * Constructor más completo, con todas las propiedades del objeto
	 * @param x
	 * @param y
	 * @param img
	 * @param nombre
	 * @param probabilidadDisparo
	 */
	public BlueBlock(int x, int y, String img, String nombre) {
		super(x, y, img);
		this.nombre = nombre;
	}
	
	// Acciones de cada monstruo

	/**
	 * Metodo que devuelve un String con todos los valores de este objeto.
	 */
	public String toString() {
		return "Monster [nombre=" + nombre + ", getX()=" + getX()
				+ ", getY()=" + getY() + ", getImg()=" + getImg() + "]";
	}

	
	// Getters y Setters 
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Utilizado para pintar un monstruo, según sus coordenadas de x e y
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.ancho, this.alto);
	}

	@Override
	public void actua() {
		// TODO Auto-generated method stub
		
	}

}
