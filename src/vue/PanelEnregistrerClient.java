package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;

import modele.Dao.DaoClients;
import modele.Entites.Client;

public class PanelEnregistrerClient extends JFrame implements ActionListener, KeyListener {
	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("S'inscrire");


	private JTextField txtNomc = new JTextField();
	private JTextField txtPrenomc = new JTextField();
	private JTextField txtRue = new JTextField();
	private JTextField txtCP = new JTextField();
	private JTextField txtVillec = new JTextField();
	private JTextField txtPays_c = new JTextField();
	private JTextField txtMail_c = new JTextField();
	private JTextField txtDatenaiss_c = new JTextField();
	private JTextField txtMdp_c = new JTextField();

	public <User> PanelEnregistrerClient() {
		// construction du panel form
		this.panelForm.setLayout(new GridLayout(11, 2));
		this.panelForm.setBounds(20, 40, 250, 340);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.add(new JLabel("Nom Client : "));
		this.panelForm.add(this.txtNomc);
		this.panelForm.add(new JLabel("Prenom Client: "));
		this.panelForm.add(this.txtPrenomc);
		this.panelForm.add(new JLabel("rue : "));
		this.panelForm.add(this.txtRue);
		this.panelForm.add(new JLabel("Code postal: "));
		this.panelForm.add(this.txtCP);
		this.panelForm.add(new JLabel("Ville : "));
		this.panelForm.add(this.txtVillec);
		this.panelForm.add(new JLabel("Pays : "));
		this.panelForm.add(this.txtPays_c);
		this.panelForm.add(new JLabel("mail : "));
		this.panelForm.add(this.txtMail_c);
		this.panelForm.add(new JLabel(" Date de naissance  : "));
		this.panelForm.add(this.txtDatenaiss_c);
		this.panelForm.add(new JLabel("Mdp : "));
		this.panelForm.add(this.txtMdp_c);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
			

		// rendre les boutons clicables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.txtNomc.addKeyListener(this);
		this.txtPrenomc.addKeyListener(this);
		this.txtRue.addKeyListener(this);
		this.txtCP.addKeyListener(this);
		this.txtVillec.addKeyListener(this);
		this.txtPays_c.addKeyListener(this);
		this.txtMail_c.addKeyListener(this);
		this.txtDatenaiss_c.addKeyListener(this);
		this.txtMdp_c.addKeyListener(this);
		
		this.add(panelForm, BorderLayout.NORTH);


        setTitle("Form Inscription");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
		// construction de la table



			}



	public void viderChamps() {
		this.txtNomc.setText("");
		this.txtPrenomc.setText("");
		this.txtRue.setText("");
		this.txtCP.setText("");
		this.txtVillec.setText("");
		this.txtPays_c.setText("");
		this.txtMail_c.setText("");
		this.txtDatenaiss_c.setText("");
		this.txtMdp_c.setText("");
		this.btEnregistrer.setText("Modifier");
		this.txtNomc.setBackground(Color.white);
		this.txtPrenomc.setBackground(Color.white);
		this.txtRue.setBackground(Color.white);
		this.txtCP.setBackground(Color.white);
		this.txtVillec.setBackground(Color.white);
		this.txtPays_c.setBackground(Color.white);
		this.txtMail_c.setBackground(Color.white);
		this.txtDatenaiss_c.setBackground(Color.white);
		this.txtMdp_c.setBackground(Color.white);

	}

	public void traitement(int choix) {
		String nom = this.txtNomc.getText();
		String prenom = this.txtPrenomc.getText();
		String rue = this.txtRue.getText();
		String cp = this.txtCP.getText();
		String ville = this.txtVillec.getText();
		String pays = this.txtPays_c.getText();
		String mail = this.txtMail_c.getText();
		String datenaiss = this.txtDatenaiss_c.getText();
		String mdp = this.txtMdp_c.getText();
		if (nom.equals("")) {
			this.txtNomc.setBackground(Color.red);
		}
		if (prenom.equals("")) {
			this.txtPrenomc.setBackground(Color.red);
		}
		if (rue.equals("")) {
			this.txtRue.setBackground(Color.red);
		}
		if (cp.equals("")) {
			this.txtCP.setBackground(Color.red);
		}
		if (ville.equals("")) {
			this.txtVillec.setBackground(Color.red);
		}
		if (pays.equals("")) {
			this.txtPays_c.setBackground(Color.red);
		}
		if (mail.equals("")) {
			this.txtMail_c.setBackground(Color.red);
		}
		if (datenaiss.equals("")) {
			this.txtDatenaiss_c.setBackground(Color.red);
		}

		if ((!mdp.equals("")) && modele.Dao.DaoClients.validemdp(mdp)) {
			this.txtMdp_c.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,
					"Le mot de passe doit avoir au moins 8 caract�re dont une majuscule, une minuscule, un caract�re special et un chiffre");
		}
		if (nom.equals("") || prenom.equals("") || rue.equals("") || ville.equals("") || cp.equals("")
				|| pays.equals("") || mail.equals("") || datenaiss.equals("")
				|| ((!mdp.equals("")) && DaoClients.validemdp(mdp))) {
			if (!(!mdp.equals("")) && DaoClients.validemdp(mdp)) {
				JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
			}
			this.viderChamps();
		} else if (choix == 0) {
			Client unClient = new Client(0, nom, prenom, rue, cp, ville, pays, mail, datenaiss, "");
			modele.Dao.DaoClients.insertClient(unClient);

			// on recupere le client ins�r� pour son nouvel ID
			unClient = DaoClients.selectWhereClient(mail);
			JOptionPane.showMessageDialog(this, "insertion reussie dans la base de donnee");
		} 

		this.viderChamps();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) {
		JFrame jframe = new JFrame();
		// jframe.setBou
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
