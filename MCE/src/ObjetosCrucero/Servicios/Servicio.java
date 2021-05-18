package ObjetosCrucero.Servicios;

import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Servicio {


    //Atributos


    private String codigo;
    private String nombre;
    private float salario;

    public static ArrayList<Servicio> listaServicios;


    //Constructores


    //Vacio
    public Servicio() {
    }

    //Con parametros
    public Servicio(String codigo, String nombre, float salario) {
        setCodigo(codigo);
        setNombre(nombre);
        setSalario(salario);
    }

    //Copia
    public Servicio(Servicio original) {
        setCodigo(original.getCodigo());
        setNombre(original.getNombre());
        setSalario(original.getSalario());
    }


    //Getters y Setters


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }


    //ToString


    @Override
    public String toString() {
        return this.getNombre();
    }


    //Metodos


    /**
     * Añade el servicio a la base de datos
     *
     * @param servicio
     * @throws Exception
     */
    public static void addServicio(Servicio servicio) throws Exception {

        String crearServicio = ("INSERT INTO SERVICIO VALUES (?, ?, ?);");

        try (PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(crearServicio);) {
            //Sentencia SQL para añadir la información
            DBUtils.getConnectionDB().setAutoCommit(false);
            sentencia.setString(1, servicio.getCodigo());
            sentencia.setString(2, servicio.getNombre());
            sentencia.setFloat(3, servicio.getSalario());
            sentencia.executeUpdate();

            DBUtils.getConnectionDB().commit();

        } catch (SQLException sqle){
            sqle.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
        }

    }

    public static void pullListaServicios() throws SQLException {

        ArrayList<Servicio> newListaServicios = new ArrayList<Servicio>();
        String select = ("SELECT * FROM SERVICIO;");
        Statement st = DBUtils.getConnectionDB().createStatement();
        ResultSet resultSet = st.executeQuery(select);
        while (resultSet.next()) {
            Servicio serv = new Servicio(
                    resultSet.getString("CODIGO_SERVICIO"),
                    resultSet.getString("NOMBRE_SERVICIO"),
                    resultSet.getFloat("SALARIO_SERVICIO")
            );
            newListaServicios.add(serv);
        }
        resultSet.close();
        st.close();
        Servicio.listaServicios = newListaServicios;
    }

    public static Servicio buscarCodigo(String codigo) {
        for (Servicio listaServicio : listaServicios) {
            if (listaServicio.getCodigo().equals(codigo)) {
                return listaServicio;
            }
        }
        return listaServicios.get(0);
    }

}