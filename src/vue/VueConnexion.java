package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modele.Dao.DaoClients;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	public static int droit;
	private JPanel panelConnexion = new JPanel();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton("Connexion");
	public VueConnexion(){
		this.setTitle("Administration Neige et Soleil");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200,200,1300,700);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		
		// construction du panelConnexion
		this.panelConnexion.setLayout(new GridLayout(3,2));
		this.panelConnexion.setBounds(540,200,700,300);
		this.panelConnexion.setBackground(Color.gray);
		this.panelConnexion.add(new JLabel("Email : "));
		this.panelConnexion.add(this.txtEmail);
		this.panelConnexion.add(new JLabel("Mdp : "));
		this.panelConnexion.add(this.txtMdp);
		this.panelConnexion.add(this.btAnnuler);
		this.panelConnexion.add(this.btConnexion);
		this.add(this.panelConnexion);
		// placement de l'image
		ImageIcon uneImage = new ImageIcon("src/images/logo.png");
		JLabel logo = new JLabel(uneImage);
		logo.setBounds(10,10,500,500);
		this.add(logo);
		this.setVisible(true);
		
		// rendre les boutons clicable
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		//this.txtEmail.setText("f@gmail.com");
		//this.txtMdp.setText("123");
		this.txtEmail.setText("f@gmail.com");
		this.txtMdp.setText("123");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btAnnuler)
		{
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource() == this.btConnexion)
		{
			traitement();
		}
	}

	public void traitement()
	{/*
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		User unUser = Modele.selectWhereClient(email);
		if(unUser == null)
		{
			JOptionPane.showMessageDialog(this, "V�rifier vos identifiants");
		}
		else
		{
			JOptionPane.showMessageDialog(this, "\n Bienvenue "+unUser.getEmail());
			VoyagesMain.instancierVueGenerale(unUser);
			VoyagesMain.rendreVisibleVueConnexion(false);
			this.txtEmail.setText("f@gmail.com");
			this.txtMdp.setText("123");
			droit = unUser.getRole();
		}*/
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			traitement ();
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
