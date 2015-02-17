import Controlador.Controlador;
import Vista.Vista;


public class Principal {
	
	public static void main(String[] argv){
		Vista vista = new Vista();
		Controlador control = new Controlador(vista);
		vista.addControls(control);
		vista.setVisible(true);
	}
}
