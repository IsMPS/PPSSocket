package psp.ud03.practica02.conexion;

public interface Conexion {

	public void enviar(byte[] texto);
	
	public void enviarString(String texto);

	public String recibir();
	
	public byte[] recibirByte();

	public void cerrar();
}
