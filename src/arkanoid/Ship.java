package arkanoid;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Ship extends Object {
	// Propiedades estáticas de esta clase
	// Propiedades que indican si se está produciendo un movimiento en una dirección
	private boolean left = false, right = false;
	// Velocidad de la nave, expresada en píxeles por cada frame
	public static int SPEED = 5;

	/**
	 * Constructor por defecto "default constructor"
	 */
	public Ship() {
		super();
	}

	/**
	 * Constructor que inicializa las propiedades del objeto
	 * 
	 * @param x
	 * @param y
	 * @param img
	 */
	public Ship(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto, ImagesCache.getInstance().getImagen(ImagesCache.SHIP_IMAGE));
	}

	/**
	 * Obtención de un String con todos los datos de un objeto Player
	 */
	public String toString() {
		return "Ship [getX()=" + getX() + ", getY()=" + getY() + ", getImg()=" + getImg() + "]";
	}

	/**
	 * Utilizado para pintar un player, según sus coordenadas de x e y
	 */

	@Override
	public void actua() {
		// Compruebo las variables booleanas que determinan el movimiento
		if (left) this.x -= SPEED;
		if (right) this.x += SPEED;
		
		// Compruebo si el player sale del canvas por cualquiera de los cuatro márgenes
		move(this.x);
	}

	/**
	 * Mediante la llamada a este método, podemos cambiar la posición del jugador a
	 * unas nuevas coordenadas
	 * 
	 * @param x
	 * @param y
	 */
	public void move(int x) {
		this.x = x;
		// Controlo los casos en los que el jugador pueda salir del Canvas
		MiCanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al objeto Canvas usado
		
		// Compruebo si el jugador sale por la derecha
		if (this.x > (canvas.getWidth() - this.ancho)) {
			this.x = canvas.getWidth() - this.ancho;
		}

		// Compruebo si el jugador sale por la izquierda
		if (this.x < 0) {
			this.x = 0;
		}
		
	}

	/**
	 * Se ejecuta al recibir un evento del teclado: tecla presionada
	 * @param e
	 */
	public void keyPressed (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true; break;
		case KeyEvent.VK_RIGHT:
			right = true; break;
		}
	}
	
	/**
	 * Se ejecuta al recibir un evento del teclado: tecla liberada
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false; break;
		case KeyEvent.VK_RIGHT:
			right = false; break;
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
