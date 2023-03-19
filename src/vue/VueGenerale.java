package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VueGenerale extends JFrame implements ActionListener 
{
	
	private JPanel panelMenu = new JPanel();
	private JButton btClient = new JButton("Client");
	private JButton btActivites = new JButton("Activites");
	private JButton btVoyages = new JButton("Voyages");

	private JButton btQuitter = new JButton("Quitter");

	private PanelClient unPanelClient;
	private PanelActivites unPanelActivites;
	private PanelVoyages unPanelVoyages;


	
	public VueGenerale()
	{

		unPanelClient = new PanelClient();
		unPanelActivites = new PanelActivites();
		unPanelVoyages = new PanelVoyages();
	
		
		this.setTitle("Voyages");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0,0,1300,700);
		this.setResizable(true);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setVisible(true);
		
		
		//contrustion du panel menu
		this.panelMenu.setLayout(new GridLayout(2,7));
		this.panelMenu.setBounds(20,10,1200,51);
		this.panelMenu.setBackground(Color.white);
		this.panelMenu.add(this.btClient);
		this.panelMenu.add(this.btVoyages);
		this.panelMenu.add(this.btActivites);



		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		
		// rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btClient.addActionListener(this);
		this.btVoyages.addActionListener(this);
		this.btActivites.addActionListener(this);

		//ajout des panels dans la fenetre		
this.add(this.unPanelActivites);
this.add(this.unPanelVoyages);
this.add(this.unPanelClient);

			
				this.setVisible(true);


}

public void hidePanel()
{
	this.unPanelClient.setVisible(false);
	this.unPanelActivites.setVisible(false);
	this.unPanelVoyages.setVisible(false);


}
@Override
public void actionPerformed(ActionEvent e)
{
	if( e.getSource() == this.btQuitter)
	{
		int retour = JOptionPane.showConfirmDialog(this, "Quitter Application","voulez vous quitter l'application ?", JOptionPane.YES_NO_OPTION);
		if (retour ==0)
		{
			this.dispose();
		}
	}else if(e.getSource()==this.btClient)
	{
		System.out.println("Client");
		hidePanel();
		this.unPanelClient.setVisible(true);
		
	}else if(e.getSource()==this.btVoyages)
	{
		System.out.println("Voyage");
		hidePanel();
		this.unPanelVoyages.setVisible(true);
	}else if(e.getSource()==this.btActivites)
	{		
	System.out.println("Activites");
		hidePanel();
		this.unPanelActivites.setVisible(true);
	}
	
}
}
	
