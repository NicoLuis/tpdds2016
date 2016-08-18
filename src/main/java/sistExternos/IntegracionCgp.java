package sistExternos;

import java.time.LocalTime;
import java.util.ArrayList;

import org.uqbar.geodds.Polygon;

import poi.CGP;
import poi.Direccion;
import poi.Franja;
import poi.POI;
import poi.Servicio;

public class IntegracionCgp implements InterfazExterna {

	static IntegracionCgp instancia;
	private ServicioExternoCGP servicioExterno ;

	private IntegracionCgp() {
	}

	public static IntegracionCgp GetInstance() {

		if (instancia == null) {
			instancia = new IntegracionCgp();
		}
		return instancia;
	}

	public ArrayList<CentroDTO> buscarEnSistExterno(String busqueda) {
		ArrayList<CentroDTO> listaDTO = new ArrayList<CentroDTO>();
	   listaDTO	= servicioExterno.buscar(busqueda);
		return listaDTO;
	}
    
	
	//Adapter 
	public POI adaptarCGP(CentroDTO centro) {
		CGP nuevoCGP;

		nuevoCGP = new CGP(new Polygon(), "nuevo");
		for (ServicioDTO s : centro.servicios) {

			Servicio servicio = new Servicio(s.getNombre());
			for (RangoServicioDTO rango : s.getRango()) {
				Franja franja = new Franja(LocalTime.of(rango.horarioDesde, rango.minutoDesde),
						LocalTime.of(rango.horarioHasta, rango.minutoHasta), rango.dia, rango.dia);

				servicio.agregarFranja(franja);

				nuevoCGP.addServicio(servicio);
			}

		}

		nuevoCGP.setNombreDirector(centro.director);
		nuevoCGP.setTelefono(centro.telefono);

		String domicilio[] = centro.domicilio.split("\\s+");
		String calle = domicilio[0];
		String stringNum = domicilio[1];
		int numero = Integer.parseInt(stringNum);

		Direccion direccion = new Direccion(calle, numero, -1, '0');
		nuevoCGP.setDireccion(direccion);
		return nuevoCGP;
	}

	public void setServicioExterno(ServicioExternoCGP servicioExterno) {
		this.servicioExterno = servicioExterno;
	}

	public ArrayList<POI> buscar(String busqueda) {
		
		ArrayList<POI> listaCGP = new ArrayList<POI>();
		ArrayList<CentroDTO> listaDTO = buscarEnSistExterno(busqueda);
		for (CentroDTO centro : listaDTO) {
			listaCGP.add(adaptarCGP(centro));

		}
		return listaCGP;
	}
}