package psp.ud03.practica02.conexion;

public class ConexionException extends RuntimeException {
	  public ConexionException(Exception e) {
	    super(e);
	  }
	  
	  public ConexionException(String msg) {
	    super(msg);
	  }
	}