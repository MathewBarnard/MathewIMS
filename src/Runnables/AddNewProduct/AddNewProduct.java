package Runnables.AddNewProduct;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import InventoryManagementSystem.Product;

public abstract class AddNewProduct implements Runnable {
	
	protected String productName;
	protected String productStock;
	
	public AddNewProduct() {
		
		productName  = "";
		productStock = "";
	}
	
	public void run() {
		
		if(!inputProductName())  return;
		if(!inputProductStock()) return;
		addToDatabase();
	}
	
	private boolean inputProductName() {
		
		productName = JOptionPane.showInputDialog("Enter the name of the new product.");
		
		// Stop adding a new product if the dialog window is closed
		if (productName == null || productName == "") {
			return false;
		}
		
		return true;
	}
	
	private boolean inputProductStock() {
		
		productStock = JOptionPane.showInputDialog("Enter the current stock of the new product.");
		
		if (productStock == null){
			return false;
		}
		
		return true;
	}
	
	protected abstract void addToDatabase();
}
