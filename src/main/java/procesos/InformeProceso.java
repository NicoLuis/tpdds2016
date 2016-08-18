package procesos;

import java.time.LocalDateTime;

public class InformeProceso{

	public boolean ejecucionExitosa;
	public int cantTerminalesAfectadas;
	public LocalDateTime fechaEjecucion;
	public TipoProceso tipoProceso;
	
	public InformeProceso(TipoProceso tipoProceso, boolean ejecucionExitosa, int cantTerminalesAfectadas, LocalDateTime fechaEjecucion) {
		this.tipoProceso = tipoProceso;
		this.ejecucionExitosa = ejecucionExitosa;
		this.cantTerminalesAfectadas = cantTerminalesAfectadas;
		this.fechaEjecucion = fechaEjecucion;
	}
	
}
