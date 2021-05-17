package ObjetosCrucero;


import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Camarote{

    //Atributos

    private String codigoCamarote;
    private Tarifa tarifa;
    private int numeroCamas;

    //Constructor

    public Camarote() {
    }

    public Camarote(String codigoCamarote, int numeroCamas){
        this.setCodigoCamarote(codigoCamarote);
        this.setNumeroCamas(numeroCamas);
        this.setTarifa(obtenerTarifa());
    }

    public Camarote(String codigoCamarote, Tarifa tarifa, int numeroCamas) {
        setCodigoCamarote(codigoCamarote);
        setTarifa(tarifa);
        setNumeroCamas(numeroCamas);
    }

    public Camarote(Camarote original) {
        setCodigoCamarote(original.getCodigoCamarote());
        setTarifa(original.getTarifa());
        setNumeroCamas(original.getNumeroCamas());
    }

    //Getters y setters


    public String getCodigoCamarote() {
        return codigoCamarote;
    }

    public void setCodigoCamarote(String codigoCamarote) {
        this.codigoCamarote = codigoCamarote;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    //toString


    @Override
    public String toString() {
        return "Camarote{" +
                "codigoCamarote='" + codigoCamarote + '\'' +
                ", tarifa=" + tarifa +
                ", numeroCamas=" + numeroCamas +
                '}';
    }



    //Métodos

    /**
     * Obtenemos la tarifa del camarote a traves de su codigo.
     * @return devuelve la tarifa de dicho camarote
     * [IMPORTANTE - Debemos incluir dentro de la BBDD el COSTE de cada tarifa]
     */
    private Tarifa obtenerTarifa(){
        //La tarifa a devolver
        Tarifa tarifa = new Tarifa();
        String sentenciaSQL = (
                "SELECT * " +
                        "FROM TARIFA" +
                        "WHERE CODIGO_TARIFA = (SELECT CODIGO_TARIFA" +
                        "                       FROM CAMAROTE" +
                        "                       WHERE CODIGO_CAMAROTE = ?)"
        );
        try (PreparedStatement busquedaDDBB = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {
            busquedaDDBB.setString(1, this.getCodigoCamarote());
            ResultSet resultados = busquedaDDBB.executeQuery();

            if (resultados.next()) {
                tarifa.setNombreTarifa(resultados.getString("NOMBRE_TARIFA"));
                tarifa.setCodigoTarifa(resultados.getString("CODIGO_TARIFA"));
                tarifa.setCoste(resultados.getFloat("COSTE"));
            }

            resultados.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        // Devolvemos la tarifa obtenida, vacia en caso de no poder encontrarla.
        return tarifa;
    }

}
