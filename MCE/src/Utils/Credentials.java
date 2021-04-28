package Utils;

import ObjetosCrucero.Servicios.RecursosHumanos;
import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.TipoServicio;
import javafx.event.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
						TipoServicio.valueOf("RRHH")
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
	public static void logOut(Event event) throws IOException {
		loggedUser = null;
		WindowUtils.cambiarVentana(
				event,
				"Iniciar sesión",
				"../Ventanas/LogIn/log_in.fxml",
				false
		);
	}

	/**
	 * Este metodo te dice si un dni es valido segun si esta bien formatado
	 * y mira si la letra es valida.
	 *
	 * @param dni
	 * @return true si es valido, false si no
	 */
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

	/**
	 * valida un nombre para asegurarse de que solo contiene letras
	 * y entre 2 y 30 caracteres
	 *
	 * @param nombre
	 * @return true si es valido, false si no
	 */
	public static boolean validarNombre(String nombre) {
		Pattern patronNombre = Pattern.compile("[A-Z]{2,30}");
		Matcher validadorPatron = patronNombre.matcher(nombre.toUpperCase(Locale.ROOT));
		if (validadorPatron.matches()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * valida que el apellido solo contiene letras y como maximo un espacio
	 * el apellido es de minimo 4 letras y meximo de 39 + espacio
	 *
	 * @param apellido
	 * @return true si es valido, false si no
	 */
	public static boolean validarApellido(String apellido) {
		Pattern patronApellido = Pattern.compile("[A-Z]{2,19}[ ]{0,1}[A-Z]{2,20}");
		Matcher validadorPatron = patronApellido.matcher(apellido.toUpperCase(Locale.ROOT));
		if (validadorPatron.matches()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * valida que una direccion solo contenga letras, numeros, espacios, o º y '
	 * la direccion debe tener entre 10 y 40 caracteres
	 *
	 * @param direccion
	 * @return true si es valido, false si no
	 */
	public static boolean validarDireccion(String direccion) {
		Pattern patronDireccion = Pattern.compile("[A-Z 0-9,º']{10,40}");
		Matcher validadorPatron = patronDireccion.matcher(direccion.toUpperCase(Locale.ROOT));
		if (validadorPatron.matches()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * valida que el poseedor de la fecha de nacimiento introducida tenga minimo 18 años y maximo 100
	 * resta la fecha introducida a la actual en milisegundos y hace un cambio de unidad de milisegundos a años
	 *
	 * @param fecha
	 * @return true si es valido, false si no
	 */
	public static boolean validarFecha(Date fecha) {
		int diaHoy = LocalDate.now().getDayOfMonth();
		int mesHoy = LocalDate.now().getMonthValue();
		int anoHoy = LocalDate.now().getYear();
		Date hoy = new Date(anoHoy, mesHoy, diaHoy);
		int anos = (int) (hoy.getTime()-fecha.getTime())/(31556952*1000);
		if (anos > 18 && anos < 100) {
			return true;
		}
		else {
			return false;
		}
	}

}
