package reportes;

import java.util.List;
import java.time.*;

import poi.POI;
import poi.Terminal;

public class Busqueda {

	private String busqueda;
	private List<POI> resultadoBusqueda;
	private LocalDateTime fechaBusqueda;
	private Long demora;
	private Terminal terminal;

	public Busqueda(String busqueda, List<POI> resultadoBusqueda, LocalDateTime fechaBusqueda, Long demora,
			Terminal terminal) {
		this.busqueda = busqueda;
		this.resultadoBusqueda = resultadoBusqueda;
		this.fechaBusqueda = fechaBusqueda;
		this.demora = demora;
		this.terminal = terminal;
	}

	public boolean mismaFecha(LocalDate fecha) {
		return fechaBusqueda.toLocalDate().equals(fecha);
	}

	public boolean mismaTerminal(Terminal terminal) {
		return this.terminal.getNombre().equals(terminal.getNombre());

	}

	public Terminal getTerminal() {
		return terminal;
	}

	public LocalDate getDate() {
		return fechaBusqueda.toLocalDate();
	}

	public List<POI> getResultadoBusqueda() {
		return resultadoBusqueda;
	}
}
