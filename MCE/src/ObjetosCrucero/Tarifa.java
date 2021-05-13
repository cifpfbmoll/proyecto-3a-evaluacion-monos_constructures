package ObjetosCrucero;

public class Tarifa {
    private String codigoTarifa;
    private String nombreTarifa;
    private float coste;

    public String getCodigoTarifa() {
        return codigoTarifa;
    }

    public void setCodigoTarifa(String codigoTarifa) {
        this.codigoTarifa = codigoTarifa;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }


    public Tarifa(){}

    public Tarifa(String codigoTarifa, String nombreTarifa, float coste){
        this.setCodigoTarifa(codigoTarifa);
        this.setNombreTarifa(nombreTarifa);
        this.setCoste(coste);
    }
}
