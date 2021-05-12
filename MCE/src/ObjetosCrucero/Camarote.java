package ObjetosCrucero;


public class Camarote{

    //Atributos

    private String codigoCamarote;

    /*
        La tarifa del camarote es un valor calculado, este proviene a traves del TIPO_CAMAROTE, la tarifa se calcula
        a traves de ese atributo mediante una query.
     */
    private float tarifa; // private String tipoCamarote; // private TipoCamarote tipoCamarote;
    private int numeroCamas;

    //Construcctor

    public Camarote() {
    }

    public Camarote(String codigoCamarote, float tarifa, int numeroCamas) {
        setCodigoCamarote(codigoCamarote);
        setTarifa(tarifa);
        setNumeroCamas(numeroCamas);
    }

    public Camarote(Camarote original) {
        setCodigoCamarote(original.getCodigoCamarote());
        setTarifa(original.getTarifa());
        setNumeroCamas(original.getNumeroCamas());
    }


    //Getters y setters


    public String getCodigoCamarote() {
        return codigoCamarote;
    }

    public void setCodigoCamarote(String codigoCamarote) {
        this.codigoCamarote = codigoCamarote;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    //toString


    @Override
    public String toString() {
        return "Camarote{" +
                "codigoCamarote='" + codigoCamarote + '\'' +
                ", tarifa=" + tarifa +
                ", numeroCamas=" + numeroCamas +
                '}';
    }

    //Métodos

}
