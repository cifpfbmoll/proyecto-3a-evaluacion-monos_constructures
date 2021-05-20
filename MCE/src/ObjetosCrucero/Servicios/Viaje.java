package ObjetosCrucero.Servicios;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Viaje {
    //ATRIBUTOS
    private String codigoCrucero;
    private LocalDate fechaEmbarque;
    private LocalDate fechaLlegada;
    private String descripcion; //intuyo que es valoracion
    private List<Parada> listaParadas;

    //CONSTRUCTOR VACIO
    public Viaje() {
    }

    //CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Viaje(String codigoCrucero, LocalDate fechaEmbarque, LocalDate fechaLlegada, String descripcion, List<Parada> listaParadas) {
        this.codigoCrucero = codigoCrucero;
        this.fechaEmbarque = fechaEmbarque;
        this.fechaLlegada = fechaLlegada;
        this.descripcion = descripcion;
        this.listaParadas = listaParadas;
    }

    //GETTERS Y SETTERS
    public String getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(String codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public LocalDate getFechaEmbarque() {
        return fechaEmbarque;
    }

    public void setFechaEmbarque(LocalDate fechaEmbarque) {
        this.fechaEmbarque = fechaEmbarque;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Parada> getListaParadas() {
        return listaParadas;
    }

    public void setListaParadas(List<Parada> listaParadas) {
        this.listaParadas = listaParadas;
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
