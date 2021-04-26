package ObjetosCrucero.Servicios;
import java.util.Date;

public abstract class Cliente extends Usuario{

    private Date fechaNacimientoCliente;
    private String codigoDescuento;
    private String telefonoCliente;

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

    /**
     * Constructor con todos los parámetros
     */

    public Cliente(String dni, String nombre, String apellido, Date fechaNacimientoCliente, String codigoDescuento, String telefonoCliente) {
        super(dni, nombre, apellido);
        this.setFechaNacimientoCliente(fechaNacimientoCliente);
        this.setCodigoDescuento(codigoDescuento);
        this.setTelefonoCliente (telefonoCliente);
    }
}