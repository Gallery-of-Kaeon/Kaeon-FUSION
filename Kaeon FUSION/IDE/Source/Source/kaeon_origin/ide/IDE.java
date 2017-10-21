package kaeon_origin.ide;

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

import io.IO;
import kaeon_fusion.KaeonFUSION;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class IDE implements ActionListener {
	
	JTextArea input;
	JTextArea output;

	JButton run;
	JButton setArguments;
	JButton showONE;

	String arguments;
	
	PhilosophersStone fusion;

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

		frame.setTitle("Kaeon Origin");

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

			run.setText("Stop");
			run.setActionCommand("Stop");
			
			setArguments.setEnabled(false);
			showONE.setEnabled(false);

			output.setText("");

			KaeonFUSION fusion = new KaeonFUSION();
			
			this.fusion = fusion;
			
			PhilosophersStone argumentStone = getArgumentStone(getArguments(arguments));
			PhilosophersStoneUtilities.publiclyConnect(fusion, argumentStone);
			
			PhilosophersStone console = new PhilosophersStone() {
				
				public Object onCall(ArrayList<Object> packet) {
					
					if(((String) packet.get(0)).equalsIgnoreCase("Log") ||
							((String) packet.get(0)).equalsIgnoreCase("Input")) {
						
						@SuppressWarnings("unchecked")
						ArrayList<Object> processed = (ArrayList<Object>) packet.get(1);
						
						String string = "";
						
						for(Object object : processed)
							string += object;
						
						if(((String) packet.get(0)).equalsIgnoreCase("Log"))
							output.setText(output.getText() + string);
						
						if(((String) packet.get(0)).equalsIgnoreCase("Input"))
							return JOptionPane.showInputDialog(output, string);
					}
					
					return null;
				}
			};
			
			console.tags.add("Console");
			
			PhilosophersStoneUtilities.publiclyConnect(fusion, console);

			new Thread() {

				public void run() {
					
					try {
						fusion.process(ONEPlus.parseONEPlus(input.getText()));
					}
					
					catch(Exception exception) {

						output.setForeground(Color.RED);

						output.setText("An Error Occurred");
						
						exception.printStackTrace();
					}

					run.setText("Run");
					run.setActionCommand("Run");
					
					setArguments.setEnabled(true);
					showONE.setEnabled(true);
				}
			}.start();
		}

		if(command.equals("Stop"))
			PhilosophersStoneUtilities.call(fusion, "Stop");

		if(command.equals("Show ONE")) {

			try {
				
				output.setForeground(Color.BLACK);
				
				output.setText("" + ONEPlus.parseONEPlus(input.getText()));
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
	
	public static PhilosophersStone getArgumentStone(ArrayList<Object> arguments) {
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? arguments : null;
			}
		};
		
		argumentStone.tags.add("Arguments");
		
		return argumentStone;
	}
}