package arkanoid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Object {
	// Propiedades protegidas (visibles en la propia clase y en los subtipos) de cada actor
	protected int x, y; // Coordenadas x e y del actor
	protected int ancho, alto; // ancho y alto que ocupa el actor en pantalla
	protected BufferedImage img; // Imagen del actor
	

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
	public Object(int x, int y, int ancho, int alto, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.setImg(img);
	}
	
	/**
	 * Este método abstracto indica que todos los subtipos están obligados a implementarlo. Lo usaremos para pintar cada
	 * personaje.
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(this.img, this.x, this.y, null);
	} 
	/**
	 * Método que permite que cada actor realice las acciones que necesite en la creación de cada Frame
	 */
	public abstract void actua ();
	
	public void collidesWith(Object a) {
	}

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
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
		this.ancho = this.img.getWidth();
		this.alto = this.img.getHeight();
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
