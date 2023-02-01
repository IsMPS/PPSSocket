package psp.ud03.practica02;

import java.util.Scanner;

import psp.ud03.practica02.resources.ConexionException;
import psp.ud03.practica02.resources.client.Cliente;

public class MainFileClientApp {

	private static final String DEFAULT_HOST = "localhost";
	  private static final int DEFAULT_PORT = 4321;
	  
	  public static void main(String[] args) {
	    
	    // Si se ha pasado la direccion del servidor la toma, si no usa la direccion por defecto
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
	      do {
	        // Lee el mensaje desde teclado
	        System.out.print("Introduzca el mensaje a enviar (vacio para acabar): ");
	        mensaje = sc.nextLine();
	        // Si no es el mensaje de fin
	        if (mensaje.length() > 0) {
	          // Lo envía
	          cliente.enviar(mensaje);
	          // Recibe la respuesta
	          String respuesta = cliente.recibir();
	          // Y la imprime
	          System.out.println("Respuesta recibida: " + respuesta);
	        }
	      } while (mensaje.length() > 0);
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

}
