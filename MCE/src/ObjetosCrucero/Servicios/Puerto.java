package ObjetosCrucero.Servicios;
import java.util.Scanner;
import java.util.ArrayList;
public class Puerto {
    //ATRIBUTOS
    private String codigoCrucero;
    private String nombreCrucero;
    private String codigoCiudad;

    //CONSTRUCTOR VACIO
    public Puerto() {
    }

    //CONSTRUCTOR CON TODOS LOS PARAMETROS
    public Puerto(String codigoCrucero, String nombreCrucero, String codigoCiudad) {
        this.codigoCrucero = codigoCrucero;
        this.nombreCrucero = nombreCrucero;
        this.codigoCiudad = codigoCiudad;
    }

    //GETTERS Y SETTERS
    public String getCodigoCrucero() {
        return codigoCrucero;
    }

    public void setCodigoCrucero(String codigoCrucero) {
        this.codigoCrucero = codigoCrucero;
    }

    public String getNombreCrucero() {
        return nombreCrucero;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public void setNombreCrucero(String nombreCrucero) {
        this.nombreCrucero = nombreCrucero;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Puerto{" +
                "codigoCrucero='" + codigoCrucero + '\'' +
                ", nombreCrucero='" + nombreCrucero + '\'' +
                ", codigoCiudad='" + codigoCiudad + '\'' +
                '}';
    }

    //METODO SOLICITAR DATOS
    public void SolicitarDatos(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Introduce el codigo del crucero");
        this.setCodigoCrucero(codigoCrucero);
        System.out.println("Introduce el nombre del crucero");
        this.setNombreCrucero(nombreCrucero);
        System.out.println("Introduce el codigo de la ciudad");
        this.setCodigoCiudad(codigoCiudad);
    }

    //BUSCAMOS PARADA
    public static void buscarParada (ArrayList <Puerto> listaPuertos){
        Scanner sc = new Scanner (System.in);
        System.out.println("Dime el numero de la parada que quieres buscar");
        int numeroParada = sc.nextInt();

        for (int i =0; i<listaPuertos.size();i++){
            if (listaPuertos.get(i) instanceof Parada){
                if (((Parada) listaPuertos.get(i)).getNumeroParada()==numeroParada){
                    System.out.println(((Parada)listaPuertos.get(i)).toString());
                }
            }else{
                System.out.println("Numero de parada incorrecto");
            }
        }

    }

}
