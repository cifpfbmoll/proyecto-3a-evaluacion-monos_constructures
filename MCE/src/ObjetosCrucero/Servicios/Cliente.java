package ObjetosCrucero.Servicios;
import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

    private Date fechaNacimientoCliente;
    private String codigoDescuento;
    private String telefonoCliente;

    private List<Billete> listaBilletes;


    //Getters y setters

    public Date getFechaNacimientoCliente() {
        return fechaNacimientoCliente;
    }
    public void setFechaNacimientoCliente(Date fechaNacimientoCliente) {
        this.fechaNacimientoCliente = fechaNacimientoCliente;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }
    public void setCodigoDescuento(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public List<Billete> getListaBilletes() {
        return listaBilletes;
    }
    public void setListaBilletes(List<Billete> listaBilletes) {
        this.listaBilletes = listaBilletes;
    }


    /**
     * Constructor con todos los parámetros
     */

    public Cliente(String dni, String nombre, String apellido, Date fechaNacimientoCliente, String codigoDescuento, String telefonoCliente) {
        super(dni, nombre, apellido);
        this.setFechaNacimientoCliente(fechaNacimientoCliente);
        this.setCodigoDescuento(codigoDescuento);
        this.setTelefonoCliente (telefonoCliente);
        this.setListaBilletes(obtenerBilletes());
    }


    // Editado por joseluissaiz el 12/05/2021

    /**
     * Obtenemos la lista de billetes que el cliente podria tener
     * @return una lista de billetes (objeto Billete)
     */
    private List<Billete> obtenerBilletes() {

        //La lista a retornar
        List<Billete> listaBilletes = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM BILLETE WHERE NIE LIKE ?";

        try (PreparedStatement busquedaBBDD = DBUtils.getConnectionDB().prepareStatement(sentenciaSQL)) {

            busquedaBBDD.setString(1, this.getDni());
            ResultSet resultados = busquedaBBDD.executeQuery();

            while (resultados.next()){
                listaBilletes.add(new Billete(
                        resultados.getString("CODIGO_BILLETE"),
                        resultados.getString("NIE"),
                        resultados.getDate("FECHA_EMBARQUE_BILLETE"),
                        resultados.getString("CODIGO_CAMAROTE"),
                        resultados.getString("CODIGO_CRUCERO")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        //Devolvemos la lista, vacia o con datos
        return listaBilletes;

    }

}