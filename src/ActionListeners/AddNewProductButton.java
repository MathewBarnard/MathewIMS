package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Runnables.AddNewProduct;

public class AddNewProductButton extends BasicButton {
	
	AddNewProduct mAddNewProduct;
	
	public AddNewProductButton(Runnable response) {
		
		super(response);
		mAddNewProduct = (AddNewProduct)response;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		mAddNewProduct.run();
	}
}
