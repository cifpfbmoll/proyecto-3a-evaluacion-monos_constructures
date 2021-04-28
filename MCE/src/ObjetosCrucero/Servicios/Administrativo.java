package ObjetosCrucero.Servicios;

import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Administrativo extends Empleado{

    /**
     * Constructor con parametros de superclase
     */
    public Administrativo(String codigoEmpleado, String nieEmpleado, String nombreEmpleado, String apellidoEmpleado, String nombreServicio) {
        super(codigoEmpleado, nieEmpleado, nombreEmpleado, apellidoEmpleado, nombreServicio);
    }

    /*Metodos de clase
    La clase Administrativo tiene acceso al estado del crucero, a la lista de pasajeros y a la lista de la tipulación
    */

    public static ArrayList<ObjetosCrucero.Crucero> getEstadoCrucero() throws SQLException {
        ArrayList<ObjetosCrucero.Crucero> listaCruceros = new ArrayList();

        String crucerosSQL = "SELECT * FROM CRUCERO";
        PreparedStatement sentencia = DBUtils.getConnectionDB().prepareStatement(crucerosSQL);
        ResultSet resultSet = sentencia.executeQuery();

        ObjetosCrucero.Crucero crucero;

        while (resultSet.next()) {
            crucero = new ObjetosCrucero.Crucero();
            crucero.setCodigoCrucero(resultSet.getString("CODIGO_CRUCERO"));
            crucero.setNombreCrucero(resultSet.getString("NOMBRE_CRUCERO"));
            crucero.setModeloCrucero(resultSet.getString("MODELO_CRUCERO"));
            crucero.setEslora(resultSet.getInt("ESLORA"));
            crucero.setManga(resultSet.getInt("MANGA"));
            crucero.setCalado(resultSet.getInt("CALADO"));

            listaCruceros.add(crucero);
        }
        DBUtils.getConnectionDB().close();
        return listaCruceros;
    }

    //public static ArrayList<Cliente> getListaPasajeros();

    public static ArrayList<Empleado> getListaTripulacion() {
        return null;
    }

}
