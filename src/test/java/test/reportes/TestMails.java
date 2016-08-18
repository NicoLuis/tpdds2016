package test.reportes;

import org.junit.*;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.mockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import java.util.ArrayList;
import java.util.List;

import poi.*;
import procesos.IniciarMail;
import reportes.*;

public class TestMails {

	MailHandler mockMail;;
	IniciarMail iniciadorMail;
	List<POI> listPoi;
	
	@Before
	public void init() {
        
		mockMail = mock(MailHandler.class);
		iniciadorMail = new IniciarMail();
		mockMail.setTimeMax(234l);
		listPoi = new ArrayList<POI>(); 

	}

	@Test
	
	public void siElTiempoEsMenorNoDebeLLamarMailHandler(){
		
		iniciadorMail.ejecutar("" ,listPoi, 23l, new Terminal(""));
		
		//verify(mockMail).enviarMail("", new ArrayList<POI>());
		verify(mockMail,times(0)).enviarMail("", listPoi);	
		
	}

	
@Test
	
	public void siElTiempoEsMayorDebeLLamarMailHandler(){
		
		iniciadorMail.ejecutar("" ,listPoi, 0l, new Terminal(""));
		
		//verify(mockMail).enviarMail("", new ArrayList<POI>());
		verify(mockMail).getInstancia().enviarMail("", listPoi);	
		
	}
	
	
	

}
