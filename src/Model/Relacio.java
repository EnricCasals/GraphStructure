package Model;

import java.util.LinkedList;

public class Relacio {
	private Node pare;
	private LinkedList<Node> fills;
	
	public Relacio(Node pare){
		this.pare = pare;
		fills = new LinkedList<Node>();
	}
	
	public void add(Node fill){
		fills.add(fill);
	}
	
	public LinkedList<Node> getFills(){
		return fills;
	}
	
	public Node getPare(){
		return pare;
	}
}
