package Ventanas.LogIn;

import Utils.Credentials;
import Utils.DBUtils;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class LogInController {
	@FXML
	TextField id;

	@FXML
	PasswordField password;

	@FXML
	Button acceder;

	@FXML
	AnchorPane login_card;

	@FXML
	HBox login_warning;

	/**
	 * Oculta el mensaje de error de cuando no se introducen correctamente las credenciales al iniciar la sesión.
	 */
	private void ocultarError(){
		login_warning.setVisible(false);
		id.getStyleClass().remove(0);
		id.getStyleClass().add("text_field");
		password.getStyleClass().remove(0);
		password.getStyleClass().add("text_field");
	}


	/**
	 * Trata de validar que las credenciales introducidas son correctas y coiniden con los datos de un usuario de
	 * nuestra base de datos
	 */
	private void iniciarSesion(Event event) {
		try {
			if (DBUtils.employeeLogin(id.getText(), DBUtils.encrypt(password.getText(), password.getText()))){
				if (password.getText().equals("MCE123")){
					WindowUtils.cambiarVentana(
						event,
						"Cambiar contraseña",
						"/Ventanas/LogIn/changePasssword/changePassword.fxml",
						false
					);
				} else {
					Credentials.loadUserWindow(event);
				}
			} else {
				login_warning.setVisible(true);
				id.getStyleClass().remove(0);
				id.getStyleClass().add("text_field_error");
				password.getStyleClass().remove(0);
				password.getStyleClass().add("text_field_error");
				WindowUtils.errorShakeScreen(login_card);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}


	/**
	 * Evento para realizar la validación del Inicio de sesión y llamar a la base de datos.
	 */
	EventHandler<MouseEvent> loginOnClick = event -> {
		login_card.requestFocus();
		iniciarSesion(event);
	};

	//Soporte para cuando se presiona ENTER
	EventHandler<KeyEvent> loginOnEnter = event -> {
		if (event.getCode() == KeyCode.ENTER){
			login_card.requestFocus();
			iniciarSesion(event);
		}
	};

	/**
	 * Evento para ocultar el mensaje de error cuando se introducen nuevos valores en los campos de texto.
	 */
	EventHandler<KeyEvent> ocultarAlEscribir = event -> ocultarError();

	@FXML
	private void initialize() throws InterruptedException {

		ocultarError();

		//Añadiendo las funcionalidades a los nodos
		acceder.setOnMouseClicked(loginOnClick);
		id.setOnKeyPressed(loginOnEnter);
		password.setOnKeyPressed(loginOnEnter);
		id.addEventHandler(KeyEvent.KEY_PRESSED, ocultarAlEscribir);
		password.addEventHandler(KeyEvent.KEY_PRESSED, ocultarAlEscribir);

		//Creando la animación de entrada
		Animation.card_animation_BOTTOM_CENTER(login_card);
	}
}
