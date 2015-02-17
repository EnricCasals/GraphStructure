package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Node;
import Model.Relacio;

@SuppressWarnings("serial")
public class Vista extends JFrame{
	private static final float ALTURA = 400;
	private static final float AMPLADA = 500;
	
	public static final String NOU_NODE = "Nou Node";
	public static final String MODIFICA_NODE = "Modifica Node";
	public static final String BUSCA_CAMI = "Buscar Ruta";
	
	
	private PintaNodes nodes;
	private JTextField origen;
	private JTextField desti;
	private JButton buscar;
	private JMenuItem nouNode;
	private JMenuItem modificaNode;
	
	
	public Vista(){
		
		JMenuBar menu = new JMenuBar();
		JMenu opcions = new JMenu("Opcions");
		
		nouNode = new JMenuItem(NOU_NODE);
		modificaNode = new JMenuItem(MODIFICA_NODE);
		
		opcions.add(nouNode);
		opcions.add(modificaNode);
		
		menu.add(opcions);
		
		getContentPane().add(menu, BorderLayout.NORTH);
		
		JPanel peticio = new JPanel(new FlowLayout());
		origen = new JTextField();
		origen.setPreferredSize(new Dimension(50, 30));
		desti = new JTextField();
		desti.setPreferredSize(new Dimension(50, 30));
		buscar = new JButton(BUSCA_CAMI);
		
		
		peticio.add(new JLabel("Origen: "));
		peticio.add(origen);
		peticio.add(new JLabel("Desti: "));
		peticio.add(desti);
		peticio.add(buscar);
		
		getContentPane().add(peticio, BorderLayout.SOUTH);
		
		nodes = new PintaNodes();
		getContentPane().add(nodes);
				
		this.setSize((int)ALTURA, (int)AMPLADA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void addControls(ActionListener listener){
		buscar.addActionListener(listener);
		nouNode.addActionListener(listener);
		modificaNode.addActionListener(listener);
	}
	
	public String getOrigen(){
		return origen.getText();
	}
	
	public String getDesti(){
		return desti.getText();
	}
	
	public void setRelacions(LinkedList<Relacio> relacions){
		nodes.setRelacions(relacions);
	}
	
	public void setRecorregut(LinkedList<Node> recorregut){
		nodes.setRecorregut(recorregut);
	}
}
