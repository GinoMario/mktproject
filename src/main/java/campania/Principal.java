package campania;

import javax.swing.JOptionPane;
import campania.Datos;
import campania.Robot;

public class Principal{

	public static void main(){
		
		

    	int rpta = JOptionPane.showConfirmDialog( null,"Está por dar inicio al Robot.\n¿Desea continuar?","CAMPAÑAS",JOptionPane.YES_NO_OPTION);
    	
    	if (rpta == 0){
    		
    		Datos.crearLog();
    		
    		//ABRIR NAVEGADOR
    		Util.launchapp();  		
    		 		
    		Robot robot = new Robot();
    		//ABRIR WHATSAPP
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
