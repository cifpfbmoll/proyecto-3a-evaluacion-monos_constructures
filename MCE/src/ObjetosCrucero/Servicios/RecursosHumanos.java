package ObjetosCrucero.Servicios;

import Utils.DBUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecursosHumanos extends Empleado {


	/**
	 * Constructor con todos los parámetros
	 */
	public RecursosHumanos(String codigoEmpleado, String nieEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreServicio) {
		super(codigoEmpleado, nieEmpleado, nombreEmpleado, apellidoEmpleado, nombreServicio);
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
		DBUtils.createConnectionDB();
		String empleadosSQL = ("SELECT * FROM EMPLEADO;");
		PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(empleadosSQL);
		ResultSet resultSet = sentencia.executeQuery();



		//Rellenamos la lista con los datos obtenidos
		while ( resultSet.next() ){

			Empleado empleadoItr = new Empleado(
					resultSet.getString("CODIGO_EMPLEADO"),
					resultSet.getString("NIE_EMPLEADO"),
					resultSet.getString("NOMBRE_EMPLEADO"),
					resultSet.getString("APELLIDO_EMPLEADO"),
					resultSet.getString("CODIGO_SERVICIO"),
					resultSet.getString("DOMICILIACION_EMPLEADO"),
					Date.valueOf(resultSet.getString("FECHA_NACIMIENTO_EMPLEADO"))
			);
			listaEmpleados.add(empleadoItr);
		}
		DBUtils.getConnectionDB().close();
		return listaEmpleados;
	}

}
