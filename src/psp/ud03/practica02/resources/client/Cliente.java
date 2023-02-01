package psp.ud03.practica02.resources.client;

import java.net.Socket;

import psp.ud03.practica02.resources.Conexion;
import psp.ud03.practica02.resources.ConexionException;
import psp.ud03.practica02.resources.ConexionSocket;


public class Cliente {

  private Conexion conexion;
  
  public Cliente(String host, int port) {
    // Creamos y conectamos el socket (no se almacenan los parametros)
    try {
      // Se intenta crear (y conectar)
      conexion = new ConexionSocket(new Socket(host, port));
    } catch (Exception e) {
      // Si no se puede conectar, lanzamos una excepcion
      throw new ConexionException(e);
    }
  }
  
  public void enviar(String msg) {
    conexion.enviar(msg);
  }
  
  public String recibir() {
    return conexion.recibir();
  }

  public void cerrar() {
    conexion.cerrar();
  }
  

}
