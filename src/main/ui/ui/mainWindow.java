package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import poi.POI;
import bases.BasePOIs;

import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.ImageIcon;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class mainWindow {

	private JFrame frame;
	private BasePOIs base = new BasePOIs();
	private JList<String> listaNombresPOIs = new JList<String>();
	private POI poiAux;
	private JLabel lblPoi_1;
	private JLabel lblLatitud;
	private JLabel lblLongitud;
	private JLabel lblDireccion;
	private JLabel lblTipo;
	private JLabel lblNombrePOI2;
	private JLabel lblDistanciaPOI2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/resources/Sin título-2.png")));
		frame.setTitle("POI");
		frame.setBounds(100, 100, 565, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Butones = new JPanel();
		Butones.setToolTipText("");
		
		
		JButton btnCalcularDistancia = new JButton("Distancia");
		btnCalcularDistancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CalcularDistanciaIngresados hola = new CalcularDistanciaIngresados();
				hola.frmCalcularDistancia.setVisible(true);
			}
		});
		
		JButton btnDisponibilidad = new JButton("Disponibilidad");
		
		JPanel panel_info_distancia = new JPanel();
		panel_info_distancia.setVisible(false);
		
		JButton btnCalcularDistanciaA = new JButton("Distancia a un POI");
		btnCalcularDistanciaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_info_distancia.setVisible(true);
				calcularDistancia();
			}
		});
		GroupLayout gl_Butones = new GroupLayout(Butones);
		gl_Butones.setHorizontalGroup(
			gl_Butones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Butones.createSequentialGroup()
					.addGap(7)
					.addComponent(btnCalcularDistancia, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDisponibilidad, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCalcularDistanciaA, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addGap(26))
		);
		gl_Butones.setVerticalGroup(
			gl_Butones.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Butones.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_Butones.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCalcularDistancia, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addGroup(gl_Butones.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCalcularDistanciaA, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addComponent(btnDisponibilidad, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
					.addGap(7))
		);
		Butones.setLayout(gl_Butones);
		
		JLabel lblPoi = new JLabel("POI");
		lblPoi.setIcon(new ImageIcon(mainWindow.class.getResource("/resources/Sin título-2.png")));
		lblPoi.setBackground(Color.WHITE);
		lblPoi.setForeground(Color.RED);
		lblPoi.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 30));
		
		JPanel panel_info_poi = new JPanel();
		panel_info_poi.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblInformacionDePoi = new JLabel("Informacion de POI");
		lblInformacionDePoi.setVisible(false);
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPoi, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_info_distancia, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblInformacionDePoi, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE))
								.addComponent(panel_info_poi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(Butones, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPoi, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Butones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblInformacionDePoi)
							.addGap(11)
							.addComponent(panel_info_poi, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_info_distancia, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		lblDistanciaPOI2 = new JLabel("Distancia");
		
		lblNombrePOI2 = new JLabel("Nombre");
		GroupLayout gl_panel_info_distancia = new GroupLayout(panel_info_distancia);
		gl_panel_info_distancia.setHorizontalGroup(
			gl_panel_info_distancia.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_info_distancia.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_info_distancia.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNombrePOI2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addComponent(lblDistanciaPOI2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_info_distancia.setVerticalGroup(
			gl_panel_info_distancia.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info_distancia.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombrePOI2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDistanciaPOI2)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel_info_distancia.setLayout(gl_panel_info_distancia);
		
		
		listaNombresPOIs.setModel(new AbstractListModel<String>() {
			public int getSize() {
				return base.getListaPois().size();
			}
			public String getElementAt(int index) {
				return base.getListaPois().get(index).getNombre();
			}
		});
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_info_poi.setVisible(true);
				lblInformacionDePoi.setVisible(true);
				guardarPoi();
			}
		});
		
		JButton btnNewButton = new JButton("Deseleccionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInformacionDePoi.setVisible(false);
				panel_info_poi.setVisible(false);
				panel_info_distancia.setVisible(false);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(listaNombresPOIs, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addComponent(btnSeleccionar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(listaNombresPOIs, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSeleccionar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		lblLatitud = new JLabel("Latitud");
		
		lblLongitud = new JLabel("Longitud");
		
		lblPoi_1 = new JLabel("Nombre");
		
		lblDireccion = new JLabel("Direccion");
		
		lblTipo = new JLabel("Tipo");
		
		GroupLayout gl_panel_info_poi = new GroupLayout(panel_info_poi);
		gl_panel_info_poi.setHorizontalGroup(
			gl_panel_info_poi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info_poi.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_info_poi.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPoi_1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDireccion)
						.addComponent(lblTipo)
						.addComponent(lblLatitud, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongitud, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_panel_info_poi.setVerticalGroup(
			gl_panel_info_poi.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_info_poi.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPoi_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDireccion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTipo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLatitud)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLongitud)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		panel_info_poi.setLayout(gl_panel_info_poi);
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnPoi = new JMenu("Nuevo");
		menuBar.add(mnPoi);
		
		JMenuItem mntmNuevo = new JMenuItem("POI");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoPOI hola = new NuevoPOI(base);
				hola.frmNuevoPoi.setVisible(true);
			}
		});
		mnPoi.add(mntmNuevo);
		

		
	}
	
	
	
	void guardarPoi(){
		String nombrePOI = listaNombresPOIs.getSelectedValue();
		poiAux = base.getListaPois().stream().filter((p -> p.getNombre() == nombrePOI)).findFirst().get();
		if( poiAux.getNombre() != null ) lblPoi_1.setText("Nombre    " + poiAux.getNombre()); else lblPoi_1.setText("Nombre");
		if( poiAux.getUbicacion() != null ){
			lblLatitud.setText("Latitud    " + String.valueOf(poiAux.getUbicacion().latitude()));
			lblLongitud.setText("Longitud    " + String.valueOf(poiAux.getUbicacion().longitude()));
		}else{
			lblLatitud.setText("Latitud");
			lblLongitud.setText("Longitud");
		}
		if( poiAux.getDireccion() != null ) lblDireccion.setText("Direccion    " + poiAux.getDireccion()); else lblDireccion.setText("Direccion");
		if( poiAux.getNombre() != null ) lblPoi_1.setText("Nombre    " + poiAux.getNombre()); else lblPoi_1.setText("Nombre");

		lblTipo.setText("Tipo    " );//+ poiAux.getTipo());
	}
	
	void calcularDistancia(){
		String nombrePOI = listaNombresPOIs.getSelectedValue();
		POI poi_para_calcular = base.getListaPois().stream().filter((p -> p.getNombre() == nombrePOI)).findFirst().get();
		if( poi_para_calcular.getNombre() != null ) lblNombrePOI2.setText("Nombre    " + poi_para_calcular.getNombre()); else lblNombrePOI2.setText("Nombre");
		
		if( poi_para_calcular.getUbicacion() != null ) {
			BigDecimal distancia = new BigDecimal( poiAux.getUbicacion().distance(poi_para_calcular.getUbicacion())*1000 );
			distancia = distancia.setScale(0, RoundingMode.HALF_UP);
			
			lblDistanciaPOI2.setText("Distancia    " + String.valueOf(distancia) + " m"); 
		}else lblDistanciaPOI2.setText("Distancia");
		
	}
}
