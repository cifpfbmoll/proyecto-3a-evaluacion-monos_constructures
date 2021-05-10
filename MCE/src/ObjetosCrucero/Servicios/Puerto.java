package ObjetosCrucero.Servicios;
public class Puerto {
    //ATRIBUTOS
    private String codigoCrucero;
    private String nombreCrucero;
    private String codigoCiudad;

    //CONSTRUCTOR VACIO
    public Puerto() {
    }

    //CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Puerto(String codigoCrucero, String nombreCrucero, String codigoCiudad) {
        this.codigoCrucero = codigoCrucero;
        this.nombreCrucero = nombreCrucero;
        this.codigoCiudad = codigoCiudad;
    }
    //GETTERS Y SETTERS
    public String getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(String codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public String getNombreCrucero() {
        return nombreCrucero;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public void setNombreCrucero(String nombreCrucero) {
        this.nombreCrucero = nombreCrucero;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Puerto{" +
                "codigoCrucero='" + codigoCrucero + '\'' +
                ", nombreCrucero='" + nombreCrucero + '\'' +
                ", codigoCiudad='" + codigoCiudad + '\'' +
                '}';
    }
}
