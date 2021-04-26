package ObjetosCrucero.Servicios;

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

    //public static* ArrayList<Crucero> getEstadoCrucero();

    //public static* ArrayList<Cliente> getListaPasajeros();

    //public static* ArrayList<Empleado> getListaTripulacion();

}
