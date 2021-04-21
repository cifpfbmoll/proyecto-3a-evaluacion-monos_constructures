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
	public Empleado(String codigoEmpleado, String nieEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreServicio) {
		super(nombreEmpleado, apellidoEmpleado, nieEmpleado);
		this.setCodigoEmpleado(codigoEmpleado);
		this.setNombreServicio(nombreServicio);
	}


}
