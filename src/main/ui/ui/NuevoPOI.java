package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import bases.BasePOIs;

import javax.swing.JButton;

import org.uqbar.geodds.Point;

import poi.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

public class NuevoPOI {

	public JFrame frmNuevoPoi;
	private BasePOIs base;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoPOI window = new NuevoPOI();
					window.frmNuevoPoi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevoPOI() {
		initialize();
	}
	
	public NuevoPOI(BasePOIs base) {
		initialize();
		this.base = base;
	}
	
	JTextPane textPane_Nombre;
	JTextPane textPane_Dir;
	JTextPane textPane_Lat;
	JTextPane textPane_Lon;
	JComboBox<String> comboBox;
	JLabel lblVolveAChequear;
	private JLabel lblComuna;
	private JComboBox<String> comboBox_1;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoPoi = new JFrame();
		frmNuevoPoi.setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoPOI.class.getResource("/resources/Sin título-2.png")));
		frmNuevoPoi.setTitle("Nuevo POI");
		frmNuevoPoi.setBounds(100, 100, 450, 300);
		frmNuevoPoi.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		textPane_Nombre = new JTextPane();
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		textPane_Dir = new JTextPane();
		
		JLabel lblLatitud = new JLabel("Latitud");
		
		textPane_Lat = new JTextPane();
		
		JLabel lblLongitud = new JLabel("Longitud");
		
		textPane_Lon = new JTextPane();
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNuevoPoi.setVisible(false);
			}
		});
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"CGP", "Parada de Colectivo", "Sucursal de Banco", "Local Comercial"}));
		
		lblVolveAChequear = new JLabel("Volve a chequear los datos");
		lblVolveAChequear.setForeground(Color.RED);
		lblVolveAChequear.setVisible(false);
		
		lblComuna = new JLabel("Comuna");
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>() {
			public int getSize() {
				return base.getListaComunas().size();
			}
			public String getElementAt(int index) {
				return base.getListaComunas().get(index).getNombre();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmNuevoPoi.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDireccion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textPane_Dir, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addComponent(textPane_Nombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLatitud, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLongitud, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textPane_Lon, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addComponent(textPane_Lat, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAgregar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblVolveAChequear)
							.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblComuna))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_1, 0, 354, Short.MAX_VALUE)
								.addComponent(comboBox, 0, 354, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_Dir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLatitud, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_Lat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLongitud, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_Lon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblComuna))
					.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnCancelar)
						.addComponent(lblVolveAChequear))
					.addContainerGap())
		);
		frmNuevoPoi.getContentPane().setLayout(groupLayout);
	}
	
	
	
	
	
	
	
	
	void agregar(){
		String tipo = (String) comboBox.getSelectedItem();
		POI poi_aAgregar = new POI();
		if( tipo.startsWith("CGP") )		poi_aAgregar = new CGP();
		if( tipo.startsWith("Parada") )		poi_aAgregar = new ParadaColectivo();
		if( tipo.startsWith("Sucursal") )	poi_aAgregar = new SucursalBanco();
		if( tipo.startsWith("Local") )		poi_aAgregar = new LocalComercial();
		
		if( textPane_Nombre.getText().isEmpty() || textPane_Dir.getText().isEmpty() || 
			textPane_Lat.getText().isEmpty() || textPane_Lon.getText().isEmpty())
		{	
			lblVolveAChequear.setVisible(true);		
		}else{
			poi_aAgregar.setNombre(textPane_Nombre.getText());
			poi_aAgregar.setDireccion(textPane_Dir.getText());
			poi_aAgregar.setUbicacion( new Point( Double.parseDouble(textPane_Lat.getText()) , Double.parseDouble(textPane_Lon.getText() )) );
			Comuna comuna = base.getListaComunas().stream().filter((c -> c.getNombre() == (String) comboBox_1.getSelectedItem() )).findFirst().get();
			poi_aAgregar.setComuna(comuna);
		}

	}
}
