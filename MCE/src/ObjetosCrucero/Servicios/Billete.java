package ObjetosCrucero.Servicios;
import Utils.DBUtils;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Billete {
    //Atributos

    private String codigoBillete;
    private Date fechaEmbarque;
    private String codigoCamarote;
    private String codigoCrucero;

    //Getter&Setter

    public String getCodigoBillete() {
        return codigoBillete;
    }

    public void setCodigoBillete(String codigoBillete) {
        this.codigoBillete = codigoBillete;
    }

    public Date getFechaEmbarque() {
        return fechaEmbarque;
    }

    public void setFechaEmbarque(Date fechaEmbarque) {
        java.sql.Date fecha = new java.sql.Date(0); //Milisegundo cero
        this.fechaEmbarque = fecha;
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

    public Billete(String codigoBillete, Date fechaEmbarque, String codigoCamarote, String codigoCrucero) {
        this.setCodigoBillete(codigoBillete);
        this.setFechaEmbarque(fechaEmbarque);
        this.setCodigoCamarote(codigoCamarote);
        this.setCodigoCrucero(codigoCrucero);
    }
    //Ejemplo estructura insert. Tener en cuenta el objeto billete y atributos
     public void addBillete() throws Exception {
         PreparedStatement insertarBillete = null;
         try{
            //Sentencia SQL para añadir la información.
            DBUtils.getConnectionDB().setAutoCommit(false);

            String billetesSQL = ("INSERT INTO BILLETE VALUES (?,?,?);");
            insertarBillete = DBUtils.getConnectionDB().prepareStatement(billetesSQL);
            insertarBillete.setString(1, getCodigoBillete());
            insertarBillete.setDate(2, getFechaEmbarque());
            insertarBillete.setString(3, getCodigoCamarote());
            insertarBillete.setString(4, getCodigoCrucero());
            insertarBillete.executeUpdate();
            DBUtils.getConnectionDB().commit();
        } catch (SQLException sqle){
            sqle.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
            insertarBillete.close();
        }
    }

    public static List<Billete> getListaBilletes() throws SQLException{

        List<Billete> listaBilletes = new ArrayList<Billete>();

        //Sentencia SQL para obtener la información
        DBUtils.createConnectionDB();
        String billetesSQL= ("SELECT * FROM BILLETE GROUP BY CODIGO_CRUCERO;");
        PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(billetesSQL);
        ResultSet resultSet = sentencia.executeQuery();

        //Rellenamos la lista con los datos obtenidos
        while ( resultSet.next() ){

            Billete billeteItr = new Billete(
                    resultSet.getString("CODIGO_BILLETE"),
                    Date.valueOf(resultSet.getString("FECHA_EMBARQUE_VIAJE")),
                    resultSet.getString("CODIGO_CAMAROTE"),
                    resultSet.getString("CODIGO_CRUCERO")
            );
            listaBilletes.add(billeteItr);
        }
        DBUtils.getConnectionDB().close();
        return listaBilletes;
    }
    // Metodo para crear el Archivo
    public void escrituraBilletes() throws IOException{

        File archivoSalida = new File("./archivos/listadoBilletes.txt");
        //Definimos el contenido
        String linea1 = "LISTADO BILLETES DE MCE CRUCEROS ENTERPRISE \n";
        String linea2 = "CRUCERO: \n" + this.getCodigoCrucero();
        BufferedWriter bw;
        if (archivoSalida.exists()){
            bw = new BufferedWriter(new FileWriter(archivoSalida));
            bw.write("El fichero de texto ya esta creado. \n");
        } else {
            bw = new BufferedWriter(new FileWriter(archivoSalida));
            bw.write("Se ha creado el fichero. \n");
        }
        bw.write(linea1);
        bw.write(linea2);
        bw.close();
    }
}


