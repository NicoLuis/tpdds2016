package procesos;

import java.util.ArrayList;

public class ConfigAccionesPorSeleccion extends ConfigAccionesExtras {
     
	private ArrayList<String> listaNombresTerminalesSeleccionadas;
	
	public ConfigAccionesPorSeleccion(ManejadorErrores manejador, ArrayList<AccionExtra> listaAcciones) {

		super(manejador, listaAcciones);

	}

	public int ejecutarProceso() {
   
		return homeTerminal.agregarATerminalesSeleccionadas(listaNombresTerminalesSeleccionadas, accionesExtrasParaUsuarios);

	}

	public void setListaNombresTerminalesSeleccionadas(ArrayList<String> listaNombresTerminalesSeleccionadas) {
		this.listaNombresTerminalesSeleccionadas = listaNombresTerminalesSeleccionadas;
	}

	
	
	
	
}
