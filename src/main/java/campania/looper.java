package campania;

import java.util.concurrent.atomic.AtomicBoolean;

public class looper implements Runnable {

    private AtomicBoolean keepRunning;

    public looper() {
    	keepRunning = new AtomicBoolean(true);
    }

    public void stop() {
    	frameClass.desactivarBtnStop();
    	Variables.blnContinuar=false;
    	keepRunning.set(false);
    }
    
    public void exit() {
    	Robot.cerrarPantallas();
    	Robot.salir();    	
    }

    @Override
    public void run() {
    	Robot.IniciarRobot();
        while (keepRunning.get()) {        	
            Robot.Schedule();
        }
    }

}