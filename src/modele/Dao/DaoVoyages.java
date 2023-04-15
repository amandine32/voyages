package modele.Dao;

import java.sql.ResultSet;
import java.nio.charset.*;
import java.security.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modele.Entites.Activites;
import modele.Entites.Client;
import modele.Entites.Voyages;


public class DaoVoyages {
	private static Bdd uneBdd = new Bdd("localhost","bddvoyages","root","");
//	private static Bdd uneBdd = new Bdd("172.20.111.117","neigesoleil","amandine","amandine");
	
	/********************UTILITAIRE******************/
	
	/************************HASH***********************/
	public static String sha256(final String base)
	{
	    try{
	        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        final byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        final StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < hash.length; i++) {
	            final String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) 
	              hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	/*******************REGEX***********************/

	public static boolean validemdp(String mdp)
    {
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
  
        Pattern p = Pattern.compile(regex);
  
        Matcher m = p.matcher(mdp);
  
        return !m.matches();
    }
	
	public static boolean validemail(String mail)
    {
		String regex = "^(.+)@(.+)$";
  
        Pattern p = Pattern.compile(regex);
  
        Matcher m = p.matcher(mail);
  
        return !m.matches();
    }
	
	public static String antiInjectionSql(String mot)
    {
        String re = "[;\\\\/:*?!\"<>;|&`']";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(mot);
        String outputString = matcher.replaceAll("");
        return outputString;
    }
	public static String antiInjectionSqlNomPrenom(String mot)
    {
        String re = "[;\\\\/:*?+!\"<>;|&`'[0-9]]";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(mot);
        String outputString = matcher.replaceAll("");
        return outputString;
    }
	public static void insertVoyages (Voyages unVoyage)
{

		String requete ="insert into voyages values"
                + "(null, '"+antiInjectionSql(unVoyage.getDatedeb_voyage())+
                "','"+antiInjectionSql(unVoyage.getDatefin_voyage())+
                "','"+antiInjectionSql(unVoyage.getLieu_voyage())+
                "',null);";
 
    try
    {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaConnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
}

public static ArrayList<Voyages> selectAllVoyages(String mot)
{
    ArrayList<Voyages> lesVoyages = new ArrayList<Voyages>();
    String requete = "";
    if(mot.equals(""))
    {
        requete="select * from voyages ; ";
    }
    else
    {
        requete="select * from voyages where datedeb_voyage like '%"+mot
        		+"%' or datefin_voyage like '%"+mot
                +"%' or lieu_voyage like '%"+mot
                +"%' ;";
    }
    try
    {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaConnexion().createStatement();
        ResultSet desResultats = unStat.executeQuery(requete); //fetchAll en php
        //parcours des r�sultats pour construire les instances de clients
        while (desResultats.next())
        {
            Voyages UnVoyage = new Voyages(
                    desResultats.getInt("idv"),
                    desResultats.getString("datedeb_voyage"),
                    desResultats.getString("datefin_voyage"),
                    desResultats.getString("lieu_voyage")
                    );
            // on ajoute cet objet � la liste des clients
            lesVoyages.add(UnVoyage);

        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
    
    return lesVoyages;
}

public static void deleteVoyage(int idv)
{
    String requete ="delete from voyages where idv = " + idv +";";
    
    try
    {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaConnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
}

public static Voyages selectWhereVoyages(String idv)
{
    Voyages UnVoyage = null;
    String requete = "select * from voyages where idv = '"+idv+"';" ;
    try
    {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaConnexion().createStatement();
        ResultSet unResultat = unStat.executeQuery(requete); //fetchAll en php
        //parcours des r�sultats pour construire les instances de clients
        if (unResultat.next())
        {
            UnVoyage = new Voyages(
                    unResultat.getInt("idv"),
                    unResultat.getString(" datedeb_voyage"),
                    unResultat.getString("datefin_voyage"),
                    unResultat.getString("lieu_voyage")
                    
                    );
        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
    return UnVoyage;
}


public static void updateVoyage(Voyages UnVoyage)
{
	String requete ="update voyages set datedeb_voyage= '"+antiInjectionSql(UnVoyage.getDatedeb_voyage())
	+"', datefin_voyage = '"+antiInjectionSql(UnVoyage.getDatefin_voyage())
	+"', lieu_voyage = '"+antiInjectionSql(UnVoyage.getLieu_voyage())
	+"' where idv = " + UnVoyage.getIdv()+
	";";
    try
    {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaConnexion().createStatement();
        unStat.execute(requete);
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
}

}