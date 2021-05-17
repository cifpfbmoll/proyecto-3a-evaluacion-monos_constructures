package Ventanas.RecursosHumanos.DeleteStaff.Completed;

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

public class RrhhDeleteCompleted {

	//Objetos Crucero
	private static Empleado empleadoDespedido;

	//Elementos gráficos

	@FXML
	AnchorPane mainCard;

	@FXML
	Label fechaYHora;

	@FXML
	Label userInfo;


	//Botones

	@FXML
	Button despedirEmpleado;

	@FXML
	Button paginaPrincipal;


	EventHandler<MouseEvent> despedirOtroEmpleado = event -> {
		try {
			WindowUtils.cambiarVentana(
					event,
					"Tramitar despido",
					"/Ventanas/RecursosHumanos/DeleteStaff/rrhh_delete_staff.fxml",
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
	 * @param empleado El empleado recién eliminado.
	 */
	public static void setInformacionEmpleado(Empleado empleado){
		empleadoDespedido = empleado;
	}

	@FXML
	private void initialize() throws InterruptedException {

		// Ajustamos la opacidad de entrada
		mainCard.setOpacity(0);

		//Ajustamos los distintos textos del panel
		Animation.setFechaYHora(fechaYHora);

		//representamos la informacion del empleado despedido
		userInfo.setText(
			empleadoDespedido.getCodigoEmpleado() + "\n" +
			empleadoDespedido.getDni() + "\n" +
			empleadoDespedido.getNombre() + "\n" +
			empleadoDespedido.getApellido() + "\n" +
			empleadoDespedido.getFechaNacimiento() + "\n" +
			empleadoDespedido.getDireccion() + "\n" +
			empleadoDespedido.getTipoServicio()
		);

		//Añadimos la funcionalidad a los botones
		despedirEmpleado.addEventHandler(MouseEvent.MOUSE_CLICKED, despedirOtroEmpleado);
		paginaPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, volverPaginaPrincipal);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
