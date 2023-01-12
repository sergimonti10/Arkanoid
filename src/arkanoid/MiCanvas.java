package arkanoid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;


public class MiCanvas extends Canvas {
	
	List<Object> objects = null;

	/**
	 * Constructor
	 * @param actores
	 */
	public MiCanvas (List<Object> objects) {
		this.objects = objects;
	}
	/**
	 * Sobrescritura del méotod paint(), aquí tengo el control sobre aquello que se va a pintar en pantalla.
	 */
	@Override
	public void paint(Graphics g) {
		// Pinto el fondo
		this.setBackground(Color.black);
		
		// Pinto cada uno de los actores
		for (Object a : this.objects) {
			a.paint(g);
		}
	}
}
