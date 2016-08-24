package bases;

import org.joda.time.DateTime;

public class Busqueda {

	public Busqueda(){}
	public Busqueda(String u, String p, String fechaYhora, int cant){
		setUsuario(u);
		setParametros(p);
		setCantResultados(cant);
		setFechaYhora(fechaYhora);
	}


	private String usuario;
	private String parametros;
	private int cantResultados;
	private  String fechaYhora;
	
	
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
	public String getFechaYhora() {
		return fechaYhora;
	}
	public void setFechaYhora(String fechaYhora) {
		this.fechaYhora = fechaYhora;
	}
	
}
