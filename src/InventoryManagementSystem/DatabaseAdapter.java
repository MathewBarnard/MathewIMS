package InventoryManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseAdapter {

	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://localhost/MathewIMS";
	
	private static String USER = "mathewbarnard";
	private static String PASS = "Dynamite6464";
	
	// Returns a product from the database with a matching ID
	public static Product FindProductById(int id) {
		
		Connection conn = null;
		Statement stmt = null;
		
		Product product = new Product();
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sql = "SELECT ID, Name, Stock FROM Products WHERE ID = " + id + "";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				product.setID(rs.getInt("ID"));
				product.setName(rs.getString("Name")); 
				product.setStock(rs.getInt("Stock"));
			}
			
			conn.close();
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
		}
		
		return product;
	}
	
	// Updates the entire entry of a product within the database
	public static void UpdateStockByProduct(Product product) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sql = "UPDATE Products SET Stock = " + product.getStock() + " WHERE ID = " + product.getID();
			stmt.executeUpdate(sql);
			
			conn.close();
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
	
	public static int CountEntries() {
		
		Connection conn = null;
		Statement stmt = null;
		
		int count = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sql = "SELECT ID, Name, Stock FROM Products";
			ResultSet rs = stmt.executeQuery(sql);
			
			Product product = new Product();
			
			String printOut = "";
			
			while(rs.next()) {
				
				count += 1;
			}
		}
		catch (Exception e) {
			
		}
		
		return count;
	}
}
