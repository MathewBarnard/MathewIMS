package InventoryManagementSystem;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class InventoryManagementSystem {

	ArrayList<Product> mProducts;
	
	// Constructor for the IMS
	public InventoryManagementSystem() {
		
		mProducts = new ArrayList<Product>();
	}
	
	public boolean initialise() {
		
		return true;
	}
	
	public void AddNewProduct(Product newProduct) {
		
		newProduct.setID(mProducts.size());
		mProducts.add(newProduct);
		System.out.println("Product added");
	}
	
	public void printStockReport()
	{
		try {
			PrintWriter out = new PrintWriter("StockReport.txt", "UTF-8");
			
			// Print out details of every item in the database
			for (int i = 0; i < mProducts.size(); ++i) {
				Product currentProduct = mProducts.get(i);
				out.println("Product Code: " + currentProduct.getID() + 
						"		Name: " + currentProduct.getName() + 
						"		Stock: " + currentProduct.getStock());
				
				System.out.println("Printed!");
			}
			
			out.close();
		}
		catch (UnsupportedEncodingException e)
		{
			System.out.println("Exception!");
		}
		catch (FileNotFoundException e) {
			
			System.out.println("Exception!");
		}
	}
	
	public Product findProductById(int id) {
		
		for (int i = 0; i < mProducts.size(); ++i) {
			
			if (mProducts.get(i).getID() == id) {
				
				return mProducts.get(i);
			}
		}
		
		return null;
	}
}

