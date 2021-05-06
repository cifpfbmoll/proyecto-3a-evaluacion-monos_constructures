package Ventanas.RecursosHumanos.AddStaff.Completed;

import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.TipoServicio;
import Utils.Credentials;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class RrhhAddCompleted {

	//Elementos gráficos

	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;


	//Botones

	@FXML
	Button nuevoUsuario;

	@FXML
	Button paginaPrincipal;


	EventHandler<MouseEvent> crearUnNNuevoUsuario = event -> {
		try {
			WindowUtils.cambiarVentana(
					event,
					"Crear un nuevo usuario",
					"/Ventanas/RecursosHumanos/AddStaff/rrhh_add_staff.fxml",
					false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};


	EventHandler<MouseEvent> volverPaginaPrincipal = event -> {
		try {
			WindowUtils.cambiarVentana(
					event,
					"Crear un nuevo usuario",
					"/Ventanas/RecursosHumanos/MainPage/rrhh_main_page.fxml",
					false
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};



	@FXML
	private void initialize() throws InterruptedException {

		// Ajustamos la opacidad de entrada
		mainCard.setOpacity(0);

		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);

		//Añadimos la funcionalidad a los botones
		nuevoUsuario.addEventHandler(MouseEvent.MOUSE_CLICKED, crearUnNNuevoUsuario);
		paginaPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, volverPaginaPrincipal);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
