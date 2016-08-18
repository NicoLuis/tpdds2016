package reportes;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import poi.POI;

public class MailHandler {
		
	private long tiempoMax;
	private String mailAdmin;
	private String mailEnvio;
	
	private static MailHandler Instancia;
	public static MailHandler getInstancia(){
		if(Instancia == null)Instancia = new MailHandler(120, "", "");
		
		return Instancia;
	}
	
	private MailHandler(long tiempoMax, String mailAdmin, String mailEnvio){
		this.tiempoMax = tiempoMax;
		this.mailAdmin = mailAdmin;
		this.mailEnvio = mailEnvio;
	}
	
	public void notificarError(){
		//Aca deberia enviar el mail notificando que hubo un fallo en los procesos
	}
	
	public void enviarMail(String busqueda, List<POI> resultadoBusqueda){
		
		//setteo de parametros
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		
		//Envio de email
		try{
			MimeMessage mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(mailEnvio));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(mailAdmin));
			mensaje.setSubject("Notificacion tardanza busqueda");
			
			mensaje.setText( formatearTexto(busqueda, resultadoBusqueda) );
			
			Transport.send(mensaje);
		}catch(MessagingException excepcion){
			excepcion.printStackTrace();
		}

	}
	
	private String formatearTexto(String busqueda, List<POI> resultadoBusqueda){
		
		String textoFormateado = "Detalle:\r\n";
		
		textoFormateado += "Busqueda: " + busqueda + "\r\n";
		
		Integer i = 1;
		
		for(POI poi : resultadoBusqueda){
			
			textoFormateado += "Resultado " + i.toString() + ":" + poi.getNombre() + "\r\n";
		}
		
		return textoFormateado;
	}
	
	public long getTiempoMax() {
		return tiempoMax;
	}
	public void setTimeMax(long tiempoMax)
	{
		this.tiempoMax = tiempoMax;
	}

	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
	}

	public void setMailEnvio(String mailEnvio) {
		this.mailEnvio = mailEnvio;
	}
}
