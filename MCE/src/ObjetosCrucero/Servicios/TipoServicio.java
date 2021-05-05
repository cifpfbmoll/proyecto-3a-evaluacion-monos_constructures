package ObjetosCrucero.Servicios;


public enum TipoServicio {
    RRHH ("RRHH", 30000),
    ADMIN ("Admin", 30000),
    KITCHEN ("Kitchen", 27000),
    HOUSEKEEPING ("Housekeeping", 26500),
    VENTAS ("Ventas", 35000);

    private String value;
    private int salario;

    TipoServicio(String value, int salario){
        this.value = value;
        this.salario = salario;
    }

    /**
     * Obtener un tipo de servicio a partir de una String
     * @param valor la string con el valor
     * @return devuelve el tipo de servicio en caso de encontrarse, o nulo en caso contrario.
     */
    public static TipoServicio fromString(String valor) {
        for (TipoServicio ts : TipoServicio.values()) {
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
