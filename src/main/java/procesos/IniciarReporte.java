package procesos;

import java.time.LocalDateTime;
import java.util.List;

import poi.POI;
import poi.Terminal;
import reportes.Busqueda;
import reportes.ReportHandler;

public class IniciarReporte implements AccionExtra {

	private ReportHandler gestionadorReportes;

	public void ejecutar(String busqueda, List<POI> resultadoBusqueda, Long tiempo, Terminal terminal) {

		gestionadorReportes
				.guardarInformacion(new Busqueda(busqueda, resultadoBusqueda, LocalDateTime.now(), tiempo, terminal));

	}

	public void setGestionadorReportes(ReportHandler handler) {
		this.gestionadorReportes = handler;
	}

}
