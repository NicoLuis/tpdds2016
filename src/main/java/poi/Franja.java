package poi;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Franja {
	//CONSTRUCTOR
	
		public Franja(LocalTime unHorarioDeApertura, LocalTime unHorarioDeCierre, int unDiaDeInicioDeAtencion, int unDiaDeFinDeAtencion){
			this.setHorarioDeApertura(unHorarioDeApertura);
			this.setHorarioDeCierre(unHorarioDeCierre);
			this.setDiaDeInicioDeAtencion(unDiaDeInicioDeAtencion);
			this.setDiaDeFinDeAtencion(unDiaDeFinDeAtencion);
		}
		public Franja(int unHorarioDeApertura, int unHorarioDeCierre, int unDiaDeInicioDeAtencion, int unDiaDeFinDeAtencion){

			LocalTime horaAp = LocalTime.now().withHour(unHorarioDeApertura);
			LocalTime horaCierre = LocalTime.now().withHour(unHorarioDeCierre);
			
			this.setHorarioDeApertura(horaAp);
			this.setHorarioDeCierre(horaCierre);
			this.setDiaDeInicioDeAtencion(unDiaDeInicioDeAtencion);
			this.setDiaDeFinDeAtencion(unDiaDeFinDeAtencion);
		}
		public Franja(int unHorarioDeApertura, int minutoApe, int unHorarioDeCierre, int minutoCierre, int unDiaDeInicioDeAtencion, int unDiaDeFinDeAtencion){

			LocalTime horaAp = LocalTime.now().withHour(unHorarioDeApertura).withMinute(minutoApe);
			LocalTime horaCierre = LocalTime.now().withHour(unHorarioDeCierre).withMinute(minutoCierre);
			
			this.setHorarioDeApertura(horaAp);
			this.setHorarioDeCierre(horaCierre);
			this.setDiaDeInicioDeAtencion(unDiaDeInicioDeAtencion);
			this.setDiaDeFinDeAtencion(unDiaDeFinDeAtencion);
		}
		
		//ATRIBUTOS
			
		LocalTime		horarioDeApertura;
		LocalTime		horarioDeCierre;
		int				diaDeInicioDeAtencion;	//1 lunes - 7 domingo
		int				diaDeFinDeAtencion;
		
		//METODOS
		
		public boolean estaDisponible(LocalDateTime fechaYHora){
			return this.horarioDisponible(fechaYHora) && this.diaDisponible(fechaYHora);
		}
		
		public boolean estaDisponible(){
			return this.horarioDisponible(LocalDateTime.now()) && this.diaDisponible(LocalDateTime.now());
		}
				
		public boolean horarioDisponible(LocalDateTime fechaYHora){	
			return fechaYHora.toLocalTime().isAfter(horarioDeApertura) 
				&& fechaYHora.toLocalTime().isBefore(horarioDeCierre);
		}
		public boolean diaDisponible(LocalDateTime fechaYHora){
			int dia = fechaYHora.getDayOfWeek().getValue();
			return diaDeInicioDeAtencion <= dia
					&& dia <= diaDeFinDeAtencion;
		}
	
		
		//GETTERS Y SETTERS
		
		public LocalTime getHorarioDeApertura() {
			return horarioDeApertura;
		}

		public void setHorarioDeApertura(LocalTime horarioDeApertura) {
			this.horarioDeApertura = horarioDeApertura;
		}

		public LocalTime getHorarioDeCierre() {
			return horarioDeCierre;
		}

		public void setHorarioDeCierre(LocalTime horarioDeCierre) {
			this.horarioDeCierre = horarioDeCierre;
		}

		public int getDiaDeInicioDeAtencion() {
			return diaDeInicioDeAtencion;
		}

		public void setDiaDeInicioDeAtencion(int diaDeIncioDeAtencion) {
			this.diaDeInicioDeAtencion = diaDeIncioDeAtencion;
		}

		public int getDiaDeFinDeAtencion() {
			return diaDeFinDeAtencion;
		}

		public void setDiaDeFinDeAtencion(int diaDeFinDeAtencion) {
			this.diaDeFinDeAtencion = diaDeFinDeAtencion;
		}
		
}
