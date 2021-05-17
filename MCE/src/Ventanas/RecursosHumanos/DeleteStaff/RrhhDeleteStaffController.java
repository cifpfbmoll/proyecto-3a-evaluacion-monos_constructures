package Ventanas.RecursosHumanos.DeleteStaff;

import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.TipoServicio;
import Utils.Credentials;
import Utils.DBUtils;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import Ventanas.RecursosHumanos.AddStaff.Completed.RrhhAddCompleted;
import Ventanas.RecursosHumanos.DeleteStaff.Completed.RrhhDeleteCompleted;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RrhhDeleteStaffController {

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

	@FXML
	Button despedirEmpleado;



	//Formulario

	@FXML
	TextField dni;

	@FXML
	TextField razon;


	// Errores

	@FXML
	HBox errorDNI;

	@FXML
	HBox errorRazon;


	//Evento para volver a la anterior pantalla
	EventHandler<MouseEvent> goBack = event -> {
		String nombreEmpleadoCompleto = Credentials.getLoggedUser().getNombre() + " " + Credentials.getLoggedUser().getApellido();
		Animation.card_animation_EXIT_TO_RIGHT(
				mainCard,
				"Recursos Humanos ~ " + nombreEmpleadoCompleto,
				"../Ventanas/RecursosHumanos/MainPage/rrhh_main_page.fxml",
				event
				);
	};


	//Evento para despedir a un empleado en la base de datos
	EventHandler<MouseEvent> borrarEmpleado = event -> {
		ocultarErrores();
		if (dniValido() && razonValida()) {
			Empleado empleado = new Empleado(dni.getText());
			RrhhDeleteCompleted.setInformacionEmpleado(Empleado.getEmbleadoByDNI(empleado.getDni()));
			RecursosHumanos.tramitarDespido(empleado);
			try {
				WindowUtils.cambiarVentana(
						event,
						"Despido completado",
						"../Ventanas/RecursosHumanos/DeleteStaff/Completed/rrhh_delete_completed.fxml",
						false
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			WindowUtils.errorShakeScreen(mainCard);
		}
	};



	/**
	 * Comprobamos que el DNI introducido en el formulario existe dentro de la base de datos y por lo tanto pertenece a un empleado.
	 * @return devolvemos true o false depenciendo del resultado de la busqueda.
	 * @throws SQLException Devuelve una excepcion en relacion a la conexion con la base de datos.
	 */
	private boolean dniValido() {

		//El booleano que se va a retornar indica si el dni existe o no
		boolean validez = false;

		String sentenciaSQL = "SELECT * FROM EMPLEADO WHERE NIE_EMPLEADO = ?";

		try (PreparedStatement busquedaDDBB = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {

			busquedaDDBB.setString(1, dni.getText());
			ResultSet resultados = busquedaDDBB.executeQuery();

			/*
			Tambien comprobamos de paso si el empleado ya a sido despedido o no anteriormente
			quitar [&& resultados.getBoolean("ACTIVO")] del if para realizar pruebas con empleados ya despedidos.
			 */
			if (resultados.next() && resultados.getBoolean("ACTIVO")) {
				validez = true;
			} else {
				errorDNI.setVisible(true);
			}

			resultados.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return validez;
	}


	/**
	 * Comprobamos la validez de la razon del despido del empleado
	 * @return verdadero o falso en funcion a si es valido o no
	 */
	private boolean razonValida(){
		boolean valida = true;

		if (razon.getText().length() < 10 || razon.getText().length() > 40){
			valida = false;
		}

		//Aqui se pueden añadir mas comprobaciones en un futuro [...]

		if (!valida){
			errorRazon.setVisible(true);
		}

		return valida;
	}


	//Método para reestablecer todos los errores en la pantalla
	private void ocultarErrores(){
		errorDNI.setVisible(false);
		errorRazon.setVisible(false);
	}


	@FXML
	private void initialize() throws InterruptedException {

		// Ajustamos la opacidad de entrada
		mainCard.setOpacity(0);

		// Ajustando la velocidad de "Scroll" del ScrollPane
		final double SPEED = 0.002;
		wtabScroll.getContent().setOnScroll(scrollEvent -> {
			double deltaY = scrollEvent.getDeltaY() * SPEED;
			wtabScroll.setVvalue(wtabScroll.getVvalue() - deltaY);
		});


		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);

		//Añadimos la funcionalidad a los botones
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);
		despedirEmpleado.addEventHandler(MouseEvent.MOUSE_CLICKED, borrarEmpleado);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);

		ocultarErrores();

	}

}
