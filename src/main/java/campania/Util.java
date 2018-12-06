package campania;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	
    public static void esperaObjeto(WebElement element) {    	
        new WebDriverWait(Variables.driver, 10).until(ExpectedConditions.visibilityOf(element));        
    }
    

    public static void invisibleObjeto(By element) {    	
        new WebDriverWait(Variables.driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(element));        
    }

    public static void launchapp() {
    	try{
    		System.setProperty("webdriver.chrome.driver", "C:\\Robotwsp\\chromedriver.exe");
    		Variables.driver = new ChromeDriver();
    		Variables.driver.manage().window().maximize();
    		Variables.driver.get("https://web.whatsapp.com/");    
    		
    	}catch (Exception e) {
    		Datos.escribirLog("Error en Util.launchapp: Se detuvo la ejecución del driver Chrome. Error: "+e.getMessage());
//			JOptionPane.showMessageDialog(null, "No se encontró el driver Chrome, por favor comuniquese con su administrador");
//			Robot.cerrarPantallas();
		}		
    }
    
	public static String ObtenerFecha()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String HoraActual = dateFormat.format(cal.getTime());
		HoraActual = dateFormat.format(cal.getTime());
		return HoraActual;
	}
	

    public static void Cerrar_Pantallas() throws IOException
    {
    	Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
    	Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");	
    }
    

    public static void deleteAllCookies() {
        Variables.driver.manage().deleteAllCookies();
    }
    
    public static void ScrollIntoView(WebElement element) {
        ((JavascriptExecutor) Variables.driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

	
}
