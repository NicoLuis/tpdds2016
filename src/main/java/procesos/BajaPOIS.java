package procesos;

import java.time.LocalDate;
import java.util.List;
import bases.HomePois;
import sistExternos.ServicioREST;
import sistExternos.ServicioREST.Baja;

public class BajaPOIS extends Proceso {
	ServicioREST servicioDeBajas;
	List<Baja> listaBajaPOIS;
	
	public BajaPOIS(ManejadorErrores manejador, ServicioREST servicio){
		super(manejador);
		this.servicioDeBajas = servicio;
		listaBajaPOIS = servicioDeBajas.enviarBajas();
	}

	public int ejecutarProceso() throws ExcepcionProceso {//chequear si tiene que lanza esa excepcion

		int cont = 0;
		
		for (Baja baja : listaBajaPOIS) {
			if (baja.fechaDeBaja.isBefore(LocalDate.now())) {
				
				cont++;
								
				HomePois.GetInstancia().removerPoiPorBusqueda(baja.texto);

			}

		}
		
		return cont;
	}
	
	@Override
	public TipoProceso getTipoProceso()
	{
		return TipoProceso.BAJA_POIS;
	}

}
