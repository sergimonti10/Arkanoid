package arkanoid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;


public class MiCanvas extends Canvas {
	
	List<PrincipalObject> objects = null;
	
	private BufferStrategy strategy = null;

	/**
	 * Constructor
	 * @param actores
	 */
	public MiCanvas (List<PrincipalObject> objects) {
		this.objects = objects;
	}
	/**
	 * Sobrescritura del méotod paint(), aquí tengo el control sobre aquello que se va a pintar en pantalla.
	 */
	public void drawScene () {
		// Tengo que inicializar el objeto "strategy" una única vez
		if (this.strategy == null) {
			// El Canvas se dibujará en pantalla con una estrategia de doble búffer
			this.createBufferStrategy(2);
			// Obtengo una referencia a la estrategia de doble búffer.
			strategy = getBufferStrategy();
			// Resuelve un problema de sincronización de memoria de vídeo en Linux
			Toolkit.getDefaultToolkit().sync();			
		}
		// Obtengo el objeto gráfico que me permita pintar en el doble búffer
		Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		// Pinto un rectángulo negro que ocupe toda la escena
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Pinto cada uno de los actores
		for (PrincipalObject a : this.objects) {
			a.paint(g);
		}
		strategy.show();
	}
}
