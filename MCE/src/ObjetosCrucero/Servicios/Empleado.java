package ObjetosCrucero.Servicios;

public abstract class Empleado extends Usuario{

	private String codigoEmpleado;
	private String nombreServicio;

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
	 * Constructor con todos los parámetros
	 */
	public Empleado(String codigoEmpleado, String dni, String nombre, String apellido, String nombreServicio) {
		super( dni, nombre, apellido);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setNombreServicio(nombreServicio);
	}


}
