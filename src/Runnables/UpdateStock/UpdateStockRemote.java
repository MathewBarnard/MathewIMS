package Runnables.UpdateStock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import InventoryManagementSystem.Product;

public class UpdateStockRemote extends UpdateStock {

	public UpdateStockRemote() {
		
	}
	
	protected void changeStockLevel() {
		
		// First me must retrieve the current product to check its stock
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://www.mathewbarnard.co.uk/get_product_by_id.php?"
					+ "ID=" + productID);
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			mInputStream = entity.getContent();	
		}
		catch (IOException e) {
			
			System.out.println("Product failed to add: " + e.toString());
		}	
		
		// Parse the data received back from the server
		try {
			
			System.out.println("Printing...");
			String aDataRow = "";
			BufferedReader myReader = new BufferedReader(new InputStreamReader(mInputStream)); 
			
			while((aDataRow = myReader.readLine()) != null) {
				
				String[] splitProduct = aDataRow.split(":");
				Product product = new Product(Integer.valueOf(splitProduct[0]), splitProduct[1], Integer.valueOf(splitProduct[2]));
				
				product.updateStock(adjustValue);
				System.out.println(product.getID() + ", " + product.getName() + ", " + product.getStock());
				
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost("http://www.mathewbarnard.co.uk/change_product_stock.php?"
						+ "ID=" + String.valueOf(productID)
						+ "&Stock=" + String.valueOf(product.getStock()));
				HttpResponse response = httpClient.execute(httppost);
				HttpEntity entity = response.getEntity();
				mInputStream = entity.getContent();	
			}
		}
		catch (IOException e) {

		}
		finally {			
			
		}
	}
}
