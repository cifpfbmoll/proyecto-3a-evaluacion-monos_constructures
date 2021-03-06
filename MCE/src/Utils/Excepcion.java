package Utils;

public enum Excepcion {

	//OPERATION
	OPERATION_NOT_COMPLETED ("Error en la Operación", "No se ha podido completar la operación solicitada"),

	//SQL
	SQL_NOT_CONNECTED ("Se ha producido un error de Conexión", "No se puede conectar con la base de datos de MCE"),
	SQL_NOT_FOUND ("Los campos no han sido encontrados", "No se ha encontrado ninguna referencia a los campos introducidos"),
	SQL_CRITICAL ("Conexión", "Se ha producido un error crítico en relación a la base de datos"),
	SQL_PRIMARY_CONSTRAINT_VIOLATION ("Los datos introducidos no son correctos", "El valor introducido, ya corresponde a otro en la base de datos"),

	//FILE
	FILE_NOT_RECHEABLE ("Archivo", "No se ha encontrado el archivo");

	//[Aqui se añadiran todas las demás excepciones posibles]


	private final String tipoError;
	private final String mensajeError;

	Excepcion(String tipoError, String mensajeError) {
		this.tipoError = tipoError;
		this.mensajeError = mensajeError;
	}

	public String getTipoError() {return this.tipoError;}
	public String getMensajeError() {return this.mensajeError;}

}
