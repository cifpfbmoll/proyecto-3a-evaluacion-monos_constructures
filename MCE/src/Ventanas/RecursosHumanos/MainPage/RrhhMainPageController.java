package Ventanas.RecursosHumanos.MainPage;

import ObjetosCrucero.Servicios.RecursosHumanos;
import Utils.Credentials;
import Utils.Mensajes;
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


	@FXML
	private void initialize(){
		welcomeMessage.setText(Mensajes.getMensajeBienvenida());
		Animation.setFechaYHora(fechaYHora);
		Tooltip.install(logout, new Tooltip("Cerrar sesión"));
		Animation.card_animation_LEFT_RIGHT(mainCard);
		userInfo.setText(Mensajes.getUserInfo());
		logout.addEventHandler(MouseEvent.MOUSE_CLICKED, logOut);

		final double SPEED = 0.005;
		wtabScroll.getContent().setOnScroll(scrollEvent -> {
			double deltaY = scrollEvent.getDeltaY() * SPEED;
			wtabScroll.setVvalue(wtabScroll.getVvalue() - deltaY);
		});

	}


}
