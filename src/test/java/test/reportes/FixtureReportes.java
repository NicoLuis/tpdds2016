package test.reportes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.uqbar.geodds.Polygon;

import poi.CGP;
import poi.POI;
import poi.ParadaColectivo;
import poi.Servicio;
import poi.Terminal;
import reportes.Busqueda;
import reportes.ReportHandler;
import reportes.ReportePorFecha;
import reportes.ReportePorResultadosParciales;
import reportes.ReportePorTerminal;

public class FixtureReportes {
	public ReportePorFecha reporteFecha;
	public ReportePorTerminal reporteTerminal;
	public ReportePorResultadosParciales reportePorResultadosParciales1;
	public ReportePorResultadosParciales reportePorResultadosParciales2;

	// Inicializacion de POI
	public Polygon area = new Polygon();
	public Servicio servicio = new Servicio("Banco");
	public CGP cgp = new CGP(area, servicio, "Gestion de Bancos");

	public ParadaColectivo colectivo1 = new ParadaColectivo("114");

	public ParadaColectivo colectivo2 = new ParadaColectivo("47");

	public ParadaColectivo colectivo3 = new ParadaColectivo("7");

	@Before
	public void init() {

		// Inicializacion Listas de POI
		List<POI> listaPoi1 = new ArrayList<POI>();
		List<POI> listaPoi2 = new ArrayList<POI>();
		List<POI> listaPoi3 = new ArrayList<POI>();
		List<POI> listaPoi4 = new ArrayList<POI>();

		listaPoi1.add(cgp);
		listaPoi1.add(colectivo1);
		listaPoi1.add(colectivo2);
		listaPoi1.add(colectivo3);
		listaPoi2.add(colectivo1);
		listaPoi2.add(colectivo2);
		listaPoi2.add(colectivo3);
		listaPoi3.add(colectivo2);
		listaPoi3.add(colectivo3);
		listaPoi4.add(colectivo3);

		// listaPoi1 tiene 4 Busqueda1
		// listaPoi2 tiene 3 Busqueda2 y Busqueda5
		// listaPoi3 tiene 2 Busqueda3
		// listaPoi4 tiene 1 Busqueda4

		// Term1 tiene 4+2=6
		// Term2 tiene 3+1+3=7

		// inicializacion de Busquedas
		Busqueda busqueda1 = new Busqueda("", listaPoi1, LocalDateTime.now(), 178823411l, new Terminal("term1"));
		Busqueda busqueda2 = new Busqueda("", listaPoi2, LocalDateTime.now(), 178823411l, new Terminal("term2"));
		Busqueda busqueda3 = new Busqueda("", listaPoi3, LocalDateTime.now(), 178823411l, new Terminal("term1"));
		Busqueda busqueda4 = new Busqueda("", listaPoi4,
				LocalDateTime.of(LocalDate.of(2016, 4, 23), LocalTime.of(23, 32)), 178823411l, new Terminal("term2"));
		Busqueda busqueda5 = new Busqueda("", listaPoi2,
				LocalDateTime.of(LocalDate.of(2016, 4, 23), LocalTime.of(23, 32)), 178823411l, new Terminal("term2"));
		ArrayList<Busqueda> lista = new ArrayList<Busqueda>();
		ReportHandler manejadorReportes = new ReportHandler(lista);

		manejadorReportes.guardarInformacion(busqueda1);
		manejadorReportes.guardarInformacion(busqueda2);
		manejadorReportes.guardarInformacion(busqueda3);
		manejadorReportes.guardarInformacion(busqueda4);
		manejadorReportes.guardarInformacion(busqueda5);

		reporteFecha = manejadorReportes.generarReportePorFecha();

		reporteTerminal = manejadorReportes.generarReportePorTerminal();

		reportePorResultadosParciales1 = manejadorReportes.generarReportePorResultadoParcial("term1");
		reportePorResultadosParciales2 = manejadorReportes.generarReportePorResultadoParcial();

	}

}
