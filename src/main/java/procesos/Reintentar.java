package procesos;

import java.time.LocalDateTime;

public class Reintentar implements ManejadorErrores {
	
	private int cantReintentos;

	public Reintentar(int cantReintentos){
		this.cantReintentos = cantReintentos;
	}
	
	@Override
	public void manejarExcepcion(AdminProcesos admin, Proceso proceso, LocalDateTime horaInicioEjecucion) {
	
		if(cantReintentos > 0){
			cantReintentos--;
			proceso.ejecutar(admin);
		}else{
			new NotificarAdmin().manejarExcepcion(admin, proceso, horaInicioEjecucion);
		}
	}

}
