package arkanoid;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Arkanoid {

	private static int FPS = 60;
	private JFrame window = null;
	private List<Object> objects = new ArrayList<Object>();
	private MiCanvas canvas = null;
	private static Ship ship = null;
	public static Arkanoid instance = null;
	
	public static Arkanoid getInstance() {
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Arkanoid();
		}
		return instance;
	}
	
	public Arkanoid () {
		JFrame window = new JFrame("Arkanoid");
		window.setBounds(400, 0, 500, 650);
		
		// Para colocar objetos sobre la ventana debo asignarle un "layout" (plantilla) al panel principal de la ventana
		window.getContentPane().setLayout(new BorderLayout());
		
		// Creo una lista de actores que intervendrá en el juego.
		objects = createObjects();
		
		// Creo y agrego un canvas, es un objeto que permitirá dibujar sobre él
		canvas = new MiCanvas(objects);
		// Envío los eventos de movimientos de ratón al jugador
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				ship.move(e.getX());
			}			
		});
		
		// Desvío los eventos de teclado hasta el jugador
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				ship.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				ship.keyReleased(e);
			}
		});

    window.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
    window.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
    window.setVisible(true);
		
		// Tras mostrar la ventana, consigo que el foco de la ventana vaya al
		// Canvas, para que pueda escuchar los eventos del teclado
		canvas.requestFocus();
		
		// Control del evento de cierre de ventana
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeAplication();
			}
		});
		
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Arkanoid.getInstance().game();
	}

	public void game () {
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
			
			// Calculo los millis que debemos parar el proceso, generando 60 FPS.
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
		
		Ball ball = new Ball(237, 510, null, null, 7, 7);
		objects.add(ball);
		
		//Construyo un player para este juego y lo agrego a la lista
		ship = new Ship(212, 525, null);
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


	public MiCanvas getCanvas() {
		// TODO Auto-generated method stub
		return canvas;
	}

	private void closeAplication() {
		String [] options ={"Accept","Cancel"};
		int election = JOptionPane.showOptionDialog(window,"Do you want to close the application?","Exit aplication",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE, null, options, "Accept");
		if (election == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
