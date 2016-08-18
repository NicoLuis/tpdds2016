package sistExternos;

import java.util.ArrayList;

public class ServicioDTO {

	private String nombre;
	private ArrayList<RangoServicioDTO> rango;

	public String getNombre() {
		return nombre;
	}

	public ArrayList<RangoServicioDTO> getRango() {
		return rango;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRango(ArrayList<RangoServicioDTO> rango) {
		this.rango = rango;
	}

}
