package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class VoyagesMain 
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
}
