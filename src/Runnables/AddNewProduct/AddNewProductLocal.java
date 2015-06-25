package Runnables.AddNewProduct;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AddNewProductLocal extends AddNewProduct {

	public AddNewProductLocal() {
		
		super();
	}
	
	protected void addToDatabase() {
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
			
			// Find the number of entries in the table
			stmt = conn.createStatement();
			String sql = "SELECT ID FROM Products";
			ResultSet rs = stmt.executeQuery(sql);
			
			int count = 0;
			
			while(rs.next()) {
				
				count += 1;
			}
		
			rs.close();
			
			System.out.println("Inserting record into the table...");
			stmt = conn.createStatement();
			sql = "INSERT INTO Products VALUES (" + count + ", '" + productName + "', " + productStock + ")";
			stmt.executeUpdate(sql);
			conn.close();
		} 
		catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
}