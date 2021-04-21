package ObjetosCrucero.Servicios;

public abstract class Usuario {

	private String nombre;
	private String apellidos;
	private String nie;

	public Usuario(String nombre, String apellidos, String nie){
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setNie(nie);
	}


	//Getters y setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNie() {
		return nie;
	}

	public void setNie(String nie) {
		this.nie = nie;
	}

}
