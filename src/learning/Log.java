package learning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Log extends JFrame{
	
	public static void main(String[] args){
		Log frameTabel = new Log();
	}
	
	JButton login = new JButton("login");
	JPanel panel = new JPanel();
	JTextField user = new JTextField(15);
	JPasswordField pass = new JPasswordField();
	
	Log(){
		super("Login Autentification");
		setSize(600,400);
		setLocation(50,100);
		panel.setLayout (null); 

		user.setBounds(70,30,150,20);
		pass.setBounds(70,65,150,20);
		login.setBounds(110,100,80,20);
		
		panel.add(login);
		panel.add(user);
		panel.add(pass);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionlogin();
	}

	public void actionlogin() {
		
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = user.getText();
				String password = pass.getText();
				
			if (name.equals("mingxuan") && password.equals("5016")) {
				MainFrame face= new MainFrame();
				face.setVisible(true);				
				dispose();
			} else {

				JOptionPane.showMessageDialog(null,"Wrong Password / Username");
				user.setText("");
				pass.setText("");
				user.requestFocus();
				}				  		 
	
			}
			
		});
	}
	
	
}
