package Utils;

import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.Empleado;
import javafx.event.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Credentials {

	private static Empleado loggedUser;


	//Getter general para extraer la información del usuario actual.
	public static Empleado getLoggedUser() {
		return loggedUser;
	}


	/**
	 * Registra los datos del login con la base de datos y los enlaza con el usuario actual que está utilizando la
	 * aplicación, por defecto, el usuario es abstracto y se mantiene sin instanciar, pero al realizar el login hacemos
	 * que se instancie dependiendo del servicio al que pertenece con sus referentes datos.
	 *
	 * @param userInfo El resultado del login con la base de datos.
	 * @throws SQLException Falla al no poder recuperar los datos de la BBDD.
	 */
	public static void setUserAtService(ResultSet userInfo) throws SQLException {
		switch (userInfo.getString("CODIGO_SERVICIO")){
			case "RRHH":

				loggedUser = new RecursosHumanos(
						userInfo.getString(1),
						userInfo.getString(2),
						userInfo.getString(3),
						userInfo.getString(4),
						"Recursos Humanos"
				);
				break;
			//Aquí se irán añadiendo los distintos servicios de la aplicación
		}
	}


	/**
	 * Función para mostrar la pantalla de inicio de un empleado dependiendo de su servicio.
	 * @param event El evento que llama a esta función
	 * @throws IOException Excepción al no poder leer el archivo en el FXMLPath.
	 */
	public static void loadUserWindow(Event event) throws IOException {
		String nombreEmpleadoCompleto = loggedUser.getNombre() + " " + loggedUser.getApellidos();

		if ( loggedUser instanceof RecursosHumanos ){
			WindowUtils.cambiarVentana(
					event,
					"Recursos Humanos ~ " + nombreEmpleadoCompleto,
					"../Ventanas/RecursosHumanos/MainPage/rrhh_main_page.fxml",
					true
			);
		}
	}

	/**
	 * Método para cerrar la sesión del usuario actual y volver a mostrar la pantalla del log in.
	 * @param event el evento por el cual se está accediendo al método (REQUISITO).
	 * @throws IOException lanza una excepción en caso de que no se pueda cargar la ventana.
	 */
	public static void logOut(Event event) throws IOException {
		loggedUser = null;
		WindowUtils.cambiarVentana(
				event,
				"Iniciar sesión",
				"../Ventanas/LogIn/log_in.fxml",
				false
		);
	}

}
