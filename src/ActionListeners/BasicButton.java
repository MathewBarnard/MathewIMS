package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class BasicButton implements ActionListener {
	
	Thread mResponse;
	
	public BasicButton(Runnable response) {

		mResponse = new Thread(response);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		mResponse.run();
	}
}
