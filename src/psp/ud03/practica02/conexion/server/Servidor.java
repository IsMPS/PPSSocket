package psp.ud03.practica02.conexion.server;

import java.net.ServerSocket;

import psp.ud03.practica02.conexion.Conexion;
import psp.ud03.practica02.conexion.ConexionException;
import psp.ud03.practica02.conexion.ConexionSocket;

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
