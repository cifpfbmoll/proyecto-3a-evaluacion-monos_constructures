package ObjetosCrucero.Servicios;
import java.sql.PreparedStatement;
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
    //Métodos
    /*PreparedStatement pstInsertarBillete;
    String sqlNuevoBillete = "INSERT INTO Billete VALUES (?,?,?)";
    pstInsertarBillete = cn.preparedStatement(sqlNuevoBillete);
    pstInsertarBillete.setString(1, "43217180L");
    pstInsertarBillete.setDate(2, "14/07/1996");
    pstInsertarBillete.setString(3, "HDF23");
    pstInsertarBillete.executeUpdate();
*/
}


