package reportes;

import java.util.ArrayList;
import java.util.List;


import poi.Terminal;

public class ReportePorTerminal {

	public class Entrada {

		public String nombreUsuario;
		public int cantResultados;

		public Entrada(String nombreUsuario, int cantResultados) {
			this.nombreUsuario = nombreUsuario;
			this.cantResultados = cantResultados;
		}
	}

	List<Entrada> infoReporte = new ArrayList<Entrada>();

	public void agregarEntrada(String nombreUsuario, int cantResultados) {
		infoReporte.add(new Entrada(nombreUsuario, cantResultados));
	}

	public List<Entrada> getInfoReporte() {
		return infoReporte;
	}

}
