package Ventanas.TermsConfirmed;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Clase controladora del panel de términos y condiciones.
 */
public class TermsConfirmedController {
	@FXML
	Button salir;
	@FXML
	Button iniciar;

	/**
	 * Evento para cerrar el programa si el usuario así lo decide.
	 */
	EventHandler<MouseEvent> cerrarPrograma = event -> {
		System.exit(0);
	};

	/**
	 * Al mostrarse la ventana se ejecuta este método
	 */
	@FXML
	private void initialize(){
		salir.addEventHandler(MouseEvent.MOUSE_CLICKED, cerrarPrograma);
	}

}
