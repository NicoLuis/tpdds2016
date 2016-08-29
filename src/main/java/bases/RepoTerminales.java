package bases;


import java.util.ArrayList;


import poi.Terminal;


public class RepoTerminales {
	private ArrayList<Terminal> listaTerminales;
	private boolean usuarioActualEsAdmin;

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
	
	public boolean getBooleanAdmin(){
		return usuarioActualEsAdmin;
	}
	public void setBooleanAdmin(boolean usuarioActualEsAdmin){
		this.usuarioActualEsAdmin = usuarioActualEsAdmin;
	}
	//
	public void crearTerminales(){
		crearUsuario("admin", "admin", true);
		crearUsuario("user", "user", false);
		crearUsuario("nLuis", "qwe123", true);
		crearUsuario("jperez", "1234juan", false);
	}
	
	
	public void crearUsuario(String nombre, String pass, boolean admin){
		Terminal abc = new Terminal();
		abc.setUsuario(nombre);
		abc.setpass(pass);
		abc.setAdmin(admin);
		addTerminal(abc);
	}
	
	
}
