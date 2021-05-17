package Utils;

import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.Servicio;
import javafx.event.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
						Servicio.buscarCodigo("RRHH")
				);
				break;
			//Aquí se irán añadiendo los distintos servicios de la aplicación
		}
	}

	public static boolean validarCodigoEmpleado(String codigo){
		return codigo.matches("[0-9]{3}");
	}

	/**
	 * Función para mostrar la pantalla de inicio de un empleado dependiendo de su servicio.
	 * @param event El evento que llama a esta función
	 * @throws IOException Excepción al no poder leer el archivo en el FXMLPath.
	 */
	public static void loadUserWindow(Event event) throws IOException {
		String nombreEmpleadoCompleto = loggedUser.getNombre() + " " + loggedUser.getApellido();

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
	public static void logOut(Event event) throws IOException, SQLException {
		loggedUser = null;
		DBUtils.getConnectionDB().close();
		WindowUtils.cambiarVentana(
				event,
				"Iniciar sesión",
				"../Ventanas/LogIn/log_in.fxml",
				false
		);
	}

	public static boolean validarDni(String dni) {
		String listaLetras = "TRWAGMYFPDXBNJZSQVHLCKE";
		dni = dni.toUpperCase();
		Pattern patronDni = Pattern.compile("[0-9]{8}[A-Z]");
		Matcher validadorPatron = patronDni.matcher(dni);
		if (validadorPatron.matches()) {
			int letra = (int) (Long.parseLong(dni.replaceAll("([A-Z])",""))%23);
			if (listaLetras.charAt(letra) == dni.charAt(8)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}


	public static boolean validarCodigoEmpleado(String codigo){
		return codigo.matches("[0-9]{3}");
	}

	public static boolean validarNombre(String nombre) {
		Pattern patronNombre = Pattern.compile("[A-Z]{2,20}[ ]?[A-Z]{2,20}");
		Matcher validadorPatron = patronNombre.matcher(nombre.toUpperCase(Locale.ROOT));
		if (validadorPatron.matches()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean validarApellido(String apellido) {
		Pattern patronApellido = Pattern.compile("[A-Z]{2,20}[ ]?[A-Z]{2,20}");
		Matcher validadorPatron = patronApellido.matcher(apellido.toUpperCase(Locale.ROOT));
		return validadorPatron.matches();
	}

	public static boolean validarDireccion(String direccion) {
		Pattern patronDireccion = Pattern.compile("[A-Z 0-9,º'/]{10,50}");
		Matcher validadorPatron = patronDireccion.matcher(direccion.toUpperCase(Locale.ROOT));
		return validadorPatron.matches();
	}

	public static boolean validarFecha(LocalDate fechaNacimiento) {
		LocalDate fechaDeHoy = LocalDate.now();
		int edad = Period.between(fechaNacimiento, fechaDeHoy).getYears();
		return edad >= 18 && edad <= 120;
	}

	public static boolean validarFecha(LocalDate fechaNacimiento) {
		LocalDate fechaDeHoy = LocalDate.now();
		int edad = Period.between(fechaNacimiento, fechaDeHoy).getYears();
		return edad >= 18 && edad <= 120;
	}

}
