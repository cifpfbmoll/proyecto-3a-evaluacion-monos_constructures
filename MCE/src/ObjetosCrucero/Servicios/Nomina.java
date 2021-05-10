package ObjetosCrucero.Servicios;

import java.time.LocalDate;

public class Nomina {

    //Atributos

    LocalDate fechaNomina;
    int horasOrdinarias;
    int horasExtra;
    int plus;

    //Constructor

    public Nomina() {
    }

    public Nomina(LocalDate fechaNomina, int horasOrdinarias, int horasExtra, int plus) {
        setFechaNomina(fechaNomina);
        setHorasOrdinarias(horasOrdinarias);
        setHorasExtra(horasExtra);
        setPlus(plus);
    }

    public Nomina(Nomina original) {
        setFechaNomina(original.getFechaNomina());
        setHorasOrdinarias(original.getHorasExtra());
        setHorasExtra(original.getHorasOrdinarias());
        setPlus(original.getPlus());
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



}
