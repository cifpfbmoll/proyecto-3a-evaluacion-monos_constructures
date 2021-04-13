package Launch;

import Utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase se encarga de leer e interpretar los valores introducidos dentro del archivo AppLaunch.mce.
 * Su intención es la de poder validar que se han aceptado o denegado una serie de permisos que se han descrito dentro
 * del archivo LAUNCH.
 */
public class LaunchInterpreter {

	private static final String filePath = "src/Launch/AppLaunch.mce";
	public static final String TERMINOS_Y_CONDICIONES = "$Legal.TYC";

	/**
	 * Esta función comprueba si los términos y condiciones han sido o no aceptados todavía.
	 * @return Devuelve Verdadero o falso dependiendo de si han sido o no aceptados.
	 * @throws IOException Lanza una excepción si el archivo AppLaunch no se encuentra presente. (NO DEBERÍA)
	 */
	public static boolean checkTermsAndConditions() throws IOException {
		return FileUtils.stringInFile(TERMINOS_Y_CONDICIONES + " = aceptado",filePath);
	}

	/**
	 * Esta función se encarga de modificar los valores del archivo AppLaunch.
	 * Su función primordial es aceptar los permisos y/o condiciones que vaya interponiendo la aplicación.
	 * @param valor //El valor que va a ser aceptado
	 * @param estado //El estado de aceptación.
	 */
	public static void replaceValue(String valor, String estado) {
		try {
			// Almacenamos el contenido del archivo dentro del búffer.
			BufferedReader file = new BufferedReader(new FileReader(filePath));
			StringBuilder inputBuffer = new StringBuilder();
			String line;

			while ((line = file.readLine()) != null) {
				inputBuffer.append(line);
				inputBuffer.append('\n');
			}
			file.close();
			String inputStr = inputBuffer.toString();

			// Lógica para reemplazar el contenido del archivo
			if (estado.equals(" = aceptado")) {
				inputStr = inputStr.replace(valor + " = denegado", valor + " = aceptado");
			} else if (estado.equals(" = denegado")){
				inputStr = inputStr.replace(valor + " = aceptado", valor + " = denegado");
			}

			// Reescribimos el archivo originando los cambios en la misma línea.
			FileOutputStream fileOut = new FileOutputStream(filePath);
			fileOut.write(inputStr.getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("No se ha podidio ubicar el archivo AppLaunch.mce");
		}
	}

}
