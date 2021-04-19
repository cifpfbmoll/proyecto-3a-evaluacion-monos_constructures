package Utils;

import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.Usuario;
import javafx.event.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Credentials {

	private static Usuario loggedUser;


	//Getter general para extraer la información del usuario actual.
	public static Usuario getLoggedUser() {
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
						userInfo.getString(4)
				);
				break;
			//Aquí se irán añadiendo los distintos servicios de la aplicación
		}
		testing();
	}


	/**
	 * Función para mostrar la pantalla de inicio de un empleado dependiendo de su servicio.
	 * @param event El evento que llama a esta función
	 * @throws IOException Excepción al no poder leer el archivo en el FXMLPath.
	 */
	public static void loadUserWindow(Event event) throws IOException {
		String nombreEmpleadoCompleto = loggedUser.getNombreEmpleado() + " " + loggedUser.getApellidoEmpleado();

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
	 * [TESTING]
	 * Este método está enfocado especialmente para el PROCESO DE DESARROLLO de la aplicación
	 * Este debe ser eliminado de la versión final junto a todas sus referencias en el código final.
	 */
	private static void testing(){
		System.out.println("Bienvenido, " + getLoggedUser().getNombreEmpleado());
		if (loggedUser instanceof RecursosHumanos){
			System.out.println("Esperamos que le vaya bien su día en RRHH");
		}
	}

}
