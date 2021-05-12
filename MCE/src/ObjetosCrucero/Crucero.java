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

    //crea una lista con todos los cruceros y su informacion
    public static ArrayList<Crucero> getListaCrucero() throws SQLException {
        ArrayList<Crucero> listaCrucero = new ArrayList<>();

        String cruceroSQL = ("SELECT * FROM CRUCERO");
        PreparedStatement sentencia = DBUtils.getConnectionDB().prepareStatement(cruceroSQL);
        ResultSet resultset = sentencia.executeQuery();

        while (resultset.next()) {
            Crucero crucero = new Crucero(
                    resultset.getString("CODIGO_CRUCERO"),
                    resultset.getString("NOMBRE_CRUCERO"),
                    resultset.getString("MODELO_CRUCERO"),
                    resultset.getInt("ESLORA"),
                    resultset.getInt("MANGA"),
                    resultset.getInt("CALADO"),
                    resultset.getInt(null)//corresponde a nCamarotes, actualizar BBDD
            );
            //crucero.setListaCamarotes(Camarotes.getListaCamarotes());
            listaCrucero.add(crucero);
        }
        resultset.close();

        return listaCrucero;
    }

    //argumentos de entrada?
    public static void insertarCrucero() {
        try {
            //CRUCERO VACIO PARA PRUEBAS
            Crucero crucero = new Crucero();

            DBUtils.getConnectionDB().setAutoCommit(false);
            //necesaria lista de camarotes?
            String empleadosSQL = ("INSERT INTO CRUCERO VALUES (?, ?, ?, ?, ?, ?)");
            PreparedStatement sentencia = DBUtils.getConnectionDB().prepareStatement(empleadosSQL);
            sentencia.setString(1, crucero.getCodigoCrucero());
            sentencia.setString(2, crucero.getNombreCrucero());
            sentencia.setString(3, crucero.getModeloCrucero());
            sentencia.setString(4, crucero.getEslora());
            sentencia.setString(5, crucero.getManga());
            sentencia.setString(6, crucero.getCalado());
            sentencia.executeUpdate();

            DBUtils.getConnectionDB().commit();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
        }
    }
}
