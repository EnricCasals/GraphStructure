package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import Model.Node;
import Model.Relacio;

@SuppressWarnings("serial")
public class PintaNodes extends JPanel{
	
	private static final int RADI = 10;

	private Graphics2D grafics;
	private LinkedList<Relacio> relacions;
	private LinkedList<Node> recorregut;
	
	
	public PintaNodes(){
		super();
		relacions = null;
	}
	
	
	@Override
	protected void paintComponent(Graphics g){		
		super.paintComponent(g);

		this.setBackground(Color.BLACK);
		
		grafics = (Graphics2D) g;

		grafics.setColor(Color.BLUE);
		if(relacions != null){
			for(Relacio n : relacions){
				pintaRelacio(n);
			}
			
			grafics.setColor(Color.WHITE);
			for(Relacio n : relacions){
				pintaNode(n.getPare());
			}
		}
		
		grafics.setColor(Color.YELLOW);
		if(recorregut != null){
			for(int i = 1; i < recorregut.size(); i++){
				grafics.drawLine(recorregut.get(i).getX() + RADI, recorregut.get(i).getY() + RADI, 
						recorregut.get(i - 1).getX() + RADI, recorregut.get(i - 1).getY() + RADI);
			}
		}

	}
	
	private void pintaNode(Node node){
		int x = node.getX();
		int y = node.getY();
		
		grafics.fillOval(x, y, RADI * 2, RADI * 2);
		grafics.drawString(node.getText() , x, y);
	}
	
	private void pintaRelacio(Relacio relacio){
		Node pare = relacio.getPare();
		
		for(Node fill : relacio.getFills()){
			grafics.drawLine(pare.getX() + RADI, pare.getY() + RADI, fill.getX() + RADI, fill.getY() + RADI);
		}
		
	}
	
	public void setRelacions(LinkedList<Relacio> relacions){
		this.relacions = relacions;
	}
	
	public void setRecorregut(LinkedList<Node> recorregut){
		this.recorregut = recorregut;
	}
	
}
