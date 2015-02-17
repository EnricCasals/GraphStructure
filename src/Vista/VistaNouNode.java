package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class VistaNouNode extends JFrame {

	public static final String ADD = "Afegir Node";
	
	private JTextField nom;
	private JTextField coordenadaX;
	private JTextField coordenadaY;
	private JButton afegir;
	private JList<String> llista;
	private JList<String> llistaAfegits;
	
	
	public VistaNouNode(String[] nodes){
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 30));
		coordenadaX = new JTextField();
		coordenadaX.setPreferredSize(new Dimension(50, 30));
		coordenadaY = new JTextField();
		coordenadaY.setPreferredSize(new Dimension(50, 30));
		afegir = new JButton(ADD);
		
		//MENU SUPERIOR
		JPanel textos = new JPanel(new GridLayout(2, 1));
		JPanel aux = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux.add(new JLabel("Text: "));
		aux.add(nom);
		
		textos.add(aux);
		
		aux = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux.add(new JLabel("X: "));
		aux.add(coordenadaX);
		aux.add(new JLabel("Y: "));
		aux.add(coordenadaY);
		
		textos.add(aux);
		getContentPane().add(textos, BorderLayout.NORTH);
		
		
		//MENU CENTRAL
		aux = new JPanel(new GridLayout(1, 2));

		llista = new JList<String>(); 
		llistaAfegits = new JList<String>(); 
		llista.setBorder(BorderFactory.createTitledBorder("Disponibles"));
		llistaAfegits.setBorder(BorderFactory.createTitledBorder("Relacions"));
		
		llista.setListData(nodes);
		
		aux.add(llista);
		aux.add(llistaAfegits);
		
		getContentPane().add(aux);
		
		//MENU UNFERIOR
		aux = new JPanel(new FlowLayout());
		aux.add(afegir);
		
		getContentPane().add(aux, BorderLayout.SOUTH);
		
		this.setSize(200, 400);
		this.setLocationRelativeTo(null);
		
	}

	public void addControls(ActionListener listener, ListSelectionListener list){
		afegir.addActionListener(listener);
		llista.addListSelectionListener(list);
	}
	
	public void addNovaRelacio(Vector<String> nou){
		llistaAfegits.setListData(nou);
	}
	
	public String getNom(){
		return nom.getText();
	}
	
	public String getCoordX(){
		return coordenadaX.getText().toString();
	}
	
	public String getCoordY(){
		return coordenadaY.getText().toString();
	}
}
