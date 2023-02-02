package psp.ud03.practica02.conexion.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import psp.ud03.practica02.conexion.Conexion;

public class ServidorHilo extends Thread {

	private Conexion conexion;

	public ServidorHilo(Conexion conexion) {
		this.conexion = conexion;
	}

	@Override
	public void run() {
		// Mientras haya mensajes
		String peticion = null;
			peticion = conexion.recibirString();
			File archivo = new File(peticion);
			byte[] total;
			// Si se pudo recibir (si la conexión no se ha cerrado)
			if (peticion != null) {
				if (archivo.exists()) {
					// lee el fichero en bytes
					byte[] respuesta = new String("OK\n\r").getBytes();
					byte[] texto = null;
					try {
						texto = Files.readAllBytes(archivo.toPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
					total = new byte[respuesta.length + texto.length];
					System.arraycopy(respuesta, 0, total, 0, respuesta.length);
					System.arraycopy(texto, 0, total, respuesta.length, texto.length);
				} else
					total = new String("ERR\n\r").getBytes();
				// Y lo enviamos como respuesta
				conexion.enviarByte(total);
			}
	}
}