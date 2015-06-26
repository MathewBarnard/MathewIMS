package InventoryManagementSystem;

import java.util.ArrayList;

public class PurchaseOrder {

	ArrayList<PurchaseOrderLine> mProducts;
	
	public PurchaseOrder() {
		
		mProducts = new ArrayList<PurchaseOrderLine>();
	}
	
	public class PurchaseOrderLine {

		private int 	mQuantity;
		private String 	mProductName;
		private float 	mCost;
		
		public PurchaseOrderLine(String productName) {
			
			mQuantity = 0;
			mProductName = productName;
		}
		
		public int changeQuantity(int quantity) {
			
			mQuantity += quantity;
			
			return mQuantity;
		}
	}
}
