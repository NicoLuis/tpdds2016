package main.java.poi;
import java.time.LocalDateTime;
public class RangoDeAtencion {
	//CONSTRUCTOR
	
		public RangoDeAtencion(double unHorarioDeApertura, double unHorarioDeCierre, int unDiaDeInicioDeAtencion, int unDiaDeFinDeAtencion){
			this.setHorarioDeApertura(unHorarioDeApertura);
			this.setHorarioDeCierre(unHorarioDeCierre);
			this.setDiaDeInicioDeAtencion(unDiaDeInicioDeAtencion);
			this.setDiaDeFinDeAtencion(unDiaDeFinDeAtencion);
		}
		
		//ATRIBUTOS
			
		double			horarioDeApertura;	//HH.mm
		double			horarioDeCierre;	//HH.mm
		int				diaDeInicioDeAtencion;	//1 lunes - 6 domingo
		int				diaDeFinDeAtencion;
		
		//GETERS Y SETERS
		
		public double getHorarioDeApertura() {
			return horarioDeApertura;
		}

		public void setHorarioDeApertura(double horarioDeApertura) {
			this.horarioDeApertura = horarioDeApertura;
		}

		public double getHorarioDeCierre() {
			return horarioDeCierre;
		}

		public void setHorarioDeCierre(double horarioDeCierre) {
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
			
		
		
		//METODOS
		
		public boolean disponible(LocalDateTime unTiempo){
			return this.horarioDisponible(unTiempo) && this.diaDisponible(unTiempo);
		}
		
		public boolean horarioDisponible(LocalDateTime unTiempo){
			double	tiempoEnHorasMinutos = this.pasarAHorasMinutos(unTiempo);
			double  horarioDeApertura = this.getHorarioDeApertura();
			double  horarioDeCierre = this.getHorarioDeCierre();
			return	(horarioDeApertura <= tiempoEnHorasMinutos)
					&& (tiempoEnHorasMinutos <= horarioDeCierre);
		}
		public boolean diaDisponible(LocalDateTime unTiempo){
			int unDiaDeLaSemana = unTiempo.getDayOfWeek().getValue();
			int diaDeInicioDeAtencion = this.getDiaDeInicioDeAtencion() ;
			int diaDeFinDeAtencion = this.getDiaDeFinDeAtencion();
			return (diaDeInicioDeAtencion <= unDiaDeLaSemana) 
					&& (unDiaDeLaSemana <= diaDeFinDeAtencion);
		}
		
		public double pasarAHorasMinutos(LocalDateTime unTiempo){
			double horas = unTiempo.getHour();
			double minutos = unTiempo.getMinute();
			return horas + (minutos/100);
		}
		
}
