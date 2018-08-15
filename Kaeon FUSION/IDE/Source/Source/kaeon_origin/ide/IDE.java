package kaeon_origin.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
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
import kaeon_origin.ide.utilities.web.VerticalLayout;
import kaeon_origin.utilties.Utilities;
import one.Element;
import one.ElementUtilities;
import one_plus.ONEPlus;
import philosophers_stone.PhilosophersStone;
import philosophers_stone.PhilosophersStoneUtilities;

public class IDE extends PhilosophersStone implements ActionListener {

	public ArrayList<Input> inputs;
	public ArrayList<Output> outputs;

	public Input currentInput;
	public Output currentOutput;

	public JFrame frame;

	public JPanel base;
	public JPanel content;

	public JPanel input;
	public JPanel output;

	public JPanel in;
	public JPanel out;

	public JButton one;
	public JButton options;
	
	public boolean inputEnlarged;
	public boolean outputEnlarged;
	
	public JTextArea enlargeText;
	public JTextArea enlargeOutput;

	public Font font;

	public int newFiles;
	public int newConsoles;
	
	public String openFolder;
	public String saveFolder;
	
	public String originBuild;
	public ArrayList<String> originSource;
	
	public String fusionBuild;
	public ArrayList<String> fusionSource;
	
	public String updatePath;
	
	public String originHelp;
	public String fusionHelp;
	
	public String defaultFileExtension;

	public IDE(Element originData) {
		
		tags.add("Kaeon Origin");

		inputs = new ArrayList<Input>();
		outputs = new ArrayList<Output>();

		newFiles = 1;
		
		originSource = new ArrayList<String>();
		fusionSource = new ArrayList<String>();
		
		initializeFrame();
		
		try {
			defaultFileExtension = ElementUtilities.getChild(originData, "Default File Extension").children.get(0).content;
		}
		
		catch(Exception exception) {
			defaultFileExtension = "op";
		}
		
		try {
			
			if(originData.children.size() > 0)
				initializeInputs(originData);
		}
		
		catch(Exception exception) {
			
		}
		
		frame.revalidate();
		
		frame.setVisible(true);
		
		new Thread() {
			
			public void run() {
				frame.repaint();
				frame.revalidate();
			}
		}.start();
	}

	public void initializeFrame() {

		initializeLookAndFeel();

		font = new Font("None", Font.BOLD, scale(14));

		frame = new JFrame();
		frame.setLayout(new GridLayout(1, 1));
		
		base = new JPanel();
		base.setLayout(new GridLayout(1, 1));
		
		content = new JPanel();
		
		content.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		initializeInputPanel(inputPanel);

		content.add(inputPanel, BorderLayout.WEST);

		JPanel workPanel = new JPanel();

		initializeWorkPanel(workPanel);

		content.add(workPanel, BorderLayout.CENTER);

		JPanel outputPanel = new JPanel();
		initializeOutputPanel(outputPanel);

		content.add(outputPanel, BorderLayout.EAST);
		
		base.add(content);
		frame.add(base);

		frame.setTitle("Kaeon Origin - Kaeon FUSION");
		frame.setSize(scale(800), scale(500));
		
		frame.addWindowListener(new WindowAdapter() {
			
		    public void windowClosing(WindowEvent e) {
		    	
		    	saveState();
		    	
		        System.exit(0);
		    }
		});
	}

	public static void initializeLookAndFeel() {

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
		input.setLayout(new VerticalLayout());

		inputPanel.add(new JScrollPane(input), BorderLayout.CENTER);
		inputPanel.add(manage, BorderLayout.NORTH);
		inputPanel.add(select, BorderLayout.SOUTH);

		manage.setLayout(new GridLayout(2, 1));

		JPanel io = new JPanel();
		JPanel file = new JPanel();

		options = createButton("Options");
		
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
		output.setLayout(new VerticalLayout());

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

		settings.setLayout(new GridLayout(1, 1));
		settings.setBackground(Color.WHITE);
		
		one = createButton("ONE");
		
		options = createButton("Options");
		settings.add(options);

		out.setLayout(new BorderLayout());
		out.setBackground(Color.WHITE);

		JScrollPane console = new JScrollPane();

		out.add(createButton("Export"), BorderLayout.SOUTH);
		out.add(console, BorderLayout.CENTER);

		this.in = in;
		this.out = out;
	}

	public void initializeInputs(Element originData) {

		Element files = null;
		
		try {
			updatePath = ElementUtilities.getChild(originData, "Update Path").children.get(0).content;
		}
		
		catch(Exception exception) {
			updatePath = "";
		}
		
		try {
			originHelp = ElementUtilities.getChild(originData, "Kaeon Origin Help").children.get(0).content;
		}
		
		catch(Exception exception) {
			originHelp = "";
		}
		
		try {
			fusionHelp = ElementUtilities.getChild(originData, "Kaeon FUSION Help").children.get(0).content;
		}
		
		catch(Exception exception) {
			fusionHelp = "";
		}
		
		try {
			
			files =
					ElementUtilities.getChild(
							ElementUtilities.getChild(
									ElementUtilities.getChild(originData, "Perspectives"),
									"Kaeon FUSION"),
							"Files");
		}
		
		catch(Exception exception) {
			files = new Element();
		}
		
		try {
			
			for(int i = 0; i < files.children.size(); i++) {
				
				Element file = files.children.get(i);
				
				Input input = getInput();
				
				input.button.setText(file.content);
				
				if(ElementUtilities.hasChild(file, "Path")) {
					
					input.path = ElementUtilities.getChild(file, "Path").children.get(0).content;
					
					try {
						input.text.setText(IO.openAsString(input.path));
					}
					
					catch(Exception exception) {
						
					}
				}
				
				else if(ElementUtilities.hasChild(file, "Source"))
					input.text.setText(ElementUtilities.getChild(file, "Source").children.get(0).content);
				
				if(ElementUtilities.hasChild(file, "Arguments"))
					input.arguments = ElementUtilities.getChild(file, "Arguments").children.get(0).content;
				
				this.input.add(input.panel);
			}
			
			String build =
					ElementUtilities.getChild(
							ElementUtilities.getChild(
									ElementUtilities.getChild(
											originData,
											"Kaeon Origin"),
									"Workspace"),
							"Build").children.get(0).content;
			
			if(build.length() > 0)
				originBuild = build;
			
			Element source =
					ElementUtilities.getChild(
							ElementUtilities.getChild(
									ElementUtilities.getChild(
											originData,
											"Kaeon Origin"),
									"Workspace"),
							"Source");
			
			for(int i = 0; i < source.children.size(); i++)
				originSource.add(source.children.get(i).content);
			
			build =
					ElementUtilities.getChild(
						ElementUtilities.getChild(
								ElementUtilities.getChild(
										ElementUtilities.getChild(
												originData,
												"Perspectives"),
										"Kaeon FUSION"),
								"Workspace"),
						"Build").children.get(0).content;
			
			if(build.length() > 0)
				fusionBuild = build;
			
			source =
					ElementUtilities.getChild(
							ElementUtilities.getChild(
									ElementUtilities.getChild(
											ElementUtilities.getChild(
													originData,
													"Perspectives"),
											"Kaeon FUSION"),
									"Workspace"),
							"Source");
			
			for(int i = 0; i < source.children.size(); i++)
				fusionSource.add(source.children.get(i).content);
		}
		
		catch(Exception exception) {
			
		}
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
			
			int newFiles = 1;
			
			while(true) {
				
				input.button.setText("New File " + newFiles);
				newFiles++;
				
				boolean duplicate = false;
				
				for(int i = 0; i < inputs.size(); i++) {
					
					if(input != inputs.get(i) &&
							inputs.get(i).button.getText().equals("New File " + (newFiles - 1))) {
						
						duplicate = true;
						
						break;
					}
				}
				
				if(!duplicate)
					break;
			}

			this.input.add(input.panel);

			setCurrentInput(input);

			one.setBackground(new Color(100, 150, 255));
			one.setText("Show ONE");
			one.setActionCommand("Show ONE");
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

			one.setBackground(new Color(100, 150, 255));
			one.setText("Show ONE");
			one.setActionCommand("Show ONE");
		}

		if(command.equals("Enable Super")) {
			options.setBackground(new Color(150, 125, 25));
			options.setText("Disable Super");
			options.setActionCommand("Disable Super");
		}

		if(command.equals("Disable Super")) {
			options.setBackground(new Color(100, 150, 255));
			options.setText("Enable Super");
			options.setActionCommand("Enable Super");
		}

		if(command.equals("Run") && currentInput != null) {

			Output output = new Output();

			output.text = new JTextArea();
			output.text.setEditable(false);
			output.text.setFont(new Font(Font.MONOSPACED, Font.BOLD, scale(14)));
			output.text.setTabSize(4);
			output.text.setForeground(Color.BLACK);

			output.pane = new JScrollPane(output.text);

			output.panel = new JPanel();

			output.panel.setBackground(Color.WHITE);

			output.radioButton = new JRadioButton();
			
			int newConsoles = 0;
			
			while(true) {
				
				newConsoles++;
				
				output.button = createButton(currentInput.button.getText() + " - " + newConsoles);
				output.button.setActionCommand("Output " + newConsoles);
				
				boolean duplicate = false;
				
				for(int i = 0; i < outputs.size(); i++) {
					
					if(output != outputs.get(i) &&
							output.button.getText().equals(outputs.get(i).button.getText())) {
						
						duplicate = true;
					}
				}
				
				if(!duplicate)
					break;
			}

			output.panel.add(output.radioButton);
			output.panel.add(output.button);

			this.output.add(output.panel);

			KaeonFUSION fusion = new KaeonFUSION();
			
			PhilosophersStoneUtilities.publiclyConnect(fusion, this);

			output.fusion = fusion;

			outputs.add(output);

			setCurrentOutput(output);

			for(int i = 0; i < outputs.size(); i++)
				outputs.get(i).button.setActionCommand("Output " + i);

			PhilosophersStone console = new PhilosophersStone() {

				public Object onCall(ArrayList<Object> packet) {

					if(((String) packet.get(0)).equalsIgnoreCase("Log") ||
							((String) packet.get(0)).equalsIgnoreCase("Input")) {

						@SuppressWarnings("unchecked")
						ArrayList<Object> processed = (ArrayList<Object>) packet.get(1);

						String string = "";

						for(Object object : processed)
							string += object;

						if(((String) packet.get(0)).equalsIgnoreCase("Log")) {
							
							output.text.setText(output.text.getText() + string);
							
							if(enlargeOutput != null)
								enlargeOutput.setText(enlargeOutput.getText() + string);
						}

						if(((String) packet.get(0)).equalsIgnoreCase("Input"))
							return JOptionPane.showInputDialog(frame, string);
					}

					return null;
				}
			};

			console.tags.add("Console");

			PhilosophersStoneUtilities.publiclyConnect(fusion, console);

			PhilosophersStone workspace = new PhilosophersStone() {

				public Object onCall(ArrayList<Object> packet) {

					if(((String) packet.get(0)).equalsIgnoreCase("Get Workspace")) {
						
						ArrayList<String> workspace = new ArrayList<String>();

						workspace.addAll(originSource);
						workspace.addAll(fusionSource);
						
						return workspace;
					}

					if(((String) packet.get(0)).equalsIgnoreCase("Get Build Workspace")) {
						
						if(fusionBuild != null)
							return fusionBuild;
						
						if(originBuild != null)
							return originBuild;
						
						return "";
					}
					
					return null;
				}
			};

			workspace.tags.add("Workspace");

			PhilosophersStoneUtilities.publiclyConnect(fusion, workspace);

			PhilosophersStoneUtilities.publiclyConnect(
					fusion,
					Utilities.getArgumentStone(
							getArguments(
									currentInput.arguments)));

			new Thread(

				new Runnable() {

					public void run() {

						try {
							
							fusion.processKaeonFUSION(
									ONEPlus.parseONEPlus(
											(options.getText().equals("Disable Super") ? "[USE: SUPER] [SUPER]\n" : "") +
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
								(options.getText().equals("Disable Super") ? "[USE: SUPER] [SUPER]\n" : "") +
								currentInput.text.getText()));
			}

			catch(Exception exception) {
				text.setForeground(Color.RED);
				text.setText("Invalid ONE+.");
			}

			in.add(new JScrollPane(text), BorderLayout.CENTER);

			one.setBackground(new Color(150, 125, 25));
			one.setText("Show ONE+");
			one.setActionCommand("Show ONE+");
		}

		if(command.equals("Show ONE+")) {

			in.remove(2);
			in.add(currentInput.pane, BorderLayout.CENTER);

			one.setBackground(new Color(100, 150, 255));
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

			File file = null;
			
			JFileChooser chooser = new JFileChooser();
			
			if(openFolder != null)
				chooser.setCurrentDirectory(new File(openFolder, ""));
			
			int returnVal = chooser.showOpenDialog(null);

			if(returnVal == JFileChooser.APPROVE_OPTION)
				file = chooser.getSelectedFile();
			
			if(file == null)
				return;

			openFolder = file.getAbsolutePath().substring(0,
					file.getAbsolutePath().lastIndexOf(File.separator));

			String name = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(File.separator) + 1);
			
			for(int i = 0; i < inputs.size(); i++) {
				
				if(inputs.get(i).button.getText().equals(name)) {
					
					int option = JOptionPane.showConfirmDialog(
							frame,
							"The selected file already exists in the workspace.\nDo you want to overwrite the file in the workspace?",
							"Kaeon Origin",
							JOptionPane.YES_NO_OPTION);
					
					if(option == JOptionPane.YES_OPTION) {

						if(inputs.get(i) == currentInput) {

							currentInput = null;

							in.remove(2);
							in.add(new JPanel(), BorderLayout.CENTER);

							one.setBackground(new Color(100, 150, 255));
							one.setText("Show ONE");
							one.setActionCommand("Show ONE");
						}

						input.remove(inputs.get(i).panel);

						inputs.remove(i);
						
						break;
					}
					
					return;
				}
			}
			
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

			Input input = getInput();

			input.button.setText(name);

			this.input.add(input.panel);

			input.text.setText(content);
			input.path = file.getAbsolutePath();

			setCurrentInput(input);

			one.setBackground(new Color(100, 150, 255));
			one.setText("Show ONE");
			one.setActionCommand("Show ONE");

			saveState();
		}

		if(command.equals("Save")) {

			for(Input input : inputs) {

				if(input.radioButton.isSelected())
					save(input);
			}
			
			if(currentInput != null) {
				
				if(!currentInput.radioButton.isSelected()) {
					
					int option = JOptionPane.showConfirmDialog(
							frame,
							"The current file: \"" + currentInput.button.getText() + "\", is not selected. Would you like to save it anyway?",
							"Kaeon Origin",
							JOptionPane.YES_NO_OPTION);
					
					if(option == JOptionPane.YES_OPTION)
						save(currentInput);
				}
			}

			saveState();
		}

		if(command.equals("Export") && currentInput != null) {
			
			String export = "";
			
			if(fusionBuild != null)
				export = fusionBuild;
			
			else if(originBuild != null)
				export = originBuild;
			
			export += currentInput.button.getText() + File.separator;
			
			File directory = new File(export);
			
		    if(!directory.exists())
		        directory.mkdir();
			
			for(int i = 0; i < inputs.size(); i++) {
				
				if(inputs.get(i).radioButton.isSelected() || inputs.get(i) == currentInput) {
					
					IO.save(
							inputs.get(i).path != null ?
									IO.openAsString(inputs.get(i).path) :
									inputs.get(i).text.getText(),
							export +
							(inputs.get(i).path != null ?
									inputs.get(i).path.substring(inputs.get(i).path.lastIndexOf(File.separator) + 1) :
									inputs.get(i).button.getText()));
				}
			}
			
			JOptionPane.showMessageDialog(frame, "Exported successfully.");
		}

		frame.repaint();
		frame.revalidate();
	}
	
	public void save(Input input) {

		if(input.path != null)
			IO.save(input.text.getText(), input.path);

		else {

			JOptionPane.showMessageDialog(frame, "Save " + input.button.getText() + ".");

			File file = saveAs(input.text.getText());

			if(file != null) {

				String name = file.getAbsolutePath();
				input.path = name;
				
				int start = name.indexOf(File.separator) != -1 ? name.lastIndexOf(File.separator) + 1 : 0;
				int end = name.length();
				
				input.button.setText(name.substring(start, end));
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

		text.setBackground(Color.WHITE);
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
		
		Element element = new Element();
		
		Element update = ElementUtilities.createElement("Update Path");
		ElementUtilities.addChild(element, update);
		
		ElementUtilities.addChild(update, ElementUtilities.createElement(updatePath));
		
		Element originLink = ElementUtilities.createElement("Kaeon Origin Help");
		ElementUtilities.addChild(element, originLink);
		
		ElementUtilities.addChild(originLink, ElementUtilities.createElement(originHelp));
		
		Element fusionLink = ElementUtilities.createElement("Kaeon FUSION Help");
		ElementUtilities.addChild(element, fusionLink);
		
		ElementUtilities.addChild(fusionLink, ElementUtilities.createElement(fusionHelp));
		
		Element extension = ElementUtilities.createElement("Default File Extension");
		ElementUtilities.addChild(element, extension);
		
		ElementUtilities.addChild(extension, ElementUtilities.createElement(defaultFileExtension));
		
		Element origin = ElementUtilities.createElement("Kaeon Origin");
		ElementUtilities.addChild(element, origin);
		
		Element perspective = ElementUtilities.createElement("Perspective");
		ElementUtilities.addChild(origin, perspective);
		
		ElementUtilities.addChild(perspective, ElementUtilities.createElement("Kaeon FUSION"));
		
		Element originWorkspace = ElementUtilities.createElement("Workspace");
		ElementUtilities.addChild(origin, originWorkspace);
		
		Element originSource = ElementUtilities.createElement("Source");
		ElementUtilities.addChild(originWorkspace, originSource);
		
		for(int i = 0; i < this.originSource.size(); i++)
			ElementUtilities.addChild(originSource, ElementUtilities.createElement(this.originSource.get(i)));
		
		Element originBuild = ElementUtilities.createElement("Build");
		ElementUtilities.addChild(originWorkspace, originBuild);
		
		if(this.originBuild != null)
			ElementUtilities.addChild(originBuild, ElementUtilities.createElement(this.originBuild));
		
		else
			ElementUtilities.addChild(originBuild, ElementUtilities.createElement(""));
		
		Element folder = ElementUtilities.createElement("Folder");
		ElementUtilities.addChild(origin, folder);
		
		Element open = ElementUtilities.createElement("Open");
		ElementUtilities.addChild(folder, open);
		
		if(openFolder != null)
			ElementUtilities.addChild(open, ElementUtilities.createElement(openFolder));
		
		else
			ElementUtilities.addChild(open, ElementUtilities.createElement(""));
		
		Element save = ElementUtilities.createElement("Save");
		ElementUtilities.addChild(folder, save);
		
		if(saveFolder != null)
			ElementUtilities.addChild(save, ElementUtilities.createElement(saveFolder));
		
		else
			ElementUtilities.addChild(save, ElementUtilities.createElement(""));
		
		Element perspectives = ElementUtilities.createElement("Perspectives");
		ElementUtilities.addChild(element, perspectives);
		
		Element fusion = ElementUtilities.createElement("Kaeon FUSION");
		ElementUtilities.addChild(perspectives, fusion);
		
		Element fusionWorkspace = ElementUtilities.createElement("Workspace");
		ElementUtilities.addChild(fusion, fusionWorkspace);
		
		Element fusionSource = ElementUtilities.createElement("Source");
		ElementUtilities.addChild(fusionWorkspace, fusionSource);
		
		for(int i = 0; i < this.fusionSource.size(); i++)
			ElementUtilities.addChild(fusionSource, ElementUtilities.createElement(this.fusionSource.get(i)));
		
		Element fusionBuild = ElementUtilities.createElement("Build");
		ElementUtilities.addChild(fusionWorkspace, fusionBuild);
		
		if(this.fusionBuild != null)
			ElementUtilities.addChild(fusionBuild, ElementUtilities.createElement(this.fusionBuild));
		
		else
			ElementUtilities.addChild(fusionBuild, ElementUtilities.createElement(""));
		
		Element files = ElementUtilities.createElement("Files");
		ElementUtilities.addChild(fusion, files);
		
		for(int i = 0; i < inputs.size(); i++) {
			
			String name = inputs.get(i).button.getText();
			
			Element file = ElementUtilities.createElement(name);
			ElementUtilities.addChild(files, file);
			
			if(inputs.get(i).path != null) {
				
				Element path = ElementUtilities.createElement("Path");
				ElementUtilities.addChild(file, path);
				
				ElementUtilities.addChild(path, ElementUtilities.createElement(inputs.get(i).path));
			}
			
			else {
				
				Element source = ElementUtilities.createElement("Source");
				ElementUtilities.addChild(file, source);
				
				if(inputs.get(i) == currentInput && inputEnlarged) {
					
					ElementUtilities.addChild(
							source,
							ElementUtilities.createElement(
									enlargeText.getText()));
				}
				
				else {
					
					ElementUtilities.addChild(
							source,
							ElementUtilities.createElement(
									inputs.get(i).text.getText()));
				}
			}
			
			if(inputs.get(i).arguments != null) {
				
				Element arguments = ElementUtilities.createElement("Arguments");
				ElementUtilities.addChild(file, arguments);
				
				ElementUtilities.addChild(arguments, ElementUtilities.createElement(inputs.get(i).arguments));
			}
		}
		
		for(int i = 0; i < files.children.size(); i++) {
			
			int match = 1;
			
			for(int j = i + 1; j < files.children.size(); j++) {
				
				if(files.children.get(i).content.equals(files.children.get(j).content)) {
					
					files.children.get(j).content = match + " - " + files.children.get(j).content;
					
					match++;
				}
			}
		}
		
		IO.save("" + element, "Origin.op");
	}

	public File saveAs(String file) {

		ArrayList<String> lines = new ArrayList<String>();
		lines.add(file);

		return saveAs(lines, defaultFileExtension);
	}

	public File saveAs(ArrayList<String> lines, String fileExtension) {

		JFileChooser fc = new JFileChooser();
		
		if(saveFolder != null)
			fc.setCurrentDirectory(new File(saveFolder, ""));
		
		PrintWriter outputStream = null;

		int rval = fc.showSaveDialog(fc);

		File file = null;

		if(rval == JFileChooser.APPROVE_OPTION) {

			file = fc.getSelectedFile();

			saveFolder = file.getAbsolutePath().substring(0,
					file.getAbsolutePath().lastIndexOf(File.separator));

			try {

				fileExtension = fileExtension.trim();

				if(fileExtension.length() > 0) {

					if(file.toString().indexOf('.') == -1)
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