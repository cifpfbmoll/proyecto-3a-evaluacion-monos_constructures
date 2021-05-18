package ObjetosCrucero;

import ObjetosCrucero.Servicios.Camarote;
import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crucero {
    String codigoCrucero;
    String nombreCrucero;
    String modeloCrucero;
    int eslora;
    int manga;
    int calado;
    int nCamarotes;
    Camarote listaCamarotes[];

    public Crucero() {
    }

    public Crucero(String codigoCrucero, String nombreCrucero, String modeloCrucero, int eslora, int manga, int calado, int nCamarotes) {
        this.setCodigoCrucero(codigoCrucero);
        this.setNombreCrucero(nombreCrucero);
        this.setModeloCrucero(modeloCrucero);
        this.setEslora(eslora);
        this.setManga(manga);
        this.setCalado(calado);
        this.setnCamarotes(nCamarotes);
        this.listaCamarotes = new Camarote[nCamarotes];
    }

    public Crucero(Crucero cCopia) {
        this.setCodigoCrucero(cCopia.getCodigoCrucero());
        this.setNombreCrucero(cCopia.getNombreCrucero());
        this.setModeloCrucero(cCopia.getModeloCrucero());
        this.setEslora(cCopia.getEslora());
        this.setManga(cCopia.getManga());
        this.setCalado(cCopia.getCalado());
        this.setnCamarotes(cCopia.getnCamarotes());
        this.setListaCamarotes(cCopia.getListaCamarotes());
    }

    public String getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(String codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public String getNombreCrucero() {
        return nombreCrucero;
    }

    public void setNombreCrucero(String nombreCrucero) {
        this.nombreCrucero = nombreCrucero;
    }

    public String getModeloCrucero() {
        return modeloCrucero;
    }

    public void setModeloCrucero(String modeloCrucero) {
        this.modeloCrucero = modeloCrucero;
    }

    public int getEslora() {
        return eslora;
    }

    public void setEslora(int eslora) {
        this.eslora = eslora;
    }

    public int getManga() {
        return manga;
    }

    public void setManga(int manga) {
        this.manga = manga;
    }

    public int getCalado() {
        return calado;
    }

    public void setCalado(int calado) {
        this.calado = calado;
    }

    @Override
    public String toString() {
        return "Crucero{" +
                "codigoCrucero='" + codigoCrucero + '\'' +
                ", nombreCrucero='" + nombreCrucero + '\'' +
                ", modeloCrucero='" + modeloCrucero + '\'' +
                ", eslora=" + eslora +
                ", manga=" + manga +
                ", calado=" + calado +
                '}';
    }

    public int getnCamarotes() {
        return nCamarotes;
    }

    public void setnCamarotes(int nCamarotes) {
        this.nCamarotes = nCamarotes;
    }

    public Camarote[] getListaCamarotes() {
        return listaCamarotes;
    }

    public void setListaCamarotes(Camarote[] listaCamarotes) {
        this.listaCamarotes = listaCamarotes;
    }

    //inserta datos a un crucero
    public void crearCrucero() {}

    //crea una lista con todos los cruceros y su informacion
    public static ArrayList<Crucero> getListaCrucero() throws SQLException {
        PreparedStatement sentencia = null;
        ResultSet resultSet = null;
        try {
            ArrayList<Crucero> listaCrucero = new ArrayList<>();

            String cruceroSQL = ("SELECT * FROM CRUCERO;");
            sentencia = DBUtils.getConnectionDB().prepareStatement(cruceroSQL);
            resultSet = sentencia.executeQuery();

            while (resultSet.next()) {
                Crucero crucero = new Crucero(
                        resultSet.getString("CODIGO_CRUCERO"),
                        resultSet.getString("NOMBRE_CRUCERO"),
                        resultSet.getString("MODELO_CRUCERO"),
                        resultSet.getInt("ESLORA"),
                        resultSet.getInt("MANGA"),
                        resultSet.getInt("CALADO"),
                        resultSet.getInt(null)//corresponde a nCamarotes, actualizar BBDD
                );
                //crucero.setListaCamarotes(Camarotes.getListaCamarotes());
                listaCrucero.add(crucero);
            }
            return listaCrucero;
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } finally {
            sentencia.close();
            resultSet.close();
        }


    }

    public static Crucero getCrucero(String idCrucero) throws  SQLException {
        PreparedStatement sentencia = null;
        ResultSet resultSet = null;
        try {
            String cruceroSQL = ("SELECT * FROM CRUCERO WHERE CODIGO_CRUCERO = '" + idCrucero + "';");

            sentencia = DBUtils.getConnectionDB().prepareStatement(cruceroSQL);
            resultSet = sentencia.executeQuery();

            Crucero crucero = new Crucero(
                    resultSet.getString("CODIGO_CRUCERO"),
                    resultSet.getString("NOMBRE_CRUCERO"),
                    resultSet.getString("MODELO_CRUCERO"),
                    resultSet.getInt("ESLORA"),
                    resultSet.getInt("MANGA"),
                    resultSet.getInt("CALADO"),
                    resultSet.getInt(null)//corresponde a nCamarotes
            );
            //crucero.setListaCamarotes(Camarotes.getListaCamarotes());

            return crucero;
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } finally {
            sentencia.close();
            resultSet.close();
        }
    }

    //argumentos de entrada?
    public static void insertarCrucero() throws SQLException{
        PreparedStatement sentencia = null;
        try {
            //CRUCERO VACIO PARA PRUEBAS
            Crucero crucero = new Crucero();
            crucero.crearCrucero();

            DBUtils.getConnectionDB().setAutoCommit(false);
            //necesaria lista de camarotes?
            String empleadosSQL = ("INSERT INTO CRUCERO VALUES (?, ?, ?, ?, ?, ?);");
            sentencia = DBUtils.getConnectionDB().prepareStatement(empleadosSQL);
            sentencia.setString(1, crucero.getCodigoCrucero());
            sentencia.setString(2, crucero.getNombreCrucero());
            sentencia.setString(3, crucero.getModeloCrucero());
            sentencia.setInt(4, crucero.getEslora());
            sentencia.setInt(5, crucero.getManga());
            sentencia.setInt(6, crucero.getCalado());
            sentencia.executeUpdate();

            DBUtils.getConnectionDB().commit();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
            sentencia.close();
        }
    }
}
