package reportes;

import java.time.*;
import org.joda.time.DateTime;
import java.util.*;

public class ReportePorFecha{

	public class Entrada{

		public DateTime fecha;
		public int cantBusquedas;
		
		public Entrada(DateTime fecha, int cantBusquedas) {
			this.fecha = fecha;
			this.cantBusquedas = cantBusquedas;
                }
		
	}
	
	private List<Entrada> infoReporte = new ArrayList<Entrada>();
	
	public List<Entrada> getInfoReporte() {
		return infoReporte;
	}

	public void agregarEntrada(DateTime fecha, int cantBusquedas){
		infoReporte.add(new Entrada(fecha,cantBusquedas));
	}
        
        public DateTime getFecha(Entrada entrada){
            return entrada.fecha;
        
        }
}    
        
	


