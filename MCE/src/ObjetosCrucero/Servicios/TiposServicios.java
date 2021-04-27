package ObjetosCrucero.Servicios;


public enum TiposServicios{
    RRHH ("RRHH", 30000),
    ADMIN ("Admin", 30000),
    KITCHEN ("Kitchen", 27000),
    HOUSEKEEPING ("Hosusekeeping", 26500),
    VENTAS ("Ventas", 35000);

    private String value;
    private int salario;

    TiposServicios(String value, int salario){
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
