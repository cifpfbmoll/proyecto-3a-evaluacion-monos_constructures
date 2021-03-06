package ObjetosCrucero.Servicios;

import Utils.DBUtils;
import Utils.Excepcion;
import Ventanas.Excepciones.ExcepcionesController;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import ObjetosCrucero.Servicios.Servicio;

public class Empleado extends Usuario{

	private String codigoEmpleado;
	private Servicio Servicio;

    //Getters y setters

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public Servicio getServicio() {
		return Servicio;
	}

	public void setServicio(Servicio tiposServicios) {
		this.Servicio = tiposServicios;
	}

	/**
	 * Constructor con todos los parámetros
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, Servicio servicio, String direccion, LocalDate fechaNacimiento) {
		super( dni, nombre, apellido, direccion, fechaNacimiento);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setServicio(servicio);
	}

	/**
	 * Constructor esencial
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, Servicio servicio){
		super(dni, nombre, apellido);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setServicio(servicio);
	}

	/**
	 * Constructor basico (solo para obtener info de la bbdd)
	 */
	public Empleado(String dni) {
		super(dni);
	}

	/**
	 * Obtenemos el perfil de un empleado a traves de su DNI
	 * @param dni el dni del empleado a buscar
	 * @return devuelve un empleado o nulo en caso de excepcion o busqueda sin resultados.
	 */
	public static Empleado getEmbleadoByDNI(String dni){
		Empleado empleado = null;
		String sentenciaSQL = "SELECT * FROM EMPLEADO WHERE NIE_EMPLEADO = ?";

		try (PreparedStatement busquedaDDBB = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {
			busquedaDDBB.setString(1, dni);
			ResultSet resultados = busquedaDDBB.executeQuery();
			if (resultados.next()){
				empleado = new Empleado(
						resultados.getString("CODIGO_EMPLEADO"),
						resultados.getString("NIE_EMPLEADO"),
						resultados.getString("NOMBRE_EMPLEADO"),
						resultados.getString("APELLIDO_EMPLEADO"),
						ObjetosCrucero.Servicios.Servicio.buscarCodigo(resultados.getString("CODIGO_SERVICIO")),
						resultados.getString("DOMICILIACION_EMPLEADO"),
						LocalDate.parse(resultados.getDate("FECHA_NACIMIENTO_EMPLEADO").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				);
			}
			resultados.close();
		} catch (SQLException sqle) {
			ExcepcionesController.lanzarExcepcion(Excepcion.SQL_NOT_FOUND);
			sqle.printStackTrace();
		}
		return empleado;
	}

	public static Empleado getEmbleadoByCodigo(int codigo){
		Empleado empleado = null;
		String sentenciaSQL = "SELECT * FROM EMPLEADO WHERE CODIGO_EMPLEADO = ?";

		try (PreparedStatement busquedaDDBB = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {
			busquedaDDBB.setInt(1, codigo);
			ResultSet resultados = busquedaDDBB.executeQuery();
			if (resultados.next()){
				empleado = new Empleado(
						resultados.getString("CODIGO_EMPLEADO"),
						resultados.getString("NIE_EMPLEADO"),
						resultados.getString("NOMBRE_EMPLEADO"),
						resultados.getString("APELLIDO_EMPLEADO"),
						ObjetosCrucero.Servicios.Servicio.buscarCodigo(resultados.getString("CODIGO_SERVICIO")),
						resultados.getString("DOMICILIACION_EMPLEADO"),
						LocalDate.parse(resultados.getDate("FECHA_NACIMIENTO_EMPLEADO").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				);
			}
			resultados.close();
		} catch (SQLException sqle) {
			ExcepcionesController.lanzarExcepcion(Excepcion.SQL_NOT_FOUND);
			sqle.printStackTrace();
		}
		return empleado;
	}

	public static ArrayList<Empleado> getEmbleadoAll() {
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String sentenciaSQL = "SELECT * FROM EMPLEADO;";

		try (PreparedStatement busquedaDDBB = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {
			ResultSet resultados = busquedaDDBB.executeQuery();
			while (resultados.next()){
				Empleado e = new Empleado(
						resultados.getString("CODIGO_EMPLEADO"),
						resultados.getString("NIE_EMPLEADO"),
						resultados.getString("NOMBRE_EMPLEADO"),
						resultados.getString("APELLIDO_EMPLEADO"),
						ObjetosCrucero.Servicios.Servicio.buscarCodigo(resultados.getString("CODIGO_SERVICIO")),
						resultados.getString("DOMICILIACION_EMPLEADO"),
						LocalDate.parse(resultados.getDate("FECHA_NACIMIENTO_EMPLEADO").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				);
				lista.add(e);
			}
			resultados.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return lista ;
	}

}
