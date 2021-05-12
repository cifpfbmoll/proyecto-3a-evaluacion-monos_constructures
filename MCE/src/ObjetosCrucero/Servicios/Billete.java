package ObjetosCrucero.Servicios;
import Utils.DBUtils;

import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class Billete {
    //Atributos

    private String nie;
    private Date fechaEmbarque;
    private String codigoCamarote;
    private String codigoCrucero;

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

    public String getCodigoCrucero() { return codigoCrucero; }

    public void setCodigoCrucero(String codigoCrucero) { this.codigoCrucero = codigoCrucero;}

    //Construsctor

    public Billete(String nie, Date fechaEmbarque, String codigoCamarote, String codigoCrucero) {
        this.setNie(nie);
        this.setFechaEmbarque(fechaEmbarque);
        this.setCodigoCamarote(codigoCamarote);
        this.setCodigoCrucero(codigoCrucero);
    }

    //Editado por joseluissaiz el 12/05/2021

    /**
     * Constructor esencial
     * @param nie_cliente
     * @param fecha_embarque_billete
     * @param codigo_camarote
     */
    public Billete(String nie_cliente, Date fecha_embarque_billete, String codigo_camarote) {
        this.setNie(nie_cliente);
        this.setFechaEmbarque(fecha_embarque_billete);
        this.setCodigoCamarote(codigo_camarote);
    }

    /*
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
    */

    //Editado por @joseluissaiz el 12/05/2021
    public void insertar(Billete billete) throws SQLException {

        String sqlNuevoBillete = "INSERT INTO Billete VALUES (?,?,?)";

        try (PreparedStatement pstInsertarBillete = DBUtils.getConnectionDB().prepareStatement(sqlNuevoBillete)) {

            pstInsertarBillete.setString(1, billete.getNie());
            pstInsertarBillete.setDate(2, Date.valueOf(LocalDate.now()));
            pstInsertarBillete.setString(3, billete.getCodigoCamarote());
            pstInsertarBillete.executeUpdate();

        } catch (SQLException sqle) {

            /*
              Posiblemente haya que cambiar la forma en la cual pasamos la fecha ya que se
              necesita un formato datetime, por eso solo voy a incluir esta excepcion de
              momento.
            */
            if (sqle.getErrorCode() == -180) {
                System.out.println("SQLERROR: La fecha no tiene el formato requerido por la base de datos");
            } else {
                System.out.println("SQLERROR: Ha habido un error inesperado con la base de datos");
                sqle.printStackTrace();
            }
        }
    }

}


