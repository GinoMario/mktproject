package campania;

import javax.swing.JOptionPane;
import campania.Datos;
import campania.Robot;

public class Principal{

	public static void main(String[] args) throws Throwable {

    	int rpta = JOptionPane.showConfirmDialog( null,"Está por dar inicio al Robot. ¿Desea continuar?","CAMPAÑAS",JOptionPane.YES_NO_OPTION);
    	
    	if (rpta == 0){
    		
    		Datos.crearLog();
    		
    		Util.launchapp();
    		 		
    		Robot robot = new Robot();			
    		robot.WspPage();			
			
			while(true){				
				
				//BUSCAR CAMPANIAS PENDIENTES
				Datos.Schedule();

				if(Variables.blnpendiente){
					
					//ABRIR CHAT X NUMERO
					robot.abrirchat();
					
					//ENVIAR MENSAJE
					robot.enviarsms();
				}
			}		

    	}
	}
}
