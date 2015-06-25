package Runnables.UpdateStock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import InventoryManagementSystem.DatabaseAdapter;
import InventoryManagementSystem.Product;

public class UpdateStockLocal extends UpdateStock {

	public UpdateStockLocal() {
		
	}
	
	protected void changeStockLevel() {
		
		try {	
			// Query the database for the product of a matching ID
			Product product = DatabaseAdapter.FindProductById(productID);
			
			// Adjust the stock value of the product
			product.updateStock(adjustValue);
			
			// Push the new product details back to the database
			DatabaseAdapter.UpdateStockByProduct(product);
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
}
