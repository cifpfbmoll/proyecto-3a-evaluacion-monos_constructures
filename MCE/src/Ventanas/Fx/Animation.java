package Ventanas.Fx;

import Utils.WindowUtils;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class Animation {

	//Paquete de animaciones de ventanas


	public enum FadeType {IN, OUT};

	/**
	 * Añade un efecto a las ventanas cambiando el canal ALPHA mediante una transición.
	 * @param node el nodo que va a ser afectado
	 * @param fadeType seleccionamos el tipo de efecto deseado
	 */
	private static void addFadeTransition(Node node, FadeType fadeType){
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1500));

		if ( fadeType.equals(FadeType.IN) ) {
			fade.setFromValue(0);
			fade.setToValue(1);
		} else if ( fadeType.equals(FadeType.OUT) ){
			fade.setFromValue(1);
			fade.setToValue(0);
		}

		fade.setAutoReverse(false);
		fade.setNode(node);
		fade.play();
	}



	private enum TransitionOrientation {X, Y};

	/**
	 * Creamos una transición que desplaza un Nodo de la ventana
	 * @param from desde donde se va a desplazar
	 * @param to hasta donde se va a desplazar
	 * @param node el nodo que va a ser afectado
	 * @param tOrientation Si se realiza vertical o horizontalmente
	 */
	private static void makeInTransition(int from, int to, Node node, TransitionOrientation tOrientation){
		TranslateTransition translate = new TranslateTransition();

		if ( tOrientation.equals(TransitionOrientation.X) ){
			translate.setFromX(from);
			translate.setToX(to);
		} else if ( tOrientation.equals(TransitionOrientation.Y) ){
			translate.setFromY(from);
			translate.setToY(to);
		}

		translate.setDuration(Duration.millis(1000));
		translate.setAutoReverse(false);
		translate.setNode(node);
		translate.play();
	}


	// Transiciones de entrada


	public static void card_animation_BOTTOM_CENTER(Node node){
		makeInTransition(2000, 0, node, TransitionOrientation.Y);
		addFadeTransition(node, FadeType.IN);
	}

	public static void card_animation_TOP_CENTER(Node node){
		makeInTransition(-2000, 0, node, TransitionOrientation.Y);
		addFadeTransition(node, FadeType.IN);
	}

	public static void card_animation_LEFT_CENTER(Node node){
		makeInTransition(-2000, 0, node, TransitionOrientation.X);
		addFadeTransition(node, FadeType.IN);
	}

	public static void card_animation_RIGHT_CENTER(Node node){
		makeInTransition(2000, 0, node, TransitionOrientation.X);
		addFadeTransition(node, FadeType.IN);
	}



	/**
	 * Creamos una transición de SALIDA incluyendo un cambio de ventana
	 * @param from desde donde se va a desplazar
	 * @param to hasta donde se va a desplazar
	 * @param node el nodo que va a ser afectado
	 * @param tOrientation Si se realiza vertical o horizontalmente
	 * @param title el titulo de la siguiente ventana
	 * @param FXMLPath la ruta del archivo .fxml de la siguiente ventana
	 */
	private static void makeOutTransition(int from, int to, Node node, TransitionOrientation tOrientation, String title, String FXMLPath, Event event){
		TranslateTransition translate = new TranslateTransition();

		if ( tOrientation.equals(TransitionOrientation.X) ){
			translate.setFromX(from);
			translate.setToX(to);
		} else if ( tOrientation.equals(TransitionOrientation.Y) ){
			translate.setFromY(from);
			translate.setToY(to);
		}

		translate.setDuration(Duration.millis(1000));
		translate.setAutoReverse(false);
		translate.setNode(node);
		translate.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ev) {
				try {
					WindowUtils.cambiarVentana(event, title, FXMLPath, false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		translate.play();
	}


	//Transiciones de salida

	public static void card_animation_EXIT_TO_LEFT(Node node, String title, String FXMLPath, Event event){
		makeOutTransition(0, -2000, node, TransitionOrientation.X, title, FXMLPath, event);
	}

	public static void card_animation_EXIT_TO_RIGHT(Node node, String title, String FXMLPath, Event event){
		makeOutTransition(0, 2000, node, TransitionOrientation.X, title, FXMLPath, event);
	}


	// Animaciones personalizadas (Ajustando nodos varios)


	/**
	 * Actualizar fecha y hora en un nodo (Posiblemente un label)
	 * @param label la etiqueta de texto que va a contener la fecha y hora
	 */
	public static void setFechaYHora (Label label) {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0),
						actionEvent -> {

							// Obtenemos la fecha
							Month mes = LocalDate.now().getMonth();
							String mesString = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

							DayOfWeek dia = LocalDate.now().getDayOfWeek();
							String diaString = dia.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

							String diaStringFormat = diaString.substring(0, 1).toUpperCase() + diaString.substring(1);

							String diaMes = Integer.toString(MonthDay.now().getDayOfMonth());

							String anyoString = Integer.toString(LocalDate.now().getYear());

							// Obtenemos la hora
							Calendar time = Calendar.getInstance();
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
							String hora = simpleDateFormat.format(time.getTime());

							label.setText(diaStringFormat + " " + diaMes + " de " + mesString + " del " + anyoString + ", " + hora);
						}
				),
				new KeyFrame(Duration.seconds(1))
		);
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.play();
	}
}
