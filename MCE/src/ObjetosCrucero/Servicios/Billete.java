package ObjetosCrucero.Servicios;
import java.util.Date;

public class Billete {
    //Atributos
    
    private String nie;
    private Date fechaEmbarque;
    private String codigoCamarote;

    //Getter&Setter

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public Date getFechaEmbarque() {
        return fechaEmbarque;
    }

    public void setFechaEmbarque(Date fechaEmbarque) {
        this.fechaEmbarque = fechaEmbarque;
    }

    public String getCodigoCamarote() {
        return codigoCamarote;
    }

    public void setCodigoCamarote(String codigoCamarote) {
        this.codigoCamarote = codigoCamarote;
    }

    //Construsctor

    public Billete(String nie, Date fechaEmbarque, String codigoCamarote) {
        this.setNie(nie);
        this.setFechaEmbarque(fechaEmbarque);
        this.setCodigoCamarote(codigoCamarote);
    }
    /*//Métodos
    String sqlNuevoBillete = "INSERT INTO Billete VALUES (?,?,?)";
    */

}


