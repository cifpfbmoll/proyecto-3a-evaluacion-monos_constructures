package ObjetosCrucero.Servicios;

public abstract class Usuario {

	private String nifUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;

	//Getters y setters

	public String getNifUsuario() {
		return nifUsuario;
	}

	public void setNifUsuario(String nifUsuario) {
		this.nifUsuario = nifUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	/**
	 * Constructor con todos los parámetros
	 */
	public Usuario(String nifUsuario, String nombreUsuario, String apellidoUsuario) {
		this.nifUsuario = nifUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
	}
}
