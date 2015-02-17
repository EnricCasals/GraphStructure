package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Graf;
import Model.Node;
import Vista.Vista;
import Vista.VistaNouNode;

public class Controlador implements ActionListener, ListSelectionListener {
	private Vista vista;
	private Graf graf;
	private Vector<String> novesRelacions;
	private VistaNouNode nouNode;
	
	public Controlador(Vista vista){
		super();
		this.vista = vista;
		this.graf = new Graf();
		popullateGraf();
		vista.setRelacions(graf.getRelacions());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case Vista.BUSCA_CAMI:
				gestionaBuscarCami();
				break;
			case Vista.NOU_NODE:
				novesRelacions = new Vector<String>();
				nouNode = new VistaNouNode(graf.getStringFromNodes());
				nouNode.addControls(this, this);
				nouNode.setVisible(true);
				break;
			case Vista.MODIFICA_NODE:
				break;
			case VistaNouNode.ADD:
				gestioInsereixNode();
				break;
		}
	}
	

	private void gestionaBuscarCami(){
		String origen = vista.getOrigen();
		String desti = vista.getDesti();
		
		if(origen.isEmpty()){
			JOptionPane.showMessageDialog(vista, "Node Origen esta buit", "Missing Field", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(desti.isEmpty()){
			JOptionPane.showMessageDialog(vista, "Node Desti esta buit", "Missing Field", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Node primer = graf.getNodeFromName(origen);
		Node ultim = graf.getNodeFromName(desti);
		
		if(primer == null){
			JOptionPane.showMessageDialog(vista, "Node Origen inexistent", "Node Not Found", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(ultim == null){
			JOptionPane.showMessageDialog(vista, "Node Desti inexistent", "Node Not Found", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		vista.setRecorregut(graf.dijstra(primer, ultim));
		vista.repaint();
	}
	
	private void popullateGraf(){
		Node a = new Node(20, 20, "A");
		Node b = new Node(50, 200, "B");
		Node c = new Node(150, 10, "C");
		Node d = new Node(300, 300, "D");
		Node e = new Node(300, 20, "E");
		Node f = new Node(10, 290, "F");
		Node g = new Node(150, 310, "G");
		Node h = new Node(80, 80, "H");
		Node i = new Node(150, 70, "I");
		Node j = new Node(50, 310, "J");
		Node k = new Node(350, 50, "K");
		
		
		
		graf.addNode(a);
		graf.addNode(d);
		graf.addNode(c);
		graf.addNode(b);
		graf.addNode(h);
		graf.addNode(e);
		graf.addNode(f);
		graf.addNode(g);
		graf.addNode(i);
		graf.addNode(j);
		graf.addNode(k);
		
		
		
		graf.addRelacio(a, b);
		graf.addRelacio(b, a);
		graf.addRelacio(c, d);
		graf.addRelacio(d, c);
		graf.addRelacio(c, a);
		graf.addRelacio(a, c);
		graf.addRelacio(h, d);
		graf.addRelacio(d, h);
		graf.addRelacio(e, g);
		graf.addRelacio(g, e);
		graf.addRelacio(d, g);
		graf.addRelacio(g, d);
		graf.addRelacio(e, d);
		graf.addRelacio(d, e);
		graf.addRelacio(h, b);
		graf.addRelacio(b, h);
		graf.addRelacio(h, c);
		graf.addRelacio(c, h);
		graf.addRelacio(h, i);
		graf.addRelacio(i, h);
		graf.addRelacio(i, c);
		graf.addRelacio(c, i);
		graf.addRelacio(j, i);
		graf.addRelacio(i, j);
		graf.addRelacio(j, k);
		graf.addRelacio(k, j);
		graf.addRelacio(d, k);
		graf.addRelacio(k, d);
		graf.addRelacio(a, f);
		graf.addRelacio(f, a);
		graf.addRelacio(f, i);
		graf.addRelacio(i, f);
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()){
			JList<String> source =((JList<String>)e.getSource());
			String valor = source.getSelectedValue();
			novesRelacions.add(valor);
			nouNode.addNovaRelacio(novesRelacions);
			
		}
	}
	
	private void gestioInsereixNode(){
		int x = Integer.parseInt(nouNode.getCoordX());
		int y = Integer.parseInt(nouNode.getCoordY());
		String nom = nouNode.getNom();
		
		Node nou = new Node(x, y, nom);
		graf.addNode(nou);
		
		for(String nodeRelacio : novesRelacions){
			graf.addRelacio(nou, graf.getNodeFromName(nodeRelacio));
			graf.addRelacio(graf.getNodeFromName(nodeRelacio), nou);
			vista.setRelacions(graf.getRelacions());
		}
		
		vista.repaint();
		nouNode.setVisible(false);
		nouNode = null;
	}
}
