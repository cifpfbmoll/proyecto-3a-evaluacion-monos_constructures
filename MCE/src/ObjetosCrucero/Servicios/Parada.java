package ObjetosCrucero.Servicios;

import Utils.DBUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

//PARADA HEREDA DE PUERTO
public class Parada extends Puerto{
    //ATRIBUTOS
    private Date fechaEmbarque;
    private int numeroParada;
    private Date fechaLlegada;
    private Date fechaSalida;


    //CONSTRUCTOR VACIO
    public Parada() {
    }

    //CONSTRUCTOR COPIA


    //COSNTRUCTOR CON TODOS LOS PARAMETROS
    public Parada(String codigoCrucero, String nombrePuerto, String codigoCiudad, String codigoPuerto, Date fechaEmbarque,
                  Date fechaLlegada, Date fechaSalida, int numeroParada) {
        super(codigoPuerto, nombrePuerto, codigoCiudad);
        this.setFechaEmbarque(fechaEmbarque);
        this.setNumeroParada(numeroParada);
        this.setFechaLlegada(fechaLlegada);
        this.setFechaSalida(fechaSalida);
    }

    //GETTERS Y SETTERS
    public Date getFechaEmbarque() {

        return fechaEmbarque;
    }

    public void setFechaEmbarque(Date fechaEmbarque) {

        this.fechaEmbarque = fechaEmbarque;
    }

    public int getNumeroParada()
    {
        return numeroParada;
    }

    public void setNumeroParada(int numeroParada)
    {
        this.numeroParada = numeroParada;
    }

    public Date getFechaLlegada()
    {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada)
    {
        this.fechaLlegada = fechaLlegada;
    }

    public Date getFechaSalida()
    {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida)
    {
        this.fechaSalida = fechaSalida;
    }

    //TOSTRING
    @Override
    public String toString() {
        return super.toString() +
                "Parada{" +
                ", fechaEmbarque=" + fechaEmbarque +
                ", numeroParada=" + numeroParada +
                ", fechaLlegada=" + fechaLlegada +
                ", fechaSalida=" + fechaSalida +
                '}';
    }

    //METODOS
    //AÑADIR PARADA
    public void addParada() throws SQLException {
        PreparedStatement insertarParada = null;
        try{
            //Sentencia SQL para añadir la información.
            DBUtils.getConnectionDB().setAutoCommit(false);

            String puertosSQL = ("INSERT INTO PARADA VALUES (?,?,?);");
            insertarParada.setString(1, getCodigoPuerto());
            insertarParada.setString(2, getNombrePuerto());
            insertarParada.setString(3, getCodigoCiudad());
            insertarParada.executeUpdate();
            DBUtils.getConnectionDB().commit();
        } catch (SQLException sqle){
            sqle.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
            insertarParada.close();
        }
    }
    //
    //SOLICITAR DATOS PARADA
    public void SolicitarDatosParada (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la fecha de embarque");
        this.setFechaEmbarque(fechaEmbarque);
        System.out.println("Introduce el numero de la parada");
        this.setNumeroParada(numeroParada);
        System.out.println("Introduce la fehca de llegada");
        this.setFechaLlegada(fechaLlegada);
        System.out.println("Introduce la fecha de salida");
        this.setFechaSalida(fechaSalida);
    }

    //ELIMINAR PARADA
    public static void eliminarParada (ArrayList <Parada> listaParadas){
        Scanner sc = new Scanner (System.in);
        System.out.println("introduce el numero de la parada");
        int numeroParada = sc.nextInt();

        for (int i =0; i<listaParadas.size(); i++){
            //eliminamos los datos de parada si esta en la lista
            if (listaParadas.get(i).getNumeroParada() == numeroParada){
                listaParadas.remove(i);
                System.out.println("La parada ha sido eliminada");
            }else {
                System.out.println("Numero de parada incorrecto");
            }
        }
    }

}






