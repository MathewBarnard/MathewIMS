package LiveStockSimulation;

import java.util.Calendar;
import java.util.Random;

import InventoryManagementSystem.DatabaseAdapter;
import InventoryManagementSystem.Product;

public class LiveStockSimulator {
	
	private static LiveStockSimulator mInstance = null;
	
	private static Thread mSimulatorThread = null;

	private static long mLastTick;
	private static long mCount;
	private static long mTimePerOrder = 1000;

	public static LiveStockSimulator getInstance() {
		
		if (mInstance == null)
			mInstance = new LiveStockSimulator();
		else
			mInstance = null;
		
		System.out.println("Initialised");
		return mInstance;
	}
	
	public LiveStockSimulator() {
		
		mCount = 0;
		mLastTick = System.currentTimeMillis();
		mSimulatorThread = new Thread(new ProcessSimulation());
		mSimulatorThread.start();
	}
	
	private class ProcessSimulation implements Runnable {
		
		public void run() {
			
			Random numGenerator = new Random();
			
			while(true) {
				
				long mCurrentTick = System.currentTimeMillis();
				long timeElapsed = mCurrentTick - mLastTick;
				mLastTick = mCurrentTick;			
				mCount += timeElapsed;
				
				// Simulate an order every second
				if(mCount > mTimePerOrder) {
					mCount = 0;
					int numberOfEntries = DatabaseAdapter.CountEntries();
					Product product = DatabaseAdapter.FindProductById(numGenerator.nextInt(numberOfEntries));
					product.updateStock(-1);
					DatabaseAdapter.UpdateStockByProduct(product);
				}
			}
		}
	}
}
