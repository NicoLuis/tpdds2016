package bases;

import org.joda.time.DateTime;

public class Busqueda {

	public Busqueda(){}
	public Busqueda(DateTime fechaYhora, String usuario, String parametros, int cant, String nombrePoisResultado){
		setUsuario(usuario);
		setParametros(parametros);
		setCantResultados(cant);
		setFechaYhora(fechaYhora);
		setNombrePoisResultado(nombrePoisResultado);
	}


	private String usuario;
	private String parametros;
	private String nombrePoisResultado;
	private int cantResultados;
	private  DateTime fechaYhora;
	
	
	public int getCantResultados() {
		return cantResultados;
	}
	public void setCantResultados(int cantResultados) {
		this.cantResultados = cantResultados;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
        public boolean mismaTerminal(Busqueda busqueda) {
	        return this.getUsuario().equals(busqueda.getUsuario());

	}
        public boolean mismaFecha(DateTime fecha) {
		return fechaYhora.equals(fecha);
	}
	public DateTime getFechaYhora() {
		return fechaYhora;
	}
	public void setFechaYhora(DateTime fechaYhora) {
		this.fechaYhora = fechaYhora;
	}
	public String getNombrePoisResultado() {
		return nombrePoisResultado;
	}
	public void setNombrePoisResultado(String nombrePoisResultado) {
		this.nombrePoisResultado = nombrePoisResultado;
	}
	
}
