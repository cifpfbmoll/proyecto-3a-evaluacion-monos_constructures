package ObjetosCrucero.Servicios;
import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

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
    //Ejemplo estructura insert. Tener en cuenta el objeto billete y atributos
    public void insertar() throws SQLException {
        Date fecha = new Date(1921, 3, 1);
        fecha.getTime();
        DBUtils.createConnectionDB();
        String sqlNuevoBillete = "INSERT INTO Billete VALUES (?,?,?)";
        PreparedStatement pstInsertarBillete = DBUtils.getConnectionDB().prepareStatement(sqlNuevoBillete);
        pstInsertarBillete.setString(1, "43217180L");
        pstInsertarBillete.setDate(2, fecha);
        pstInsertarBillete.setString(3, "HDF23");
        pstInsertarBillete.executeUpdate();
        pstInsertarBillete.close();
    }
}


