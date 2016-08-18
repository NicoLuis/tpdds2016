package procesos;

import java.time.LocalDateTime;

public class SinAccion implements ManejadorErrores {

	@Override
	public void manejarExcepcion(AdminProcesos admin, Proceso proceso, LocalDateTime horaInicioEjecucion) {
		//no hace nada
		admin.notificarFinEjecucion(proceso, new InformeProceso(proceso.getTipoProceso(),false,-1,horaInicioEjecucion));
	}

}
