package Ventanas.LogIn.changePasssword;

import Utils.Credentials;
import Utils.DBUtils;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePasswordController {
	@FXML
	PasswordField newPassword;

	@FXML
	PasswordField repeatPassword;

	@FXML
	Button confirmar;

	@FXML
	AnchorPane mainCard;

	@FXML
	HBox errorPassword;

	/**
	 * Oculta el mensaje de error de cuando no se introducen correctamente las credenciales al iniciar la sesión.
	 */
	private void ocultarError(){
		errorPassword.setVisible(false);
		newPassword.getStyleClass().remove(0);
		newPassword.getStyleClass().add("text_field");
		repeatPassword.getStyleClass().remove(0);
		repeatPassword.getStyleClass().add("text_field");
	}


	/**
	 * Trata de validar que las credenciales introducidas son correctas y coiniden con los datos de un usuario de
	 * nuestra base de datos
	 */
	private void iniciarSesion(Event event) {
		try {
			if (clavesValidas()){
				actualizarClave();
				Credentials.loadUserWindow(event);
			} else {
				errorPassword.setVisible(true);
				newPassword.getStyleClass().remove(0);
				newPassword.getStyleClass().add("text_field_error");
				repeatPassword.getStyleClass().remove(0);
				repeatPassword.getStyleClass().add("text_field_error");
				WindowUtils.errorShakeScreen(mainCard);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * Actualiza la contraseña dentro de la base de datos
	 * @throws SQLException lanza una excepción en caso de fallar la conexión o no poder realizar el update
	 */
	private void actualizarClave() throws SQLException {
		try {
			//Sentencia SQL para añadir la información
			DBUtils.getConnectionDB().setAutoCommit(false);

			String empleadosSQL = ("UPDATE EMPLEADO " +
					"               SET CONTRASEÑA_EMPLEADO = ? " +
					"               WHERE CODIGO_EMPLEADO LIKE ?;");
			PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(empleadosSQL);
			sentencia.setString(1, DBUtils.encrypt(newPassword.getText(), newPassword.getText()));
			sentencia.setString(2, Credentials.getLoggedUser().getCodigoEmpleado());
			sentencia.executeUpdate();

			DBUtils.getConnectionDB().commit();

		} catch (Exception sqle){
			sqle.printStackTrace();
			DBUtils.getConnectionDB().rollback();
		} finally {
			DBUtils.getConnectionDB().setAutoCommit(true);
		}
	}

	/**
	 * Comprueba la validez de la nueva contraseña
	 * @return Devuelve verdadero si las contraseñas introducidas son validas
	 */
	private boolean clavesValidas(){
		boolean sonValidas = true;
		if (newPassword.getText().length() < 6){
			sonValidas = false;
			errorPassword.setVisible(true);
		}
		if (newPassword.getText().equals("MCE123")){
			sonValidas = false;
			errorPassword.setVisible(true);
		}
		if (!newPassword.getText().equals(repeatPassword.getText())){
			sonValidas = false;
			errorPassword.setVisible(true);
		}
		return sonValidas;
	}


	/**
	 * Evento para realizar la validación del Inicio de sesión y llamar a la base de datos.
	 */
	EventHandler<MouseEvent> confirmOnClick = event -> {
		mainCard.requestFocus();
		iniciarSesion(event);
	};

	//Soporte para cuando se presiona ENTER
	EventHandler<KeyEvent> confirmOnEnter = event -> {
		if (event.getCode() == KeyCode.ENTER){
			mainCard.requestFocus();
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
		confirmar.setOnMouseClicked(confirmOnClick);
		newPassword.setOnKeyPressed(confirmOnEnter);
		repeatPassword.setOnKeyPressed(confirmOnEnter);
		newPassword.addEventHandler(KeyEvent.KEY_PRESSED, ocultarAlEscribir);
		repeatPassword.addEventHandler(KeyEvent.KEY_PRESSED, ocultarAlEscribir);

		//Creando la animación de entrada
		Animation.card_animation_BOTTOM_CENTER(mainCard);
	}
}
