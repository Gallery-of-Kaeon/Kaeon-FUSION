package kaeon_origin.ide.utilities;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kaeon_origin.ide.IDE;

@SuppressWarnings("serial")
public class OptionsPane extends JFrame implements ActionListener {
	
	public IDE ide;
	
	public OptionsPane(IDE ide) {
		
		this.ide = ide;
		
		setSize(200, 150);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			
            public void windowClosing(WindowEvent e) {
            	ide.options.setEnabled(true);
            }
        });
		
		JPanel panel = new JPanel();
		
		add(panel);
		
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(new GridLayout(2, 1));

//		panel.add(createButton("Set Perspective"));
		panel.add(createButton("Set Workspace"));
		panel.add(createButton("Help"));
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
		
		if(command.equals("Help")) {
			
			try {
				Runtime.getRuntime().exec("explorer \"https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md\"");
			}
			
			catch(Exception exception) {
				
			}
		}
		
//		if(command.equals("Set Perspective")) {
//			JOptionPane.showMessageDialog(this, "Coming soon!");
//		}
		
		if(command.equals("Set Workspace")) {
			
		}
	}
}