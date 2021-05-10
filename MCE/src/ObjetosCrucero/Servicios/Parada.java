package ObjetosCrucero.Servicios;

import java.sql.Date;

public class Parada {
    //ATRIBUTOS
    private String codigoCrucero;
    private Date fechaEmbarque;
    private String codigoPuerto;
    private int numeroParada;
    private Date fechaLlegada;
    private Date fechaSalida;

    //CONSTRUCTOR VACIO
    public Parada() {
    }

    //COSNTRUCTOR CON TODOS LOS PARAMETROS
    public Parada(String codigoCrucero, String codigoPuerto, Date fechaEmbarque,
                  Date fechaLlegada, Date fechaSalida, int numeroParada) {
        this.codigoCrucero = codigoCrucero;
        this.fechaEmbarque = fechaEmbarque;
        this.numeroParada =numeroParada;
        this.codigoPuerto = codigoPuerto;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
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

    public String getCodigoPuerto() {
        return codigoPuerto;
    }

    public void setCodigoPuerto(String codigoPuerto) {
        this.codigoPuerto = codigoPuerto;
    }

    public int getNumeroParada() {
        return numeroParada;
    }

    public void setNumeroParada(int numeroParada) {
        this.numeroParada = numeroParada;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Parada{" +
                "codigoCrucero='" + codigoCrucero + '\'' +
                ", fechaEmbarque=" + fechaEmbarque +
                ", codigoPuerto='" + codigoPuerto + '\'' +
                ", numeroParada=" + numeroParada +
                ", fechaLlegada=" + fechaLlegada +
                ", fechaSalida=" + fechaSalida +
                '}';
    }
//METODOS