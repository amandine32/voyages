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

import controleur.Voyages;
import controleur.Tableau;
import modele.Modele;

public class PanelVoyages<Tableau> extends PanelDeBase implements ActionListener, KeyListener {

	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Modifier");

	private JTextField txtDatedeb_voyage = new JTextField();
	private JTextField txtDatefin_voyage = new JTextField();
	private JTextField txtLieu_voyage = new JTextField();

	private JTable uneTable;
	private JScrollPane uneScroll;
	private controleur.Tableau unTableau;
	private JPanel panelRechercher = new JPanel();
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelVoyages() {
		super(Color.gray);
		// construction du panel form
		this.panelForm.setLayout(new GridLayout(11, 2));
		this.panelForm.setBounds(20, 40, 250, 340);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.add(new JLabel("date de début voyage : "));
		this.panelForm.add(this.txtDatedeb_voyage);
		this.panelForm.add(new JLabel("date de fin voyage: "));
		this.panelForm.add(this.txtDatefin_voyage);
		this.panelForm.add(new JLabel("lieu voyage : "));
		this.panelForm.add(this.txtLieu_voyage);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);

		// construction du panelRechercher
		this.panelRechercher.setLayout(new GridLayout(1, 3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		;
		this.panelRechercher.setBackground(Color.gray);
		this.panelRechercher.add(new JLabel("Filtrer les voyages : "));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(this.panelRechercher);

		// rendre les boutons clicables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtDatedeb_voyage.addKeyListener(this);
		this.txtDatefin_voyage.addKeyListener(this);
		this.txtLieu_voyage.addKeyListener(this);

		// construction de la table
		String entetes[] = { "Idv", "date debut voyage", "date fin voyage", "lieu voyage" };

		Object donnees[][] = this.getDonnees("");
		this.unTableau = new controleur.Tableau(entetes, donnees);

		this.uneTable = new JTable((TableModel) unTableau);
		this.uneScroll = new JScrollPane(this.uneTable);
		this.uneScroll.setBounds(280, 80, 900, 300);
		this.add(this.uneScroll);

		this.uneTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int nbClic = e.getClickCount();
				int numLigne = uneTable.getSelectedRow();
				if (nbClic == 2) {
					int retour = JOptionPane.showConfirmDialog(null, "voulez-vous supprimer le Voyages ?",
							"Suppression voyage", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idv = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						// on supprime le client dans la base
						Modele.deleteVoyage(idv);
						// on le supprime de l'affichage
						unTableau.supprimerLigne(numLigne);
					}
				} else if (nbClic == 1) {

					txtDatedeb_voyage.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtDatefin_voyage.setText(unTableau.getValueAt(numLigne, 2).toString());
					txtLieu_voyage.setText(unTableau.getValueAt(numLigne, 3).toString());
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

			}
		});
	}

	public Object[][] getDonnees(String mot) {
		ArrayList<Voyages> lesVoyages = Modele.selectAllVoyages(mot);
		Object[][] matrice = new Object[lesVoyages.size()][4];
		int i = 0;
		for (Voyages unVoyage : lesVoyages) {
			matrice[i][0] = unVoyage.getIdv();
			matrice[i][1] = unVoyage.getDatedeb_voyage();
			matrice[i][2] = unVoyage.getDatefin_voyage();
			matrice[i][3] = unVoyage.getLieu_voyage();
			i++;
		}
		return matrice;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer")) {
			this.traitement(0);
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier")) {
			this.traitement(1);
		} else if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			this.unTableau.setDonnees(this.getDonnees(mot));
		}

	}

	public void viderChamps() {
		this.txtDatedeb_voyage.setText("");
		this.txtDatefin_voyage.setText("");
		this.txtLieu_voyage.setText("");

	}

	public void traitement(int choix) {
		String datedebut = this.txtDatedeb_voyage.getText();
		String datefin = this.txtDatefin_voyage.getText();
		String lieu = this.txtLieu_voyage.getText();

		if (datedebut.equals("")) {
			this.txtDatedeb_voyage.setBackground(Color.red);
		}
		if (datefin.equals("")) {
			this.txtDatefin_voyage.setBackground(Color.red);
		}
		if (lieu.equals("")) {
			this.txtLieu_voyage.setBackground(Color.red);
		}

		if (datedebut.equals("") || datefin.equals("") || lieu.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
			this.viderChamps();
		} else if (choix == 0) {
			Voyages unVoyage = new Voyages(0, datedebut, datefin, lieu);
			modele.Modele.insertVoyages(unVoyage);

			// on recupere le client ins�r� pour son nouvel ID
			unVoyage = modele.Modele.selectWhereVoyages(lieu);
			JOptionPane.showMessageDialog(this, "insertion reussie dans la base de donnee");
			Object ligne[] = { unVoyage.getIdv(), unVoyage.getDatedeb_voyage(), unVoyage.getDatefin_voyage(),
					unVoyage.getLieu_voyage() };
			this.unTableau.ajouterLigne(ligne);
		} else {
			int numLigne = this.uneTable.getSelectedRow();
			int idv = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			Voyages unVoyage = new Voyages(idv, datedebut, datefin, lieu);
			modele.Modele.updateVoyage(unVoyage);
			Object ligne[] = { unVoyage.getIdv(), unVoyage.getDatedeb_voyage(), unVoyage.getDatefin_voyage(),
					unVoyage.getLieu_voyage() };
			this.unTableau.modifierLigne(numLigne, ligne);
		}

		this.viderChamps();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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

	public static void main(String args[]) {
		JFrame jframe = new JFrame();
		// jframe.setBou
	}

}
