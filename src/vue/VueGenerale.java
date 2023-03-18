package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Main.VoyagesMain;

public class VueGenerale extends JFrame implements ActionListener 
{
	
	private JPanel panelMenu = new JPanel();
	private JButton btClient = new JButton("Client");

	private JButton btQuitter = new JButton("Quitter");

	private PanelClient unPanelClient;

	
	public VueGenerale()
	{

		unPanelClient = new PanelClient();

		this.setTitle("Administration neige et soleil");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200,200,1300,700);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		ImageIcon uneImage = new ImageIcon("src/images/logorond2.png");
		JLabel logo = new JLabel(uneImage);
		logo.setBounds(30,410,250,250);
		this.add(logo);
		this.setVisible(true);
		
		
		//contrustion du panel menu
		this.panelMenu.setLayout(new GridLayout(2,7));
		this.panelMenu.setBounds(20,10,1200,51);
		this.panelMenu.setBackground(Color.BLACK);
		this.panelMenu.add(this.btClient);

		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		
		// rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btClient.addActionListener(this);

		

		//ajout des panels dans la fenetre

		this.add(this.unPanelClient);

		
		
		//this.add(this.unPanelBord);
		
		
		this.setVisible(true);
	}

	public void hidePanel()
	{
	
		this.unPanelClient.setVisible(false);

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
				VoyagesMain.rendreVisibleVueConnexion(true);
			}

	}
		}
	}
		

