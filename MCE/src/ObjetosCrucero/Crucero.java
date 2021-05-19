package ObjetosCrucero;

import ObjetosCrucero.Servicios.Camarote;
import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crucero {

    //Distintivos del crucero
    private String codigoCrucero;
    private String nombreCrucero;
    private String modeloCrucero;

    //Medidas
    private int eslora;
    private int manga;
    private int calado;

    //Lista de camarotes
    private List<Camarote> listaCamarotes;


    public Crucero() {
    }

    public Crucero(String codigoCrucero, String nombreCrucero, String modeloCrucero, int eslora, int manga, int calado, int nCamarotes) {
        this.setCodigoCrucero(codigoCrucero);
        this.setNombreCrucero(nombreCrucero);
        this.setModeloCrucero(modeloCrucero);
        this.setEslora(eslora);
        this.setManga(manga);
        this.setCalado(calado);
        this.setListaCamarotes(obtenerCamarotes());
    }

    public Crucero(Crucero cCopia) {
        this.setCodigoCrucero(cCopia.getCodigoCrucero());
        this.setNombreCrucero(cCopia.getNombreCrucero());
        this.setModeloCrucero(cCopia.getModeloCrucero());
        this.setEslora(cCopia.getEslora());
        this.setManga(cCopia.getManga());
        this.setCalado(cCopia.getCalado());
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

    public List<Camarote> getListaCamarotes() {
        return listaCamarotes;
    }

    public void setListaCamarotes(List<Camarote> listaCamarotes) {
        this.listaCamarotes = listaCamarotes;
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


    // Editado por joseluissaiz el 12/05/2021

    /**
     * Obtenemos la lista de camarotes del crucero y lo rellenamos dentro del constructor
     * @return devuelve la lista (con datos o vacia) de camarotes del crucero en cuestion.
     */
    public List<Camarote> obtenerCamarotes(){

        //La lista a retornar
        List<Camarote> listaCamarotes = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM CAMAROTE WHERE CODIGO_CRUCERO = ?";

        try (PreparedStatement busquedaDDBB = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {
            busquedaDDBB.setString(1, this.getCodigoCrucero());
            ResultSet resultado = busquedaDDBB.executeQuery();

            while (resultado.next()) {
                listaCamarotes.add(new Camarote(
                        resultado.getString("CODIGO_CAMAROTE"),
                        resultado.getInt("NUMERO_CAMAS")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        //Devolvemos la lista vacia, o con datos
        return listaCamarotes;
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
            return null;
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
            return null;
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
