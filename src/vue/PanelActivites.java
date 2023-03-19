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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Dao.DaoActivites;
import modele.Entites.Activites;
import modele.Entites.Reservation;



public class PanelActivites<Tableau> extends PanelDeBase implements ActionListener, KeyListener
{
	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");

	private JTextField txtNom_activite = new JTextField();
	private JTextField txtType_activite = new JTextField();

	




	private JTable uneTable ;
	private JScrollPane uneScroll;
	private vue.Tableau unTableau;
	private JPanel panelRechercher = new JPanel();
    private JTextField txtMot = new JTextField();
    private JButton btRechercher = new JButton("Rechercher");
	
	public PanelActivites()
	{
		super (Color.gray);
		//construction du panel form
		this.panelForm.setLayout(new GridLayout(4,2));
		this.panelForm.setBounds(20,40,250,340);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.add(new JLabel("Nom activite  : "));
		this.panelForm.add(this.txtNom_activite);
		this.panelForm.add(new JLabel(" Type activite: "));
		this.panelForm.add(this.txtType_activite );
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		
		//remplir le combo 
			
		


		
		// construction du panelRechercher
        this.panelRechercher.setLayout(new GridLayout(1,3));
        this.panelRechercher.setBounds(300,40,460,20);;
        this.panelRechercher.setBackground(Color.gray);
        this.panelRechercher.add(new JLabel("Filtrer les activites : "));
        this.panelRechercher.add(this.txtMot);
        this.panelRechercher.add(this.btRechercher);
        this.add(this.panelRechercher);
		
		
		//rendre les boutons clicables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtMot.addKeyListener(this);
		this.txtNom_activite.addKeyListener(this);
		this.txtType_activite.addKeyListener(this);
	


		
		
		//construction de la table
		String entetes [] = {"Ida "," Nom_activite","Type_activite"};
		
		Object donnees [][] = this.getDonnees("");
		this.unTableau = new   vue.Tableau (entetes,donnees);
		
		this.uneTable= new JTable(unTableau);
		this.uneScroll = new JScrollPane(this.uneTable);
		this.uneScroll.setBounds(290,80,900,300);
		this.add(this.uneScroll);
		
		this.uneTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int nbClic = e.getClickCount();
				int numLigne = uneTable.getSelectedRow();
				if(nbClic == 2)
				{
					int retour = JOptionPane.showConfirmDialog(null,"voulez-vous supprimer l'activité ?","Suppression de l'actvité", JOptionPane.YES_NO_OPTION);
					if (retour==0)
					{
						int ida = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					
						// on supprime le contrat dans la base
						DaoActivites.deleteActivite(ida);
						//on le supprime de l'affichage
						unTableau.supprimerLigne(numLigne);
					}
				}else if(nbClic==1)
				{
					txtNom_activite.setText(unTableau.getValueAt(numLigne,1).toString());
					txtType_activite.setText(unTableau.getValueAt(numLigne,2).toString());
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
		ArrayList<Activites> lesActivites = DaoActivites.selectAllActivites(0,mot);
		Object[][] matrice = new Object [lesActivites.size()][4];
		int i=0;
		for(Activites uneActivite : lesActivites)
		{
			matrice[i][0] = uneActivite.getIda();
			matrice[i][1] = uneActivite.getNom_activite();
			matrice[i][2] = uneActivite.getType_activite();


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
		this.txtNom_activite.setText("");
		this.txtType_activite.setText("");


		this.btEnregistrer.setText("Enregistrer");
		this.txtNom_activite.setBackground(Color.white);
		this.txtType_activite.setBackground(Color.white);

	}
	
	public void traitement (int choix)
	{
		String nom_activite = this.txtNom_activite.getText();
		String type_activite = this.txtType_activite.getText();


		if (choix==0)
		{
			Activites uneActivite =  new Activites(
					0,
					nom_activite,
					type_activite);
			DaoActivites.insertActivite(uneActivite);
			
			//on recupere le Contrat_location ins�r� pour son  nouvel ID
			uneActivite= DaoActivites.selectWhereActivites(0,nom_activite,type_activite);
			JOptionPane.showMessageDialog(this,"insertion reussie dans la base de donnee");
			Object ligne[]={uneActivite.getIda(),
					uneActivite.getNom_activite(),
					uneActivite.getType_activite()};
			this.unTableau.ajouterLigne(ligne);
		}
		else
		{
			int numLigne= this.uneTable.getSelectedRow();
			int ida= Integer.parseInt(this.unTableau.getValueAt(numLigne,0).toString());
			Activites uneActivite =  new Activites(
					ida,
					nom_activite,
					type_activite);
			DaoActivites.updateActivites(uneActivite);
			Object ligne[]={uneActivite.getIda(),
					uneActivite.getNom_activite(),
					uneActivite.getType_activite()};
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
}
