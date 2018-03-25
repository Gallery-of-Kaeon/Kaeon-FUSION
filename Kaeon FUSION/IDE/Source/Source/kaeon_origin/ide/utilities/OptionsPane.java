package kaeon_origin.ide.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import io.IO;
import kaeon_origin.ide.IDE;
import one_plus.ONEPlus;

@SuppressWarnings("serial")
public class OptionsPane extends JFrame implements ActionListener {
	
	public IDE ide;
	
	public String mode;
	public String back;
	
	public JPanel panel;
	public JPanel enlargePanel;
	
	public OptionsPane(IDE ide) {
		
		this.ide = ide;
		
		setSize(ide.scale(200), ide.scale(150));
		setResizable(false);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			
            public void windowClosing(WindowEvent e) {
            	ide.options.setEnabled(true);
            }
        });
		
		panel = new JPanel();
		
		add(panel);
		
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(new GridLayout(2, 1));

		panel.add(createButton("Kaeon Origin"));
		panel.add(createButton("Kaeon FUSION"));
	}

	public JButton createButton(String string) {

		JButton button = new JButton(string);

		button.setFont(ide.font);
		button.setActionCommand(string);
		button.addActionListener(this);

		button.setBackground(new Color(100, 150, 255));
		button.setForeground(Color.WHITE);

		return button;
	}

	public void actionPerformed(ActionEvent event) {
		
		String command = event.getActionCommand();
		
		if(command.equals("Kaeon Origin")) {

			setSize(ide.scale(200), ide.scale(200));
			
			panel.removeAll();
			
			panel.setLayout(new GridLayout(3, 1));

			panel.add(createButton("Settings"));
			panel.add(createButton("Help"));
			panel.add(createButton("Back"));
			
			mode = command;
		}
		
		if(command.equals("Kaeon FUSION")) {

			setSize(ide.scale(200), ide.scale(200));
			
			panel.removeAll();
			
			panel.setLayout(new GridLayout(3, 1));
	
			panel.add(createButton("Settings"));
			panel.add(createButton("Help"));
			panel.add(createButton("Back"));
			
			mode = command;
		}
		
		if(command.equals("Back")) {
			
			if(back == null) {
	
				setSize(ide.scale(200), ide.scale(150));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(2, 1));
				
				panel.add(createButton("Kaeon Origin"));
				panel.add(createButton("Kaeon FUSION"));
			}
			
			else if(back.startsWith("Settings")) {
				
				back = null;

				setSize(ide.scale(200), ide.scale(200));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(3, 1));
				
				panel.add(createButton("Settings"));
				panel.add(createButton("Help"));
				panel.add(createButton("Back"));
			}
			
			else if(back.startsWith("Set View")) {
				
				command = "Settings";
				
				back = "Kaeon Origin";
			}
		}
		
		if(command.equals("Help")) {
			
			try {
				
				if(mode.equals("Kaeon Origin"))
					Runtime.getRuntime().exec("explorer \"https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md\"");
				
				if(mode.equals("Kaeon FUSION"))
					Runtime.getRuntime().exec("explorer \"https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/README.md\"");
			}
			
			catch(Exception exception) {
				
			}
		}
		
		if(command.equals("Settings")) {

			if(mode.equals("Kaeon FUSION")) {
				
				setSize(ide.scale(200), ide.scale(200));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(3, 1));

//				panel.add(createButton("Set Workspace"));
				panel.add(createButton("Set Arguments"));
				panel.add(createButton("Show ONE"));
				panel.add(createButton("Back"));
			}

			if(mode.equals("Kaeon Origin")) {
				
				setSize(ide.scale(200), ide.scale(250));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(4, 1));
				
				panel.add(createButton("Set Perspective"));
//				panel.add(createButton("Set Workspace"));
				panel.add(createButton("Set View"));
				panel.add(createButton("Print"));
				panel.add(createButton("Back"));
			}
			
			back = command + " " + mode;
		}
		
		if(command.equals("Enlarge Input") && !ide.inputEnlarged && ide.currentInput != null) {
			
			ide.inputEnlarged = true;
			ide.outputEnlarged = false;
			
			ide.base.removeAll();
			
			enlargePanel = new JPanel();
			enlargePanel.setLayout(new BorderLayout());
			
			JTextArea text = new JTextArea(ide.currentInput.text.getText());
			
			text.setFont(new Font(Font.MONOSPACED, Font.BOLD, ide.scale(14)));
			text.setTabSize(4);
			
			ide.enlargeText = text;
			
			enlargePanel.add(new JScrollPane(text), BorderLayout.CENTER);
			enlargePanel.add(ide.createButton("Options"), BorderLayout.NORTH);
			
			ide.base.add(enlargePanel);
			
			ide.frame.revalidate();
		}
		
		if(command.equals("Enlarge Output") && !ide.outputEnlarged && ide.currentOutput != null) {
			
			ide.outputEnlarged = true;
			ide.inputEnlarged = false;
			
			ide.base.removeAll();
			
			enlargePanel = new JPanel();
			enlargePanel.setLayout(new BorderLayout());
			
			ide.currentInput.text.setText(ide.enlargeText.getText());

			JTextArea text = new JTextArea(ide.currentOutput.text.getText());
			
			text.setFont(new Font(Font.MONOSPACED, Font.BOLD, ide.scale(14)));
			text.setEditable(false);
			text.setTabSize(4);
			
			enlargePanel.add(new JScrollPane(text), BorderLayout.CENTER);
			enlargePanel.add(ide.createButton("Options"), BorderLayout.NORTH);
			
			ide.base.add(enlargePanel);
			
			ide.frame.revalidate();
		}
		
		if((command.equals("Minimize Input") && ide.inputEnlarged) ||
				(command.equals("Minimize Output") && ide.outputEnlarged)) {
			
			ide.inputEnlarged = false;
			ide.outputEnlarged = false;
			
			if(command.equals("Minimize Input"))
				ide.currentInput.text.setText(ide.enlargeText.getText());
			
			ide.base.removeAll();
			ide.base.setLayout(new GridLayout(1, 1));
			
			ide.base.add(ide.content);

			ide.base.repaint();
			ide.frame.revalidate();
		}
		
		if(command.equals("Set View") ||
				command.equals("Enlarge Input") ||
				command.equals("Enlarge Output") ||
				command.equals("Minimize Input") ||
				command.equals("Minimize Output")) {
			
			setSize(ide.scale(200), ide.scale(200));
			
			panel.removeAll();
			
			panel.setLayout(new GridLayout(3, 1));
			
			panel.add(createButton(!ide.inputEnlarged ? "Enlarge Input" : "Minimize Input"));
			panel.add(createButton(!ide.outputEnlarged ? "Enlarge Output" : "Minimize Output"));
			panel.add(createButton("Back"));
			
			back = "Set View";
		}
		
		if(command.equals("Print") && ide.currentInput != null) {
			
			BufferedImage image =
					new BufferedImage(
							ide.currentInput.text.getWidth(),
							ide.currentInput.text.getHeight(),
							BufferedImage.TYPE_INT_ARGB);
			
			ide.currentInput.text.paint(image.getGraphics());
			
			Printer.print(image);
		}

		if(command.equals("Set Arguments") && ide.currentInput != null) {

			String arguments = ide.currentInput.arguments;

			if(arguments != null) {

				arguments =
						JOptionPane.showInputDialog(
								ide.frame,
								"The current arguments are:\n\n" +
										arguments +
								"\n\nEnter the desired arguments:");
			}

			else {

				arguments =
						JOptionPane.showInputDialog(
								ide.frame,
								"There are no current arguments.\n\n" +
								"Enter the desired arguments:");
			}

			if(arguments != null) {

				if(arguments.equals(""))
					ide.currentInput.arguments = null;

				else
					ide.currentInput.arguments = arguments;
			}
		}

		if(command.equals("Show ONE") && ide.currentInput != null) {
			
			JFrame oneFrame = new JFrame();

			oneFrame.setSize(ide.scale(500), ide.scale(500));
			oneFrame.setVisible(true);
			
			oneFrame.setLayout(new BorderLayout());
			
			JTextArea text = new JTextArea();

			text.setBackground(Color.WHITE);
			text.setFont(new Font(Font.MONOSPACED, Font.BOLD, ide.scale(14)));
			text.setEditable(false);
			text.setTabSize(4);

			try {
				
				text.setText(
						"" + ONEPlus.parseONEPlus(
								ide.currentInput.text.getText()));
			}

			catch(Exception exception) {
				text.setForeground(Color.RED);
				text.setText("Invalid ONE+.");
				exception.printStackTrace();
			}

			oneFrame.add(new JScrollPane(text), BorderLayout.CENTER);
		}
		
		if(command.equals("Set Perspective")) {
			
			if(IO.open() != null)
				JOptionPane.showMessageDialog(this, "Invalid perspective.");
		}
		
		revalidate();
	}
}