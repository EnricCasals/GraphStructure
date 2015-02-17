package Model;

import java.util.LinkedList;

public class Graf {
	private LinkedList<Relacio> graf;
	private LinkedList<Node> recorregut;
	private LinkedList<Node> recprregutFinal;
	private int midaGraf;
	private int midaRecorregut;
	
	public Graf(){
		graf = new LinkedList<Relacio>();
		recorregut = new LinkedList<Node>();
	}
	
	public void addNode(Node nou){
		graf.add(new Relacio(nou));
	}
	
	public void addRelacio(Node pare, Node fill){
		midaGraf++;
		for(Relacio r: graf){
			if(r.getPare().equals(pare)){
				r.add(fill);
			}
		}
	}
	
	public LinkedList<Relacio> getRelacions(){
		return graf;
	}
	
	public LinkedList<Node> dijstra(Node origen, Node desti){
		midaRecorregut = midaGraf;
		recorregut = new LinkedList<Node>();
		recprregutFinal = new LinkedList<Node>();
		boolean trobat = false;
		int i = 0;
		while(!trobat && i < graf.size()){
			if(graf.get(i).getPare().equals(origen)){
				trobat = true;
			}else{
				i++;
			}
		}
		recorregut.add(origen);
		recorregutPerNodes(graf.get(i), desti, 0);
		
		return recprregutFinal;
	}
	
	@SuppressWarnings("unchecked")
	private void recorregutPerNodes(Relacio camins, Node desti, int k){
		for(Node node : camins.getFills()){
			if(node.equals(desti)){
				recorregut.add(node);
				if(k < midaRecorregut){
					midaRecorregut = k;
					recprregutFinal =  ((LinkedList<Node>) recorregut.clone());
				}
				if(recorregut.size() > 0)
					recorregut.removeLast();
				
			}else if(recorregut.contains(node)){
				
			}else{
				boolean trobat = false;
				int i = 0;
				while(!trobat && i < graf.size()){
					if(graf.get(i).getPare().equals(node)){
						trobat = true;
					}else{
						i++;
					}
				}
				recorregut.add(node);
				recorregutPerNodes(graf.get(i), desti, k + 1);
				if(recorregut.size() > 0)
					recorregut.removeLast();
			}
		}
	}
	
	public Node getNodeFromName(String nom){
		for(Relacio rel : graf){
			if(rel.getPare().getText().equals(nom)){
				return rel.getPare();
			}
		}
		return null;
	}

	public String[] getStringFromNodes(){
		String[] nodes = new String[graf.size()];
		int index = 0;
		for(Relacio r : graf){
			nodes[index++] = r.getPare().getText();
		}
		
		return nodes;
	}


	
}
