import Launch.LaunchInterpreter;
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
        testing();

        if (LaunchInterpreter.checkTermsAndConditions()){
            System.out.println("Something went good");
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
    public static void testing(){
        System.out.println("-------> Testing for class App.java");
        LaunchInterpreter.replaceValue(LaunchInterpreter.TERMINOS_Y_CONDICIONES, " = denegado");
    }

}
