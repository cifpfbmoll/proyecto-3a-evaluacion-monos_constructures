import Launch.LaunchInterpreter;
import ObjetosCrucero.Servicios.RecursosHumanos;
import Utils.DBUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Esta es la clase principal del programa.
 * Contiene el método MAIN que se encarga de cargar la página principal.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //[TESTING]
        //testing();

        if (LaunchInterpreter.checkTermsAndConditions()){
            Parent root = FXMLLoader.load(getClass().getResource("Ventanas/LogIn/log_in.fxml"));
            primaryStage.setTitle("Iniciar sesión");
            primaryStage.getIcons().add(new Image("Ventanas/Trash/img/MC_Logo.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
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
    }

}
