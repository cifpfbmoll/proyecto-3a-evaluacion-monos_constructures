package ObjetosCrucero.Servicios;

import Utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecursosHumanos extends Empleado {


	/**
	 * Constructor con todos los parámetros
	 */
	public RecursosHumanos(String codigoEmpleado, String nieEmpleado, String nombreEmpleado, String apellidoEmpleado, Servicio servicio) {
		super(codigoEmpleado, nieEmpleado, nombreEmpleado, apellidoEmpleado, servicio);
	}


	// Métodos de la clase

	/**
	 * Esta función se encarga de crear una lista con la información e todos los empleados de la empresa
	 *  [ACTUALIZACIÓN PENDIENTE] ~ Añadir salario a la lista ?
	 * @return Devuelve una lista con toda la información referente a los empleados de la empresa
	 * @throws SQLException Lanza una excepción cuando no se puede acceder a la base de datos
	 */
	public static List<Empleado> getListaEmpleados() throws SQLException {

		List<Empleado> listaEmpleados = new ArrayList<Empleado>();

		//Sentencia SQL para obtener la información
		String empleadosSQL = ("SELECT * FROM EMPLEADO;");
		PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(empleadosSQL);
		ResultSet resultSet = sentencia.executeQuery();


		ArrayList<Servicio> listaServicios = Servicio.getListaServicios();

		//Rellenamos la lista con los datos obtenidos
		while ( resultSet.next() ){

			Empleado empleadoItr = new Empleado(
					resultSet.getString("CODIGO_EMPLEADO"),
					resultSet.getString("NIE_EMPLEADO"),
					resultSet.getString("NOMBRE_EMPLEADO"),
					resultSet.getString("APELLIDO_EMPLEADO"),
					Servicio.buscarCodigo(resultSet.getString("CODIGO_SERVICIO")),
					resultSet.getString("DOMICILIACION_EMPLEADO"),
					LocalDate.parse(resultSet.getString("FECHA_NACIMIENTO_EMPLEADO"))
			);
			listaEmpleados.add(empleadoItr);
		}
		resultSet.close();
		sentencia.close();
		return listaEmpleados;
	}


	/**
	 * Añadimos un empleado a nuestra base de datos, obteniendo la información desde el formulario de la ventana.
	 * @param empleado el empleado que va a ser añadido
	 * @throws Exception SQLException: la consulta falla, Encrypt: Cuando no se puede cifrar la contraseña.
	 */
	public static void addEmpleado(Empleado empleado) throws Exception {

		String empleadosSQL = ("INSERT INTO EMPLEADO VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

		try (PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(empleadosSQL)) {
			//Sentencia SQL para añadir la información
			DBUtils.getConnectionDB().setAutoCommit(false);


			sentencia.setString(1, empleado.getCodigoEmpleado());
			sentencia.setString(2, empleado.getDni());
			sentencia.setString(3, empleado.getNombre());
			sentencia.setString(4, empleado.getApellido());
			sentencia.setString(5, empleado.getDireccion());
			sentencia.setString(6, empleado.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy-dd-MM")));
			sentencia.setString(7, empleado.getServicio().getCodigo());
			sentencia.setString(8, DBUtils.encrypt("MCE123", "MCE123"));
			sentencia.executeUpdate();

			DBUtils.getConnectionDB().commit();

		} catch (SQLException sqle){
			sqle.printStackTrace();
			DBUtils.getConnectionDB().rollback();
		} finally {
			DBUtils.getConnectionDB().setAutoCommit(true);
		}

	}
}
