package psp.ud03.practica02.resources.server;

import psp.ud03.practica02.resources.Conexion;

public class ServidorHilo extends Thread {

	  private Conexion conexion;
	  
	  public ServidorHilo(Conexion conexion) {
	    this.conexion = conexion;
	  }
	  
	  @Override
	  public void run() {
	    // Mientras haya mensajes
	    String peticion = null;
	    do {
	      peticion = conexion.recibir();
	      // Si se pudo recibir (si la conexión no se ha cerrado)
	      if (peticion != null) {
	        // Lo convertimos a mayúsculas
	        String respuesta = peticion.toUpperCase();
	        // Y lo enviamos como respuesta
	        conexion.enviar(respuesta);
	      }
	    } while (peticion != null);
	  }
	}