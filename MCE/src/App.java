import Launch.LaunchInterpreter;
import ObjetosCrucero.Servicios.Billete;
import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import Utils.DBUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ObjetosCrucero.Servicios.Servicio.getListaServicios;

/**
 * Esta es la clase principal del programa.
 * Contiene el método MAIN que se encarga de cargar la página principal.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //[TESTING]
        //testing();
        DBUtils.createConnectionDB();

        getListaServicios();


        if (LaunchInterpreter.checkTermsAndConditions()){
            Parent root = FXMLLoader.load(getClass().getResource("Ventanas/LogIn/log_in.fxml"));
            primaryStage.setTitle("Iniciar sesión");
            primaryStage.getIcons().add(new Image("Ventanas/Trash/img/MC_Logo.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(true);
            primaryStage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Ventanas/TermsAndConditions/terms_and_conditions.fxml"));
            primaryStage.setTitle("Términos y condiciones");
            primaryStage.getIcons().add(new Image("Ventanas/Trash/img/MC_Logo.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * [TESTING]
     * Este método está enfocado especialmente para el PROCESO DE DESARROLLO de la aplicación
     * Este debe ser eliminado de la versión final junto a todas sus referencias en el código final.
     */
    public static void testing() throws Exception {
        System.out.println("-------> Testing for class App.java");
        LaunchInterpreter.replaceValue(LaunchInterpreter.TERMINOS_Y_CONDICIONES, " = denegado");

        //encript
        System.out.println(DBUtils.encrypt("1234", "1234"));

        //Obtener la lista de empleados desde la BBDD
        List<Empleado> listaTest = RecursosHumanos.getListaEmpleados();
        for (Empleado empleado : listaTest){
            System.out.println(empleado.getNombre() + " " + empleado.getApellido());
        }

        Billete billete = new Billete("4354345", "33333333a", new java.sql.Date(0), "20004d", "fqg325rr");

        billete.escrituraBilletes();

    }

}
