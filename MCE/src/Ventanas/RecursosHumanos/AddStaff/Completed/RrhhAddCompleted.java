package Ventanas.RecursosHumanos.AddStaff.Completed;

import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.TipoServicio;
import Utils.Credentials;
import Utils.Mensajes;
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

	//Objetos Crucero
	private static Empleado nuevoEmpleado;

	//Elementos gráficos

	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;

	@FXML
	Label userInfo;


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

	/**
	 * Método para obtener la informacion del empleado a través de la ventana anterior.
	 * @param empleado El empleado recién creado
	 */
	public static void setInformacionEmpleado(Empleado empleado){
		nuevoEmpleado = empleado;
	}

	@FXML
	private void initialize() throws InterruptedException {

		// Ajustamos la opacidad de entrada
		mainCard.setOpacity(0);

		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);

		//CAMBIAR---- Debe cojer los datos del usuario creado, no del loggeado.
		userInfo.setText(
				nuevoEmpleado.getCodigoEmpleado() + "\n" +
						nuevoEmpleado.getDni() + "\n" +
						nuevoEmpleado.getNombre() + "\n" +
						nuevoEmpleado.getApellido() + "\n" +
						nuevoEmpleado.getFechaNacimiento() + "\n" +
						nuevoEmpleado.getDireccion() + "\n" +
						nuevoEmpleado.getTipoServicio()
		);

		//Añadimos la funcionalidad a los botones
		nuevoUsuario.addEventHandler(MouseEvent.MOUSE_CLICKED, crearUnNNuevoUsuario);
		paginaPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, volverPaginaPrincipal);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
