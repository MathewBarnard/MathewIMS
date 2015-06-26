package Runnables.UpdateStock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextPane;

import InventoryManagementSystem.DatabaseAdapter;
import InventoryManagementSystem.Product;

public class UpdateStockLocal extends UpdateStock {

	// The view that needs update when the stock goes below a certain level
	JTextPane mTextPane;
	
	public UpdateStockLocal(JTextPane textPane) {
		
		mTextPane = textPane;
	}
	
	protected void changeStockLevel() {
		
		try {	
			// Query the database for the product of a matching ID
			Product product = DatabaseAdapter.findProductById(productID);
			
			// Adjust the stock value of the product
			product.updateStock(adjustValue);
			
			// Push the new product details back to the database
			DatabaseAdapter.updateStockByProduct(product);
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
}
