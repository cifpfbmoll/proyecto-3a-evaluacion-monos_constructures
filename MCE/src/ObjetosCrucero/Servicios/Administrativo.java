package ObjetosCrucero.Servicios;

import Utils.DBUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Administrativo extends Empleado{

    /**
     * Constructor con parametros de superclase
     */
    public Administrativo(String codigoEmpleado, String nieEmpleado, String nombreEmpleado, String apellidoEmpleado, TipoServicio servicio) {
        super(codigoEmpleado, nieEmpleado, nombreEmpleado, apellidoEmpleado, servicio);
    }

    /*Metodos de clase
    La clase Administrativo tiene acceso al estado del crucero, a la lista de pasajeros y a la lista de la tipulación
    */

    public static ArrayList<ObjetosCrucero.Crucero> getEstadoCrucero() throws SQLException {
        ArrayList<ObjetosCrucero.Crucero> listaCruceros = new ArrayList();

        DBUtils.createConnectionDB();
        String crucerosSQL = "SELECT * FROM CRUCERO;";
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

    public static ArrayList<Cliente> getListaPasajeros() {
        return null;
    }

    public static ArrayList<Empleado> getListaTripulacion() throws SQLException {
        ArrayList<Empleado> listaTripulacion = new ArrayList();

        DBUtils.createConnectionDB();
        String tripulacionSQL = "SELECT * FROM empleado, viaje, asignacion " +
                "WHERE empleado.CODIGO_EMPLEADO = asignacion.CODIGO_EMPLEADO " +
                "and asignacion.CODIGO_EMPLEADO LIKE ?;";
        PreparedStatement sentencia = DBUtils.getConnectionDB().prepareStatement(tripulacionSQL);
        ResultSet resultSet = sentencia.executeQuery();

        Empleado tripulacion;

        while (resultSet.next()) {
            tripulacion = new Empleado(
                    resultSet.getString("CODIGO_EMPLEADO"),
                    resultSet.getString("NIE_EMPLEADO"),
                    resultSet.getString("NOMBRE_EMPLEADO"),
                    resultSet.getString("APELLIDO_EMPLEADO"),
                    TipoServicio.valueOf(resultSet.getString("CODIGO_SERVICIO")),
                    resultSet.getString("DOMICILIACION_EMPLEADO"),
                    Date.valueOf(resultSet.getString("FECHA_NACIMIENTO_EMPLEADO"))
            );
            listaTripulacion.add(tripulacion);
        }
        DBUtils.getConnectionDB().close();
        return listaTripulacion;
    }

}
