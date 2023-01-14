package arkanoid;

import java.awt.Graphics;

public abstract class Object {
	// Propiedades protegidas (visibles en la propia clase y en los subtipos) de cada actor
	protected int x, y; // Coordenadas x e y del actor
	protected int ancho = 40, alto = 15; // ancho y alto que ocupa el actor en pantalla
	protected String img; // Imagen del actor

	/**
	 * Constructor sin parámetros de entrada
	 */
	public Object() {
	}

	/**
	 * Constructor con parámetros de entrada
	 * @param x
	 * @param y
	 * @param img
	 */
	public Object(int x, int y, String img) {
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	/**
	 * Este método abstracto indica que todos los subtipos están obligados a implementarlo. Lo usaremos para pintar cada
	 * personaje.
	 * @param g
	 */
	public abstract void paint(Graphics g); 
	/**
	 * Método que permite que cada actor realice las acciones que necesite en la creación de cada Frame
	 */
	public abstract void actua ();

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	// Getters y setters
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
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

	protected abstract Object getCanvas();

	protected abstract int getWidth();

	protected abstract int getHeight();


	
}
