package ObjetosCrucero.Servicios;

import ObjetosCrucero.Camarote;

import java.sql.Date;


public class Ventas {
    //Atributes

   private Billete billeteVenta;
   private Empleado empleadoVenta;
   private Camarote camaroteVenta;
   private Date fechaVenta;
   private String nombre;
   private String apellido;

    //Getters & Setters

    public Billete getBilleteVenta() {
        return billeteVenta;
    }

    public void setBilleteVenta(Billete billeteVenta) {
        this.billeteVenta = billeteVenta;
    }

    public Empleado getEmpleadoVenta() {
        return empleadoVenta;
    }

    public void setEmpleadoVenta(Empleado empleadoVenta) {
        this.empleadoVenta = empleadoVenta;
    }

    public Camarote getCamaroteVenta() {
        return camaroteVenta;
    }

    public void setCamaroteVenta(Camarote camaroteVenta) {
        this.camaroteVenta = camaroteVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    //Constructor

    public Ventas(Billete billeteVenta, Empleado empleadoVenta, Camarote camaroteVenta, Date fechaVenta, String nombre, String apellido) {
        this.setBilleteVenta(billeteVenta);
        this.setEmpleadoVenta(empleadoVenta);
        this.setCamaroteVenta(camaroteVenta);
        this.setFechaVenta(fechaVenta);
        this.setNombre(nombre);
        this.setApellido(apellido);
    }

    //Metodos

}

