package ObjetosCrucero.Servicios;

import java.time.LocalDate;

public class Nomina {

    //Atributos

    private LocalDate fechaNomina;
    private int horasOrdinarias;
    private int horasExtra;
    private float salario;
    private int plus;

    //Constructor

    public Nomina() {
    }

    public Nomina(LocalDate fechaNomina, int horasOrdinarias, int horasExtra, int plus, int salario) {
        setFechaNomina(fechaNomina);
        setHorasOrdinarias(horasOrdinarias);
        setHorasExtra(horasExtra);
        setPlus(plus);
        setSalario(salario);
    }

    public Nomina(Nomina original) {
        setFechaNomina(original.getFechaNomina());
        setHorasOrdinarias(original.getHorasExtra());
        setHorasExtra(original.getHorasOrdinarias());
        setPlus(original.getPlus());
        setSalario(original.getSalario());
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

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    //toString


    @Override
    public String toString() {
        return "Nomina{" +
                "fechaNomina=" + fechaNomina +
                ", horasOrdinarias=" + horasOrdinarias +
                ", horasExtra=" + horasExtra +
                ", plus=" + plus +
                '}';
    }

    //Metodos

    public float calcularSalario() {

        LocalDate fechaNomina = this.getFechaNomina();
        int horasExtra = this.getHorasExtra();
        int horasOrdinarias = this.getHorasOrdinarias();
        float salario = this.salario;
        int plus = this.getPlus();

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

}