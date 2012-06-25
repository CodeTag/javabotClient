package bomberbot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Bomberbot starter package for Java
 * */
public class BomberBot {

	private PrintWriter outToServer = null;
	private Scanner inFromServer = null;
	private Socket socketCliente = null;
	private Bot bot = null;

	private boolean conectado = false;

	public BomberBot() {
		try {
			conectar("ekeiscoJava", "265656");
			controlConexion();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void conectar(String user, String token)
			throws UnknownHostException, IOException {
		socketCliente = new Socket("localhost", 5000);
		outToServer = new PrintWriter(socketCliente.getOutputStream());
		inFromServer = new Scanner(new InputStreamReader(
				socketCliente.getInputStream()));
		inFromServer = inFromServer.useDelimiter("\r\n");
		String bienvenida = inFromServer.next();
		System.out.println(bienvenida);
		outToServer.print(user + "," + token);
		outToServer.flush();
		conectado = true;
	}

	private void controlConexion() throws IOException {
		String serverMessage = "";
		String message[] = null;
		while (conectado) {
			System.out.println("turno");
			serverMessage = inFromServer.next();
			message = serverMessage.split(";");
			if (message[0].equals("EMPEZO")) {
				bot = new Bot(message[2].charAt(0));
				bot.updateMap(message[1]);
			} else if (message[0].equals("TURNO")) {
				System.out.println("turno: " + message[1]);
				bot.updateMap(message[2]);
				outToServer.print(bot.move());
				outToServer.flush();
			} else if (message[0].equals("PERDIO")) {
				System.out.println("perdi :(");
			}

		}
	}

	public static void main(String[] args) {
		new BomberBot();
	}

}
