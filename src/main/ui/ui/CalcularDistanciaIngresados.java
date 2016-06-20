package ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;

import org.uqbar.geodds.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import poi.*;

public class CalcularDistanciaIngresados {

	JFrame frmCalcularDistancia;
	private JTextField texto_UbicacionX_1;
	private JTextField texto_UbicacionY_1;
	private JTextField texto_UbicacionX_2;
	private JTextField texto_UbicacionY_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalcularDistanciaIngresados window = new CalcularDistanciaIngresados();
					window.frmCalcularDistancia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalcularDistanciaIngresados() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalcularDistancia = new JFrame();
		frmCalcularDistancia.setTitle("Calcular Distancia");
		frmCalcularDistancia.setBounds(100, 100, 324, 244);
		frmCalcularDistancia.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmCalcularDistancia.getContentPane().setLayout(null);
		
		JLabel lblPoi = new JLabel("Calcular Distancia");
		lblPoi.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 30));
		lblPoi.setBounds(10, 11, 414, 38);
		frmCalcularDistancia.getContentPane().add(lblPoi);

		
		JLabel lblUbicacion = new JLabel("Ubicacion X");
		lblUbicacion.setBounds(10, 59, 85, 14);
		frmCalcularDistancia.getContentPane().add(lblUbicacion);
		
		JLabel lblUbicacionY = new JLabel("Ubicacion Y");
		lblUbicacionY.setBounds(10, 84, 69, 14);
		frmCalcularDistancia.getContentPane().add(lblUbicacionY);
		
		texto_UbicacionX_1 = new JTextField();
		texto_UbicacionX_1.setBounds(101, 60, 200, 14);
		frmCalcularDistancia.getContentPane().add(texto_UbicacionX_1);
		texto_UbicacionX_1.setColumns(10);
		
		texto_UbicacionY_1 = new JTextField();
		texto_UbicacionY_1.setBounds(101, 84, 200, 14);
		frmCalcularDistancia.getContentPane().add(texto_UbicacionY_1);
		texto_UbicacionY_1.setColumns(10);
		
		
		
		
		
		JLabel lblUbicacion2 = new JLabel("Ubicacion X");
		lblUbicacion2.setBounds(10, 122, 85, 14);
		frmCalcularDistancia.getContentPane().add(lblUbicacion2);
		
		JLabel lblUbicacionY2 = new JLabel("Ubicacion Y");
		lblUbicacionY2.setBounds(10, 147, 69, 14);
		frmCalcularDistancia.getContentPane().add(lblUbicacionY2);
		
		texto_UbicacionX_2 = new JTextField();
		texto_UbicacionX_2.setBounds(101, 122, 200, 14);
		frmCalcularDistancia.getContentPane().add(texto_UbicacionX_2);
		texto_UbicacionX_2.setColumns(10);
		
		texto_UbicacionY_2 = new JTextField();
		texto_UbicacionY_2.setBounds(101, 147, 200, 14);
		frmCalcularDistancia.getContentPane().add(texto_UbicacionY_2);
		texto_UbicacionY_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(183, 270, 118, 23);
		frmCalcularDistancia.getContentPane().add(lblNewLabel);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(String.valueOf( distanciaPoisIngresados() ) + " m");
			}
		});
		btnCalcular.setBounds(10, 172, 163, 23);
		frmCalcularDistancia.getContentPane().add(btnCalcular);
		
	}
	
	BigDecimal distanciaPoisIngresados(){
		POI poi1 = new POI();
		double ubX_1 = Double.parseDouble(texto_UbicacionX_1.getText());
		double ubY_1 = Double.parseDouble(texto_UbicacionY_1.getText());
		poi1.setUbicacion( new Point(ubX_1, ubY_1) );

		POI poi2 = new POI();
		double ubX_2 = Double.parseDouble(texto_UbicacionX_2.getText());
		double ubY_2 = Double.parseDouble(texto_UbicacionY_2.getText());
		poi2.setUbicacion( new Point(ubX_2, ubY_2) );
		
		BigDecimal bigDecimal = new BigDecimal( poi1.getUbicacion().distance(poi2.getUbicacion())*1000 );
		return bigDecimal.setScale(0, RoundingMode.HALF_UP);
	}
	
	
	
}
