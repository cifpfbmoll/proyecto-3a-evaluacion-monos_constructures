package ObjetosCrucero.Servicios;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.Date;

public class Empleado extends Usuario{

	private String codigoEmpleado;
	private TipoServicio tipoServicio;

	//Getters y setters

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tiposServicios) {
		this.tipoServicio = tiposServicios;
	}

	/**
	 * Constructor con todos los parámetros
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, TipoServicio servicio, String direccion, LocalDate fechaNacimiento) {
		super( dni, nombre, apellido, direccion, fechaNacimiento);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setTipoServicio(servicio);
	}

	/**
	 * Constructor esencial
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, TipoServicio servicio){
		super(dni, nombre, apellido);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setTipoServicio(servicio);
	}


}
