package edu.dgepc.alumnos.model;

/**
 * Clase "molde" para serializar/deserializar....
 * https://api.chiquito.ws/
 * {
    "quote": "Gastas menos que el horno de Carpanta"
	}
 * @author usuario
 *
 */
public class FraseChiquito {
	
	private String quote;

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	public FraseChiquito() {
		// TODO Auto-generated constructor stub
	}

	public FraseChiquito(String quote) {
		super();
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "FraseChiquito [quote=" + quote + "]";
	}
	
	

}
