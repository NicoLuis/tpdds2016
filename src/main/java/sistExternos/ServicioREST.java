package sistExternos;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicioREST {

	private ArrayList<Baja> listaDeBajas = new ArrayList<Baja>();

	public class Baja {

		public LocalDate fechaDeBaja;
		public String texto;

		public Baja(String textoBusqeuda, LocalDate fecha) {

			this.fechaDeBaja = fecha;
			this.texto = textoBusqeuda;

		}

	}

	public ArrayList<Baja> enviarBajas() {

		return this.listaDeBajas;
	}

}
