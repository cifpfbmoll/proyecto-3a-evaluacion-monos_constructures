package Ventanas.Excepciones;

import Utils.Excepcion;
import javafx.scene.control.Alert;

public class ExcepcionesController {


	public static void lanzarExcepcion(Excepcion excepcion){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(excepcion.getTipoError());
		alert.setContentText(excepcion.getMensajeError());

		alert.showAndWait();
	}
}
