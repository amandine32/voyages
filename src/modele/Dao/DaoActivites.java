package modele.Dao;

import java.sql.PreparedStatement;
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


public class DaoActivites {
	// private static Bdd uneBdd = new Bdd("localhost","bddvoyages","root","root");
	private static Bdd uneBdd = new Bdd("localhost","bddvoyages","root","");
	
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
	
	public static int insertActivite(Activites uneActivite)
{int id;
	String requete ="insert into activites values"
			+"(null,'"
			+uneActivite.getNom_activite()+
			"','"+uneActivite.getType_activite()+"');";
	try
	{
		uneBdd.seConnecter();
		PreparedStatement unStat = uneBdd.getMaConnexion().prepareStatement(requete,
                Statement.RETURN_GENERATED_KEYS);
       
		unStat.executeUpdate();
        
        ResultSet generatedKeys = unStat.getGeneratedKeys();
        
            if (generatedKeys.next()) {
                id=generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
		unStat.close();
		uneBdd.seDeConnecter();
		return id;
	}
	catch(SQLException exp)
	{
		System.out.println("Erreur execution requete : "+ requete);
		exp.printStackTrace();
	}
	return 0;
}

public static ArrayList<Activites> selectAllActivites(int ida, String mot)
{
	ArrayList<Activites> lesActivites = new ArrayList<Activites>();
	String requete;
	if(ida ==0 && mot.equals(""))
	{
		requete= "select * from activites ; ";
	}
	else if(ida!=0 && mot.equals(""))
	{
		requete= "select * from activites where ida = "+ida+" ; ";
	}
	else
	{
		requete="select * from activites where ida like '%"+mot
				+"%' or nom_activte like '%"+mot
				+"%' or type_activite like '%"+mot;
	}
	try
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnexion().createStatement();
		ResultSet desResultats = unStat.executeQuery(requete); //fetchAll en php
		//parcours des r�sultats pour construire les instances de contrat_location;
		while (desResultats.next())
		{
			Activites uneActivite = new Activites(
					desResultats.getInt("ida"),
					desResultats.getString("nom_activite"),
					desResultats.getString("type_activite")
				);
			// on ajoute cet objet � la liste des contrat_mandat_locatif
			lesActivites.add(uneActivite);
		}
		unStat.close();
		uneBdd.seDeConnecter();
	}
	catch(SQLException exp)
	{
		System.out.println("Erreur execution requete : "+ requete);
	}
	
	return lesActivites;
}

public static Activites selectWhereActivites(int ida)
{
String requete="";

requete = "select * from activites where ida = '"+ida+"';" ;

Activites uneActivite = null;
try
{
	uneBdd.seConnecter();
	Statement unStat = uneBdd.getMaConnexion().createStatement();
	ResultSet unResultat = unStat.executeQuery(requete); //fetchAll en php
	//parcours des r�sultats pour construire les instances de contrat_location
	if (unResultat.next())
	{
		uneActivite = new Activites(
				unResultat.getInt("ida"),
				unResultat.getString("nom_activite"),
				unResultat.getString("type_activite")
				);
	}
	unStat.close();
	uneBdd.seDeConnecter();
}
catch(SQLException exp)
{
	System.out.println("Erreur execution requete : "+ requete);
}
return uneActivite;
}

public static void deleteActivite(int ida)
{
	String requete ="delete from activites where ida = " + ida +";";
	System.out.println(requete);
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

public static void updateActivites(Activites uneActivite)
{
	String requete ="update activites set nom_activite= '"
			+uneActivite.getNom_activite()+"', type_activite = '"
			+uneActivite.getType_activite()+"' "+ "where ida = " + uneActivite.getIda()+
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