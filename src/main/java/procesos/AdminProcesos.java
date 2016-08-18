package procesos;

import java.util.*;
import java.time.*;

public class AdminProcesos {
	
	List<InformeProceso> informes;
	
	boolean ejecutando; //Para que ejecute uno a la vez.
	Stack<Proceso> colaProcesos;
	Timer timer;
	TaskEjecucionProcesos task;
	
	LocalTime horaInicio = LocalTime.of(0, 0);
	LocalTime horaFin = LocalTime.of(8,0);
	
	public AdminProcesos()
	{
		colaProcesos = new Stack<Proceso>();
		informes = new ArrayList<InformeProceso>();

		task = new TaskEjecucionProcesos(this);
		
		timer = new Timer();
		timer.schedule(task, 1000);
		
		ejecutando = false;
	}
	
	public void notificarFinEjecucion(Proceso proceso, InformeProceso informe)
	{
		ejecutando = false;
		
		colaProcesos.remove(proceso);
		
		informes.add(informe); 
	}
	
	public void bloquear()
	{
		ejecutando = true;
	}
	
	public boolean puedeEjecutar()
	{
		return !this.ejecutando
				&& LocalTime.now().isAfter(horaInicio)
				&& LocalTime.now().isBefore(horaFin);
	}
	
	public void agregarProceso(Proceso proceso){
		colaProcesos.push(proceso);
	}
	
	public Stack<Proceso> getColaProcesos(){
		return this.colaProcesos;
	}
}
