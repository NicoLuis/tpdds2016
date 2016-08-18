package procesos;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

import bases.HomePois;
import poi.LocalComercial;
import poi.POI;

public class ActualizacionLocales extends Proceso {

	String path;

	public ActualizacionLocales(ManejadorErrores manejador, String path) {
		super(manejador);
		this.path = path;
	}

	@Override
	public int ejecutarProceso() throws ExcepcionProceso {

		int cont = 0;
		try {

			FileReader fileReader = new FileReader(path);
			BufferedReader reader = new BufferedReader(fileReader);

			// Obtengo la lista de locales
			List<POI> locales = HomePois.GetInstancia().getLocales();

			String linea = reader.readLine();

			while (linea != null) {

				String[] array = linea.split(";");

				List<POI> local = locales.stream().filter(l -> l.getNombre().equals(array[0]))
						.collect(Collectors.toList());

				if (!local.isEmpty()) {
					
					// Tomo el primer elemento de la lista de locales y le
					// setteo la nueva lista de palabras clave
					((LocalComercial) local.get(0)).setPalabrasClave(new ArrayList<String>(Arrays.asList(array[1].split(" "))));

					// Afecte un elemento, incremento el contador
					cont++;
				}else{
					LocalComercial nuevoPoi = new LocalComercial();
					nuevoPoi.setNombre(array[0]);
					nuevoPoi.setPalabrasClave(new ArrayList<String>(Arrays.asList(array[1].split(" "))));
					HomePois.GetInstancia().agregarPoi(nuevoPoi);
				}
				
				linea = reader.readLine();
			}

			reader.close();
			fileReader.close();

			return cont;
		} catch (FileNotFoundException fnf) {
			throw new ExcepcionProceso();
		} catch (IOException ioe) {
			throw new ExcepcionProceso();
		}
	}

	@Override
	public TipoProceso getTipoProceso() {
		return TipoProceso.ACTUALIZACION_LOCALES;
	}

}
