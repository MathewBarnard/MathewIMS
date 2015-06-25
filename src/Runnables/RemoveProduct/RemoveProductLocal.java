package Runnables.RemoveProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import InventoryManagementSystem.Product;

public class RemoveProductLocal extends RemoveProduct {

	
	public RemoveProductLocal() {
		
	}
	
	protected void removeProduct() {
		
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
			String sql = "DELETE FROM Products WHERE ID = " + productID;
			stmt.executeUpdate(sql);
			
			conn.close();
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
}
