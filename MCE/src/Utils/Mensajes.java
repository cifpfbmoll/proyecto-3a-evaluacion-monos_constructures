package Utils;

import java.util.Random;

public class Mensajes {

	public static String getUserInfo(){
		return new String(
				Credentials.getLoggedUser().getDni() + "\n" +
						Credentials.getLoggedUser().getNombre() +"\n" +
						Credentials.getLoggedUser().getApellido() + "\n" +
						Credentials.getLoggedUser().getCodigoEmpleado() + "\n" +
						Credentials.getLoggedUser().getServicio()
				);
	}

	public static String getMensajeBienvenida(){
		String nombreUsuario = Credentials.getLoggedUser().getNombre();
		String[] welcomeMessages = new String[]{
				"¡Buenos días " + nombreUsuario + " !",
				"¿Le apetece un café,  " + nombreUsuario + "?",
				"¡Que hoy se note lo que vales " + nombreUsuario + "!",
				"Nos alegramos de verte " + nombreUsuario,
				"¿Qué desea hacer hoy, " + nombreUsuario + "?",
				"Hace un día perfecto, como tú " + nombreUsuario,
				"¿Alguna buena noticia " + nombreUsuario + "?",
				"Cree en ti, " + nombreUsuario,
				"Tu sonrisa me alegra el día, " + nombreUsuario,
				"Hoy va a ser un buen día, ¿Verdad, " + nombreUsuario + "?",
				"¡Qué grande eres " + nombreUsuario + "!",
				"Si puedes imaginarlo, puedes hacerlo " + nombreUsuario,
				"¡De nuevo a trabajar " + nombreUsuario + "!",
				"La rutina a veces puede ser maravillosa " + nombreUsuario,
				"Un gran esfuerzo consigue grandes recompensas " + nombreUsuario,
				"Si lees esto es porque eres genial " + nombreUsuario,
				"Hola " + nombreUsuario + ", ¿algo que hacer hoy?"
				//[...]
		};

		Random random=new Random();
		int fraseDelDia = random.nextInt(welcomeMessages.length);
		return welcomeMessages[fraseDelDia];
	}

}
