package Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;

/**
 * Esta clase se encarga de gestionar las conexiones y peticiones realizadas a la base de datos definida en los atributos.
 * Desde aquí se creará y cerrará la conexión, se crearán los métodos pertinentes para realizar diversas actividades.
 */
public class DBUtils {
	private static final String DB_PATH = "51.178.152.222:3306/mce_cruceros";
	private static final String DB_USER = "mce_cruceros";
	private static final String DB_PASSWORD = "a223@sign@l:23:sign&%lo@d::bl0ck";

	private static Connection connectionDB;

	/**
	 * Esta función se encarga de conectar el programa a la base de datos mediante un usuario y contraseña.
	 * Se debe llamar desde clases externas, NUNCA desde un método interno de esta clase.
	 * @throws SQLException Devuelve una excepción en caso de no poder conectarse a la base de datos.
	 */
	public static void createConnectionDB() throws SQLException {
		connectionDB = DriverManager.getConnection("jdbc:mysql://" + DB_PATH, DB_USER, DB_PASSWORD);
	}

	/**
	 * Obtenemos la conexión a la base de datos
	 * @return devolvemos la conexión creada anteriormente
	 */
	public static Connection getConnectionDB(){
		return connectionDB;
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
	 * Esta función se encarga de autentificar la identidad de un empleado dentro de la DDBB
	 * @param nombre El nombre de nuestro empleado
	 * @param password La contraseña correspondiente.
	 * @return Devuelve verdadero o falso dependiendo del resultado de la operación.
	 * @throws SQLException Lanza una excepción en caso de no estar bien formulada la consulta [NO DEBERÍA].
	 */
	public static boolean employeeLogin(String nombre, String password) throws SQLException {
		createConnectionDB();
		String log = "select " +
				"               codigo_empleado, " +
				"               nie_empleado, " +
				"               nombre_empleado, " +
				"               apellido_empleado, " +
				"               codigo_servicio " +
				"          from " +
				"               empleado " +
				"          where " +
				"               nie_empleado LIKE ? AND contraseña_empleado LIKE ?";
		PreparedStatement sentencia= getConnectionDB().prepareStatement(log);
		sentencia.setString(1, nombre);
		sentencia.setString(2, password);
		ResultSet resultSet = sentencia.executeQuery();

		boolean isValidLogin;

		if (resultSet.next()){
			Credentials.setUserAtService(resultSet);
			isValidLogin = true;
		} else {
			isValidLogin = false;
		}

		return isValidLogin;
	}


	/**
	 * Encriptación de una cadena de texto, funciona para añadir privacidad a nuestra base de datos, de forma que sea
	 * casi imposible descifrar una clave sin conocerla.
	 * El tipo de algoritmo aplicado es Blowfish.
	 * @param strClearText El texto sin cifrar
	 * @param strKey La clave privada del algoritmo
	 * @return Devuelve la cadena de texto cifrada
	 * @throws Exception Produce una excepción en caso de no poder cifrar la cadena
	 */
	public static String encrypt(String strClearText,String strKey) throws Exception{
		String strData;
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
			strData=new String(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return strData;
	}
}
