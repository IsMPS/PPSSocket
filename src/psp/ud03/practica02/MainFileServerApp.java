package psp.ud03.practica02;

import psp.ud03.practica02.conexion.Conexion;
import psp.ud03.practica02.conexion.server.Servidor;
import psp.ud03.practica02.conexion.server.ServidorHilo;

public class MainFileServerApp {

	 private static final int DEFAULT_PORT = 4321;

	  public static void main(String[] args) {
	    // Si se pasa el puerto, lo toma, si no toma el puerto por defecto
	    String portString = (args.length > 0) ? args[0] : Integer.toString(DEFAULT_PORT);
	    // Se intenta convertir el puerto a entero. Si no se puede se termina con error
	    try {
	      int port = Integer.parseInt(portString);
	      // Creamos el servidor con el puerto
	      Servidor servidor = new Servidor(port);

	      // Esperamos conexiones una detrás de otra (Si se produce un error, se termina)
	      Conexion conexion = null;
	      while ((conexion = servidor.esperarConexion()) != null) {
	        // Creamos un hilo para procesar los mensajes de la nueva conexion
	        ServidorHilo worker = new ServidorHilo(conexion);
	        worker.start();
	      }
	    } catch (NumberFormatException e) {
	      System.err.println("El puerto proporcionado no es válido");
	      return;
	    }
	      
	  }

}
