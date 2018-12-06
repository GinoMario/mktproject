package campania;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*;

public class frameClass extends JFrame{

	private static final long serialVersionUID = 1L;
	private static JButton btnIniciar = new JButton("Iniciar");
	private static JButton btnDetener = new JButton("Stop");
	private static JButton btnSalir = new JButton("Salir");
	static JLabel lblCelular = new JLabel("");
	static JLabel lblStatus = new JLabel("");
	
    private class thehandler implements ActionListener{
    	private looper looper;
    	
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnIniciar) {
            	frameClass.desactivarBtnIniciar();
            	looper = new looper();
            	Thread t = new Thread(looper);
            	t.start();
            }else if(e.getSource() == btnDetener){
                looper.stop();
                looper = null;
            }else if(e.getSource() == btnSalir){
            	looper = new looper();
            	looper.exit();
            	looper = null;
            }
        }
    }
	
	public frameClass() {
		
		setLayout(null);
//		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		setType(Type.UTILITY);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(0, 0, 273, 178);
		setTitle("Robot Whatsapp");
		setResizable(false);
		
		thehandler handler = new thehandler();
		
		btnIniciar.setBounds(10, 12, 73, 34);
		add(btnIniciar);
        btnIniciar.addActionListener(handler);
		
//		btnDetener.setBounds(93, 12, 73, 34);
//		add(btnDetener);
//		btnDetener.addActionListener(handler);
		
		btnSalir.setBounds(176, 12, 73, 34);
		add(btnSalir);
		btnSalir.addActionListener(handler);		
		
		lblCelular.setBounds(105, 95, 300, 26);
		add(lblCelular);

		lblStatus.setBounds(105, 61, 300, 26);
		add(lblStatus);
		
		JLabel lblNroCelular = new JLabel("Celular Origen:");
		lblNroCelular.setBounds(10, 101, 85, 14);
		add(lblNroCelular);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 66, 85, 14);		
		add(lblEstado);	
		
		desactivarBtnStop();
	}
	
	public static void desactivarBtnIniciar(){
		btnIniciar.setEnabled(false);
		btnIniciar.setBackground(Color.LIGHT_GRAY);
//		btnDetener.setEnabled(true);
//		btnDetener.setBackground(Color.RED);
		btnSalir.setBackground(Color.RED);
		btnSalir.setEnabled(true);
	}
	
	public static void desactivarBtnStop(){
//		btnDetener.setEnabled(false);
//		btnDetener.setBackground(Color.LIGHT_GRAY);
		btnIniciar.setEnabled(true);
		btnIniciar.setBackground(Color.GREEN);
		btnSalir.setEnabled(true);
		btnSalir.setBackground(Color.LIGHT_GRAY);
	}
	
	public static void setCelularOrigen(){
		lblCelular.setText(Variables.str_numero_origen);			
	}

	public static void setStatus(String status){
		lblStatus.setText(status);
		if(status.equals("Ejecutando...")){
			lblStatus.setForeground(Color.GREEN);
		}else{
			lblStatus.setForeground(Color.RED);
		}
	}
}
