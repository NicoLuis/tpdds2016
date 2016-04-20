package main.java.principal;

public class Coordenada {
	private double gradosLat, minutosLat, segundosLat;
	private char sentidoLat;
	private double gradosLon, minutosLon, segundosLon;
	private char sentidoLon;
	
	
	public double aDecimal(double grados, double minutos, double segundos, char sentido){
		double decimal = grados + minutos/60 + segundos/3600;
		if(sentido == 'S' || sentido == 'W') return -decimal;
		return decimal;
	}
	
	public double calcularDistancia(double lat2, double lon2){

		double radioTierra = 6371000; // metres
		double latRad1 = radianes(getDecimalLat());
		double latRad2 = radianes(lat2);
		double ΔlatRad = radianes(lat2-getDecimalLat());
		double ΔlonRad = radianes(lon2-getDecimalLon());

		double a = Math.sin(ΔlatRad/2) * Math.sin(ΔlatRad/2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.sin(ΔlonRad/2) * Math.sin(ΔlonRad/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return radioTierra * c;
	
	}
	
	public double radianes(double a){
		return a*(Math.PI)/180;
	}
	
	
	
	
	/*     ---------------------         Getters y Setters      ---------------------         */

	public void setLatitud(double grados, double minutos, double segundos, char sentido) {
		this.gradosLat = grados; 
		this.minutosLat = minutos; 
		this.segundosLat = segundos;
		this.sentidoLat = sentido;
	}
	
	public void setLongitud(double grados, double minutos, double segundos, char sentido) {
		this.gradosLon = grados; 
		this.minutosLon = minutos; 
		this.segundosLon = segundos;
		this.sentidoLon = sentido;
	}

	public double getDecimalLat() {
		return aDecimal(getGradosLat(), getMinutosLat(), getSegundosLat(), getSentidoLat());
	}

	public double getDecimalLon() {
		return aDecimal(getGradosLon(), getMinutosLon(), getSegundosLon(), getSentidoLon());
	}

	public double getGradosLat() {
		return gradosLat;
	}

	public void setGradosLat(int gradosLat) {
		this.gradosLat = gradosLat;
	}

	public double getMinutosLat() {
		return minutosLat;
	}

	public void setMinutosLat(int minutosLat) {
		this.minutosLat = minutosLat;
	}

	public double getSegundosLat() {
		return segundosLat;
	}

	public void setSegundosLat(int segundosLat) {
		this.segundosLat = segundosLat;
	}

	public char getSentidoLat() {
		return sentidoLat;
	}

	public void setSentidoLat(char sentidoLat) {
		this.sentidoLat = sentidoLat;
	}

	public double getGradosLon() {
		return gradosLon;
	}

	public void setGradosLon(int gradosLon) {
		this.gradosLon = gradosLon;
	}

	public double getMinutosLon() {
		return minutosLon;
	}

	public void setMinutosLon(int minutosLon) {
		this.minutosLon = minutosLon;
	}

	public double getSegundosLon() {
		return segundosLon;
	}

	public void setSegundosLon(int segundosLon) {
		this.segundosLon = segundosLon;
	}

	public char getSentidoLon() {
		return sentidoLon;
	}

	public void setSentidoLon(char sentidoLon) {
		this.sentidoLon = sentidoLon;
	}
	
}

