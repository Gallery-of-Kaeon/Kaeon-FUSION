package dev.ide;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fusion_interface.ACEInterface;
import interfaces.StandardInterfacePack;
import io.IO;
import kaeon_fusion.interface_module.Interface;
import kaeon_fusion.super_mode.SuperMode;
import one_plus.ONEPlus;

public class IDE implements ActionListener {
	
	JTextArea input;
	JTextArea output;
	
	JButton run;
	
	public IDE() {
		
		JFrame frame;
		
		frame = new JFrame();
		frame.setTitle("Kaeon Dev");
		
		frame.setSize(500, 500);
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
		input.setTabSize(2);
		
		control.add(new JScrollPane(input), BorderLayout.CENTER);
		
		run = new JButton("Run");
		run.setActionCommand("Run");
		run.addActionListener(this);
		
		control.add(run, BorderLayout.SOUTH);
		
		frame.add(control);
		
		output = new JTextArea();
		output.setEditable(false);
		
		frame.add(new JScrollPane(output));
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DevConsole.setConsole(output);
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
		
		if(command.equals("Run")) {
			
			run.setEnabled(false);
			output.setText("");
			
			DevKaeonFUSION fusion = new DevKaeonFUSION();
			
			ArrayList<Interface> standard = StandardInterfacePack.getInterfaces();
			
			for(int i = 0; i < standard.size(); i++)
				fusion.publiclyConnectMutually(standard.get(i));
			
			fusion.publiclyConnectMutually(new ACEInterface());
			
			try {
				
				String text = input.getText();
				ONEPlus code = new ONEPlus(text);
				
				if(text.indexOf("[SUPER]") != -1)
					SuperMode.superMode(code);
				
				fusion.process(code);
			}
			
			catch(Exception exception) {
				
			}
			
			run.setEnabled(true);
		}
	}
}