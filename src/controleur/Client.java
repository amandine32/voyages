package controleur;

public class Client {

    private int idc;
    private String nomc, prenomc, rue, cp, villec, pays_c, mail_c, datenaiss_c, mdp_c;

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
			this.villec = ville_c;
			this.mdp_c = mdp_c;
			this.pays_c = pays_c;
			
}

public int getIdc() {
    return idc;
}

public void setIdc(int idc) {
    this.idc = idc;
}

public String getNomc() {
    return nomc;
}

public void setNomc(String nomc) {
    this.nomc = nomc;
}

public String getPrenomc() {
    return prenomc;
}

public void setPrenom_c(String prenomc) {
    this.prenomc = prenomc;
}

public String getMail_c() {
    return mail_c;
}

public void setMail_c(String mail_c) {
    this.mail_c = mail_c;
}

public String getDatenaiss_c() {
    return datenaiss_c;
}

public void setDatenaiss_c(String datenaiss_c) {
    this.datenaiss_c = datenaiss_c;
}

public String getRue() {
    return rue;
}

public void setRue(String rue) {
    this.rue = rue;
}

public String getVillec() {
    return villec;
}

public void setVillec(String villec) {
    this.villec = villec;
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

public void setCP(String cP) {
    cp = cp;
}

public String getPays_c() {
    return pays_c;
}

public void setPays(String nomc) {
    this.pays_c = pays_c;
}


    
}
