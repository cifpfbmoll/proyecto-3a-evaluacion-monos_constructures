package Ventanas.TermsAndConditions;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class TermsAndConditionsController {
	@FXML
	Button aceptar;
	@FXML
	Button rechazar;

	/**
	 * Este evento se encarga de cargar la página de confirmación de los términos y condiciones.
	 * @catch Falla en caso de no poder cargar la página.
	 * IMPORTANTE! debemos añadir una ventana para mostrar las distintas excepciones a los usuarios.
	 */
	EventHandler<MouseEvent> aceptarTerminos = event ->{
		try {
			Parent termsConfirmed = FXMLLoader.load(getClass().getResource("../TermsConfirmed/terms_confirmed.fxml"));
			Scene termsConfirmedScene = new Scene(termsConfirmed);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(termsConfirmedScene);
		} catch (IOException e) {
			e.printStackTrace();
		}

	};

	/**
	 * Este evento cierra el programa ya que el usuario ha decidido NO aceptar los términos y condiciones.
	 */
	EventHandler<MouseEvent> cerrarPrograma = event -> {
		System.exit(0);
	};

	/**
	 * Este evento se lanza al cargar la página
	 */
	@FXML
	private void initialize() {
		aceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, aceptarTerminos);
		rechazar.addEventHandler(MouseEvent.MOUSE_CLICKED, cerrarPrograma);
	}

}
