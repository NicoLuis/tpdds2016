package bases;

import org.joda.time.DateTime;
import java.util.Calendar;

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
      
	public DateTime getFechaYhora() {
		return fechaYhora;
	}
	public void setFechaYhora(DateTime fechaYhora) {
		this.fechaYhora = fechaYhora;
	}
        
        public boolean mismaFecha(DateTime fecha) {
            
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(this.fechaYhora.toDate());
        c2.setTime(fecha.toDate());

        boolean sameDay = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
              c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR) && 
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
        		
            return sameDay;
            
	}
        
	public String getNombrePoisResultado() {
		return nombrePoisResultado;
	}
	public void setNombrePoisResultado(String nombrePoisResultado) {
		this.nombrePoisResultado = nombrePoisResultado;
	}
	
}
