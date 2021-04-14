package ObjetosCrucero.Servicios;

public abstract class Usuario {

	private String codigoEmpleado;
	private String nieEmpleado;
	private String nombreEmpleado;
	private String apellidoEmpleado;


	//Getters y setters

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getNieEmpleado() {
		return nieEmpleado;
	}

	public void setNieEmpleado(String nieEmpleado) {
		this.nieEmpleado = nieEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApellidoEmpleado() {
		return apellidoEmpleado;
	}

	public void setApellidoEmpleado(String apellidoEmpleado) {
		this.apellidoEmpleado = apellidoEmpleado;
	}



	/**
	 * Constructor con todos los parámetros
	 */
	public Usuario(String codigoEmpleado, String nieEmpleado, String nombreEmpleado, String apellidoEmpleado) {
		this.setCodigoEmpleado(codigoEmpleado);
		this.setNieEmpleado(nieEmpleado);
		this.setNombreEmpleado(nombreEmpleado);
		this.setApellidoEmpleado(apellidoEmpleado);
	}


}
