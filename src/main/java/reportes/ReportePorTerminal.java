package reportes;

import java.util.ArrayList;
import java.util.List;

import poi.Terminal;

public class ReportePorTerminal {

	public class Entrada {

		public Terminal terminal;
		public int cantResultados;

		public Entrada(Terminal terminal, int cantResultados) {
			this.terminal = terminal;
			this.cantResultados = cantResultados;
		}
	}

	List<Entrada> infoReporte = new ArrayList<Entrada>();

	public void agregarEntrada(Terminal terminal, int cantResultados) {
		infoReporte.add(new Entrada(terminal, cantResultados));
	}

	public List<Entrada> getInfoReporte() {
		return infoReporte;
	}

}
