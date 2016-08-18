package procesos;

import java.util.ArrayList;

import bases.HomeTerminal;

public abstract class ConfigAccionesExtras extends Proceso {

	protected ArrayList<AccionExtra> accionesExtrasParaUsuarios;
	protected HomeTerminal homeTerminal;

	protected ConfigAccionesExtras(ManejadorErrores manejador, ArrayList<AccionExtra> listaAcciones) {
		super(manejador);
		accionesExtrasParaUsuarios = listaAcciones;
		homeTerminal = HomeTerminal.GetInstancia();

	}

	@Override
	public abstract int ejecutarProceso() throws ExcepcionProceso;
	
	public void setAccionesExtrasParaUsuarios(ArrayList<AccionExtra> accionesExtrasParaUsuarios) {
		this.accionesExtrasParaUsuarios = accionesExtrasParaUsuarios;
	}

	@Override
	public TipoProceso getTipoProceso(){
		return TipoProceso.AGREGAR_ACCIONES;
	}
}
