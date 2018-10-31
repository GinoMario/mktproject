package campania;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import campania.Variables;;

public class Robot{
	
	String strCelular = "";
	
	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[2]/div[1]/div[2]/div/img")
	  private WebElement imgQR;
	 
	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[4]/div/div/div[2]/h1")
	  private WebElement imgCell;	
	 
	 @FindBy(how = How.ID, using = "action-button")
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
	  private WebElement lblNumero;

	 @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div/div/div[2]/div[1]/span/div/div/header/div/div[1]/button/span")
	  private WebElement btnBack;

    public void WspPage()
    { 
    	PageFactory.initElements(Variables.driver, this);
		Util.esperaObjeto(imgCell);
		
		ObtenerNumero();
    }
    
    public void ObtenerNumero()
    { 
    	Util.esperaObjeto(imgPerfil);
		imgPerfil.click();
		Util.esperaObjeto(lblNumero);
		Variables.str_numero_origen = lblNumero.getText();
		btnBack.click();
    }
    
    public void abrirchat()
    {    	
    	try {
    		strCelular = Variables.Rst_Pendiente.getString("NUMERO");
			Variables.driver.get("https://api.whatsapp.com/send?phone="+strCelular);
			Util.esperaObjeto(btnEnviar);
	    	btnEnviar.click();
		} catch(Exception ex) {
			Datos.escribirLog("Error al abrir chat para el número: " + strCelular);
			Datos.escribirLog("Detalle del error: "+ex.getMessage()); 
		}    	
    }
    
    public void enviarsms()
    {   	
    	try {
    		Util.esperaObjeto(txtMensaje);
			txtMensaje.sendKeys(Variables.Rst_Pendiente.getString("TEXTO"));
			Util.esperaObjeto(iconSend);
	    	iconSend.click();
	    	Util.invisibleObjeto(By.xpath("//span[@data-icon=\"msg-time\"]"));
//	    	Thread.sleep(1500);
	    	Datos.escribirLog("-----------------------------------------------");
	    	Datos.escribirLog("Se envió el mensaje al número: " + strCelular);
	    	Datos.escribirLog("-----------------------------------------------");
    	} catch(Exception ex) {
			Datos.escribirLog("ERROR al enviar mensaje al número: " + strCelular);
			Datos.escribirLog("Detalle del ERROR: "+ex.getMessage()); 			
		}    	
    }
}
