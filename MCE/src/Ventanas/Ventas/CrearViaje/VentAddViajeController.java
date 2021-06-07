package Ventanas.Ventas.CrearViaje;

import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.Servicio;
import Utils.Credentials;
import Utils.Excepcion;
import Utils.WindowUtils;
import Ventanas.Excepciones.ExcepcionesController;
import Ventanas.Fx.Animation;
import Ventanas.Ventas.CrearViaje.Completed.VentAddViajeCompleted;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class VentAddViajeController {

	//Elementos gráficos

	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;

	@FXML
	ScrollPane wtabScroll;


	//Botones

	@FXML
	VBox backButton;


	//Evento para volver a la anterior pantalla
	EventHandler<MouseEvent> goBack = event -> {
		String nombreEmpleadoCompleto = Credentials.getLoggedUser().getNombre() + " " + Credentials.getLoggedUser().getApellido();
		Animation.card_animation_EXIT_TO_RIGHT(
				mainCard,
				"Ventas ~ " + nombreEmpleadoCompleto,
				"../Ventanas/Ventas/MainPage/vent_main_page.fxml",
				event
				);
	};


	@FXML
	private void initialize() throws InterruptedException {

		// Ajustamos la opacidad de entrada
		mainCard.setOpacity(0);

		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);

		//Añadimos la funcionalidad a los botones
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
