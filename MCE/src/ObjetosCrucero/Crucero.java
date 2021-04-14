package ObjetosCrucero;

public class Crucero {
    String codigoCrucero;
    String nombreCrucero;
    String modeloCrucero;
    int eslora;
    int manga;
    int calado;

    public Crucero() {
    }

    public Crucero(String codigoCrucero, String nombreCrucero, String modeloCrucero, int eslora, int manga, int calado) {
        this.setCodigoCrucero(codigoCrucero);
        this.setNombreCrucero(nombreCrucero);
        this.setModeloCrucero(modeloCrucero);
        this.setEslora(eslora);
        this.setManga(manga);
        this.setCalado(calado);
    }

    public Crucero(Crucero cCopia) {
        this.setCodigoCrucero(cCopia.getCodigoCrucero());
        this.setNombreCrucero(cCopia.getNombreCrucero());
        this.setModeloCrucero(cCopia.getModeloCrucero());
        this.setEslora(cCopia.getEslora());
        this.setManga(cCopia.getManga());
        this.setCalado(cCopia.getCalado());
    }

    public String getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(String codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public String getNombreCrucero() {
        return nombreCrucero;
    }

    public void setNombreCrucero(String nombreCrucero) {
        this.nombreCrucero = nombreCrucero;
    }

    public String getModeloCrucero() {
        return modeloCrucero;
    }

    public void setModeloCrucero(String modeloCrucero) {
        this.modeloCrucero = modeloCrucero;
    }

    public int getEslora() {
        return eslora;
    }

    public void setEslora(int eslora) {
        this.eslora = eslora;
    }

    public int getManga() {
        return manga;
    }

    public void setManga(int manga) {
        this.manga = manga;
    }

    public int getCalado() {
        return calado;
    }

    public void setCalado(int calado) {
        this.calado = calado;
    }

    @Override
    public String toString() {
        return "Crucero{" +
                "codigoCrucero='" + codigoCrucero + '\'' +
                ", nombreCrucero='" + nombreCrucero + '\'' +
                ", modeloCrucero='" + modeloCrucero + '\'' +
                ", eslora=" + eslora +
                ", manga=" + manga +
                ", calado=" + calado +
                '}';
    }
}
