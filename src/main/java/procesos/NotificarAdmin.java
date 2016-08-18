package procesos;

import reportes.MailHandler;
import java.time.LocalDateTime;

public class NotificarAdmin implements ManejadorErrores {

	@Override
	public void manejarExcepcion(AdminProcesos admin, Proceso proceso, LocalDateTime horaInicioEjecucion) {
		
		MailHandler.getInstancia().notificarError();
		
		admin.notificarFinEjecucion(proceso, new InformeProceso(proceso.getTipoProceso(),false,-1,horaInicioEjecucion));
	}

}
