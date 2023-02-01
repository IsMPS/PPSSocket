package psp.ud03.practica02.resources.server;

import java.net.ServerSocket;

import psp.ud03.practica02.resources.Conexion;
import psp.ud03.practica02.resources.ConexionException;
import psp.ud03.practica02.resources.ConexionSocket;

public class Servidor{

	  private ServerSocket serverSocket;
	  
	  public Servidor(int port) {
	    try {
	      serverSocket = new ServerSocket(port);
	    } catch (Exception e) {
	      throw new ConexionException(e);
	    }
	  }

	  public Conexion esperarConexion() {
	    // Intentamos aceptar una conexion. Si no hay ninguna en cola espera
	    try {
	      return new ConexionSocket(serverSocket.accept());
	    } catch (Exception e) {
	      // Error. Devolvemos no ok
	      return null;
	    }
	  }
	  

	}
