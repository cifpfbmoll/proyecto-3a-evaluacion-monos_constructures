package Ventanas.RecursosHumanos.ViewStaff;

import ObjetosCrucero.Servicios.Empleado;
import ObjetosCrucero.Servicios.RecursosHumanos;
import Utils.Credentials;
import Ventanas.Fx.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class RrhhViewStaffController {

    //Elementos gráficos

    @FXML
    AnchorPane mainCard;

    @FXML
    Label fechaYHora;

    @FXML
    ScrollPane wtabScroll;


    //Panel de la tabla de empleados

    @FXML
    VBox usersPane;


    //Botones

    @FXML
    VBox backButton;


    //Evento para volver a la anterior pantalla
    EventHandler<MouseEvent> goBack = event -> {
        String nombreEmpleadoCompleto = Credentials.getLoggedUser().getNombre() + " " + Credentials.getLoggedUser().getApellido();
        Animation.card_animation_EXIT_TO_RIGHT(
                mainCard,
                "Recursos Humanos ~ " + nombreEmpleadoCompleto,
                "../Ventanas/RecursosHumanos/MainPage/rrhh_main_page.fxml",
                event
        );
    };


    private void actualizarTabla() {
        try {
            List<Empleado> lista = RecursosHumanos.getListaEmpleados();
            for (Empleado empleado : lista) {
                insertarFila(empleado);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    private void insertarFila(Empleado empleado) {

        String estiloDeFila = (
                "-fx-background-radius: 15; " +
                        "-fx-background-color: #EEE; "
        );

        String estiloTexto = ("-fx-text-fill: #555; ");

        //Este es el margen que deben tener las columnas centrales
        Insets marginRight = new Insets(0, 20, 0, 0);

        //La fila a insertar
        HBox fila;

        double anchoDeColumna = 200;

        //Nombre
        Label nombre = new Label(empleado.getNombre());
        nombre.setStyle(estiloTexto);
        HBox nombreBox = new HBox(nombre);
        HBox.setMargin(nombreBox, new Insets(0, 120, 0, -30));
        nombreBox.setPrefWidth(100);
        nombreBox.setAlignment(Pos.CENTER);

        //Apellidos
        Label apellidos = new Label(empleado.getApellido());
        apellidos.setStyle(estiloTexto);
        HBox apellidosBox = new HBox(apellidos);
        HBox.setMargin(apellidosBox, new Insets(0, 55, 0, 0));
        apellidosBox.setPrefWidth(100);
        apellidosBox.setAlignment(Pos.CENTER);

        //DNI
        Label dni = new Label(empleado.getDni());
        dni.setStyle(estiloTexto);
        HBox dniBox = new HBox(dni);
        HBox.setMargin(dniBox, new Insets(0, 80, 0, 0));
        dniBox.setPrefWidth(anchoDeColumna);
        dniBox.setAlignment(Pos.CENTER);

        //Codigo
        Label codigo = new Label(empleado.getCodigoEmpleado());
        codigo.setStyle(estiloTexto);
        HBox codigoBox = new HBox(codigo);
        HBox.setMargin(codigoBox, new Insets(0, 105, 0, 0));
        codigoBox.setPrefWidth(50);
        codigoBox.setAlignment(Pos.CENTER);

        //Domicilio
        Label domiciliacion = new Label(empleado.getDireccion());
        domiciliacion.setStyle(estiloTexto);
        HBox domiciliacionBox = new HBox(domiciliacion);
        HBox.setMargin(domiciliacionBox, new Insets(0, 105, 0, 0));
        domiciliacionBox.setPrefWidth(anchoDeColumna);
        domiciliacionBox.setAlignment(Pos.CENTER);

        //Servicio
        Label servicio = new Label(empleado.getServicio().getCodigo());
        servicio.setStyle(estiloTexto);
        HBox servicioBox = new HBox(servicio);
        HBox.setMargin(servicioBox, new Insets(0, 5, 0, 0));
        servicioBox.setPrefWidth(50);
        servicioBox.setAlignment(Pos.CENTER);


        //Ajustamos la fila
        fila = new HBox(nombreBox, apellidosBox, dniBox, codigoBox, domiciliacionBox, servicioBox);
        fila.setPrefHeight(50);
        fila.setAlignment(Pos.CENTER);
        fila.setPadding(new Insets(0, 30, 0, 30));
        VBox.setMargin(fila, new Insets(20, 30, 20, 30));
        fila.setStyle(estiloDeFila);

        usersPane.getChildren().add(fila);
    }


    @FXML
    private void initialize() throws InterruptedException {

        // Ajustamos la opacidad de entrada
        mainCard.setOpacity(0);

        // Ajustando la velocidad de "Scroll" del ScrollPane
        final double SPEED = 0.002;
        wtabScroll.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            wtabScroll.setVvalue(wtabScroll.getVvalue() - deltaY);
        });

        //Ajustamos los distintos textos del panel
        Animation.setFechaYHora(fechaYHora);

        //Añadimos la funcionalidad a los botones
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, goBack);

        //Creando la animación de entrada
        Animation.card_animation_RIGHT_CENTER(mainCard);

        actualizarTabla();
    }
}
