package kaeon_origin.ide.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kaeon_origin.ide.IDE;
import one_plus.ONEPlus;

@SuppressWarnings("serial")
public class OptionsPane extends JFrame implements ActionListener {
	
	public IDE ide;
	public String mode;
	public String back;
	
	JPanel panel;
	
	public OptionsPane(IDE ide) {
		
		this.ide = ide;
		
		setSize(ide.scale(200), ide.scale(150));
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

//		panel.add(createButton("Set Perspective"));
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
			
			panel.removeAll();
			
			panel.setLayout(new GridLayout(2, 1));
	
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
			
			else if(back.equals("Settings")) {
				
				back = null;

				setSize(ide.scale(200), ide.scale(200));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(3, 1));
				
				panel.add(createButton("Settings"));
				panel.add(createButton("Help"));
				panel.add(createButton("Back"));
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
				
				panel.add(createButton("Show ONE"));
				panel.add(createButton("Set Arguments"));
				panel.add(createButton("Back"));
				
				back = command;
			}
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
		
		revalidate();
	}
}