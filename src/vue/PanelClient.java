package vue;

import java.awt.Color;
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
import javax.swing.table.TableModel;

import controleur.Client;
import controleur.Tableau;
import modele.Modele;


public class PanelClient<Tableau> extends PanelDeBase implements ActionListener, KeyListener
{
	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Modifier");
	
	private JTextField txtNomc = new JTextField();
	private JTextField txtPrenomc = new JTextField();
	private JTextField txtRue = new JTextField();
	private JTextField txtCP = new JTextField();
	private JTextField txtVillec = new JTextField();
	private JTextField txtPays_c = new JTextField();
	private JTextField txtMail_c = new JTextField();
	private JTextField txtDatenaiss_c = new JTextField();
	private JTextField txtMdp_c = new JTextField();
	
	private JTable uneTable ;
	private JScrollPane uneScroll;
	private controleur.Tableau unTableau;
	private JPanel panelRechercher = new JPanel();
    private JTextField txtMot = new JTextField();
    private JButton btRechercher = new JButton("Rechercher");
	
	public <User> PanelClient()
	{
		super (Color.gray);
		//construction du panel form
		this.panelForm.setLayout(new GridLayout(11,2));
		this.panelForm.setBounds(20,40,250,340);
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
		this.add(this.panelForm);
		
		// construction du panelRechercher
        this.panelRechercher.setLayout(new GridLayout(1,3));
        this.panelRechercher.setBounds(300,40,460,20);;
        this.panelRechercher.setBackground(Color.gray);
        this.panelRechercher.add(new JLabel("Filtrer les clients : "));
        this.panelRechercher.add(this.txtMot);
        this.panelRechercher.add(this.btRechercher);
        this.add(this.panelRechercher);
		
		
		//rendre les boutons clicables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtNomc.addKeyListener(this);
		this.txtPrenomc.addKeyListener(this);
		this.txtRue.addKeyListener(this);
		this.txtCP.addKeyListener(this);
		this.txtVillec.addKeyListener(this);
		this.txtPays_c.addKeyListener(this);
		this.txtMail_c.addKeyListener(this);
		this.txtDatenaiss_c.addKeyListener(this);
		this.txtMdp_c.addKeyListener(this);

		
		
		//construction de la table
		String entetes [] = {"Id Client","Nom", "Prenom", "rue","cp","ville","pays","mail","datenaiss", "mdp"};
		
		Object donnees [][] = this.getDonnees("");
		this.unTableau = new controleur.Tableau (entetes,donnees);
		
		this.uneTable= new JTable((TableModel) unTableau);
		this.uneScroll = new JScrollPane(this.uneTable);
		this.uneScroll.setBounds(280,80,900,300);
		this.add(this.uneScroll);
		
		this.uneTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int nbClic = e.getClickCount();
				int numLigne = uneTable.getSelectedRow();
				if(nbClic == 2)
				{
					int retour = JOptionPane.showConfirmDialog(null,"voulez-vous supprimer le client ?","Suppression client", JOptionPane.YES_NO_OPTION);
					if (retour==0)
					{
						int idc = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						// on supprime le client dans la base
						Modele.deleteClient(idc);
						//on le supprime de l'affichage
						unTableau.supprimerLigne(numLigne);
					}
				}else if(nbClic==1)
				{
					txtNomc.setText(unTableau.getValueAt(numLigne,1).toString());
					txtPrenomc.setText( unTableau.getValueAt(numLigne,2).toString());
					txtRue.setText( unTableau.getValueAt(numLigne,3).toString());
					txtCP.setText( unTableau.getValueAt(numLigne,4).toString());
					txtVillec.setText( unTableau.getValueAt(numLigne,5).toString());
					txtPays_c.setText(unTableau.getValueAt(numLigne,6).toString());
					txtMail_c.setText(unTableau.getValueAt(numLigne,7).toString());
					txtDatenaiss_c.setText(unTableau.getValueAt(numLigne,8).toString());
					txtMdp_c.setText(unTableau.getValueAt(numLigne,9).toString());
	                btEnregistrer.setText("Modifier");
				}
				
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}
	

	public Object [][] getDonnees(String mot)
	{
		ArrayList<Client> lesClients = Modele.selectAllClients(mot);
		Object[][] matrice = new Object [lesClients.size()][10];
		int i=0;
		for(Client unClient : lesClients)
		{
			matrice[i][0] = unClient.getIdc();
			matrice[i][1] = unClient.getNomc();
			matrice[i][2] = unClient.getPrenomc();
			matrice[i][3] = unClient.getRue();
			matrice[i][4] = unClient.getCP();
			matrice[i][5] = unClient.getVillec();
			matrice[i][6] = unClient.getPays_c();
			matrice[i][7] = unClient.getMail_c();
			matrice[i][8] = unClient.getDatenaiss_c();
			matrice[i][9] = unClient.getMdp_c();
			i++;
		}
		return matrice ;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			this.traitement(0);
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		{
			this.traitement(1);
		}
		else if (e.getSource()==this.btRechercher)
		{
			String mot= this.txtMot.getText();
			this.unTableau.setDonnees(this.getDonnees(mot));
		}
		
	}


	public void viderChamps()
	{
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

	public void traitement (int choix)
	{
		String nom = this.txtNomc.getText();
		String prenom = this.txtPrenomc.getText();
		String rue = this.txtRue.getText();
		String cp = this.txtCP.getText();
		String ville =this.txtVillec.getText();
		String pays = this.txtPays_c.getText();
		String mail = this.txtMail_c.getText();
		String datenaiss = this.txtDatenaiss_c.getText();
		String mdp =this.txtMdp_c.getText();
		if(nom.equals(""))
		{
			this.txtNomc.setBackground(Color.red);
		}
		if(prenom.equals(""))
		{
			this.txtPrenomc.setBackground(Color.red);
		}
		if(rue.equals(""))
		{
			this.txtRue.setBackground(Color.red);
		}
		if(cp.equals(""))
		{
			this.txtCP.setBackground(Color.red);
		}
		if(ville.equals(""))
		{
			this.txtVillec.setBackground(Color.red);
		}
		if(pays.equals(""))
		{
			this.txtPays_c.setBackground(Color.red);
		}
		if(mail.equals(""))
		{
			this.txtMail_c.setBackground(Color.red);
		}
		if(datenaiss.equals(""))
		{
			this.txtDatenaiss_c.setBackground(Color.red);
		}

		if((!mdp.equals("")) && modele.Modele.validemdp(mdp))
		{
			this.txtMdp_c.setBackground(Color.red);
			JOptionPane.showMessageDialog(this,"Le mot de passe doit avoir au moins 8 caract�re dont une majuscule, une minuscule, un caract�re special et un chiffre");
		}
		if(nom.equals("")|| prenom.equals("")||rue.equals("") || ville.equals("") || cp.equals("") || pays.equals("") || mail.equals("") || datenaiss.equals("") ||((!mdp.equals(""))&& Modele.validemdp(mdp)))
		{
			if(!(!mdp.equals(""))&& Modele.validemdp(mdp))
			{
				JOptionPane.showMessageDialog(this,"Veuillez remplir les champs obligatoires");
			}
			this.viderChamps();
		}
		else if (choix==0)
		{
			Client unClient =  new Client(0,nom,prenom,rue,cp,ville,pays,mail,datenaiss,"");
			modele.Modele.insertClient(unClient);
			
			//on recupere le client ins�r� pour son  nouvel ID
			unClient =  Modele.selectWhereClient(mail);
			JOptionPane.showMessageDialog(this,"insertion reussie dans la base de donnee");
			Object ligne[]={unClient.getIdc(),
					unClient.getNomc(),
					unClient.getPrenomc(),
					unClient.getRue(),
					unClient.getCP(),
					unClient.getVillec(),
					unClient.getPays_c(),
					unClient.getMail_c(),
					unClient.getDatenaiss_c(),
					unClient.getMdp_c()
					};
			this.unTableau.ajouterLigne(ligne);
		}
		else
		{
			int numLigne= this.uneTable.getSelectedRow();
			int idc= Integer.parseInt(this.unTableau.getValueAt(numLigne,0).toString());
			Client unClient =  new Client(idc,nom,prenom,mail,datenaiss,rue,cp,ville,mdp,"");
			 Modele.updateClient(unClient);
			Object ligne[]={unClient.getIdc(),
					unClient.getNomc(),
					unClient.getPrenomc(),
					unClient.getRue(),
					unClient.getCP(),
					unClient.getVillec(),
					unClient.getPays_c(),
					unClient.getMail_c(),
					unClient.getDatenaiss_c(),
					unClient.getMdp_c()
					};
			this.unTableau.modifierLigne(numLigne, ligne);
		}
		
		this.viderChamps();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			String mot = this.txtMot.getText();
			this.unTableau.setDonnees(this.getDonnees(mot));
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

	public static void main(String args[]){
		JFrame jframe=new JFrame();
		//jframe.setBou
	}
}
