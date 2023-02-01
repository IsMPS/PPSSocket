package psp.ud03.practica02.resources;

public interface Conexion {

	public void enviar(String mensaje);

	public String recibir();

	public void cerrar();
}
