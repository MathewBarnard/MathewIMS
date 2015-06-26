package InventoryManagementSystem;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import java.util.Map;

public class GUIWindow {

	private static ArrayList<GUIWindow>  mActiveWindows = new ArrayList<GUIWindow>();
	
	private ArrayList<JComponentKey> mViewComponents = new ArrayList<JComponentKey>();
	
	private String mWindowName;
	
	// Constructor for the IMS
	public GUIWindow(String windowName) {
		
		mWindowName = windowName;
		
		if(!initialise())
			System.out.println("InventoryManagementSystem failed to initialise!");
		
		mActiveWindows.add(this);
	}
	
	public static GUIWindow findWindowByName(String name) {
		
		for (int i = 0; i < mActiveWindows.size(); ++i) {
			
			if(mActiveWindows.get(i).mWindowName.equals(name))
				return mActiveWindows.get(i);
		}
		
		return null;
	}
	
	public boolean initialise() {
		
		return true;
	}
	
	public void addComponent(String key, javax.swing.JComponent component) {
		
		mViewComponents.add(new JComponentKey(key, component));
	}
	
	public void updateComponent(String key) {
		
		for(int i = 0; i < mViewComponents.size(); ++i) {
			
			String theKey = (String)mViewComponents.get(i).getKey();
			
			if(key.equals(theKey)) {
				JTextPane pane = (JTextPane)mViewComponents.get(i).getValue();
				pane.setText("LOL");
			}
		}
	}
	
	public void updateTextPane(String key, String output) {
		
		for(int i = 0; i < mViewComponents.size(); ++i) {
			
			String theKey = (String)mViewComponents.get(i).getKey();
			
			if(key.equals(theKey)) {
				JTextPane pane = (JTextPane)mViewComponents.get(i).getValue();
				pane.setText(output);
			}
		}
	}
	
	private static class JComponentKey<String, JComponent> implements Map.Entry<String, JComponent> {
		
		private String key;
	    private JComponent component;

	    public JComponentKey(String key, JComponent component) {
	        this.key = key;
	        this.component = component;
	    }

	    public String getKey() {
	        return key;
	    }

	    public JComponent getValue() {
	        return component;
	    }

	    public JComponent setValue(JComponent component) {
	        JComponent old = this.component;
	        this.component = component;
	        return old;
	    }
	}
}

