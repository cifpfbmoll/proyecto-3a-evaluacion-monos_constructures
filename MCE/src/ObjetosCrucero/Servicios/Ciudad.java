package ObjetosCrucero.Servicios;
public class Ciudad {
    private String CodigoCiudad;
    private String nombreCiudad;

    //COSNTRUCTOR VACIO
    public Ciudad() {
    }

    //COSNTRUCTOR CON TODOS LOS PARAMETROS
    public Ciudad(String codigoCiudad,String nombreCiudad) {
        this.CodigoCiudad = codigoCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    //GETTERS Y SETTERS
    public String getCodigoCiudad() {
        return CodigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        CodigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Ciudad{" +
                "CodigoCiudad='" + CodigoCiudad + '\'' +
                ", nombreCiudad='" + nombreCiudad + '\'' +
                '}';
    }
}
