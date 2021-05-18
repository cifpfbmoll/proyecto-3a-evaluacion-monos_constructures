package ObjetosCrucero.Servicios;

import Utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Nomina {

    //Atributos

    private LocalDate fechaNomina;
    private int horasOrdinarias;
    private int horasExtra;
    private float salario;
    private float plus;
    private Empleado titular;

    public static ArrayList<Nomina> listaNominas;

    //Constructor

    public Nomina() {
    }

    public Nomina(LocalDate fechaNomina, int horasOrdinarias, int horasExtra, float plus, Empleado titular) {
        setFechaNomina(fechaNomina);
        setHorasOrdinarias(horasOrdinarias);
        setHorasExtra(horasExtra);
        setPlus(plus);
        setSalario(this.calcularSalario());
        setTitular(titular);
    }

    public Nomina(Nomina original) {
        setFechaNomina(original.getFechaNomina());
        setHorasOrdinarias(original.getHorasExtra());
        setHorasExtra(original.getHorasOrdinarias());
        setPlus(original.getPlus());
        setSalario(original.getSalario());
        setTitular(original.getTitular());
    }

    //Getters y Setters

    public LocalDate getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(LocalDate fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    public int getHorasOrdinarias() {
        return horasOrdinarias;
    }

    public void setHorasOrdinarias(int horasOrdinarias) {
        this.horasOrdinarias = horasOrdinarias;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    public float getPlus() {
        return plus;
    }

    public void setPlus(float plus) {
        this.plus = plus;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Empleado getTitular() {
        return titular;
    }

    public void setTitular(Empleado titular) {
        this.titular = titular;
    }

    //toString


    @Override
    public String toString() {
        return "Nomina{" +
                "fechaNomina=" + fechaNomina +
                ", horasOrdinarias=" + horasOrdinarias +
                ", horasExtra=" + horasExtra +
                ", plus=" + plus +
                ", titular=" + titular +
                '}';
    }

    //Metodos

    public float calcularSalario() {

        LocalDate fechaNomina = this.getFechaNomina();
        int horasExtra = this.getHorasExtra();
        int horasOrdinarias = this.getHorasOrdinarias();
        float salario = this.salario;
        float plus = this.getPlus();

        //percepciones

        float salarioHora = salario/30;
        float salarioHorasExtras = (float) (salarioHora*1.75*horasExtra);
        float salarioDiario = salarioHora*horasOrdinarias;
        float bccc = (salarioDiario*16/14) + plus;
        float bccp = bccc + salarioHorasExtras;
        float salarioBruto = salario + salarioHorasExtras + plus;

        //deducciones

        float dcc = (float) (bccc*4.7);
        float dParo = (float) (bccp*1.55);
        float dfp = (float) (bccp*0.10);
        float dirpf = (float) (salarioBruto*14.75);

        float salarioNeto = salarioBruto - (dcc + dParo + dfp + dirpf);

        return salarioNeto;
    }

    public static void addNomina(Nomina nomina) throws Exception {

        String empleadosSQL = ("INSERT INTO EMPLEADO VALUES (?, ?, ?, ?, ?, ?, ?, ?);");


        try (PreparedStatement sentencia= DBUtils.getConnectionDB().prepareStatement(empleadosSQL)) {

            //Sentencia SQL para añadir la información
            DBUtils.getConnectionDB().setAutoCommit(false);
            sentencia.setString(1, nomina.getTitular().getNombre());
            sentencia.setString(2, nomina.getFechaNomina().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            sentencia.setInt(3, nomina.getHorasOrdinarias());
            sentencia.setInt(4, nomina.getHorasExtra());
            sentencia.setFloat(5, nomina.getPlus());
            sentencia.executeUpdate();

            DBUtils.getConnectionDB().commit();

        } catch (SQLException sqle){
            sqle.printStackTrace();
            DBUtils.getConnectionDB().rollback();
        } finally {
            DBUtils.getConnectionDB().setAutoCommit(true);
        }

    }

    public static void pullNominas() throws SQLException {

        ArrayList<Nomina> newListaNominas = new ArrayList<Nomina>();
        String select = ("SELECT * FROM NOMINA;");
        Statement st = DBUtils.getConnectionDB().createStatement();
        ResultSet resultSet = st.executeQuery(select);
        while (resultSet.next()) {
            Nomina nomina = new Nomina(
                    LocalDate.parse(resultSet.getDate("FECHA_NOMINA").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    resultSet.getInt("HORAS_ORDINARIAS"),
                    resultSet.getInt("HORAS_EXTRAORDINARIAS"),
                    resultSet.getFloat("PLUS_NOMINA"),
                    Empleado.getEmbleadoByCodigo(resultSet.getInt("CODIGO_EMPLEADO"))
            );
            //LocalDate fechaNomina, int horasOrdinarias, int horasExtra, float plus, float salario, Empleado titular
            newListaNominas.add(nomina);
        }
        resultSet.close();
        st.close();
        Nomina.listaNominas = newListaNominas;
    }

    public static void pullNominas(Empleado empleado) throws SQLException {

        String codigoEmpleado = empleado.getCodigoEmpleado();

        ArrayList<Nomina> newListaNominas = new ArrayList<Nomina>();
        String select = ("SELECT * FROM NOMINA WHERE CODIGO_EMPLEADO = ?;");
        try (PreparedStatement pst = DBUtils.getConnectionDB().prepareStatement(select)) {
            pst.setString(1, codigoEmpleado);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Nomina nomina = new Nomina(
                        LocalDate.parse(resultSet.getDate("FECHA_NOMINA").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        resultSet.getInt("HORAS_ORDINARIAS"),
                        resultSet.getInt("HORAS_EXTRAORDINARIAS"),
                        resultSet.getFloat("PLUS_NOMINA"),
                        Empleado.getEmbleadoByCodigo(resultSet.getInt("CODIGO_EMPLEADO"))
                );
                //LocalDate fechaNomina, int horasOrdinarias, int horasExtra, float plus, float salario, Empleado titular
                newListaNominas.add(nomina);
                resultSet.close();
                pst.close();
                Nomina.listaNominas = newListaNominas;
            }
        }
        catch (SQLException sqle) {

        }
        finally {

        }
    }


}
