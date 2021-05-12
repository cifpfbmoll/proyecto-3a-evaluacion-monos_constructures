package Ventanas.RecursosHumanos.AddStaff;

import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.Servicio;
import Utils.Credentials;
import Utils.WindowUtils;
import Ventanas.Fx.Animation;
import Ventanas.RecursosHumanos.AddStaff.Completed.RrhhAddCompleted;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.util.List;

public class RrhhAddStaffController {

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
	Button crearEmpleado;



	//Formulario

	@FXML
	TextField codigoEmpleado;

	@FXML
	TextField nombre;

	@FXML
	TextField apellidos;

	@FXML
	TextField dni;

	@FXML
	TextField domiciliacion;

	@FXML
	DatePicker fechaNacimiento;

	@FXML
	ChoiceBox<Servicio> servicio;



	// Errores
	@FXML
	HBox errorCodigoEmpleado;

	@FXML
	HBox errorNombre;

	@FXML
	HBox errorApellidos;

	@FXML
	HBox errorDNI;

	@FXML
	HBox errorDomiciliacion;

	@FXML
	HBox errorFechaNacimiento;


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

	//Evento para añadir un nuevo empleado a la base de datos
	EventHandler<MouseEvent> addEmpleado = event -> {
		ocultarErrores();
		if (camposValidos()){
			try {
				Empleado newEmpleado = new Empleado (
						codigoEmpleado.getText(),
						dni.getText(),
						nombre.getText(),
						apellidos.getText(),
						servicio.getValue(),
						domiciliacion.getText(),
						fechaNacimiento.getValue()
				);
				RecursosHumanos.addEmpleado(newEmpleado);
				RrhhAddCompleted.setInformacionEmpleado(newEmpleado);
				WindowUtils.cambiarVentana(
						event,
						"Crear un nuevo usuario",
						"../Ventanas/RecursosHumanos/AddStaff/Completed/rrhh_add_completed.fxml",
						false
				);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else {
			WindowUtils.errorShakeScreen(mainCard);
		}
	};

	//Método para comprobar que los datos introducidos son válidos.
	private boolean camposValidos(){
		boolean esValido = true;
		if (!Credentials.validarCodigoEmpleado(codigoEmpleado.getText())){
			esValido = false;
			errorCodigoEmpleado.setVisible(true);
		}
		if (!Credentials.validarNombre(nombre.getText())){
			esValido = false;
			errorNombre.setVisible(true);
		}
		if (!Credentials.validarApellido(apellidos.getText())){
			esValido = false;
			errorApellidos.setVisible(true);
		}
		if (!Credentials.validarDni(dni.getText())){
			esValido = false;
			errorDNI.setVisible(true);
		}
		if (!Credentials.validarDireccion(domiciliacion.getText())){
			esValido = false;
			errorDomiciliacion.setVisible(true);
		}
		if (!Credentials.validarFecha(fechaNacimiento.getValue())){
			esValido = false;
			errorFechaNacimiento.setVisible(true);
		}
		return esValido;
	}


	//Método para reestablecer todos los errores en la pantalla
	public void ocultarErrores(){
		errorCodigoEmpleado.setVisible(false);
		errorNombre.setVisible(false);
		errorApellidos.setVisible(false);
		errorDNI.setVisible(false);
		errorDomiciliacion.setVisible(false);
		errorFechaNacimiento.setVisible(false);
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
		servicio.setItems(FXCollections.observableArrayList(
				Servicio.listaServicios
		));
		servicio.setValue(servicio.getItems().get(0));
		fechaNacimiento.setValue(LocalDate.of(
				LocalDate.now().getYear() - 18,
				LocalDate.now().getMonth(),
				LocalDate.now().getDayOfMonth()
		));
		ocultarErrores();


		//Añadimos la funcionalidad a los botones
		backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);
		crearEmpleado.addEventHandler(MouseEvent.MOUSE_CLICKED, addEmpleado);

		//Creando la animación de entrada
		Animation.card_animation_RIGHT_CENTER(mainCard);
	}

}
