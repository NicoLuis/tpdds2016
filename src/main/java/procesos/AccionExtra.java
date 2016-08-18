package procesos;

import java.util.List;

import poi.POI;
import poi.Terminal;

public interface AccionExtra {
	
	
	public void ejecutar(String busqueda,List<POI> resultadoBusqueda,Long tiempo,Terminal terminal );
	
	
}
