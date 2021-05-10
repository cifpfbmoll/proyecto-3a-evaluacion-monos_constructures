package ObjetosCrucero.Servicios;

import java.sql.Date;

public class Viaje {
    //ATRIBUTOS
    private String codigoCrucero;
    private Date fechaEmbarque;
    private Date fechaLlegada;
    private int descripcion; //intuyo que es valoracion

    //CONSTRUCTOR VACIO
    public Viaje() {
    }

    //CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Viaje(String codigoCrucero, Date fechaEmbarque, Date fechaLlegada, int descripcion) {
        this.codigoCrucero = codigoCrucero;
        this.fechaEmbarque = fechaEmbarque;
        this.fechaLlegada = fechaLlegada;
        this.descripcion = descripcion;
    }

    //GETTERS Y SETTERS
    public String getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(String codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public Date getFechaEmbarque() {
        return fechaEmbarque;
    }

    public void setFechaEmbarque(Date fechaEmbarque) {
        this.fechaEmbarque = fechaEmbarque;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Viaje{" +
                "codigoCrucero='" + codigoCrucero + '\'' +
                ", fechaEmbarque=" + fechaEmbarque +
                ", fechaLlegada=" + fechaLlegada +
                ", descripcion=" + descripcion +
                '}';
    }
}
