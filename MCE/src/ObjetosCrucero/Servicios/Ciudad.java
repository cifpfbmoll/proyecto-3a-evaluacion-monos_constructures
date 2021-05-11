package ObjetosCrucero.Servicios;
import java.util.ArrayList;
import java.util.Scanner;

public class Ciudad {
    private String CodigoCiudad;
    private String nombreCiudad;
    private ArrayList<Ciudad> listaCiudades = new ArrayList<Ciudad>();

    //COSNTRUCTOR VACIO
    public Ciudad() {
    }

    //COSNTRUCTOR CON TODOS LOS PARAMETROS
    public Ciudad(String CodigoCiudad,String nombreCiudad) {
        this.CodigoCiudad = CodigoCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    public Ciudad(Ciudad c1) {
        this.setCodigoCiudad(CodigoCiudad);
        this.setNombreCiudad(nombreCiudad);
    }

    //GETTERS Y SETTERS
    public String getCodigoCiudad() {
        return CodigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        CodigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Ciudad{" +
                "CodigoCiudad='" + CodigoCiudad + '\'' +
                ", nombreCiudad='" + nombreCiudad + '\'' +
                '}';
    }

    //METODOS
    //METODO PARA INSERTAR DATOS CIUDAD Y AÑADIRLOS
    public static void SolicitarDatosCiudad (ArrayList <Ciudad> listaCiudades){
        Ciudad NuevaCiudad = new Ciudad();
        Scanner sc = new Scanner (System.in);
        System.out.println("Dime el codigo de la ciudad");
        NuevaCiudad.setCodigoCiudad(sc.nextLine());
        System.out.println("Dime el nombre de la ciudad");
        NuevaCiudad.setNombreCiudad(sc.nextLine());
        listaCiudades.add(NuevaCiudad);
    }

    //METODO PARA ELIMINAR CIUDAD
    public static  void eliminarCiudad (ArrayList<Ciudad> listaCiudades){
        Scanner sc = new Scanner (System.in);
        System.out.println("Introduce el codigo de la ciudad que deseas eliminar");
        String CodigoCiudad = sc.nextLine();

        for (int i =0; i<listaCiudades.size(); i++){
            //Si encuentra la ciudad la eliminamos
            if (listaCiudades.get(i).getCodigoCiudad() == CodigoCiudad){
                listaCiudades.remove(i);

            }else{
                System.out.println("Codido de ciudad incorrecto");
            }
        }

    }
}
