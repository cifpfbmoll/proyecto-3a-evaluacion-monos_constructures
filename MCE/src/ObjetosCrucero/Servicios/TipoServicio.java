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

    public String getValue(){
        return value;
    }

    public int getSalario() {
        return salario;
    }
}
