package ObjetosCrucero.Servicios;

import java.time.LocalDate;

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

	public void setTipoServicio(Servicio tiposServicios) {
		this.Servicio = tiposServicios;
	}

	/**
	 * Constructor con todos los parámetros
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, Servicio servicio, String direccion, LocalDate fechaNacimiento) {
		super( dni, nombre, apellido, direccion, fechaNacimiento);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setTipoServicio(servicio);
	}

	/**
	 * Constructor esencial
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, Servicio servicio){
		super(dni, nombre, apellido);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setTipoServicio(servicio);
	}


}
