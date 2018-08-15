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
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import io.IO;
import kaeon_origin.ide.IDE;
import kaeon_origin.ide.utilities.web.VerticalLayout;
import one_plus.ONEPlus;

@SuppressWarnings("serial")
public class OptionsPane extends JFrame implements ActionListener {
	
	public IDE ide;
	
	public String mode;
	public String back;
	
	public JPanel panel;
	public JPanel enlargePanel;
	
	public JPanel workspacePanel;
	
	public OptionsPane(IDE ide) {
		
		this.ide = ide;
		
		setLocation(ide.frame.getX() + ide.scale(300), ide.frame.getY() + ide.scale(150));
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
				
				back = "Settings";
			}
			
			else if(back.startsWith("Set Workspace")) {
				
				command = "Settings";
				
				back = "Settings";
			}
		}
		
		if(command.equals("Help")) {
			
			try {
				
				if(mode.equals("Kaeon Origin"))
					Runtime.getRuntime().exec("explorer \"" + ide.originHelp + "\"");
				
				if(mode.equals("Kaeon FUSION"))
					Runtime.getRuntime().exec("explorer \"" + ide.fusionHelp + "\"");
			}
			
			catch(Exception exception) {
				
			}
		}
		
		if(command.equals("Settings")) {

			if(mode.equals("Kaeon FUSION")) {
				
				setSize(ide.scale(200), ide.scale(250));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(4, 1));

				panel.add(createButton("Set Workspace"));
				panel.add(createButton("Set Arguments"));
				panel.add(createButton("Show ONE"));
				panel.add(createButton("Back"));
			}

			if(mode.equals("Kaeon Origin")) {
				
				setSize(ide.scale(200), ide.scale(400));
				
				panel.removeAll();
				
				panel.setLayout(new GridLayout(7, 1));
				
				panel.add(createButton("Set Perspective"));
				panel.add(createButton("Set Workspace"));
				panel.add(createButton("Set Update Path"));
				panel.add(createButton("Set Default File Extension"));
				panel.add(createButton("Set View"));
				panel.add(createButton("Print"));
				panel.add(createButton("Back"));
			}
			
			back = command + " " + mode;
		}
		
		if(command.equals("Set Workspace")) {
			
			setSize(ide.scale(300), ide.scale(400));
			
			panel.removeAll();
			
			panel.setLayout(new BorderLayout());
			
			JPanel manage = new JPanel();
			
			manage.setLayout(new GridLayout(2, 1));
			manage.setBackground(Color.WHITE);
			
			JPanel add = new JPanel();
			
			add.setLayout(new GridLayout(1, 2));
			add.setBackground(Color.WHITE);
			
			add.add(createButton("Set Build"));
			add.add(createButton("Add Source"));
			
			manage.add(add);
			manage.add(createButton("Remove"));
			
			workspacePanel = new JPanel();
			
			workspacePanel.setBackground(Color.WHITE);
			workspacePanel.setLayout(new VerticalLayout());
			
			if(mode.equals("Kaeon Origin")) {
				
				for(int i = 0; i < ide.originSource.size(); i++) {
					
					JPanel panel = new JPanel();
					panel.setBackground(Color.WHITE);
					
					panel.add(new JRadioButton());
					
					JLabel label = new JLabel(ide.originSource.get(i));
					label.setFont(ide.font);
					
					panel.add(label);
					
					workspacePanel.add(panel);
				}
			}
			
			if(mode.equals("Kaeon FUSION")) {
				
				for(int i = 0; i < ide.fusionSource.size(); i++) {
					
					JPanel panel = new JPanel();
					panel.setBackground(Color.WHITE);
					
					panel.add(new JRadioButton());
					
					JLabel label = new JLabel(ide.fusionSource.get(i));
					label.setFont(ide.font);
					
					panel.add(label);
					
					workspacePanel.add(panel);
				}
			}
			
			JPanel navigate = new JPanel();
			
			navigate.setLayout(new GridLayout(2, 1));
			navigate.setBackground(Color.WHITE);
			
			JPanel select = new JPanel();
			
			select.setLayout(new GridLayout(1, 2));
			
			select.add(createButton("All"));
			select.add(createButton("None"));
			
			navigate.add(select);
			navigate.add(createButton("Back"));
			
			panel.add(manage, BorderLayout.NORTH);
			panel.add(new JScrollPane(workspacePanel), BorderLayout.CENTER);
			panel.add(navigate, BorderLayout.SOUTH);
			
			back = command + " " + mode;
		}

		if(command.equals("Set Build")) {
			
			if(mode.equals("Kaeon Origin")) {
				
				int option =
						JOptionPane.showConfirmDialog(
								this,
								(ide.originBuild != null ?
										"The current build workspace is " +
										ide.originBuild +
										".\n\nWould you like to change the build workspace?" :
										"There is no build workspace currently set." +
										"\n\nWould you like to set a build workspace?"));
				
				if(option == JOptionPane.YES_OPTION) {
					
					String directory = getDirectory();
					
					if(directory != null)
						ide.originBuild = directory;
					
					ide.saveState();
				}
				
				else if(ide.originBuild != null) {
					
					option =
							JOptionPane.showConfirmDialog(
									this,
									"Would you like to disable the current build workspace?");
					
					if(option == JOptionPane.YES_OPTION)
						ide.originBuild = null;
					
					ide.saveState();
				}
			}
			
			if(mode.equals("Kaeon FUSION")) {
				
				int option =
						JOptionPane.showConfirmDialog(
								this,
								(ide.fusionBuild != null ?
										"The current build workspace is " +
										ide.fusionBuild +
										".\n\nWould you like to change the build workspace?" :
										"There is no build workspace currently set." +
										"\n\nWould you like to set a build workspace?"));
				
				if(option == JOptionPane.YES_OPTION) {
					
					String directory = getDirectory();
					
					if(directory != null)
						ide.fusionBuild = directory;
					
					ide.saveState();
				}
				
				else if(ide.fusionBuild != null) {
					
					option =
							JOptionPane.showConfirmDialog(
									this,
									"Would you like to disable the current build workspace?");
					
					if(option == JOptionPane.YES_OPTION)
						ide.fusionBuild = null;
					
					ide.saveState();
				}
			}
		}

		if(command.equals("Add Source")) {
			
			String directory = getDirectory();
			
			if(directory != null) {
				
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				
				panel.add(new JRadioButton());
				
				JLabel label = new JLabel(directory);
				label.setFont(ide.font);
				
				panel.add(label);
				
				workspacePanel.add(panel);
				
				if(mode.equals("Kaeon Origin"))
					ide.originSource.add(directory);
				
				if(mode.equals("Kaeon FUSION"))
					ide.fusionSource.add(directory);
				
				ide.saveState();
			}
		}

		if(command.equals("All")) {
			
			for(int i = 0; i < workspacePanel.getComponentCount(); i++)
				((JRadioButton) ((JPanel) workspacePanel.getComponent(i)).getComponent(0)).setSelected(true);
		}

		if(command.equals("None")) {
			
			for(int i = 0; i < workspacePanel.getComponentCount(); i++)
				((JRadioButton) ((JPanel) workspacePanel.getComponent(i)).getComponent(0)).setSelected(false);
		}

		if(command.equals("Remove")) {
			
			for(int i = 0; i < workspacePanel.getComponentCount(); i++) {
				
				if(((JRadioButton) ((JPanel) workspacePanel.getComponent(i)).getComponent(0)).isSelected()) {
					
					if(mode.equals("Kaeon Origin"))
						ide.originSource.remove(i);
					
					if(mode.equals("Kaeon FUSION"))
						ide.originSource.remove(i);
					
					workspacePanel.remove(i);
					
					i--;
				}
			}
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

			UndoManager undoManager = new UndoManager();
			Document doc = text.getDocument();

			doc.addUndoableEditListener(new UndoableEditListener() {

				public void undoableEditHappened(UndoableEditEvent event) {
					undoManager.addEdit(event.getEdit());
				}
			});

			text.getActionMap().put("Undo", new AbstractAction("Undo") {

				public void actionPerformed(ActionEvent event) {

					try {

						if (undoManager.canUndo())
							undoManager.undo();
					}

					catch (Exception exception) {

					}
				}
			});

			text.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

			text.getActionMap().put("Redo", new AbstractAction("Redo") {

				public void actionPerformed(ActionEvent event) {

					try {

						if (undoManager.canRedo())
							undoManager.redo();
					}

					catch (Exception exception) {

					}
				}
			});

			text.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
			
			ide.enlargeText = text;
			ide.enlargeOutput = null;
			
			enlargePanel.add(new JScrollPane(text), BorderLayout.CENTER);
			enlargePanel.add(ide.createButton("Options"), BorderLayout.NORTH);
			
			ide.base.add(enlargePanel);
			
			ide.frame.revalidate();
		}
		
		if(command.equals("Enlarge Output") && !ide.outputEnlarged && ide.currentOutput != null) {
			
			ide.base.removeAll();
			
			enlargePanel = new JPanel();
			enlargePanel.setLayout(new BorderLayout());
			
			if(ide.inputEnlarged)
				ide.currentInput.text.setText(ide.enlargeText.getText());

			JTextArea text = new JTextArea(ide.currentOutput.text.getText());
			
			text.setFont(new Font(Font.MONOSPACED, Font.BOLD, ide.scale(14)));
			text.setEditable(false);
			text.setTabSize(4);
			
			ide.enlargeOutput = text;
			
			enlargePanel.add(new JScrollPane(text), BorderLayout.CENTER);
			enlargePanel.add(ide.createButton("Options"), BorderLayout.NORTH);
			
			ide.base.add(enlargePanel);
			
			ide.frame.revalidate();
			
			ide.outputEnlarged = true;
			ide.inputEnlarged = false;
		}
		
		if((command.equals("Minimize Input") && ide.inputEnlarged) ||
				(command.equals("Minimize Output") && ide.outputEnlarged)) {
			
			ide.inputEnlarged = false;
			ide.outputEnlarged = false;
			
			if(command.equals("Minimize Input"))
				ide.currentInput.text.setText(ide.enlargeText.getText());
			
			if(command.equals("Minimize Output"))
				ide.enlargeOutput = null;
			
			ide.base.removeAll();
			ide.base.setLayout(new GridLayout(1, 1));
			
			ide.base.add(ide.content);

			ide.base.repaint();
			ide.frame.revalidate();
		}

		if(command.equals("Set Update Path")) {
			
			int option = 0;
			
			if(ide.updatePath.length() == 0) {
				
				option =
						JOptionPane.showConfirmDialog(
								this,
								"No update path is set.\n" +
								"Would you like to set it?");
			}
			
			else {
				
				option =
						JOptionPane.showConfirmDialog(
								this,
								"The current update path is:\n\n" +
								ide.updatePath +
								"\n\n" +
								"Would you like to change it?");
			}
			
			if(option == JOptionPane.YES_OPTION) {
				
				String updatePath = JOptionPane.showInputDialog(this, "Set the update path:");
				
				if(updatePath != null)
					ide.updatePath = updatePath;
			}
		}

		if(command.equals("Set Default File Extension")) {
			
			int option =
					JOptionPane.showConfirmDialog(
							this,
							"The current default file extension is:\n\n" +
							ide.defaultFileExtension +
							"\n\n" +
							"Would you like to change it?");
			
			if(option == JOptionPane.YES_OPTION) {
				
				String defaultFileExtension = JOptionPane.showInputDialog(this, "Set the default file extension:");
				
				if(defaultFileExtension != null)
					ide.defaultFileExtension = defaultFileExtension;
			}
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
								this,
								"The current arguments are:\n\n" +
										arguments +
								"\n\nEnter the desired arguments:");
			}

			else {

				arguments =
						JOptionPane.showInputDialog(
								this,
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
			}

			oneFrame.add(new JScrollPane(text), BorderLayout.CENTER);
		}
		
		if(command.equals("Set Perspective")) {
			
			if(IO.open() != null)
				JOptionPane.showMessageDialog(this, "Invalid perspective.");
		}
		
		revalidate();
	}
	
	public String getDirectory() {

		JFileChooser chooser = new JFileChooser(); 
		
		if(ide.openFolder != null)
			chooser.setCurrentDirectory(new java.io.File(ide.openFolder));
		
		else
			chooser.setCurrentDirectory(new java.io.File("."));
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile().getAbsolutePath() + File.separator;
		
		return null;
	}
}