package LiveStockSimulation;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Calendar;

public class StartStockSimulation implements Runnable {
	
	public void run() {

		LiveStockSimulator.getInstance().toggleActive();
	}
}
