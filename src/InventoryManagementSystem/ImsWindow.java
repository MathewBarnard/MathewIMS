package InventoryManagementSystem;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import ActionListeners.BasicButton;
import LiveStockSimulation.LiveStockSimulator;
import LiveStockSimulation.StartStockSimulation;
import Runnables.AddNewProduct.AddNewProduct;
import Runnables.AddNewProduct.AddNewProductLocal;
import Runnables.AddNewProduct.AddNewProductRemote;
import Runnables.GenerateStockReport.GenerateStockReport;
import Runnables.RemoveProduct.RemoveProduct;
import Runnables.RemoveProduct.RemoveProductLocal;
import Runnables.UpdateStock.UpdateStock;
import Runnables.UpdateStock.UpdateStockLocal;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;

public class ImsWindow {
	
	private JFrame frame;
	/**
	 * @wbp.nonvisual location=-19,199
	 */
	private final JTextPane textDisplay = new JTextPane();
	private JTextPane textPane;
	private JMenuBar menuBar;
	private JMenu menuFile, menuUpdateRecords;
	
	private GUIWindow mMainWindow;

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
		// Initialise the application Views
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		mMainWindow = new GUIWindow("Main Window");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		frame.getContentPane().add(textPane, BorderLayout.CENTER);
		
		mMainWindow.addComponent("MainOutput", textPane);
		
		// Initialise the menu bar
		menuBar = new JMenuBar();
		
		// Initialise the menu and attach it to the menuBar
		menuFile = new JMenu("File");
		
		JMenuItem menuAddProduct = new JMenuItem("Add Product");	
		menuAddProduct.addActionListener(new BasicButton(new AddNewProductLocal()));		
		menuFile.add(menuAddProduct);
		
		JMenuItem menuRemoveProduct = new JMenuItem("Remove Product");	
		menuRemoveProduct.addActionListener(new BasicButton(new RemoveProductLocal()));		
		menuFile.add(menuRemoveProduct);
		
		JMenuItem menuChangeStock = new JMenuItem("Change Stock");
		menuChangeStock.addActionListener(new BasicButton(new UpdateStockLocal(textPane)));
		menuFile.add(menuChangeStock);
		
		JMenuItem menuStockReport = new JMenuItem("Generate Stock Report");
		menuStockReport.addActionListener(new BasicButton(new GenerateStockReport()));
		menuFile.add(menuStockReport);
		
		JMenuItem menuLiveSimulation = new JMenuItem("Start Live Simulation");
		menuLiveSimulation.addActionListener(new BasicButton(new StartStockSimulation()));
		menuFile.add(menuLiveSimulation);
		
		menuBar.add(menuFile);
		frame.setJMenuBar(menuBar);
	}
}
