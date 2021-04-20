package Ventanas.Fx;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class Animation {


	//Paquete de animaciones de ventanas

	public static void card_animation_BOTTOM_TOP(Node node){
		TranslateTransition translate = new TranslateTransition();
		translate.setFromY(900);
		translate.setToY(0);
		translate.setDuration(Duration.millis(1000));
		translate.setAutoReverse(false);
		translate.setNode(node);
		translate.play();

		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1500));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setAutoReverse(false);
		fade.setNode(node);
		fade.play();

	}

	public static void card_animation_LEFT_RIGHT(Node node){
		TranslateTransition translate = new TranslateTransition();
		translate.setFromX(900);
		translate.setToX(0);
		translate.setDuration(Duration.millis(1000));
		translate.setAutoReverse(false);
		translate.setNode(node);
		translate.play();

		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1500));
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setAutoReverse(false);
		fade.setNode(node);
		fade.play();

	}




	//Actualizar fecha y hora en un nodo (Posiblemente un label)

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
