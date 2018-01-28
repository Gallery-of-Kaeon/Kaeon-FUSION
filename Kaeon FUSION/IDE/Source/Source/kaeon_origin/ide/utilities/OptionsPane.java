package kaeon_origin.ide.utilities;

import javax.swing.JFrame;

import kaeon_origin.ide.IDE;

@SuppressWarnings("serial")
public class OptionsPane extends JFrame {
	
	public IDE ide;
	
	public OptionsPane(IDE ide) {
		
		this.ide = ide;
		
		setSize(500, 500);
		setVisible(true);
	}
}