package ObjetosCrucero.Servicios;

@Deprecated
public enum ETipoServicio {
    RRHH ("Recursos Humanos", 2500);

    private String value;
    private int salario;

    ETipoServicio(String value, int salario){
        this.value = value;
        this.salario = salario;
    }

    /**
     * Obtener un tipo de servicio a partir de una String
     * @param valor la string con el valor
     * @return devuelve el tipo de servicio en caso de encontrarse, o nulo en caso contrario.
     */
    public static ETipoServicio fromString(String valor) {
        for (ETipoServicio ts : ETipoServicio.values()) {
            if (ts.value.equalsIgnoreCase(valor)) {
                return ts;
            }
        }
        return null;
    }

    public String getValue(){
        return value;
    }

    public int getSalario() {
        return salario;
    }
}
