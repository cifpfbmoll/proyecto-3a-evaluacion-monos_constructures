package ObjetosCrucero.Servicios;

import java.util.Date;

public class Empleado extends Usuario{

	private String codigoEmpleado;
	private String nombreServicio;
	private TiposServicios tiposServicios;

	//Getters y setters

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	/**
	 * Getter y setter de TiposServicios
	 */
	public TiposServicios getTiposServicios() {
		return tiposServicios;
	}

	public void setTiposServicios(TiposServicios tiposServicios) {
		this.tiposServicios = tiposServicios;
	}

	/**
	 * Constructor con todos los parámetros
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, String nombreServicio, String direccion, Date fechaNacimiento) {
		super( dni, nombre, apellido, direccion, fechaNacimiento);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setNombreServicio(nombreServicio);
	}

	/**
	 * Constructor esencial
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, String nombreServicio){
		super(dni, nombre, apellido);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setNombreServicio(nombreServicio);
	}


}
