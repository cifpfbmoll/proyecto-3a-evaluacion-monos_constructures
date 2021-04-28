package Ventanas.RecursosHumanos.MainPage;

import ObjetosCrucero.Servicios.RecursosHumanos;
import Utils.Credentials;
import Utils.Mensajes;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class RrhhMainPageController {


	@FXML
	AnchorPane mainCard;

	@FXML
	Label welcomeMessage;

	@FXML
	VBox addUser;

	@FXML
	Label fechaYHora;

	@FXML
	ImageView logout;

	@FXML
	Label userInfo;

	@FXML
	ScrollPane wtabScroll;

	//Para cerrar la sesión
	EventHandler<MouseEvent> logOut = event ->{
		try {
			Credentials.logOut(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	//Añadir un empleado
	EventHandler<MouseEvent> crearEmpleado = event -> {
		Animation.card_animation_EXIT_TO_LEFT(
				mainCard,
				"Añadir un empleado",
				"/Ventanas/RecursosHumanos/AddStaff/rrhh_add_staff.fxml",
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

		// Generando el texto de la ventana
		Animation.setFechaYHora(fechaYHora);
		userInfo.setText(Mensajes.getUserInfo());
		welcomeMessage.setText(Mensajes.getMensajeBienvenida());

		// Asignando los eventos a los botones
		addUser.addEventHandler(MouseEvent.MOUSE_CLICKED, crearEmpleado);
		logout.addEventHandler(MouseEvent.MOUSE_CLICKED, logOut);
		Tooltip.install(logout, new Tooltip("Cerrar sesión")); //etiqueta del logout

		//Creando la animación de entrada
		Animation.card_animation_TOP_CENTER(mainCard);

	}
}
