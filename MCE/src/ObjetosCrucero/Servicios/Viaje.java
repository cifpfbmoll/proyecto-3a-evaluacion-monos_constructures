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
import java.util.ArrayList;
import java.util.List;

public class Viaje {
    //ATRIBUTOS
    private String codigoCrucero;
    private Date fechaEmbarque;
    private Date fechaLlegada;
    private String descripcion;

    //CONSTRUCTOR VACIO
    public Viaje() {
    }

    //CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Viaje(String codigoCrucero, Date fechaEmbarque, Date fechaLlegada, String descripcion) {
        this.setCodigoCrucero(codigoCrucero);
        this.setFechaEmbarque(fechaEmbarque);
        this.setFechaLlegada(fechaLlegada);
        this.setDescripcion(descripcion);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
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
    //METODOS
    //Añadir Viaje a la base de Datos
    public static void addViaje(Viaje trayecto) throws Exception {
        PreparedStatement insertarViaje = null;
        try {
            //Sentencia SQL para añadir la información
            DBUtils.getConnectionDB().setAutoCommit(false);
            String viajesSQL = ("INSERT INTO VIAJE VALUES (?, ?, ?, ?);");
            insertarViaje = DBUtils.getConnectionDB().prepareStatement(viajesSQL);
            insertarViaje.setString(2, trayecto.getCodigoCrucero());
            insertarViaje.setDate(3, trayecto.getFechaEmbarque());
            insertarViaje.setDate(4, trayecto.getFechaLlegada());
            insertarViaje.setString(5, trayecto.getDescripcion());
            insertarViaje.executeUpdate();
            DBUtils.getConnectionDB().commit();

        } catch (SQLException sqle){
            sqle.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
            DBUtils.closeDB();
        }

    }
    public static List<Viaje> getListaViaje() throws SQLException{

        List<Viaje> listaViaje = new ArrayList<Viaje>();

        //Sentencia SQL para obtener la información
        DBUtils.createConnectionDB();
        String viajesSQL= ("SELECT * FROM VIAJE GROUP BY CODIGO_CRUCERO;");
        PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(viajesSQL);
        ResultSet resultSet = sentencia.executeQuery();

        //Rellenamos la lista con los datos obtenidos
        while ( resultSet.next() ){

            Viaje viajeItr = new Viaje(
                    resultSet.getString("CODIGO_CRUCERO"),
                    resultSet.getDate("FECHA_EMBARQUE_VIAJE"),
                    resultSet.getDate("FECHA_LLEGADA_VIAJE"),
                    resultSet.getString("DESCRIPCION_VIAJE")
            );
            listaViaje.add(viajeItr);
        }
        DBUtils.getConnectionDB().close();
        return listaViaje;
    }
    // Metodo para crear el Archivo
    public void escrituraViajes() throws IOException {

        File archivoSalida = new File("./viaje/viaje_" + this.getCodigoCrucero() + this.getFechaEmbarque() + ".txt");
        //Definimos el contenido
        BufferedWriter bw;
        if (archivoSalida.exists()){
            bw = new BufferedWriter(new FileWriter(archivoSalida));
            bw.write("Se ha modificado el Viaje. \n");
        } else {
            bw = new BufferedWriter(new FileWriter(archivoSalida));
            bw.write("Se ha generado el viaje. \n");
        }
        bw.write(
                "LISTADO VIAJE DE MCE CRUCEROS ENTERPRISE \n" +
                        "CRUCERO: " + this.getCodigoCrucero() + "\n" +
                        "FECHA EMBARQUE \t \t FECHA LLEGADA \t \t FECHA \t \t" + "\n" +
                        this.getFechaEmbarque() + "\t" + "\t" + this.getFechaLlegada() + "\t" + "\t" + this.getDescripcion()
        );
        bw.close();
    }
}
