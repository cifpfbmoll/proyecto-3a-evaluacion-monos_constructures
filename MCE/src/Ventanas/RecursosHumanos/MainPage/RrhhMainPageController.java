package Ventanas.RecursosHumanos.MainPage;

import Utils.Credentials;
import Utils.Mensajes;
import Ventanas.Fx.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ImageView;
import java.awt.event.MouseListener;
import java.util.logging.Handler;


public class RrhhMainPageController {

	@FXML
	AnchorPane mainCard;

	@FXML
	Label welcomeMessage;

	@FXML
	VBox addUser;

	@FXML
	private void initialize(){
		Animation.card_animation_LEFT_RIGHT(mainCard);
		welcomeMessage.setText(Mensajes.getMensajeBienvenida());
	}


}
