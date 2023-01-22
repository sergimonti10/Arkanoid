package arkanoid;


import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;



public class Ball extends Object {
	// Propiedades privadas de cada monstruo
	private String nombre; // Nombre que recibe el monstruo
	private int velocidadX = -5;
	private int velocidadY = -5;
	private boolean left = false, right = false, start = false;
	public static int SPEED = 5;
	
	//Propiedades estáticas de esta clase
	
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
	public Ball(int x, int y, String nombre, int ancho, int alto) {
		super(x, y, ancho, alto, ImagesCache.getInstance().getImagen(ImagesCache.BALL_IMAGE));
		this.nombre = nombre;
	}
	
	// Acciones de cada monstruo

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int largo) {
		this.ancho = largo;
	}

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
		if(start == true) {
		// El monstruo se mueve de manera horizontal, en cada FPS
		this.x += this.velocidadX;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.x < 0 || this.x > Arkanoid.getInstance().getCanvas().getWidth()) {
			this.velocidadX = -this.velocidadX;
		}
		
		// Copiamos el esquema anterior para el movimiento vertical
		this.y += this.velocidadY;
		// Si el monstruo abandona la escena por la izquierda o la derecha, rebota
		if (this.y < 0 || this.y > Arkanoid.getInstance().getCanvas().getHeight()) {
			this.velocidadY = -this.velocidadY;
		}
		if (this.y > Arkanoid.getInstance().getCanvas().getHeight()) {
			this.velocidadY = 0;
			this.velocidadX = 0;
			JOptionPane.showInternalMessageDialog(null, "GAME OVER", null, velocidadX);
			System.exit(0);
		}
		}else {
		if (left) this.x -= SPEED;
		if (right) this.x += SPEED;
		
		// Compruebo si el player sale del canvas por cualquiera de los cuatro márgenes
		move(this.x);
		}
	}

	public void collidesWith(Object a) {
		super.collidesWith(a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof BlueBlock || a instanceof GreenBlock || a instanceof MagentaBlock || 
				a instanceof RedBlock || a instanceof YellowBlock || a instanceof Ship) {
			this.velocidadY = -this.velocidadY;
		}
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
		case KeyEvent.VK_SPACE:
			start = true; break;
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
