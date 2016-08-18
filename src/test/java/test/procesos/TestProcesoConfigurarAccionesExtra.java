package test.procesos;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bases.HomeTerminal;
import poi.Terminal;
import procesos.AccionExtra;
import procesos.ConfigAccionesExtraParaTodos;
import procesos.ConfigAccionesPorComuna;
import procesos.ConfigAccionesPorSeleccion;
import procesos.IniciarMail;
import procesos.IniciarReporte;
import procesos.ManejadorErrores;
import procesos.NotificarAdmin;

public class TestProcesoConfigurarAccionesExtra {

	 HomeTerminal homeTerminal;
	 ArrayList<AccionExtra> acciones;
	 IniciarMail iniciarMail;
	 IniciarReporte iniciarReporte;
	 ConfigAccionesPorComuna configAccionesExtrasComuna;
	 ConfigAccionesExtraParaTodos configAccionesExtraTodos;
	 ConfigAccionesPorSeleccion configAccionesExtraSeleccion;
	 Terminal terminal1;
	 Terminal terminal2;
	 Terminal terminal3;
	 Terminal terminal4;
	 ManejadorErrores eManejador;

	@Before

	public  void init() {
		homeTerminal = HomeTerminal.GetInstancia();
		terminal1 = new Terminal(21, 34, "Terminal1", 2);
		terminal2 = new Terminal(21, 34, "Terminal2", 2);
		terminal3 = new Terminal(21, 34, "Terminal3", 4);
		terminal4 = new Terminal(21, 34, "Terminal4", 4);
		iniciarMail = new IniciarMail();
		iniciarReporte = new IniciarReporte();
		acciones = new ArrayList<AccionExtra>();
		eManejador = new NotificarAdmin();
	}
	
	@After
	public void reset() {
		HomeTerminal.reset();
	}

	@Test

	public void seDebenAgregarAlasTerminalesDelaComuna2TodasLasAcciones() {

		acciones.add(iniciarReporte);
		acciones.add(iniciarMail);

		configAccionesExtrasComuna = new ConfigAccionesPorComuna(eManejador, acciones, 2);
		configAccionesExtrasComuna.ejecutarProceso();

		int cantidadDeAcciones = terminal1.getAccionesAdicionales().size();

		Assert.assertEquals(2, cantidadDeAcciones);
	}

	@Test

	public void seDebenAgregarATodasLasTerminalesUnaAccion() {

		acciones.add(iniciarReporte);
		configAccionesExtraTodos = new ConfigAccionesExtraParaTodos(eManejador, acciones);

		configAccionesExtraTodos.ejecutarProceso();

		int cantidadDeAccionesT1 = terminal1.getAccionesAdicionales().size();
		int cantidadDeAccionesT2 = terminal2.getAccionesAdicionales().size();
		int cantidadDeAccionesT3 = terminal3.getAccionesAdicionales().size();
		int cantidadDeAccionesT4 = terminal4.getAccionesAdicionales().size();

		Assert.assertEquals(4,cantidadDeAccionesT1 + cantidadDeAccionesT2 + cantidadDeAccionesT3 + cantidadDeAccionesT4);

	}

	@Test
	
	public void seDebeAgregarAccionesALasTerminalesSeleccionadas(){
		
		acciones.add(iniciarReporte);
		acciones.add(iniciarMail);
		
		configAccionesExtraSeleccion = new ConfigAccionesPorSeleccion(eManejador, acciones);
		
		ArrayList<String> nombresDeTerminalesASeleccionar = new ArrayList<String>();
		nombresDeTerminalesASeleccionar.add("Terminal1");
		nombresDeTerminalesASeleccionar.add("Terminal4");
		configAccionesExtraSeleccion.setListaNombresTerminalesSeleccionadas(nombresDeTerminalesASeleccionar);
		
		configAccionesExtraSeleccion.ejecutarProceso();

	    int cantidadDeAccionesT1 =  terminal1.getAccionesAdicionales().size();
	    int cantidadDeAccionesT4 =  terminal4.getAccionesAdicionales().size();
		
		Assert.assertEquals(2, cantidadDeAccionesT1);
		Assert.assertEquals(2, cantidadDeAccionesT4);
	}
	
	
	
	
}
