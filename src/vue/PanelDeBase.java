package vue;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelDeBase extends JPanel
{
	public PanelDeBase(Color uneCouleur)
	{
		this.setBackground(uneCouleur);
		this.setLayout(null);
		this.setBounds(20,40,1200,800);
		this.setVisible(false);
	}
}
