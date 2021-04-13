package Utils;

import Launch.LaunchInterpreter;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowUtils {

	/**
	 * Método para poder intercambiar ventanas gráficas dentro del programa.
	 * Este método podrá sobrecargar para establecer distintos parámetros pero de momento no los necesitamos.
	 * @param event El evento que ejecuta el cambio de página. (De él obtenemos la clase a la que pertenece).
	 * @param title El titulo de la página a la que vamos a acceder.
	 * @param FXMLPath La ruta del archivo FXML que contiene el contenido de la página.
	 * @throws IOException Lanza una excepción en caso de no poder encontrar el archivo FXML.
	 */
	public static void cambiarVentana(Event event, String title, String FXMLPath) throws IOException {
		Parent parent = FXMLLoader.load(WindowUtils.class.getResource(FXMLPath));
		Scene scene = new Scene(parent);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setTitle(title);
		window.setScene(scene);
	}
}
