package reportes;

import java.util.*;
import java.util.stream.Collectors;
import java.time.*;

public class ReportHandler {

	private List<Busqueda> busquedas;

	public ReportHandler(List<Busqueda> busquedas) {

		this.busquedas = busquedas;
	}

	public void guardarInformacion(Busqueda busqueda) {
		busquedas.add(busqueda);
	}

	public ReportePorFecha generarReportePorFecha() {

		ReportePorFecha reporte = new ReportePorFecha();

		DateTime fechaAnterior = busquedas.get(0).getDate();
		int cantBusquedas = 0;
		Iterator<Busqueda> iterator = busquedas.listIterator();
		while (iterator.hasNext()) {
			// Consigo el proximo elemento
			Busqueda actual = iterator.next();

			// si la fecha coincide incremento, sino, pase a otra fecha: agrego
			// la entrada y vuelvo a 0 la cantBusquedas
			if (actual.mismaFecha(fechaAnterior)) {
				cantBusquedas++;
			} else {
				reporte.agregarEntrada(fechaAnterior, cantBusquedas);
				cantBusquedas = 1;// cambie a cero por uno
				fechaAnterior = actual.getDate();
			}
		}
		// quedo una fecha sin agregar
		reporte.agregarEntrada(fechaAnterior, cantBusquedas);

		return reporte;
	}

	public ReportePorTerminal generarReportePorTerminal() {

		ReportePorTerminal reporte = new ReportePorTerminal();

		List<Busqueda> busquedasOrdenadas = ordenarBusquedas(this.busquedas);
		// ordeno busquedas mediante el nombre de la terminal

		Busqueda busquedaAnterior = busquedasOrdenadas.get(0);
		int cantResultados = 0;
		Iterator<Busqueda> iterator = busquedasOrdenadas.listIterator();
		while (iterator.hasNext()) {

			Busqueda actual = iterator.next();

			if (actual.mismaTerminal(busquedaAnterior.getTerminal())) {
				cantResultados = cantResultados + actual.getResultadoBusqueda().size();
			} else {
				reporte.agregarEntrada(busquedaAnterior.getTerminal(), cantResultados);
				cantResultados = actual.getResultadoBusqueda().size();
				busquedaAnterior = actual;
			}
		}

		reporte.agregarEntrada(busquedaAnterior.getTerminal(), cantResultados);

		return reporte;
	}

	public ReportePorResultadosParciales generarReportePorResultadoParcial(String terminal) {
		ReportePorResultadosParciales reporte = new ReportePorResultadosParciales();
		List<Busqueda> busquedasPorTerminal = this.busquedas.stream()
				.filter(busqueda -> busqueda.getTerminal().getNombre().equals(terminal)).collect(Collectors.toList());
		// Filtra de la lista todos los que tengan el nombre buscado

		iterarTodos(reporte, busquedasPorTerminal);
		return reporte;
	}

	public ReportePorResultadosParciales generarReportePorResultadoParcial() {
		ReportePorResultadosParciales reporte = new ReportePorResultadosParciales();
		List<Busqueda> busquedasOrdenada = ordenarBusquedas(this.busquedas);

		iterarTodos(reporte, busquedasOrdenada);
		return reporte;
	}

	public void iterarTodos(ReportePorResultadosParciales reporte, List<Busqueda> busquedas) {
		Iterator<Busqueda> iterator = busquedas.listIterator();
		while (iterator.hasNext()) {

			Busqueda actual = iterator.next();

			reporte.agregarEntrada(actual.getResultadoBusqueda().size());
		}
	}

	public void setBusquedas(List<Busqueda> busquedas) {
		this.busquedas = busquedas;
	}

	public List<Busqueda> ordenarBusquedas(List<Busqueda> busquedas) {
		Collections.sort(busquedas, new Comparator<Busqueda>() {
			public int compare(Busqueda one, Busqueda other) {
				return one.getTerminal().getNombre().compareTo(other.getTerminal().getNombre());
			}
		});
		return busquedas;
	}

	public List<Busqueda> getBusquedas() {
		return busquedas;
	}

}