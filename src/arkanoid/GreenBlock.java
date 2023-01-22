package arkanoid;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

public class GreenBlock extends Object {
	// Propiedades privadas de cada monstruo
	private String nombre; // Nombre que recibe el monstruo
	
	//Propiedades estáticas de esta clase
	
	/**
	 * Constructor sin argumentos de entrada
	 */
	public GreenBlock() {
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
	public GreenBlock(int x, int y, String nombre, int ancho, int alto) {
		super(x, y, ancho, alto, ImagesCache.getInstance().getImagen(ImagesCache.GREEN_BLOCK_IMAGE));
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
	public void actua() {
		// TODO Auto-generated method stub
		
	}
	
	public void collidesWith(Object a) {
		super.collidesWith(a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof Ball) {
			Arkanoid.getInstance().objectDelete(this);
		}
	}

	@Override
	protected Object getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
