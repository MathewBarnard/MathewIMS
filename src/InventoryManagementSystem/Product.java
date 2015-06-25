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
	
	public void setName(String name) {
		
		mProductName = name;
	}
	
	public String getName() {
		
		return mProductName;
	}
	
	public void updateStock(int adjustValue) {
		
		mStockLevel += adjustValue;
	}
	
	public void setStock(int stock) {
		
		mStockLevel = stock;
	}
	
	public int getStock() {
		
		return mStockLevel;
	}
}
