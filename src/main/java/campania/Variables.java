package campania;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import org.openqa.selenium.WebDriver;

public class Variables {
	
    public static String Tipo_Aplicativo= null;
    public static String Str_Conexion= null;
    public static Connection Cnn = null;
    public static ResultSet Rst_Pendiente = null; 
    public static String dtFechaInicio= null;
    public static Boolean blnpendiente=false; 
	public static WebDriver driver;
	public static BufferedWriter bw;
	public static File archivo;
	public static FileWriter fw;
	
//	Datos.Schedule("SQL","srvcampania.database.windows.net","BdCampania","lvs", "Jueves147@");
	public static String Str_Motor ="SQL";
	public static String Str_Servidor ="GINOLAPTOP\\SQLEXPRESS";
	public static String Str_Bd ="Automation";
	public static String Str_Usuario ="admin";
	public static String Str_Password ="admin123";

}
