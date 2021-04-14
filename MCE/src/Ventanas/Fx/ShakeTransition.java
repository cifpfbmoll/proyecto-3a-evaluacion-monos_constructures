package Ventanas.Fx;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

public class ShakeTransition extends Transition {

	private final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
	private final Timeline timeline;
	private final Node node;
	private boolean oldCache = false;
	private CacheHint oldCacheHint = CacheHint.DEFAULT;
	private final boolean useCache=true;
	private final double xIni;

	private final DoubleProperty x = new SimpleDoubleProperty();

	/**
	 * Creamos una nueva ShakeTransition (Efecto terremoto)
	 *
	 * @param node El nodo que va a ser afectado
	 */
	public ShakeTransition(final Node node) {
		this.node=node;
		statusProperty().addListener((ov, t, newStatus) -> {
			switch(newStatus) {
				case RUNNING:
					starting();
					break;
				default:
					stopping();
					break;
			}
		});

		//Creamos el efecto dentro de la línea de tiempo modificando la propiedad de X
		this.timeline= new Timeline(
				new KeyFrame(Duration.millis(0), new KeyValue(x, 0, WEB_EASE)),
				new KeyFrame(Duration.millis(100), new KeyValue(x, -10, WEB_EASE)),
				new KeyFrame(Duration.millis(200), new KeyValue(x, 10, WEB_EASE)),
				new KeyFrame(Duration.millis(300), new KeyValue(x, -10, WEB_EASE)),
				new KeyFrame(Duration.millis(400), new KeyValue(x, 10, WEB_EASE)),
				new KeyFrame(Duration.millis(500), new KeyValue(x, -10, WEB_EASE)),
				new KeyFrame(Duration.millis(600), new KeyValue(x, 10, WEB_EASE)),
				new KeyFrame(Duration.millis(700), new KeyValue(x, -10, WEB_EASE)),
				new KeyFrame(Duration.millis(800), new KeyValue(x, 10, WEB_EASE)),
				new KeyFrame(Duration.millis(900), new KeyValue(x, -10, WEB_EASE)),
				new KeyFrame(Duration.millis(1000), new KeyValue(x, 0, WEB_EASE))
		);

		//Añadimos el efecto al Nodo que hemos pasado por parámetro e indicamos la duración.
		xIni=node.getTranslateX();
		x.addListener((ob,n,n1)->(node).setTranslateX(xIni+n1.doubleValue()));

		setCycleDuration(Duration.seconds(1));
		setDelay(Duration.seconds(0.2));
	}

	/**
	 * Cuando empieza la animación
	 */
	protected final void starting() {
		if (useCache) {
			oldCache = node.isCache();
			oldCacheHint = node.getCacheHint();
			node.setCache(true);
			node.setCacheHint(CacheHint.SPEED);
		}
	}

	/**
	 * Cuando está parando la animación
	 */
	protected final void stopping() {
		if (useCache) {
			node.setCache(oldCache);
			node.setCacheHint(oldCacheHint);
		}
	}

	@Override
	protected void interpolate(double d) {
		timeline.playFrom(Duration.seconds(d));
		timeline.stop();
	}
}
