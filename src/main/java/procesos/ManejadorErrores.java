package procesos;

import java.time.LocalDateTime;

public interface ManejadorErrores {

	public void manejarExcepcion(AdminProcesos admin, Proceso proceso, LocalDateTime horaInicioEjecucion);
}
