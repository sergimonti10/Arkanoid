package arkanoid;


import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;



public class Arkanoid {

	private static int FPS = 60;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Arkanoid");
		window.setBounds(400, 0, 500, 650);
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		window.getContentPane().setLayout(new BorderLayout());
		
		// Creo una lista de actores que intervendrá en el juego.
		List<Object> objects = createObjects();
		
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		MiCanvas canvas = new MiCanvas(objects);
		window.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
		window.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		window.setVisible(true);
		
		// Comienzo un bucle, que consistirá en el juego completo.
		int millisPorCadaFrame = 1000 / FPS;
		do {
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			// Redibujo la escena
			canvas.repaint();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos actúe
			
			for (Object a : objects) {
				a.actua();
			}
			
			// Caculo los millis que debemos parar el proceso, generando 60 FPS
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa;
			// "Duermo" el proceso principal durante los milllis calculados.
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
		
	}

	/**
	 * 
	 * @return
	 */
	private static List<Object> createObjects () {
		List<Object> objects = new ArrayList<Object>();
		
		Ball ball = new Ball(237, 510, null, null);
		objects.add(ball);
		
		//Construyo un player para este juego y lo agrego a la lista
		Ship ship = new Ship(212, 525, null, null);
		objects.add(ship);
		
		int x = -13;
		
		// Creo los Monstruos del juego
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 140;
			YellowBlock yb = new YellowBlock(x, y, null, "YB");
			objects.add(yb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 122;
			RedBlock rb = new RedBlock(x, y, null, "RB");
			objects.add(rb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 104;
			GreenBlock gb = new GreenBlock(x, y, null, "GB");
			objects.add(gb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 86;
			BlueBlock bb = new BlueBlock(x, y, null, "BB");
			objects.add(bb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 68;
			MagentaBlock mb = new MagentaBlock(x, y, null, "MB");
			objects.add(mb);
		}
		
		// Devuelvo la lista con todos los actores del juego
		return objects;
	}
	
}
