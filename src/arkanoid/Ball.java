package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Object {
	public static final String IMAGEN_PLAYER = null;

	// Propiedades privadas de cada monstruo
	private String nombre; // Nombre que recibe el monstruo
	private int velocidadX = -5;
	private int velocidadY = - 5;
	
	//Propiedades estáticas de esta clase
	public static String IMAGEN_BICHO_0 = "bicho0.gif";
	
	/**
	 * Constructor sin argumentos de entrada
	 */
	public Ball() {
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
	public Ball(int x, int y, String img, String nombre) {
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
		g.setColor(Color.orange);
		g.fillRect(this.x, this.y, 7, 7);
	}

	@Override
	public void actua() {
		// El monstruo se mueve de manera horizontal, en cada FPS
		this.x += this.velocidadX;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.x < 0 || this.x > 480) {
			this.velocidadX = -this.velocidadX;
		}
		
		// Copiamos el esquema anterior para el movimiento vertical
		this.y += this.velocidadY;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.y < 0 || this.y > 605) {
			this.velocidadY = -this.velocidadY;
		}
		
	}

}
