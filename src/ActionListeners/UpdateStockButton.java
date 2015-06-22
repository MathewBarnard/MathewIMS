package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Runnables.AddNewProduct;
import Runnables.UpdateStock;

public class UpdateStockButton extends BasicButton {
	
	UpdateStock mUpdateStock;
	
	public UpdateStockButton(Runnable response) {
		
		super(response);
		mUpdateStock = (UpdateStock)response;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		mUpdateStock.run();
	}
}
