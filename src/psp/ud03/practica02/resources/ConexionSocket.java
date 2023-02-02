package psp.ud03.practica02.resources;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConexionSocket implements Conexion {

	private Socket socket;

	public ConexionSocket(Socket socket) {
		this.socket = socket;
	}

	public void enviar(byte[] mensaje) {
		// Si el socket está conectado
		if (socket.isConnected()) {
			try {
				DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
				// Enviamos el mensaje por el socket
				writer.write(mensaje);
				writer.flush();
			} catch (IOException e) {
				// En caso de cualquier error de comunicaciones
				// Lanzamos una excepción hacia arriba
				throw new ConexionException(e);
			}
		} else {
			// Si el socket no está conectado, lanzamos una excepción
			throw new ConexionException("Socket no conectado");
		}
	}

	public String recibir() {
		// Si el socket está conectado
		if (socket.isConnected()) {
			// Intenta leer del socket (vamos a usar un Reader porque usamos texto)
			// Si se recibieran datos binarios habría que usar directamente el stream
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// Leemos el resultado
				String resultado = reader.readLine();
				// Devolvemos el mensaje enviado por el servidor
				return resultado;
			} catch (Exception e) {
				// Si hay algún error con la conexión lanzamos nuestra excepcion
				throw new ConexionException(e);
			}
		} else {
			// Si el socket no está conectado lanza una excepcion
			throw new ConexionException("Socket no conectado");
		}
	}

	public void cerrar() {
		// Lo intenta cerrar. Si no se pueda no pasa nada
		try {
			socket.close();
		} catch (IOException e) {
		}
	}

	public void enviarString(String texto) {
		// Si el socket está conectado
		if (socket.isConnected()) {
			try {
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				// Enviamos el mensaje por el socket
				writer.println(texto);
				writer.flush();
			} catch (IOException e) {
				// En caso de cualquier error de comunicaciones
				// Lanzamos una excepción hacia arriba
				throw new ConexionException(e);
			}
		} else {
			// Si el socket no está conectado, lanzamos una excepción
			throw new ConexionException("Socket no conectado");
		}
	}
	
	public byte[] recibirByte() {
		// Si el socket está conectado
		if (socket.isConnected()) {
			// Intenta leer del socket (vamos a usar un Reader porque usamos texto)
			// Si se recibieran datos binarios habría que usar directamente el stream
			try {
				DataInputStream reader = new DataInputStream(socket.getInputStream());;
				// Leemos el resultado
				byte[] resultado = new byte[reader.readInt()];
				reader.readFully(resultado);
				// Devolvemos el mensaje enviado por el servidor
				return resultado;
			} catch (Exception e) {
				// Si hay algún error con la conexión lanzamos nuestra excepcion
				throw new ConexionException(e);
			}
		} else {
			// Si el socket no está conectado lanza una excepcion
			throw new ConexionException("Socket no conectado");
		}
	}
}