package procesos;

import java.util.List;

import poi.POI;
import poi.Terminal;
import reportes.MailHandler;

public class IniciarMail implements AccionExtra {

	public void ejecutar(String busqueda, List<POI> resultadoBusqueda, Long tiempo, Terminal terminal) {

		if (tiempo > MailHandler.getInstancia().getTiempoMax()) {
			MailHandler.getInstancia().enviarMail(busqueda, resultadoBusqueda);
		}

	}

}
