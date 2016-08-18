package procesos;

import java.util.TimerTask;

public class TaskEjecucionProcesos extends TimerTask{
	
	private AdminProcesos admin;

	public TaskEjecucionProcesos(AdminProcesos admin){
		this.admin = admin;
	}
	
	@Override
	public void run(){
		
		for(Proceso p : admin.getColaProcesos()){
			if(admin.puedeEjecutar())
			p.ejecutar(admin);
		}
	}
}
