package arkanoid;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

public class MagentaBlock extends PrincipalObject {
	// Propiedades privadas de cada monstruo
	private String nombre; // Nombre que recibe el monstruo
	
	//Propiedades estáticas de esta clase
	
	/**
	 * Constructor sin argumentos de entrada
	 */
	public MagentaBlock() {
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
	public MagentaBlock(int x, int y, String nombre, int ancho, int alto) {
		super(x, y, ancho, alto);
		this.nombre = nombre;
		this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.MAGENTA_BLOCK_IMAGE));
	}
	
	// Acciones de cada monstruo

	/**
	 * Metodo que devuelve un String con todos los valores de este objeto.
	 */

	
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
		super.actua();
		
	}
	
	public void collidesWith(PrincipalObject a) {
		super.collidesWith(a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof Ball) {
			Arkanoid.getInstance().objectDelete(this);
			ResourcesCache.getInstance().playSonido("explosion.wav");
			Arkanoid.getInstance().incorporateNewObject(new Explosion(this.x, this.y));
		}
	}

}
