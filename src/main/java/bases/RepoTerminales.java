package bases;


import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;


import poi.Terminal;


public class RepoTerminales {
	private ArrayList<Terminal> listaTerminales;

	// Singleton//////////////////////////////////////////
	static RepoTerminales instancia;

	private RepoTerminales() {
		listaTerminales = new ArrayList<Terminal>();
	}

	public static RepoTerminales GetInstancia() {

		if (instancia == null) {
			instancia = new RepoTerminales();
			RepoTerminales.GetInstancia().crearTerminales();
		}
		return instancia;
	}


	public static void reset() {
		instancia = new RepoTerminales();
	}
	
	public ArrayList<Terminal> getListaTerminales(){
		return listaTerminales;
	}
	
	public void addTerminal(Terminal term){
		listaTerminales.add(term);
	}
	//
	public void crearTerminales(){
		Terminal abc = new Terminal("ABC");
		abc.setUsuario("admin");
		abc.setpass("admin");
		addTerminal(abc);
		Terminal user = new Terminal("no admin");
		user.setUsuario("user");
		user.setpass("user");
		addTerminal(user);
		
	}
	
}
