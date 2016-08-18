package reportes;

import java.time.*;
import java.util.*;

public class ReportePorFecha{

	public class Entrada{

		public LocalDate fecha;
		public int cantBusquedas;
		
		public Entrada(LocalDate fecha, int cantBusquedas) {
			this.fecha = fecha;
			this.cantBusquedas = cantBusquedas;
		}
	}
	
	private List<Entrada> infoReporte = new ArrayList<Entrada>();
	
	public List<Entrada> getInfoReporte() {
		return infoReporte;
	}

	public void agregarEntrada(LocalDate fecha, int cantBusquedas){
		infoReporte.add(new Entrada(fecha,cantBusquedas));
	}
	
}
