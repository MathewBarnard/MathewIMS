package Runnables.GenerateStockReport;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import InventoryManagementSystem.Product;

public class GenerateStockReport implements Runnable {
	
	JTextPane mTextPane;
	
	public GenerateStockReport() {
		
	}
	
	public void run() {
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/MathewIMS";
		
		String USER = "mathewbarnard";
		String PASS = "Dynamite6464";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sql = "SELECT ID, Name, Stock FROM Products";
			ResultSet rs = stmt.executeQuery(sql);
			
			Product product = new Product();
			
			String printOut = "";
			
			while(rs.next()) {
				
				printOut += "[" + rs.getInt("ID") + "] " + rs.getString("Name") + ", " + rs.getInt("Stock") + "\n";
			}
			
			// Create a window that displays the stock report
			JFrame stockReportWindow = new JFrame();
			
			stockReportWindow.setBounds(200, 200, 300, 600);
			
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText(printOut);
			stockReportWindow.getContentPane().add(textPane, BorderLayout.CENTER);
			stockReportWindow.setVisible(true);
			
			//mTextPane.setText(printOut);
		}
		catch (Exception e){
			
		}
	}
}
