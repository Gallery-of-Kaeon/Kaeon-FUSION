package kaeon_origin.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import kaeon_origin.ide.utilities.Input;
import kaeon_origin.ide.utilities.OptionsPane;
import kaeon_origin.ide.utilities.Output;
import kaeon_origin.utilties.Utilities;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class IDE implements ActionListener {

	public ArrayList<Input> inputs;
	public ArrayList<Output> outputs;

	public Input currentInput;
	public Output currentOutput;

	public JFrame frame;

	public JPanel input;
	public JPanel output;

	public JPanel in;
	public JPanel out;

	public JButton one;
	public JButton sup;

	public Font font;

	public int newFiles;
	public int newConsoles;

	public Element originData;

	public IDE(Element originData) {
		initializeFrame();
		initializeInputs(originData);
	}

	public void initializeFrame() {

		initializeLookAndFeel();

		font = new Font("None", Font.BOLD, scale(14));

		frame = new JFrame();

		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		initializeInputPanel(inputPanel);

		frame.add(inputPanel, BorderLayout.WEST);

		JPanel workPanel = new JPanel();

		initializeWorkPanel(workPanel);

		frame.add(workPanel, BorderLayout.CENTER);

		JPanel outputPanel = new JPanel();
		initializeOutputPanel(outputPanel);

		frame.add(outputPanel, BorderLayout.EAST);

		frame.setTitle("Kaeon Origin");
		frame.setSize(scale(800), scale(500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void initializeLookAndFeel() {

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
	}

	public void initializeInputPanel(JPanel inputPanel) {

		inputPanel.setLayout(new BorderLayout());
		inputPanel.setBackground(Color.WHITE);

		JPanel manage = new JPanel();
		JPanel select = new JPanel();
		JPanel input = new JPanel();

		manage.setBackground(Color.WHITE);

		select.setBackground(Color.WHITE);
		select.setLayout(new GridLayout(1, 2));

		input.setBackground(Color.WHITE);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

		inputPanel.add(new JScrollPane(input), BorderLayout.CENTER);
		inputPanel.add(manage, BorderLayout.NORTH);
		inputPanel.add(select, BorderLayout.SOUTH);

		manage.setLayout(new GridLayout( /*3*/ 2, 1));

		JPanel io = new JPanel();
		JPanel file = new JPanel();

//		manage.add(createButton("Options"));
		manage.add(io);
		manage.add(file);

		io.setLayout(new GridLayout(1, 2));
		io.setBackground(Color.WHITE);

		io.add(createButton("Open"));
		io.add(createButton("Save"));

		file.setLayout(new GridLayout(1, 2));
		file.setBackground(Color.WHITE);

		file.add(createButton("New"));
		file.add(createButton("Remove"));

		JButton all = createButton("All");
		all.setActionCommand("All Input");

		JButton none = createButton("None");
		none.setActionCommand("None Input");

		select.add(all);
		select.add(none);

		this.input = input;
	}

	public void initializeOutputPanel(JPanel outputPanel) {

		outputPanel.setLayout(new BorderLayout());
		outputPanel.setBackground(Color.WHITE);

		outputPanel.add(createButton("Clear"), BorderLayout.NORTH);

		JPanel output = new JPanel();

		JPanel select = new JPanel();

		outputPanel.add(new JScrollPane(output), BorderLayout.CENTER);
		outputPanel.add(select, BorderLayout.SOUTH);

		output.setBackground(Color.WHITE);
		output.setLayout(new BoxLayout(output, BoxLayout.Y_AXIS));

		select.setBackground(Color.WHITE);
		select.setLayout(new GridLayout(1, 2));

		JButton all = createButton("All");
		all.setActionCommand("All Output");

		JButton none = createButton("None");
		none.setActionCommand("None Output");

		select.add(all);
		select.add(none);

		this.output = output;
	}
	
	public void initializeWorkPanel(JPanel workPanel) {

		workPanel.setLayout(new GridLayout(2, 1));

		workPanel.setBackground(Color.WHITE);

		JPanel in = new JPanel();
		JPanel out = new JPanel();

		workPanel.add(in);
		workPanel.add(out);

		in.setLayout(new BorderLayout());

		JPanel build = new JPanel();
		JPanel settings = new JPanel();

		JScrollPane text = new JScrollPane();

		in.add(build, BorderLayout.SOUTH);
		in.add(settings, BorderLayout.NORTH);
		in.add(text, BorderLayout.CENTER);

		build.setLayout(new GridLayout(1, 2));
		build.setBackground(Color.WHITE);

		build.add(createButton("Run"));
		build.add(createButton("Stop"));

		settings.setLayout(new GridLayout(1, /*3*/ 2));
		settings.setBackground(Color.WHITE);

		settings.add(createButton("Set Arguments"));

		one = createButton("Show ONE");
		settings.add(one);

		sup = createButton("Enable Super");
//		settings.add(sup);

		out.setLayout(new BorderLayout());
		out.setBackground(Color.WHITE);

		JScrollPane console = new JScrollPane();

		out.add(createButton("Export"), BorderLayout.SOUTH);
		out.add(console, BorderLayout.CENTER);

		this.in = in;
		this.out = out;
	}

	public void initializeInputs(Element originData) {

		inputs = new ArrayList<Input>();
		outputs = new ArrayList<Output>();

		newFiles = 1;

		Element files = null;

		if(ElementUtilities.hasChild(originData, "Files"))
			files = ElementUtilities.getChild(originData, "Files");

		else {

			files = new Element();
			files.content = "Files";

			originData.children.add(files);
		}

		for(Element file : files.children) {

			Input input = getInput();

			int start = file.content.indexOf(File.separator) != -1 ? file.content.lastIndexOf(File.separator) + 1: 0;
			int end = file.content.indexOf('.') != -1 ? file.content.lastIndexOf('.') : file.content.length();

			input.button.setText(file.content.substring(start, end));
			input.path = file.content;

			this.input.add(input.panel);

			try {
				input.text.setText(IO.openAsString(file.content));
			}

			catch(Exception exception) {

			}

			inputs.add(input);
		}

		this.originData = originData;
	}

	public JButton createButton(String string, Color color, Color textColor) {

		JButton button = createButton(string);

		button.setBackground(color);
		button.setForeground(textColor);

		return button;
	}

	public JButton createButton(String string) {

		JButton button = new JButton(string);

		button.setFont(font);
		button.setActionCommand(string);
		button.addActionListener(this);

		button.setBackground(new Color(100, 150, 255));
		button.setForeground(Color.WHITE);

		return button;
	}

	public int scale(int size) {
		return (int) (size * ((double) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1366));
	}

	public void actionPerformed(ActionEvent actionEvent) {

		String command = actionEvent.getActionCommand();

		if(command.equals("Options")) {
			new OptionsPane(this);
		}
		
		if(command.equals("New")) {

			Input input = getInput();

			input.button.setText("New File " + newFiles);
			newFiles++;

			this.input.add(input.panel);

			setCurrentInput(input);
		}

		if(command.equals("Remove")) {

			for(int i = 0; i < inputs.size(); i++) {

				if(inputs.get(i).radioButton.isSelected()) {

					if(inputs.get(i) == currentInput) {

						currentInput = null;

						in.remove(2);
						in.add(new JPanel(), BorderLayout.CENTER);

						one.setText("Show ONE");
						one.setActionCommand("Show ONE");
					}

					input.remove(inputs.get(i).panel);

					inputs.remove(i);
					i--;
				}
			}

			for(int i = 0; i < inputs.size(); i++)
				inputs.get(i).button.setActionCommand("Input " + i);

			saveState();
		}

		if(command.indexOf("Input ") == 0) {

			int index = Integer.parseInt(command.substring(6));

			setCurrentInput(inputs.get(index));

			one.setText("Show ONE");
			one.setActionCommand("Show ONE");
		}

		if(command.equals("Enable Super")) {
			sup.setText("Disable Super");
			sup.setActionCommand("Disable Super");
		}

		if(command.equals("Disable Super")) {
			sup.setText("Enable Super");
			sup.setActionCommand("Enable Super");
		}

		if(command.equals("Run") && currentInput != null) {

			Output output = new Output();

			output.text = new JTextArea();
			output.text.setEditable(false);
			output.text.setFont(new Font(Font.MONOSPACED, Font.BOLD, scale(14)));
			output.text.setTabSize(4);

			output.pane = new JScrollPane(output.text);

			output.panel = new JPanel();

			output.panel.setBackground(Color.WHITE);

			output.radioButton = new JRadioButton();
			
			newConsoles++;
			
			output.button = createButton(currentInput.button.getText() + " - " + newConsoles);
			output.button.setActionCommand("Output " + newConsoles);

			output.panel.add(output.radioButton);
			output.panel.add(output.button);

			this.output.add(output.panel);

			KaeonFUSION fusion = new KaeonFUSION();

			output.fusion = fusion;

			outputs.add(output);

			setCurrentOutput(output);

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
							output.text.setText(output.text.getText() + string);

						if(((String) packet.get(0)).equalsIgnoreCase("Input"))
							return JOptionPane.showInputDialog(frame, string);
					}

					return null;
				}
			};

			console.tags.add("Console");

			PhilosophersStoneUtilities.publiclyConnect(fusion, console);

			PhilosophersStoneUtilities.publiclyConnect(
					fusion,
					Utilities.getArgumentStone(
							getArguments(
									currentInput.arguments)));

			new Thread(

				new Runnable() {

					public void run() {

						try {
							
							fusion.process(
									ONEPlus.parseONEPlus(
											(sup.getText().equals("Disable Super") ? "[USE: SUPER] [SUPER]\n" : "") +
											currentInput.text.getText()));
						}

						catch(Exception exception) {
							
							output.text.setForeground(Color.RED);
							output.text.setText("There was an error.");
							
							exception.printStackTrace();
						}
					}
				}
			).start();
		}

		if(command.equals("Clear")) {

			for(int i = 0; i < outputs.size(); i++) {

				if(outputs.get(i).radioButton.isSelected()) {

					if(outputs.get(i) == currentOutput) {

						currentOutput = null;

						out.remove(1);
						out.add(new JPanel(), BorderLayout.CENTER);
					}

					PhilosophersStoneUtilities.call(outputs.get(i).fusion, "Stop");
					output.remove(outputs.get(i).panel);

					outputs.remove(i);
					i--;
				}
			}

			for(int i = 0; i < outputs.size(); i++)
				outputs.get(i).button.setActionCommand("Output " + i);
		}

		if(command.indexOf("Output ") == 0) {

			int index = Integer.parseInt(command.substring(7));

			setCurrentOutput(outputs.get(index));
		}

		if(command.equals("Stop") && currentInput != null)
			PhilosophersStoneUtilities.call(currentOutput.fusion, "Stop");

		if(command.equals("All Input")) {

			for(Input input : inputs)
				input.radioButton.setSelected(true);
		}

		if(command.equals("None Input")) {

			for(Input input : inputs)
				input.radioButton.setSelected(false);
		}

		if(command.equals("All Output")) {

			for(Output output : outputs)
				output.radioButton.setSelected(true);
		}

		if(command.equals("None Output")) {

			for(Output output : outputs)
				output.radioButton.setSelected(false);
		}

		if(command.equals("Show ONE") && currentInput != null) {

			in.remove(2);

			JTextArea text = new JTextArea();

			text.setBackground(new Color(200, 220, 255));
			text.setFont(new Font(Font.MONOSPACED, Font.BOLD, scale(14)));
			text.setEditable(false);
			text.setTabSize(4);

			try {
				
				text.setText(
						"" + ONEPlus.parseONEPlus(
								(sup.getText().equals("Disable Super") ? "[USE: SUPER] [SUPER]\n" : "") +
								currentInput.text.getText()));
			}

			catch(Exception exception) {
				text.setForeground(Color.RED);
				text.setText("Invalid ONE+.");
			}

			in.add(new JScrollPane(text), BorderLayout.CENTER);

			one.setText("Show ONE+");
			one.setActionCommand("Show ONE+");
		}

		if(command.equals("Show ONE+")) {

			in.remove(2);
			in.add(currentInput.pane, BorderLayout.CENTER);

			one.setText("Show ONE");
			one.setActionCommand("Show ONE");
		}

		if(command.equals("Set Arguments") && currentInput != null) {

			String arguments = currentInput.arguments;

			if(arguments != null) {

				arguments =
						JOptionPane.showInputDialog(
								frame,
								"The current arguments are:\n\n" +
										arguments +
								"\n\nEnter the desired arguments:");
			}

			else {

				arguments =
						JOptionPane.showInputDialog(
								frame,
								"There are no current arguments.\n\n" +
								"Enter the desired arguments:");
			}

			if(arguments != null) {

				if(arguments.equals(""))
					currentInput.arguments = null;

				else
					currentInput.arguments = arguments;
			}
		}

		if(command.equals("Open")) {

			File file = IO.open();
			
			if(file == null)
				return;
			
			String content = "";

			Scanner scanner = null;

			try {
				scanner = new Scanner(file);
			}

			catch (Exception exception) {

			}

			while(scanner.hasNextLine()) {

				content += scanner.nextLine();

				if(scanner.hasNextLine())
					content += '\n';
			}

			String name = file.getAbsolutePath().substring(
					file.getAbsolutePath().lastIndexOf(File.separator) + 1,
					file.getAbsolutePath().lastIndexOf('.'));

			Input input = getInput();

			input.button.setText(name);

			this.input.add(input.panel);

			input.text.setText(content);
			input.path = file.getAbsolutePath();

			setCurrentInput(input);

			saveState();
		}

		if(command.equals("Save")) {

			for(Input input : inputs) {

				if(input.radioButton.isSelected())
					save(input);
			}
			
			if(!currentInput.radioButton.isSelected()) {
				
				int option = JOptionPane.showConfirmDialog(
						frame,
						"The current file: \"" + currentInput.button.getText() + "\", is not selected. Would you like to save it anyway?",
						"Kaeon Origin",
						JOptionPane.YES_NO_OPTION);
				
				if(option == JOptionPane.YES_OPTION)
					save(currentInput);
			}

			saveState();
		}

		if(command.equals("Export") && currentInput != null) {

			int option = JOptionPane.showConfirmDialog(
					frame,
					"WARNING.\n\n" +
					"Once you export, Kaeon Orign will close and you will no longer be able to open Kaeon Origin in GUI Mode.\n\n" +
					"It is possible to reverse this by doing one of the following:\n\n" +
					"Run the following command: java -jar \"Kaeon Origin.jar\" -reset\n" +
					"Open the ONE+ file \"Origin.op\" and place an element at the root level with the content \"reset\".\n" +
					"Delete the ONE+ file \"Origin.op\".\n\n" +
					"Are you sure you want to continue?",
					"Kaeon Origin",
					JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				
				option = JOptionPane.showConfirmDialog(
						frame,
						"Export with arguments?",
						"Kaeon Origin",
						JOptionPane.YES_NO_OPTION);
				
				if(option == JOptionPane.YES_OPTION) {
					
					String arguments = JOptionPane.showInputDialog(frame, "Enter the program arguments:");
					
					if(arguments != null) {
						
						ElementUtilities.removeChild(originData, "Arguments");
						ElementUtilities.removeChild(originData, "Prompt");
	
						Element argumentsElement = new Element();
						argumentsElement.content = "Arguments";
						
						Element args = new Element();
						args.content = arguments;
						
						ElementUtilities.addChild(argumentsElement, args);
						ElementUtilities.addChild(originData, argumentsElement);
					}
				}
				
				else {
					
					ElementUtilities.removeChild(originData, "Arguments");
					
					option = JOptionPane.showConfirmDialog(
							frame,
							"Should the application prompt for arguments?",
							"Kaeon Origin",
							JOptionPane.YES_NO_OPTION);
					
					if(option == JOptionPane.YES_OPTION &&
							!ElementUtilities.hasChild(originData, "Prompt")) {
						
						Element prompt = new Element();
						prompt.content = "Prompt";
						
						ElementUtilities.addChild(originData, prompt);
					}
					
					else
						ElementUtilities.removeChild(originData, "Prompt");
				}
				
				ElementUtilities.removeChild(originData, "Source");
				
				Element source = new Element();
				source.content = "Source";
				
				Element main = new Element();
				main.content = currentInput.path;

				ElementUtilities.addChild(source, main);
				ElementUtilities.addChild(originData, source);
				
				IO.save("" + originData, "Origin.op");
				
				System.exit(0);
			}
		}

		frame.repaint();
		frame.revalidate();
	}
	
	public void save(Input input) {

		if(input.path != null)
			IO.save(input.text.getText(), input.path);

		else {

			while(true) {

				JOptionPane.showMessageDialog(frame, "Save " + input.button.getText() + ".");

				File file = saveAs(input.text.getText());

				if(file != null) {

					String name = file.getAbsolutePath();
					input.path = name;
					
					int start = name.indexOf(File.separator) != -1 ? name.lastIndexOf(File.separator) + 1 : 0;
					int end = name.indexOf('.') != -1 ? name.lastIndexOf('.') : name.length();
					
					input.button.setText(name.substring(start, end));

					break;
				}

				int option = JOptionPane.showConfirmDialog(
						frame,
						"Would you to continue without saving " + input.button.getText() + "?",
						"Kaeon Origin",
						JOptionPane.YES_NO_OPTION);
				
				if(option == JOptionPane.YES_OPTION)
					break;
			}
		}
	}

	public Input getInput() {

		Input input = new Input();

		input.text = getInputArea();
		input.pane = new JScrollPane(input.text);
		input.radioButton = new JRadioButton();

		input.button = createButton("New File");
		input.button.setActionCommand("Input " + inputs.size());

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.add(input.radioButton);
		panel.add(input.button);

		input.panel = panel;

		inputs.add(input);

		return input;
	}

	@SuppressWarnings("serial")
	public JTextArea getInputArea() {

		JTextArea text = new JTextArea();

		text.setBackground(new Color(200, 220, 255));
		text.setFont(new Font(Font.MONOSPACED, Font.BOLD, scale(14)));
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

		return text;
	}

	public void setCurrentInput(Input input) {

		currentInput = input;

		for(int i = 0; i < inputs.size(); i++) {

			if(inputs.get(i) == currentInput)
				inputs.get(i).button.setBackground(new Color(150, 125, 25));

			else
				inputs.get(i).button.setBackground(new Color(100, 150, 255));
		}

		in.remove(2);
		in.add(currentInput.pane, BorderLayout.CENTER);
	}

	public void setCurrentOutput(Output output) {

		currentOutput = output;

		for(int i = 0; i < outputs.size(); i++) {

			if(outputs.get(i) == currentOutput)
				outputs.get(i).button.setBackground(new Color(150, 125, 25));

			else
				outputs.get(i).button.setBackground(new Color(100, 150, 255));
		}

		out.remove(1);
		out.add(currentOutput.pane, BorderLayout.CENTER);
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

	public void saveState() {

		ElementUtilities.removeChild(originData, "Files");

		Element files = new Element();
		files.content = "Files";

		for(Input input : inputs) {

			if(input.path == null)
				continue;
			
			boolean repeat = false;
			
			for(Element file : files.children) {
				
				if(input.path.equalsIgnoreCase(file.content)) {
					
					repeat = true;
					
					break;
				}
			}
			
			if(repeat)
				continue;
			
			Element file = new Element();
			file.content = input.path;

			ElementUtilities.addChild(files, file);
		}

		ElementUtilities.addChild(originData, files);
		
		IO.save("" + originData, "Origin.op");
	}

	public static File saveAs(String file) {

		ArrayList<String> lines = new ArrayList<String>();
		lines.add(file);

		return saveAs(lines, "op");
	}

	public static File saveAs(ArrayList<String> lines, String fileExtension) {

		JFileChooser fc = new JFileChooser();
		PrintWriter outputStream = null;

		int rval = fc.showSaveDialog(fc);

		File file = null;

		if(rval == JFileChooser.APPROVE_OPTION) {

			file = fc.getSelectedFile();

			try {

				fileExtension = fileExtension.trim();

				if(fileExtension.length() > 0) {

					if(file.toString().indexOf('.') == -1)
						file = new File(file + "." + fileExtension);

					else if(!file.toString().substring(file.toString().lastIndexOf('.') + 1).equals(fileExtension))
						file = new File(file + "." + fileExtension);

					else
						file = new File(file.toString());
				}

				else
					file = new File(file.toString());

				outputStream = new PrintWriter(new FileOutputStream(file));

				for(int i = 0; i < lines.size(); i++) {

					if(i < lines.size() - 1)
						outputStream.println(lines.get(i));

					else
						outputStream.print(lines.get(i));
				}

				outputStream.close();
			}

			catch(Exception exception) {

			}
		}

		return file;
	}
}