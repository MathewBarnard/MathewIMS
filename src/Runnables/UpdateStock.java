package Runnables;

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

public class UpdateStock implements Runnable {
	
	private InventoryManagementSystem ims;
	InputStream mInputStream;
	
	public UpdateStock(InventoryManagementSystem ims) {
		
		this.ims = ims;
		mInputStream = null;
	}
	
	public void run() {
		
		int productID = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID."));
		
		/*Object options[] = {"Add", "Remove"};
		JFrame frame = new JFrame();
		// Determine if adding or subtracting stock
		int n = JOptionPane.showOptionDialog(
		    frame,
		    "Do you wish to add or remove stock?",
		    "Updating Stock",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[0]
		    );*/
		
		int adjustValue = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount to change the stock by."));
			
		/*// The user is removing stock
		if (n == 1) {
			adjustValue *= -1;
		}*/
		
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
