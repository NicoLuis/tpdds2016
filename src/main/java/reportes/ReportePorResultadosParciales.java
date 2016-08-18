package reportes;

import java.util.ArrayList;
import java.util.List;

public class ReportePorResultadosParciales {
	
	public class Entrada {
		public int cantResultados;

		public Entrada(int cantResultados) {
			this.cantResultados = cantResultados;
	}
}

List<Entrada> infoReporte = new ArrayList<Entrada>();

public void agregarEntrada(int cantResultados) {
	infoReporte.add(new Entrada(cantResultados));
}

public List<Entrada> getInfoReporte() {
	return infoReporte;
}
}
