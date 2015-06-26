package Runnables.RemoveProduct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import InventoryManagementSystem.Product;

public abstract class RemoveProduct implements Runnable {

	protected InputStream mInputStream;
	protected int productID;
	protected int adjustValue;

	public RemoveProduct() {

		mInputStream = null;
	}

	public void run() {

		if(!inputProductID()) 	 return;
		if(!inputConfirmation()) return;
		removeProduct();
	}

	private boolean inputProductID() {

		productID = Integer.parseInt(JOptionPane
				.showInputDialog("Enter the product ID."));

		// Check if the integer is valid
		
		return true;
	}

	private boolean inputConfirmation() {

		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog(null,
				"Are you sure you would like to remove this product?",
				"Deleting product", dialogButton);
		if (dialogResult == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	protected abstract void removeProduct();
}
