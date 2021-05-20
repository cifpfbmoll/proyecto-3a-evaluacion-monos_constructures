package ObjetosCrucero.Servicios;


import Utils.DBUtils;
import Utils.Excepcion;
import Ventanas.Excepciones.ExcepcionesController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ventas extends Empleado{

	/**
	 * 	Constructor con todos los parámetros
	 */
	public Ventas(String codigoEmpleado, String dni, String nombre, String apellido, Servicio servicio) {
		super(codigoEmpleado, dni, nombre, apellido, servicio);
	}

	/**
	 * Se crea un viaje nuevo dentro de la BBDD para poder asi asignar a posteriori Empleados y Billetes
	 * @param viaje el viaje donde se van a obtener los datos para subirlos a la bbdd.
	 * @throws SQLException si no se ha podido completar el rollback o el volver a poner el Autocommit a true
	 */
	public static void crearViaje(Viaje viaje) throws SQLException {

		//Las sentencias a ser insertadas (todas o ninguna)
		String sentenciaViaje = "INSERT INTO VIAJE VALUES(?, ?, ?, ?)";
		String sentenciaParadas = "INSERT INTO PARADAS VALUES(?, ?, ?, ?, ?, ?)";

		                                                                         //Sentencias autocloasbles
		try (PreparedStatement insertarViaje = DBUtils.getConnectionDB().prepareStatement(sentenciaViaje);
		     PreparedStatement insertarParadas = DBUtils.getConnectionDB().prepareStatement(sentenciaParadas)) {

			DBUtils.getConnectionDB().setAutoCommit(false);

			//Insertamos el viaje
			insertarViaje.setString(1, viaje.getCodigoCrucero());
			insertarViaje.setString(2, viaje.getFechaEmbarque().toString());
			insertarViaje.setString(3, viaje.getFechaLlegada().toString());
			insertarViaje.setString(4, viaje.getDescripcion());
			insertarViaje.executeUpdate();

			//Insertamos cada parada del viaje
			for (Parada parada : viaje.getListaParadas()) {
				insertarParadas.setString(1, viaje.getCodigoCrucero());
				insertarParadas.setString(2, viaje.getFechaEmbarque().toString());
				insertarParadas.setString(3, parada.getCodigoPuerto());
				insertarParadas.setInt(4, parada.getNumeroParada());
				insertarParadas.setString(5, parada.getFechaLlegada().toString());
				insertarParadas.setString(6, parada.getFechaSalida().toString());
				insertarParadas.executeUpdate();
			}
			DBUtils.getConnectionDB().commit();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			ExcepcionesController.lanzarExcepcion(Excepcion.SQL_NOT_CONNECTED);
			DBUtils.getConnectionDB().rollback();
		} finally {
			DBUtils.getConnectionDB().setAutoCommit(true);

		}
	}

}
