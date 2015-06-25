package Runnables.UpdateStock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import InventoryManagementSystem.InventoryManagementSystem;
import InventoryManagementSystem.Product;

public abstract class UpdateStock implements Runnable {
	
	protected InputStream mInputStream;
	protected int productID;
	protected int adjustValue;
	
	public UpdateStock() {
		
		mInputStream = null;
	}
	
	public void run() {
		
		// User inputs a product ID. 
		if(!inputProductID())
			return;
		// User inputs a value to adjust stock by.
		if(!inputProductValue())
			return;
		// Change the stock level for the product.
		changeStockLevel();
	}
	
	private boolean inputProductID() {
		
		productID = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID."));
		
		// Check if the integer is valid
		
		return true;
	}
	
	private boolean inputProductValue() {
	
		adjustValue = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount to change the stock by."));
		
		// Check if the integer is valid
		
		return true;
	}
	
	protected abstract void changeStockLevel();
}
