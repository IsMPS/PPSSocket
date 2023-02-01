package psp.ud03.practica02.resources;

public class ConexionException extends RuntimeException {
	  public ConexionException(Exception e) {
	    super(e);
	  }
	  
	  public ConexionException(String msg) {
	    super(msg);
	  }
	}