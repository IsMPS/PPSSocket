package psp.ud03.practica02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import psp.ud03.practica02.conexion.ConexionException;
import psp.ud03.practica02.conexion.client.Cliente;

public class MainFileClientApp {

	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 4321;
	private static final String RUTA_DEFAULT = "ficheros/";

	public static void main(String[] args) {

		// Si se ha pasado la direccion del servidor la toma, si no usa la direccion por
		// defecto
		String host = (args.length > 0) ? args[0] : DEFAULT_HOST;
		// Igual con el puerto
		String portString = (args.length > 1) ? args[1] : Integer.toString(DEFAULT_PORT);
		// Se intenta convertir el puerto a entero. Si no se puede se termina con error
		try {
			int port = Integer.parseInt(portString);

			// Creamos el cliente con la direccion y el puerto
			Cliente cliente = new Cliente(host, port);

			// Mientras no se introduzca la cadena vacía
			String mensaje;
			Scanner sc = new Scanner(System.in);

			// Lee el mensaje desde teclado
			System.out.print("Introduzca ruta de fichero: ");
			mensaje = sc.nextLine();
			// Si no es el mensaje de fin
			if (mensaje.length() > 0) {
				// Lo envía
				cliente.enviar(mensaje);

				// Recibe la respuesta
				byte[] respuesta = cliente.recibir();
				byte[] accion = new byte[4];
				System.arraycopy(respuesta, 0, accion, 0, 4);
				if (new String(accion).equals("OK\n\r")) {
					byte[] texto = new byte[respuesta.length - 4];
					System.arraycopy(respuesta, 4, texto, 0, respuesta.length - 4);

					escribirFichero(new File(mensaje), texto);
				}
			}
			// Terminamos el cliente (la conexion)
			cliente.cerrar();
			// Y el scanner
			sc.close();
			System.out.println("Terminando...");
		} catch (NumberFormatException e) {
			System.err.println("El puerto proporcionado no es válido. Terminando.");
			return;
		} catch (ConexionException e) {
			System.err.println("Error en la conexión.");
			System.err.println("Excepcion original:");
			e.printStackTrace(System.err);
		}
	}

	public static void escribirFichero(File existenteArchivo, byte[] texto) {
		File newArchivo = new File(RUTA_DEFAULT + existenteArchivo.getName());
		try {
			if (!newArchivo.exists()) {
				newArchivo.createNewFile();
			}
			FileOutputStream writer = new FileOutputStream(newArchivo);
			writer.write(texto);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
