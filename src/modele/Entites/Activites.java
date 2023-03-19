package modele.Entites;

public class Activites {
    private int ida;

    private String nom_activite, type_activite;

    public Activites(
        int ida,
        String nom_activite,
        String type_activite)

    {
        this.ida=ida;
        this.nom_activite = nom_activite;
        this.type_activite=type_activite;
    }

    public int getIda(){
        return ida;
    }

    public void setIda(int ida){
        this.ida=ida;
    }

    public String getNom_activite(){
        return nom_activite;
    }

    public void setNom_activite(String nom_activite){
        this.nom_activite=nom_activite;
    }

    public String getType_activite(){
        return type_activite;
    }

    public void setType_activite(String type_activite){
        this.type_activite=type_activite;
    }
    
}
