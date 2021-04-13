package Utils;

import java.sql.*;

/**
 * Esta clase se encarga de gestionar las conexiones y peticiones realizadas a la base de datos definida en los atributos.
 * Desde aquí se creará y cerrará la conexión, se crearán los métodos pertinentes para realizar diversas actividades.
 */
public class DBUtils {
	private static final String DB_PATH = "localhost/mce_cruceros";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	private static Connection connectionDB;
	private static Statement statementDB;

	/**
	 * Esta función se encarga de conectar el programa a la base de datos mediante un usuario y contraseña.
	 * Se debe llamar desde clases externas, NUNCA desde un método interno de esta clase.
	 * @throws SQLException Devuelve una excepción en caso de no poder conectarse a la base de datos.
	 */
	public static void connectDB() throws SQLException {
		connectionDB = DriverManager.getConnection("jdbc:mysql://" + DB_PATH, DB_USER, DB_PASSWORD);
		statementDB = connectionDB.createStatement();
	}

	/**
	 * Función para cerrar la conexión con la base de datos después de una consulta.
	 * Es IMPORTANTE utilizarla independientemente del éxito de la búsqueda ya que de lo contrario estamos generando
	 * una pérdida de rendimiento y de seguridad innecesarias.
	 * Se debe llamar desde clases externas, NUNCA desde un método interno de esta clase.
	 * @throws SQLException Devuelve una excepción cuando no puede establecer el cierre con la base de datos. [NO DEBERÍA]
	 */
	public static void closeDB() throws SQLException {
		connectionDB.close();
	}

	/**
	 * Función genérica para hacer una consulta dentro de la BBDD.
	 * @param tableName Nombre de la tabla donde vamos a hacer una consulta.
	 * @param rows Los campos implicados en la búsqueda.
	 * @param Where El apartado Where donde establecemos las condiciones de búsqueda.
	 * @return Devuelve un set de resultados que deben ser tratados.
	 * @throws SQLException Lanza una excepción cuando la consulta es incorrecta [NO DEBERÍA].
	 */
	public static ResultSet searchInTable(String tableName, String rows, String Where) throws SQLException {
		return statementDB.executeQuery("SELECT " + rows + " FROM " + tableName + " WHERE " + Where);
	}

	/**
	 * Esta función se encarga de autentificar la identidad de un empleado dentro de la DDBB
	 * @param nombre El nombre de nuestro empleado
	 * @param password La contraseña correspondiente.
	 * @return Devuelve verdadero o falso dependiendo del resultado de la operación.
	 * @throws SQLException Lanza una excepción en caso de no estar bien formulada la consulta [NO DEBERÍA].
	 */
	public static boolean employeeLogin(String nombre, String password) throws SQLException {
		ResultSet resultSet = searchInTable("EMPLEADO", "CODIGO_SERVICIO",
				"NOMBRE_EMPLEADO LIKE '" + nombre + "' AND CONTRASEÑA_EMPLEADO LIKE '" + password + "'");
		if (resultSet.next()){
			return true;
		} else {
			return false;
		}
	}
}
