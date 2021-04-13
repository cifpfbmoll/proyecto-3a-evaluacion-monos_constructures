package Utils;

import java.io.*;

/**
 * Esta clase va a almacenar todos los métodos relacionados con la lectura, escritura, creación y eliminación de archivos.
 */
public class FileUtils {

	/**
	 * Este método se encarga de ralizar una búsqueda dentro de un archivo.
	 *
	 * @param query Especifica la cadena de texto que se va a buscar.
	 * @param filePath Se introduce la ruta del archivo en el que vamos a realizar la búsqeuda.
	 * @return Devuelve verdadero o falso dependiendo del éxito de la búsqueda.
	 * @throws IOException Se lanza una excepción en caso de no poder encontrar el archivo especificado.
	 */
	public static boolean stringInFile(String query, String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		boolean found = false;
		try {
			String line;
			while (((line = br.readLine()) != null) && !found) {
				if (line.contains(query)){
					found = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return found;
	}






}
