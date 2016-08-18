package bases;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.Terminal;
import procesos.AccionExtra;

public class HomeTerminal {

	private ArrayList<Terminal> listaTerminales;

	// Singleton//////////////////////////////////////////
	static HomeTerminal instancia;

	private HomeTerminal() {
		listaTerminales = new ArrayList<Terminal>();
	}

	public static HomeTerminal GetInstancia() {

		if (instancia == null) {
			instancia = new HomeTerminal();
		}
		return instancia;
	}


	public static void reset() {
		instancia = new HomeTerminal();
	}
	/////////////////////////////////////
	public ArrayList<Terminal> getListaTerminales() {
		return listaTerminales;
	}

	public void agregarTerminal(Terminal terminal) {

		this.listaTerminales.add(terminal);
	}

	public int agregarAccionesTerminalPorComuna(int comuna, ArrayList<AccionExtra> lista) {

		List<Terminal> terminalesComuna = listaTerminales.stream().filter(t -> t.getComuna() == comuna)
				.collect(Collectors.toList());
		for (Terminal t : terminalesComuna) {
			t.setAccionesAdicionales(lista);

		}

		return terminalesComuna.size();
	}

	public int agregarAtodasTerminales(ArrayList<AccionExtra> lista) {

		for (Terminal t : listaTerminales) {

			t.setAccionesAdicionales(lista);

		}

		return listaTerminales.size();
	}

	public int agregarATerminalesSeleccionadas(ArrayList<String> listaNomTerminales, ArrayList<AccionExtra> lista) {

		for (String s : listaNomTerminales) {

			List<Terminal> terminalesComuna = listaTerminales.stream().filter(t -> t.getNombre().equals( s))
					.collect(Collectors.toList());
			terminalesComuna.get(0).setAccionesAdicionales(lista);

		}

		return listaNomTerminales.size();
	}

}
