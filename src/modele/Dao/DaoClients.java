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


public class DaoClients {
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
/***************GESTION DES client***********************/
public static void insertClient (Client unClient)
{
    String requete ="insert into client values"
                + "(null, '"+antiInjectionSql(unClient.getNomc())+
                "','"+antiInjectionSql(unClient.getPrenomc())+
                "','"+antiInjectionSql(unClient.getRue())+
                "','"+antiInjectionSql(unClient.getCp())+
                "','"+unClient.getVillec()+
                "','"+antiInjectionSql(unClient.getPays_c())+
                "','"+antiInjectionSql(unClient.getMail_c())+
                "','"+antiInjectionSql(unClient.getDatenaiss_c())+
                "','"+unClient.getMdp_c()+"');";

    
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

public static ArrayList<Client> selectAllClients(String mot)
{
    ArrayList<Client> lesCLients = new ArrayList<Client>();
    String requete = "";
    if(mot.equals(""))
    {
        requete="select * from client ; ";
    }
    else
    {
        requete="select * from client where nomc like '%"+mot
                +"%' or prenomc like '%"+mot
                +"%' or rue like '%"+mot
                +"%' or cp like '%"+mot
                +"%' or villec like '%"+mot
                +"%' or pays_c like '%"+mot
                +"%' or mail_c like '%"+mot
                +"%' or datenaiss_c like '%"+mot
                +"%' or mdp_c like '%"+mot
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

            Client UnClient = new Client(
                    desResultats.getInt("idc"),
                    desResultats.getString("nomc"),
                    desResultats.getString("prenomc"),
                    desResultats.getString("rue"),
                    desResultats.getString("cp"),                    
                    desResultats.getString("villec"),
                    desResultats.getString("pays_c"),
                    desResultats.getString("mail_c"),
                    desResultats.getString("datenaiss_c"),
                    desResultats.getString("mdp_c"));
            // on ajoute cet objet � la liste des clients
            lesCLients.add(UnClient);

        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
    
    return lesCLients;
}

public static void deleteClient(int idc)
{
    String requete ="delete from client where idc = " + idc +";";
    
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

public static Client selectWhereClient(String mail_c)
{
	Client unClient = null;
	String requete;

	
		requete = "select * from client where mail_c = '"+mail_c+"';" ;

    try
    {
        uneBdd.seConnecter();
        Statement unStat = uneBdd.getMaConnexion().createStatement();
        ResultSet unResultat = unStat.executeQuery(requete); //fetchAll en php
        //parcours des r�sultats pour construire les instances de clients
        if (unResultat.next())
        {
        	unClient = new Client(
                    unResultat.getInt("idc"),
                    unResultat.getString("nomc"),
                    unResultat.getString("prenomc"),
                    unResultat.getString("rue"),
                    unResultat.getString("cp"),
                    unResultat.getString("villec"),
                    unResultat.getString("pays_c"),
                    unResultat.getString("mail_c"),
                    unResultat.getString("datenaiss_c"),
                    unResultat.getString("mdp_c")
                    );
        }
        unStat.close();
        uneBdd.seDeConnecter();
    }
    catch(SQLException exp)
    {
        System.out.println("Erreur execution requete : "+ requete);
    }
    return unClient;
}


public static void updateClient(Client unClient)
{
	String requete;
	if (unClient.getMdp_c().equals(""))
	{
		requete ="update client set nomc= '"+antiInjectionSql(unClient.getNomc())
				+"', prenomc = '"+antiInjectionSql(unClient.getPrenomc())
				+"', rue = '"+antiInjectionSql(unClient.getRue())
				+"', cp = '"+antiInjectionSql(unClient.getCp())
				+"', villec = '"+unClient.getVillec()
				+"', pays_c = '"+antiInjectionSql(unClient.getPays_c())
				+"', mail_c = '"+antiInjectionSql(unClient.getMail_c())
				+"', datenaiss_c = '"+antiInjectionSql(unClient.getDatenaiss_c())
				+"', mdp_c = '"+antiInjectionSql(unClient.getMdp_c())
				+"' where idc = " + unClient.getIdc()+
				";";
	}else
	{
		requete ="update client set nomc= '"+antiInjectionSql(unClient.getNomc())
				+"', prenomc = '"+antiInjectionSql(unClient.getPrenomc())
				+"', rue = '"+antiInjectionSql(unClient.getRue())
				+"', cp = '"+antiInjectionSql(unClient.getCp())
				+"', villec = '"+unClient.getVillec()
				+"', pays_c = '"+antiInjectionSql(unClient.getPays_c())
				+"', mail_c = '"+antiInjectionSql(unClient.getMail_c())
				+"', datenaiss_c = '"+antiInjectionSql(unClient.getDatenaiss_c())
				+"', mdp_c = '"+sha256(unClient.getMdp_c())
				+"' where idc = " + unClient.getIdc()+
				";";
	}
	
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