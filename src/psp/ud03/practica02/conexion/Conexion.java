package psp.ud03.practica02.conexion;

public interface Conexion {

	public void enviarByte(byte[] texto);
	
	public void enviarString(String texto);

	public String recibirString();
	
	public byte[] recibirByte();

	public void cerrar();
}
