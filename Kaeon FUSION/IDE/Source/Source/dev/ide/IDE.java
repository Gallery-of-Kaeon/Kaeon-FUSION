package dev.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import interfaces.StandardInterfacePack;
import io.IO;
import kaeon_fusion_legacy.interface_module.Interface;
import kaeon_fusion_legacy.super_mode.SuperMode;
import legacy.one_plus.ONEPlus;

public class IDE implements ActionListener {

	JTextArea input;
	JTextArea output;

	JButton run;
	JButton setArguments;
	JButton showONE;

	String arguments;

	@SuppressWarnings("serial")
	public IDE() {

		try {

			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}

		catch (Exception exception) {

		}

		int size =
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) +
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());

		JFrame frame;

		frame = new JFrame();

		frame.setTitle("Kaeon Dev");

		frame.setSize((int) (size / 4.25), (int) (size / 4.25));

		frame.setLocation(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (frame.getWidth() / 2),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (frame.getHeight() / 2));

		frame.setLayout(new GridLayout(2, 1));

		JPanel control = new JPanel();
		control.setLayout(new BorderLayout());

		JPanel io = new JPanel();
		io.setLayout(new GridLayout(1, 2));

		JButton open = new JButton("Open");
		open.setActionCommand("Open");
		open.addActionListener(this);

		JButton save = new JButton("Save");
		save.setActionCommand("Save");
		save.addActionListener(this);

		io.add(open);
		io.add(save);

		control.add(io, BorderLayout.NORTH);

		input = new JTextArea();
		input.setTabSize(4);

		UndoManager undoManager = new UndoManager();
		Document doc = input.getDocument();

		doc.addUndoableEditListener(new UndoableEditListener() {
			
			public void undoableEditHappened(UndoableEditEvent event) {
				undoManager.addEdit(event.getEdit());
			}
		});

		input.getActionMap().put("Undo", new AbstractAction("Undo") {

			public void actionPerformed(ActionEvent event) {

				try {
					
					if (undoManager.canUndo())
						undoManager.undo();
				}

				catch (Exception exception) {
					
				}
			}
		});

		input.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

		input.getActionMap().put("Redo", new AbstractAction("Redo") {
			
			public void actionPerformed(ActionEvent event) {
				
				try {
					
					if (undoManager.canRedo())
						undoManager.redo();
				}
				
				catch (Exception exception) {
					
				}
			}
		});

		input.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");

		control.add(new JScrollPane(input), BorderLayout.CENTER);

		JPanel command = new JPanel();
		command.setLayout(new GridLayout(1, 3));

		run = new JButton("Run");
		run.setActionCommand("Run");
		run.addActionListener(this);

		setArguments = new JButton("Set Arguments");
		setArguments.setActionCommand("Set Arguments");
		setArguments.addActionListener(this);

		showONE = new JButton("Show ONE");
		showONE.setActionCommand("Show ONE");
		showONE.addActionListener(this);

		command.add(setArguments);
		command.add(run);
		command.add(showONE);

		control.add(command, BorderLayout.SOUTH);

		frame.add(control);

		output = new JTextArea();
		output.setEditable(false);
		output.setTabSize(4);

		int fontSize = size / 150;

		Font font = new Font("monospaced", Font.BOLD, fontSize);

		input.setFont(font);
		output.setFont(font);

		font = new Font("None", Font.BOLD, fontSize);

		open.setFont(font);
		save.setFont(font);
		run.setFont(font);
		setArguments.setFont(font);
		showONE.setFont(font);

		Color bgCol = new Color(100, 150, 255);
		Color fgCol = new Color(255, 255, 255);

		open.setBackground(bgCol);
		open.setForeground(fgCol);

		save.setBackground(bgCol);
		save.setForeground(fgCol);

		run.setBackground(bgCol);
		run.setForeground(fgCol);

		setArguments.setBackground(bgCol);
		setArguments.setForeground(fgCol);

		showONE.setBackground(bgCol);
		showONE.setForeground(fgCol);

		input.setBackground(new Color(200, 220, 255));

		frame.add(new JScrollPane(output));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DevConsole.setConsole(output);

		arguments = "";
	}

	public void actionPerformed(ActionEvent event) {

		String command = event.getActionCommand();

		if(command.equals("Open")) {

			String open = IO.openAsString();

			if(open != null)
				input.setText(open);
		}

		if(command.equals("Save")) {
			IO.saveAs(input.getText());
		}

		if(command.equals("Set Arguments")) {

			String input = JOptionPane.showInputDialog(
					output,
					(arguments.length() > 0 ?
							("Current arguments are:\n\n" + arguments) :
							"No current arguments.") +
					"\n\nSet the program arguments:");

			if(input != null)
				arguments = input;
		}

		if(command.equals("Run")) {

			output.setForeground(Color.BLACK);

			run.setEnabled(false);
			setArguments.setEnabled(false);
			showONE.setEnabled(false);

			output.setText("");

			DevKaeonFUSION fusion = new DevKaeonFUSION();

			ArrayList<Interface> standard = StandardInterfacePack.getInterfaces();

			for(int i = 0; i < standard.size(); i++)
				fusion.publiclyConnectMutually(standard.get(i));

			try {

				String text = input.getText();
				ONEPlus code = new ONEPlus(text);

				if(text.indexOf("[SUPER]") != -1)
					SuperMode.superMode(code);

				new Thread() {

					public void run() {

						fusion.process(code, getArguments(arguments));

						run.setEnabled(true);
						setArguments.setEnabled(true);
						showONE.setEnabled(true);
					}
				}.start();
			}

			catch(Exception exception) {

				output.setForeground(Color.RED);

				output.setText("An Error Occurred");

				run.setEnabled(true);
				setArguments.setEnabled(true);
				showONE.setEnabled(true);
			}
		}

		if(command.equals("Show ONE")) {

			try {

				output.setForeground(Color.BLACK);

				String text = input.getText();
				ONEPlus code = new ONEPlus(text);

				if(text.indexOf("[SUPER]") != -1)
					SuperMode.superMode(code);

				output.setText("" + code);
			}

			catch(Exception exception) {

				output.setForeground(Color.RED);

				output.setText("Invalid ONE+");
			}
		}
	}

	public static ArrayList<Object> getArguments(String string) {

		ArrayList<Object> arguments = new ArrayList<Object>();

		if(string == null)
			return arguments;

		String argument = "";
		boolean inQuote = false;

		for(int i = 0; i < string.length(); i++) {

			if(string.charAt(i) == '\"') {

				inQuote = !inQuote;

				continue;
			}

			if(string.charAt(i) == '\\' && i < string.length() - 1) {

				argument += string.charAt(i + 1);
				i++;

				continue;
			}


			if(!inQuote && Character.isWhitespace(string.charAt(i)) && argument.length() > 0) {

				arguments.add(argument);
				argument = "";

				continue;
			}

			argument += string.charAt(i);
		}

		if(argument.length() > 0)
			arguments.add(argument);

		return arguments;
	}
}