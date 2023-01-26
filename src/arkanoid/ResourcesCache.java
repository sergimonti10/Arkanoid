package arkanoid;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Esta clase se utiliza como un almacén de ficheros de imagen. Para almacenar las imágenes utilizamos
 * un HashMap<String, BufferedImage>. Además esta clase incorpora un patrón Singleton
 * @author R
 *
 */
public class ResourcesCache {

	//Propiedades estáticas de esta clase
	public static String BLUE_BLOCK_IMAGE = "BLUE_BLOCK_IMAGE.PNG";
	public static String GREEN_BLOCK_IMAGE = "GREEN_BLOCK_IMAGE.PNG";
	public static String MAGENTA_BLOCK_IMAGE = "MAGENTA_BLOCK_IMAGE.PNG";
	public static String RED_BLOCK_IMAGE = "RED_BLOCK_IMAGE.PNG";
	public static String YELLOW_BLOCK_IMAGE = "YELLOW_BLOCK_IMAGE.PNG";
	public static String SHIP_IMAGE = "SHIP_IMAGE.PNG";
	public static String BALL_IMAGE = "BALL_IMAGE.PNG";



	// HashMap que actúa como almacén de imágenes
		private HashMap<String, java.lang.Object> hmRecursos = new HashMap<String, java.lang.Object>();
		// Carpetas en la que se encuentran todos los recursos
		private String nombreCarpetaParaFile = "./src/arkanoid/resources";
		private String nombreCarpetaParaURL = "resources/";



		// Instancia Singleton
		private static ResourcesCache instance= null;


		/**
		 * Getter Singleton
		 * @return
		 */
		public static ResourcesCache getInstance () {
			if (instance == null) {
				instance = new ResourcesCache();
			}
			return instance;
		}


		/**
		 * 
		 */
		public void cargarRecursosEnMemoria () {
			File carpeta = new File(nombreCarpetaParaFile);
			for (File fichero : carpeta.listFiles()) {
		        if (fichero.isFile()) {
		        	cargarFicheroEnHashMap(fichero.getName());
		        }
		    }
		}


		/**
		 * 
		 * @param nombreFichero
		 */
		private void cargarFicheroEnHashMap (String nombreFichero) {
			// Obtengo un objeto URL para localizar el recurso
			URL url = null;
			url = getClass().getResource(nombreCarpetaParaURL + nombreFichero);
			// Discriminará el caso de que intento cargar un sonido del caso de cargar imágenes
			try {
				if (nombreFichero.endsWith(".wav") || nombreFichero.endsWith(".mp3")) {
					this.hmRecursos.put(nombreFichero, Applet.newAudioClip(url));
				} 
				else { // Si no es un sonido entiendo que se trata de una imagen
					this.hmRecursos.put(nombreFichero, ImageIO.read(url));
				}
			}
			catch (Exception ex) {
				System.out.println("No se pudo cargar el recurso " + nombreFichero);
				ex.printStackTrace();
			}
		}



		/**
		 * Mediante este método casteamos a imagen el recurso que nos proporciona el supertipo
		 * @param name
		 * @return
		 */
		public BufferedImage getImagen(String nombreFichero) {
			return (BufferedImage) hmRecursos.get(nombreFichero);
		}


		/**
		 * Ejecuta un archivo de sonido de forma aislada
		 * @param name
		 */
		public void playSonido(String nombreFichero) {
			((AudioClip)hmRecursos.get(nombreFichero)).play();
		}

		/**
		 * Reproduce un archivo de sonido en bucle
		 * @param name
		 */
		public void loopSonido(final String nombreFichero) {
			((AudioClip)hmRecursos.get(nombreFichero)).loop();
		}
}