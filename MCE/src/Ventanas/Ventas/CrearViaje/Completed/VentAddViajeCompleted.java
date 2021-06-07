package Ventanas.Ventas.CrearViaje.Completed;

import ObjetosCrucero.Servicios.Empleado;
import Utils.Credentials;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VentAddViajeCompleted {

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
					"Recursos Humanos ~ " + Credentials.getLoggedUser().getNombre() + " " + Credentials.getLoggedUser().getApellido(),
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
						nuevoEmpleado.getServicio()
		);

		//Añadimos la funcionalidad a los botones
		nuevoUsuario.addEventHandler(MouseEvent.MOUSE_CLICKED, crearUnNNuevoUsuario);
		paginaPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, volverPaginaPrincipal);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
