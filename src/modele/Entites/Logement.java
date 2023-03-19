package modele.Entites;

public class Logement {

    private int idl, idv, capacite_accueil, tarif_logement;
    private String lieu_logement;

    public Logement(
        int idl,
        int idv,
        int capacite_accueil,
        int tarif_logement,
        String lieu_logement
    )
    {
        this.idl = idl;
        this.idv= idv;
        this.capacite_accueil = capacite_accueil;
        this.tarif_logement = tarif_logement;
        this.lieu_logement= lieu_logement;
      
    }

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public int getIdv() {
        return idv;
    }

    public void setIdv(int idv) {
        this.idv = idv;
    }

    public int getCapaciteAccueil() {
        return capacite_accueil;
    }

    public void setCapaciteAccueil(int capacite_accueil) {
        this.capacite_accueil = capacite_accueil;
    }

    public int getTarifLogement() {
        return tarif_logement;
    }

    public void setTarifLogement(int tarif_logement) {
        this.tarif_logement = tarif_logement;
    }

    public String getLieuLogement() {
        return lieu_logement;
    }

    public void setLieuLogement(String lieu_logement) {
        this.lieu_logement = lieu_logement;
    }
    
}
