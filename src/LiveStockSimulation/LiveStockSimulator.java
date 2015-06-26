package LiveStockSimulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import InventoryManagementSystem.DatabaseAdapter;
import InventoryManagementSystem.GUIWindow;
import InventoryManagementSystem.Product;

public class LiveStockSimulator {
	
	private static LiveStockSimulator mInstance = null;
	
	private Thread mSimulatorThread = null;

	private long mLastTick;
	private long mCount;
	private long mTimePerOrder = 1000;
	
	private boolean mActive;

	public static LiveStockSimulator getInstance() {
		
		if (mInstance == null)
			mInstance = new LiveStockSimulator();

		return mInstance;
	}
	
	public LiveStockSimulator() {
		
		mLastTick 	= 0;
		mCount 		= 0;
		mLastTick = System.currentTimeMillis();
		mActive 	= false;
		mSimulatorThread = new Thread(new ProcessSimulation());
	}
	
	public void toggleActive() {
		
		if(mActive == true) {
			mActive = false;
		}
		else {
			mActive = true;
			if(!mSimulatorThread.isAlive()) {
				mSimulatorThread = new Thread(new ProcessSimulation());
				mSimulatorThread.start();
			}
		}
	}
	
	private class ProcessSimulation implements Runnable {
		
		public void run() {
			
			Random numGenerator = new Random();
			
			while(mActive) {
				 
				long mCurrentTick = System.currentTimeMillis();
				long timeElapsed = mCurrentTick - mLastTick;
				mLastTick = mCurrentTick;			
				mCount += timeElapsed;
				
				// Simulate an order every second
				if(mCount > mTimePerOrder) {
					System.out.println("Removing stock");
					mCount = 0;
					int numberOfEntries = DatabaseAdapter.countEntries();
					Product product = DatabaseAdapter.findProductById(numGenerator.nextInt(numberOfEntries));
					product.updateStock(-1);
					DatabaseAdapter.updateStockByProduct(product);
					
					ArrayList<Product> lowStockList = DatabaseAdapter.findLowStock();
					
					String output = "";
					
					for (int i = 0; i < lowStockList.size(); i++) {
						
						output += "Product ID " + lowStockList.get(i).getID() + ", '" + lowStockList.get(i).getName() + "' is low on stock!\n";
					}
					
					GUIWindow mainWindow = GUIWindow.findWindowByName("Main Window");
					mainWindow.updateTextPane("MainOutput", output);
				}
			}
			
			System.out.println("Shutting down!");
		}
	}
}
