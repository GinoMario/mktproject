package campania;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import campania.Variables;

public class Datos {
	public enum BasedeDatos 
	{
		sql, novalue;
		public static BasedeDatos getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	
	public static void Conectar_Dp() 
	{
	    switch (BasedeDatos.getValue(Variables.Str_Motor.toLowerCase()))
	    {
		    case sql:
				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setServerName(Variables.Str_Servidor); // Instancia
				ds.setDatabaseName(Variables.Str_Bd);  //BD       
				ds.setUser(Variables.Str_Usuario);//usuario
				ds.setPassword(Variables.Str_Password);//clave usuario	
				
				try {
					Variables.Cnn = ds.getConnection();
				} catch (SQLServerException e) {
		    		Date date = new Date();
		    		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
					JOptionPane.showMessageDialog(null, "Se perdió la conexión a la BD del servidor a las "+hourFormat.format(date)+" Por favor verifique o póngase en contacto con el Arquitecto");
					System.exit(0);					
				}
				break;
		default:
			break;
	    }
	}
	
	
	public static void Schedule()
	{
		Variables.blnpendiente = false;    	
		try
	    {
		    Conectar_Dp();		    
		    Variables.Rst_Pendiente = Variables.Cnn.prepareCall("SELECT TOP 1 * FROM CAMPANIADETALLE CD INNER JOIN CAMPANIA C ON CD.CODCAMPANIA = C.CODCAMPANIA WHERE CD.ESTADODETALLEMA ='PENDIENTE' ORDER BY CD.CODCAMPANIADETALLE ASC").executeQuery();

		   //Valida que existan scripts pendientes por ejecución
		   if (Variables.Rst_Pendiente.next() == true) 
		   {
		          	Variables.blnpendiente = true;
		           	
		           	//ACTUALIZA REGISTRO
		           	Variables.Cnn.prepareCall( "UPDATE CAMPANIADETALLE SET ESTADODETALLEMA = 'EJECUTADO' WHERE CODCAMPANIADETALLE = " + Variables.Rst_Pendiente.getString("CODCAMPANIADETALLE")).execute();
		   }
		}catch(Exception ex)
		{
			Variables.blnpendiente = false;
			System.out.println("Error Datos.Schedule: " + ex.getMessage());
		}    	
	}   
		
	
	public static void Desconectar_Sql()
	{
	   	try
	   	{	
	 		try{Variables.driver.quit();}catch(Exception e){}
	    	Variables.Cnn.close();
	   	}catch(Exception e){}
	}
	
	public static void crearLog()
	{
		Variables.dtFechaInicio = Util.ObtenerFecha();
		String ruta_log="C:\\Robotwsp\\logs\\"+Variables.dtFechaInicio.replace("/", "").replace(" ", "").replace(":", "")+".txt";
		Variables.archivo = new File(ruta_log);	
		
		escribirLog("**********SE INICIA EJECUCION**********");
	}
	
	public static void escribirLog(String log)
	{
		try {
			Variables.fw = new FileWriter(Variables.archivo,true);
			Variables.fw.write(log+"\r\n");
			Variables.fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
