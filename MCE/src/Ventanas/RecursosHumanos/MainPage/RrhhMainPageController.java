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

	//Elementos graficos
	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;

	@FXML
	Label welcomeMessage;

	@FXML
	Label userInfo;


	//Botones del Worktab

	@FXML
	VBox addUser;

	@FXML
	VBox listUser;


	//Botones GUI

	@FXML
	ImageView logout;



	@FXML
	ScrollPane wtabScroll;

	//Para cerrar la sesión
	EventHandler<MouseEvent> logOut = event ->{
		try {
			Credentials.logOut(event);
		} catch (IOException ioe) {
			ioe.printStackTrace();
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

	//Ver lista de empleados
	EventHandler<MouseEvent> verListaEmpledos = event -> {
		Animation.card_animation_EXIT_TO_LEFT(
				mainCard,
				"Lista de empleados",
				"/Ventanas/RecursosHumanos/ViewStaff/rrhh_view_staff.fxml",
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
		listUser.addEventHandler(MouseEvent.MOUSE_CLICKED, verListaEmpledos);

		logout.addEventHandler(MouseEvent.MOUSE_CLICKED, logOut);
		Tooltip.install(logout, new Tooltip("Cerrar sesión")); //etiqueta del logout

		//Creando la animación de entrada
		Animation.card_animation_TOP_CENTER(mainCard);

	}
}
