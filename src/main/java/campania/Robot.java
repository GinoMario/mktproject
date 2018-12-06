package campania;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import campania.Variables;;

public class Robot{
	
	String strCelularDestino = "";
	static Robot robot = new Robot();
	
	
	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[2]/div[1]/div[2]/div/img")
	  private WebElement imgQR;
	 
	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[4]/div/div/div[2]/h1")
	  private WebElement imgCell;	
	 
	 @FindBy(how = How.XPATH, using = "//a[@id=\"action-button\"]")
	  private WebElement btnEnviar;	 

	 @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/footer/div[1]/div[2]/div/div[2]")
	  private WebElement txtMensaje;	 

	 @FindBy(how = How.XPATH, using = "//span[@data-icon=\"send\"]")
	  private WebElement iconSend;
	 
	 @FindBy(how = How.XPATH, using = "//span[@data-icon=\"msg-time\"]")
	  private WebElement iconTime;

	 @FindBy(how = How.XPATH, using = "//*[@id=\"side\"]/header/div[1]/div/img")
	  private WebElement imgPerfil;

	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[2]/div[1]/span/div/div/div/div[2]/div[2]/div/div/div[2]")
	  private WebElement lblNumeroOrigen;

	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[2]/div[1]/span/div/div/header/div/div[1]/button/span")
	  private WebElement btnBack;

	 public static void IniciarRobot() {
			Datos.crearLog();
			
			//ABRIR NAVEGADOR
			frameClass.setStatus("Escanear QR");
			Util.launchapp();  		
				 		
			//ABRIR WHATSAPP
			robot.WspPage();		 
	 }
	 
	 
	public static void Schedule() {
			
		while(Variables.blnContinuar){				
			
			//BUSCAR CAMPANIAS PENDIENTES
			Datos.Schedule();

			if(Variables.blnpendiente){
			
				frameClass.setStatus("Ejecutando...");
				
				//ABRIR CHAT X NUMERO
				robot.abrirchat();
					
				//ENVIAR MENSAJE
				robot.enviarsms();
			}else{
				frameClass.setStatus("Esperando...");
			}
		}		
	}
	 
    public void WspPage()
    { 
    	try{
    		PageFactory.initElements(Variables.driver, this);
    		Util.esperaObjeto(imgCell);
    		
    		ObtenerNumero();
    		frameClass.setCelularOrigen();
    		
    		frameClass.setStatus("Esperando...");
    		
    		Datos.escribirLog("Se ingreso de manera correcta al número: "+Variables.str_numero_origen);
    		Variables.blnContinuar=true;
    	}catch (Exception e) {
    		Datos.escribirLog("Error: No se pudo ingresar de manera correcta a la pagina Whatsapp Web, por favor inicie nuevamente el robot. Error: "+e.getMessage());
		}    	
    }
    public static void salir()
    { 
    	System.exit(0);
    }
    
    public static void cerrarPantallas()
    { 
      	try{      		
//        	Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
//        	Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        	Variables.driver.close();
    	}catch(Exception e){}
    }
    
    
    public void ObtenerNumero()
    { 
    	Util.esperaObjeto(imgPerfil);
		imgPerfil.click();
		Util.esperaObjeto(lblNumeroOrigen);
		Variables.str_numero_origen = lblNumeroOrigen.getText();
		btnBack.click();
    }
    
    public void abrirchat()
    {    	
    	try {
    		strCelularDestino = Variables.Rst_Pendiente.getString("NUMERO");
			Variables.driver.get("https://api.whatsapp.com/send?phone="+strCelularDestino);
			Util.esperaObjeto(btnEnviar);
	    	btnEnviar.click(); 	
	    	
		} catch(Exception ex) {
			Datos.ActualizarRegistro(false);
			Variables.blnpendiente = false; 
			Datos.escribirLog("Error al abrir chat para el número: " + strCelularDestino);
			Datos.escribirLog("Detalle del error: "+ex.getMessage());
		}    	
    }
    
    public void enviarsms()
    {   	
    	if(Variables.blnpendiente){
    		try {
        		Util.esperaObjeto(txtMensaje);
    			txtMensaje.sendKeys(Variables.Rst_Pendiente.getString("TEXTO"));
    			Util.esperaObjeto(iconSend);
    	    	iconSend.click();
    	    	Util.invisibleObjeto(By.xpath("//span[@data-icon=\"msg-time\"]"));
    	    	Datos.ActualizarRegistro(true);
    	    	Datos.escribirLog("-----------------------------------------------");
    	    	Datos.escribirLog("Se envió el mensaje al número: " + strCelularDestino);
    	    	Datos.escribirLog("-----------------------------------------------");
        	} catch(Exception ex) {
        		Datos.ActualizarRegistro(false);
    			Datos.escribirLog("ERROR al enviar mensaje al número: " + strCelularDestino);
    			Datos.escribirLog("Detalle del ERROR: "+ex.getMessage());
    			Variables.blnpendiente=false;
    		}    		
    	}
    }
}
