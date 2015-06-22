package Runnables;

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

import InventoryManagementSystem.InventoryManagementSystem;
import InventoryManagementSystem.Product;

public class AddNewProduct implements Runnable {
	
	private InventoryManagementSystem ims;
	
	public AddNewProduct(InventoryManagementSystem ims) {
		
		this.ims = ims;
	}
	
	public void run() {
		
		String productName = JOptionPane.showInputDialog("Enter the name of the new product.");
		
		// Stop adding a new product if the dialog window is closed
		if (productName == null || productName == "") {
			return;
		}
		
		String[] split = productName.split(" ");
		
		productName = "";
		
		for (int i = 0; i < split.length; ++i) {
			
			productName += split[i];
			productName += "+";
		}
		
		System.out.println(productName);
		
		String productStock = JOptionPane.showInputDialog("Enter the current stock of the new product.");
		
		if (productStock == null){
			return;
		}
		
		System.out.println(productStock);
		
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://www.mathewbarnard.co.uk/add_new_product.php?"
					+ "Name=" + productName + "&"
					+ "Stock=" + productStock);
			HttpResponse response = httpClient.execute(httppost);
			
			System.out.println("Product added successfully!");
		}
		catch (IOException e) {
			
			System.out.println("Product failed to add: " + e.toString());
		}
	}
}
