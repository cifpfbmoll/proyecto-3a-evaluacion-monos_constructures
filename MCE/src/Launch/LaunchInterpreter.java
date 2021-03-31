package Launch;

import Utils.FileUtils;

import java.io.*;

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

			//Mostramos el archivo sin modificar [DEBUG]
			//System.out.println(inputStr);

			// Lógica para reemplazar el contenido del archivo
			if (estado.equals(" = aceptado")) {
				inputStr = inputStr.replace(valor + " = denegado", valor + " = aceptado");
			}

			// Para mostrar el contenido del archivo modificado [DEBUG]
			// System.out.println("----------------------------------\n" + inputStr);

			// Reescribimos el archivo originando los cambios en la misma línea.
			FileOutputStream fileOut = new FileOutputStream(filePath);
			fileOut.write(inputStr.getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("No se ha podidio ubicar el archivo AppLaunch.mce");
		}
	}

}
