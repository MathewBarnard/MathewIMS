package InventoryManagementSystem;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import ActionListeners.AddNewProductButton;
import ActionListeners.BasicButton;
import ActionListeners.UpdateStockButton;
import Runnables.AddNewProduct;
import Runnables.GenerateStockReport;
import Runnables.UpdateStock;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;

public class ImsWindow {

	// Declare the InventoryManagementSystem Controller
	InventoryManagementSystem ims;
	
	private JFrame frame;
	/**
	 * @wbp.nonvisual location=-19,199
	 */
	private final JTextPane textDisplay = new JTextPane();
	private JTextPane textPane;
	private JMenuBar menuBar;
	private JMenu menuFile, menuUpdateRecords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImsWindow window = new ImsWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImsWindow() {
		// Initialise the Inventory Management System
		ims = new InventoryManagementSystem();
		
		// Initialise the Inventory Management System. Close the application if initialisation fails
		if (!ims.initialise()) {
			System.out.println("IMS failed to initialise");
		}
		
		// Initialise the application Views
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		frame.getContentPane().add(textPane, BorderLayout.CENTER);
		textPane.setText("Text");
		
		// Initialise the menu bar
		menuBar = new JMenuBar();
		
		// Initialise the menu and attach it to the menuBar
		menuFile = new JMenu("File");
		
		JMenuItem menuItem = new JMenuItem("Add Product");	
		menuItem.addActionListener(new AddNewProductButton(new AddNewProduct(ims)));		
		menuFile.add(menuItem);
		
		JMenuItem menuChangeStock = new JMenuItem("Change Stock");
		menuChangeStock.addActionListener(new UpdateStockButton(new UpdateStock(ims)));
		menuFile.add(menuChangeStock);
		
		JMenuItem menuStockReport = new JMenuItem("Generate Stock Report");
		menuStockReport.addActionListener(new BasicButton(new GenerateStockReport(ims)));
		menuFile.add(menuStockReport);
		
		menuBar.add(menuFile);
		frame.setJMenuBar(menuBar);
	}
}
