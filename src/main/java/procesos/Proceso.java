package procesos;

import java.time.LocalDateTime;

public abstract class Proceso {
	
	LocalDateTime horaEjecucion;
	ManejadorErrores manejador;
	
	protected Proceso(ManejadorErrores manejador){
		this.manejador = manejador;
	}
	
	public void ejecutar(AdminProcesos admin){
		
		if(admin.puedeEjecutar()){

			if(horaEjecucion.isAfter(LocalDateTime.now()) )
			{
				admin.bloquear();
				LocalDateTime horaInicioEjecucion = LocalDateTime.now();
				
				try{
					int cantElementosAfectados = ejecutarProceso();
					admin.notificarFinEjecucion(this, new InformeProceso(this.getTipoProceso(),true,cantElementosAfectados,horaInicioEjecucion));
				}catch(ExcepcionProceso ex){
					manejador.manejarExcepcion(admin, this, horaInicioEjecucion);
				}
				
			}
		}
	}
	
	public abstract int ejecutarProceso() throws ExcepcionProceso; //Devuelve cant elementos afectados
	public abstract TipoProceso getTipoProceso();
}
