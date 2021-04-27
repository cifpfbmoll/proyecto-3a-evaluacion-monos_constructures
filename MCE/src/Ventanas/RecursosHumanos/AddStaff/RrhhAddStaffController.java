package Ventanas.RecursosHumanos.AddStaff;

import Utils.Credentials;
import Ventanas.Fx.Animation;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class RrhhAddStaffController {

	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;

	@FXML
	VBox backButton;

	@FXML
	ScrollPane wtabScroll;

	@FXML
	ChoiceBox tipoServicio;

	@FXML
	DatePicker fechaNacimiento;


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

		// Ajustamos la opacidad de entrada
		mainCard.setOpacity(0);

		// Ajustando la velocidad de "Scroll" del ScrollPane
		final double SPEED = 0.005;
		wtabScroll.getContent().setOnScroll(scrollEvent -> {
			double deltaY = scrollEvent.getDeltaY() * SPEED;
			wtabScroll.setVvalue(wtabScroll.getVvalue() - deltaY);
		});


		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);
		tipoServicio.setItems(FXCollections.observableArrayList(
				"Recursos Humanos", "Administración", "Ventas"
		));
		tipoServicio.setValue(tipoServicio.getItems().get(0));
		fechaNacimiento.setValue(LocalDate.of(
				LocalDate.now().getYear() - 18,
				LocalDate.now().getMonth(),
				LocalDate.now().getDayOfMonth()
		));


		//Añadimos la funcionalidad a los botones
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
