package controleur;

public class Client {

    private int idc;
    private String nomc, prenomc, rue, cp, ville_c, pays_c, mail_c, datenaiss_c, mdp_c;

    public Client(
      
        int idc,
        String nomc,
        String prenomc,
        String rue,
        String mail_c,
        String datenaiss_c,
        String mdp_c,
        String cp,
        String ville_c,
        String pays_c
       
)

{
            this.idc = idc;
			this.nomc= nomc;
			this.prenomc = prenomc;
			this.mail_c = mail_c;
			this.datenaiss_c = datenaiss_c;
			this.rue = rue;
			this.cp = cp;
			this.ville_c = ville_c;
			this.mdp_c = mdp_c;
			this.pays_c = pays_c;
			
}

public int getIdc() {
    return idc;
}

public void setIdc(int idc) {
    this.idc = idc;
}

public String getNom_c() {
    return nomc;
}

public void setNom_c(String nomc) {
    this.nomc = nomc;
}

public String getPrenom_c() {
    return prenomc;
}

public void setPrenom_c(String prenom_c) {
    this.prenomc = prenom_c;
}

public String getMail_c() {
    return mail_c;
}

public void setMail_c(String mail_c) {
    this.mail_c = mail_c;
}

public String getDate_naiss_c() {
    return datenaiss_c;
}

public void setDate_naiss_c(String date_naiss_c) {
    this.datenaiss_c = date_naiss_c;
}

public String getRue() {
    return rue;
}

public void setRue(String rue) {
    this.rue = rue;
}

public String getVille_c() {
    return ville_c;
}

public void setVille_c(String ville_c) {
    this.ville_c = ville_c;
}

public String getMdp_c() {
    return mdp_c;
}

public void setMdp_c(String mdp_c) {
    this.mdp_c = mdp_c;
}

public String getCP() {
    return cp;
}

public void setCP(String cP_c) {
    cp = cp;
}

public String getPays() {
    return pays_c;
}

public void setPays(String nomc) {
    this.pays_c = pays_c;
}


    
}
