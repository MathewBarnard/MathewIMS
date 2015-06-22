package Runnables;

import java.util.ArrayList;

import InventoryManagementSystem.InventoryManagementSystem;
import InventoryManagementSystem.Product;

public class GenerateStockReport implements Runnable {

	InventoryManagementSystem ims;
	
	public GenerateStockReport(InventoryManagementSystem ims) {
		
		this.ims = ims;
	}
	
	public void run() {
		
		ims.printStockReport();
	}
}
