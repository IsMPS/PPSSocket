package psp.ud03.practica02.conexion.client;

import java.net.Socket;

import psp.ud03.practica02.conexion.Conexion;
import psp.ud03.practica02.conexion.ConexionException;
import psp.ud03.practica02.conexion.ConexionSocket;


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
    conexion.enviarString(msg);
  }
  
  public byte[] recibir() {
    return conexion.recibirByte();
  }

  public void cerrar() {
    conexion.cerrar();
  }
  

}
