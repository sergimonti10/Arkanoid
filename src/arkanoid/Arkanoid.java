package arkanoid;

import java.awt.BorderLayout;
import java.awt.Rectangle;
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
	private List<PrincipalObject> objects = new ArrayList<PrincipalObject>();
	private MiCanvas canvas = null;
	private static Ship ship = null;
	private static Ball ball = null;
	public static Arkanoid instance = null;
	private List<PrincipalObject> objectsDelete = new ArrayList<PrincipalObject>();
	private List<PrincipalObject> objectsToIncorporate = new ArrayList<PrincipalObject>();
	
	public static Arkanoid getInstance() {
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Arkanoid();
		}
		return instance;
	}
	
	public Arkanoid () {
		window = new JFrame("Arkanoid");
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
				ball.move(e.getX());
			}			
		});
		
		// Desvío los eventos de teclado hasta el jugador
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				ship.keyPressed(e);
				ball.keyPressed(e);
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				ship.keyReleased(e);
				ball.keyReleased(e);
			}
		});

    window.getContentPane().add(canvas, BorderLayout.CENTER);
		// Consigo que la ventana no se redibuje por los eventos de Windows
    window.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
    window.setVisible(true);
		
		
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
		ResourcesCache.getInstance().cargarRecursosEnMemoria();
		Arkanoid.getInstance().game();
	}

	public void game () {
		int millisPorCadaFrame = 1000 / FPS;
		do {
			if (window.getFocusOwner() != null && !window.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			// Redibujo la escena
			canvas.drawScene();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos actúe
			for (PrincipalObject a : objects) {
				a.actua();
			}
			
			collidesDetected();    
			updateActors();
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
	private static List<PrincipalObject> createObjects () {
		List<PrincipalObject> objects = new ArrayList<PrincipalObject>();
		
		
		//Construyo un player para este juego y lo agrego a la lista
		ship = new Ship(212, 525, 55, 8);
		objects.add(ship);
		
		int xShip = ship.getX() + (ship.getAncho()/2) - 3;
		int yShip = ship.getY() - 8;
		
		ball = new Ball(xShip, yShip, null, 7, 7);
		objects.add(ball);
		
		int x = -13;
		
		// Creo los Monstruos del juego
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 140;
			YellowBlock yb = new YellowBlock(x, y, "YB", 40, 15);
			objects.add(yb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 122;
			RedBlock rb = new RedBlock(x, y, "RB", 40, 15);
			objects.add(rb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 104;
			GreenBlock gb = new GreenBlock(x, y, "GB", 40, 15);
			objects.add(gb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 86;
			BlueBlock bb = new BlueBlock(x, y, "BB", 40, 15);
			objects.add(bb);
		}
		
		x = -13;
		for (int i = 0; i < 10; i++) {
			x  += 43;
			int y = 68;
			MagentaBlock mb = new MagentaBlock(x, y, "MB", 40, 15);
			objects.add(mb);
		}
		
		// Devuelvo la lista con todos los actores del juego
		return objects;
	}
	
	public void incorporateNewObject (PrincipalObject a) {
		this.objectsToIncorporate.add(a);
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
	
	public void objectDelete (PrincipalObject a) {
		this.objectsDelete.add(a);
	}

	public void ballCollisions(PrincipalObject a) {
		
	}
	/**
	 * Incorpora los actores nuevos al juego y elimina los que corresponden
	 */
	private void updateActors () {
		for (PrincipalObject a : this.objectsToIncorporate) {
			this.objects.add(a);
		}
		this.objectsToIncorporate.clear();
		// Elimino los actores que se deben eliminar
		for (PrincipalObject a : this.objectsDelete) {
			this.objects.remove(a);
		}
		this.objectsDelete.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
		
	}
	
	private void collidesDetected() {
		// Una vez que cada actor ha actuado, intento detectar colisiones entre los actores y notificarlas. Para detectar
		// estas colisiones, no nos queda más remedio que intentar detectar la colisión de cualquier actor con cualquier otro
		// sólo con la excepción de no comparar un actor consigo mismo.
		// La detección de colisiones se va a baser en formar un rectángulo con las medidas que ocupa cada actor en pantalla,
		// De esa manera, las colisiones se traducirán en intersecciones entre rectángulos.
		PrincipalObject actor1 = ball;
			// Creo un rectángulo para este actor.
			Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
			// Compruebo un actor con cualquier otro actor
			for (PrincipalObject actor2 : this.objects) {
				// Evito comparar un actor consigo mismo, ya que eso siempre provocaría una colisión y no tiene sentido
				if (!actor1.equals(actor2)) {
					// Formo el rectángulo del actor 2
					Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getAlto());
					// Si los dos rectángulos tienen alguna intersección, notifico una colisión en los dos actores
					if (rect1.intersects(rect2)) {
						actor1.collidesWith(actor2); // El actor 1 colisiona con el actor 2
						actor2.collidesWith(actor1); // El actor 2 colisiona con el actor 1
						break;
					}
				}
			}
		
	}
	
}
