package stack.utilities.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;
import stack.utilities.stopper.Stopper;

public class DisplayGUI implements HyperlinkListener, WindowListener {
	
	public boolean running;
	
	public JFrame frame;
	public JEditorPane content;
	
	public ArrayList<Object> namespace;
	
	public DisplayGUI() {
		
	}
	
	public DisplayGUI(String html, PhilosophersStone stone, ArrayList<Object> namespace) {

		try {

			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

				if("Nimbus".equals(info.getName())) {
					
					UIManager.setLookAndFeel(info.getClassName());
					
					break;
				}
			}
		}

		catch(Exception exception) {

		}
		
		frame = new JFrame();
		
		this.namespace = namespace;
		
		content = new JEditorPane();
		
		content.setContentType("text/html");
		content.setText(html);
		content.setEditable(false);
		
		content.addHyperlinkListener(this);
		
		frame.add(new JScrollPane(content));
		
		frame.addWindowListener(this);
		
		frame.setSize(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().width / 3);
		
		frame.setVisible(true);
		
		((Stopper) PhilosophersStoneUtilities.get(stone, "Stack", "Stopper").get(0)).guis.add(this);
	}
	
	public static void print(String html) {
		
		JEditorPane content = new JEditorPane();
		
		content.setContentType("text/html");
		content.setText(html);
		content.setEditable(false);
		
		content.setBackground(Color.WHITE);
		
		try {
			content.print();
		}
		
		catch (Exception exception) {
			
		}
	}

	public void hyperlinkUpdate(HyperlinkEvent event) {

		try {
			content.setPage(event.getURL());
		}
		
		catch(Exception exception) {
			
		}
	}

	public void windowActivated(WindowEvent event) {
		
	}

	public void windowClosed(WindowEvent event) {
		frame.dispose();
	}

	public void windowClosing(WindowEvent event) {
		frame.dispose();
	}

	public void windowDeactivated(WindowEvent event) {
		
	}

	public void windowDeiconified(WindowEvent event) {
		
	}

	public void windowIconified(WindowEvent event) {
		
	}

	public void windowOpened(WindowEvent event) {
		
	}
}