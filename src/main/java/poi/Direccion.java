package poi;

public class Direccion {

	private String calle;
	private int numero;
	private int piso = -1;
	private char depto = '0';
	
	public Direccion(){}
	
	public Direccion(String calle, int numero, int piso, char depto){
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.depto = depto;
	}
	
	public Direccion(String calle){
		this.calle = calle;
	}
	
	public Direccion(String calle,  int numero){
		this.calle = calle;
		this.numero = numero;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public char getDepto() {
		return depto;
	}

	public void setDepto(char depto) {
		this.depto = depto;
	}
}
