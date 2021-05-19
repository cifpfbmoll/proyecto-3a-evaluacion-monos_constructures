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

	public static String nombreMes(int n) {
		switch (n) {
			case 1:
				return "Enero";
			case 2:
				return "Febrero";
			case 3:
				return "Marzo";
			case 4:
				return "Abril";
			case 5:
				return "Mayo";
			case 6:
				return "Junio";
			case 7:
				return "Julio";
			case 8:
				return "Agosto";
			case 9:
				return "Setiembre";
			case 10:
				return "Octubre";
			case 11:
				return "Noviembre";
			case 12:
				return "Diciembre";
			default:
				return "[error 404]";

		}
	}






}
