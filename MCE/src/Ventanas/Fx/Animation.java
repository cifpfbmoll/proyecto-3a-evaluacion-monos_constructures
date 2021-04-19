package Ventanas.Fx;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

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


	//Paquete de animaciones de botones

	public static void button_animation_GROW(Node node){
		ScaleTransition scale = new ScaleTransition(Duration.millis(2000), node);
		scale.setByX(1.5f);
		scale.setByY(1.5f);
		scale.setAutoReverse(false);
		scale.play();
	}

}
