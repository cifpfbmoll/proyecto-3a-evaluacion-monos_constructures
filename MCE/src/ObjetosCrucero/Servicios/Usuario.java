package ObjetosCrucero.Servicios;

public abstract class Usuario {

	private String dni;
	private String nombre;
	private String apellido;
	private String direccion;

	//Getters y setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccionUsuario) {
		this.direccion = direccionUsuario;
	}

	/**
	 * Constructor con todos los parámetros
	 */
	public Usuario(String dni, String nombre, String apellido, String direccion) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setDireccion(direccion);
	}

	/**
	 * Constructor esencial
	 */
	public Usuario(String dni, String nombre, String apellido) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
	}
}
