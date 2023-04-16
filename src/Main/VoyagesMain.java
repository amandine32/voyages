package Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import modele.Entites.Client;
import vue.VueConnexion;
import vue.VueGenerale;

public class VoyagesMain extends JFrame
{
	
	
	private static VueConnexion uneVueConnexion ;
	private static VueGenerale uneVueGenerale;
	//public static void rendreVisibleVueConnexion (boolean action)
	//{
	//	uneVueConnexion.setVisible(action);
	//}

	public static void instancierVueGenerale()
	{
		uneVueGenerale = new VueGenerale ();
	}
	public static void main(String args[])
	{new VueGenerale ();
		//uneVueConnexion = new VueConnexion();
	}
	
	  public void initialize(Client user) {
	        /*************** Info Panel ***************/
	        JPanel infoPanel = new JPanel();
	        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));
	        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
	        infoPanel.add(new JLabel("Name"));
	        infoPanel.add(new JLabel(user.getNomc()));
	        infoPanel.add(new JLabel("Email"));
	        infoPanel.add(new JLabel(user.getMail_c()));
	   


	        Component[] labels = infoPanel.getComponents();
	        for (int i = 0; i < labels.length; i++) {
	            labels[i].setFont(new Font("Segoe print", Font.BOLD, 18));
	        }

	        add(infoPanel, BorderLayout.NORTH);



	        setTitle("Dashboard");
	        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        setSize(1100, 650);
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }
	}

