package procesos;

import java.util.ArrayList;

public class ConfigAccionesPorComuna extends ConfigAccionesExtras {

	private int comuna;

	public ConfigAccionesPorComuna(ManejadorErrores manejador, ArrayList<AccionExtra> listaAccciones, int comuna) {

		super(manejador, listaAccciones);
		this.comuna = comuna;

	}

	public int ejecutarProceso() {

		return homeTerminal.agregarAccionesTerminalPorComuna(comuna, accionesExtrasParaUsuarios);

	}

}
