package arkanoid;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Esta clase se utiliza como un almacén de ficheros de imagen. Para almacenar las imágenes utilizamos
 * un HashMap<String, BufferedImage>. Además esta clase incorpora un patrón Singleton
 * @author R
 *
 */
public class ImagesCache {

	//Propiedades estáticas de esta clase
	public static String BLUE_BLOCK_IMAGE = "BLUE_BLOCK_IMAGE.PNG";
	public static String GREEN_BLOCK_IMAGE = "GREEN_BLOCK_IMAGE.PNG";
	public static String MAGENTA_BLOCK_IMAGE = "MAGENTA_BLOCK_IMAGE.PNG";
	public static String RED_BLOCK_IMAGE = "RED_BLOCK_IMAGE.PNG";
	public static String YELLOW_BLOCK_IMAGE = "YELLOW_BLOCK_IMAGE.PNG";
	public static String SHIP_IMAGE = "SHIP_IMAGE.PNG";
	public static String BALL_IMAGE = "BALL_IMAGE.PNG";



	// HashMap que actúa como almacén de imágenes
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();

	// Instancia Singleton
	private static ImagesCache instance= null;


	/**
	 * Getter Singleton
	 * @return
	 */
	public static ImagesCache getInstance () {
		if (instance == null) {
			instance = new ImagesCache();
		}
		return instance;
	}


	/**
	 * Este método carga un fichero de imagen del sistema de ficheros y lo devuelve
	 * como un objeto de tipo BufferedImage
	 * @param nombre
	 * @return
	 */
	private BufferedImage uploadImage (String nombre) {
		URL url=null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) { // algo ha fallado, se acaba el programa si no podemos cargar alguna imagen
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	/**
	 * M�todo utilizado desde fuera de esta clase para permitir acceder a las im�genes. En primer lugar se 
	 * busca la imagen en el almac�n, si no se encuentra se busca en el sistema de ficheros.
	 * @param nombre
	 * @return
	 */
	public BufferedImage getImagen(String nombre) {
		BufferedImage img = sprites.get(nombre);
		if (img == null) {
			img = uploadImage("resources/images/" + nombre);
			sprites.put(nombre,img);
		}
		return img;
	}
}