package procesos;

import java.util.ArrayList;

public class ConfigAccionesExtraParaTodos extends ConfigAccionesExtras {

	public ConfigAccionesExtraParaTodos(ManejadorErrores manejador, ArrayList<AccionExtra> listaAcciones) {

		super(manejador, listaAcciones);

	}

	public int ejecutarProceso() {

		return homeTerminal.agregarAtodasTerminales(accionesExtrasParaUsuarios);

	}

}
