package Ventanas.RecursosHumanos.AddStaff;

import Utils.Credentials;
import Utils.Mensajes;
import Ventanas.Fx.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.TimeUnit;

public class RrhhAddStaffController {

	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;

	@FXML
	VBox backButton;


	EventHandler<MouseEvent> goBack = event -> {
		String nombreEmpleadoCompleto = Credentials.getLoggedUser().getNombre() + " " + Credentials.getLoggedUser().getApellido();
		Animation.card_animation_EXIT_TO_RIGHT(
				mainCard,
				"Recursos Humanos ~ " + nombreEmpleadoCompleto,
				"../Ventanas/RecursosHumanos/MainPage/rrhh_main_page.fxml",
				event
				);
	};


	@FXML
	private void initialize() throws InterruptedException {

		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);

		//Añadimos la funcionalidad a los botones
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);

	}

}
