package InventoryManagementSystem;

public class Product {

	String  mProductName;
	int		mID;
	int 	mStockLevel;
	
	public Product() {
		
	}
	
	public Product(int id, String productName, int quantity) {
		
		mID 			= id;
		mProductName 	= productName;
		mStockLevel 	= quantity;
	}
	
	public void setID(int id) {
		
		mID = id;
	}
	
	public int getID() {
		
		return mID;
	}
	
	public String getName() {
		
		return mProductName;
	}
	
	public void updateStock(int adjustValue) {
		
		mStockLevel += adjustValue;
	}
	
	public int getStock() {
		
		return mStockLevel;
	}
}
