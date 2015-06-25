package Runnables.AddNewProduct;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AddNewProductRemote extends AddNewProduct {

	public AddNewProductRemote() {
		
		super();
	}
	
	protected void addToDatabase() {
		
		// Fill spaces in the productName with a + so that it can be transfered via http
		String[] split = productName.split(" ");
		
		productName = "";
		
		for (int i = 0; i < split.length; ++i) {
			
			productName += split[i];
			productName += "+";
		}
		
		try {
			System.out.println(productName + ", " + productStock);
			
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
