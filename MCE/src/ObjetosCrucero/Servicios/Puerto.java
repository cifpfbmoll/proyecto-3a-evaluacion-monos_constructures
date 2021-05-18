package ObjetosCrucero.Servicios;
import Utils.DBUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Puerto {
    //ATRIBUTOS
    private String codigoPuerto;
    private String nombrePuerto;
    private String codigoCiudad;

    //CONSTRUCTOR VACIO
    public Puerto() {
    }

    //CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Puerto(String codigoPuerto, String nombrePuerto, String codigoCiudad) {
        this.setCodigoPuerto(codigoPuerto);
        this.setNombrePuerto(nombrePuerto);
        this.setCodigoCiudad(codigoCiudad);
    }

    //GETTERS Y SETTERS


    public String getCodigoPuerto() {
        return codigoPuerto;
    }

    public void setCodigoPuerto(String codigoPuerto) {
        this.codigoPuerto = codigoPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Puerto{" +
                "codigoPuerto='" + codigoPuerto + '\'' +
                ", nombrePuerto='" + nombrePuerto + '\'' +
                ", codigoCiudad='" + codigoCiudad + '\'' +
                '}';
    }

    //METODO
    public void addPuerto() throws Exception {
        PreparedStatement insertarPuerto = null;
        try{
            //Sentencia SQL para añadir la información.
            DBUtils.getConnectionDB().setAutoCommit(false);

            String puertosSQL = ("INSERT INTO PUERTO VALUES (?,?,?);");
            insertarPuerto = DBUtils.getConnectionDB().prepareStatement(puertosSQL);
            insertarPuerto.setString(1, getCodigoPuerto());
            insertarPuerto.setString(2, getNombrePuerto());
            insertarPuerto.setString(3, getCodigoCiudad());
            insertarPuerto.executeUpdate();
            DBUtils.getConnectionDB().commit();
        } catch (SQLException sqle){
            sqle.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
            insertarPuerto.close();
        }
    }

    public static List<Puerto> getListaPuertos() throws SQLException{

        List<Puerto> listaPuertos = new ArrayList<Puerto>();

        //Sentencia SQL para obtener la información
        DBUtils.createConnectionDB();
        String puertosSQL= ("SELECT * FROM PUERTO GROUP BY CODIGO_CIUDAD;");
        PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(puertosSQL);
        ResultSet resultSet = sentencia.executeQuery();

        //Rellenamos la lista con los datos obtenidos
        while ( resultSet.next() ){

            Puerto puertoItr = new Puerto(
                    resultSet.getString("CODIGO_PUERTO"),
                    resultSet.getString("NOMBRE_PUERTO"),
                    resultSet.getString("CODIGO_CIUDAD"));
            listaPuertos.add(puertoItr);
        }
        DBUtils.getConnectionDB().close();
        return listaPuertos;
    }
    // Metodo para crear el Archivo
    public void escrituraBilletes() throws IOException {

        File archivoSalida = new File("./archivos/puertos/puerto_" + this.getCodigoPuerto() + ".txt");
        //Definimos el contenido
        BufferedWriter bw;
        if (archivoSalida.exists()){
            bw = new BufferedWriter(new FileWriter(archivoSalida));
            bw.write("Se ha modificado el puerto. \n");
        } else {
            bw = new BufferedWriter(new FileWriter(archivoSalida));
            bw.write("Se ha generado el archivo del puerto. \n");
        }
        bw.write(
                "LISTADO PUERTOS DE MCE CRUCEROS ENTERPRISE \n" +
                        "CRUCERO: " + this.getCodigoPuerto() + "\n" +
                        "CODIGO_PUERTO \t \t PUERTO \t \t CIUDAD \t \t" + "\n" +
                        this.getCodigoPuerto() + "\t" + "\t" + this.getNombrePuerto() + "\t" + "\t" + this.getCodigoCiudad()
        );
        bw.close();
    }
}
