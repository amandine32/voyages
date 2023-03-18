package controleur;

public class Detail {
    private int ida, idv;

    private float tarification_activte;

    private String  detail_activites;

    public Detail(
        float tarification_activte,
        String detail_activites,
        int ida,
        int idv)

    {
        this.tarification_activte=tarification_activte;
        this.detail_activites = detail_activites;
        this.ida=ida;
        this.idv=idv;
    }

    public float getTarification_activte(){
        return tarification_activte;
    }

    public void setTarification_activte(float tarification_activte){
        this.tarification_activte=tarification_activte;
    }

    public String getDetail_activites(){
        return detail_activites;
    }

    public void setDetail_activites(String detail_activites){
        this.detail_activites=detail_activites;
    }

    public int getIda(){
        return ida;
    }

    public void setIda(int ida){
        this.ida=ida;
    }

    public int getIdv(){
        return idv;
    }

    public void setIdv(int idv){
        this.idv=idv;
    }
    
    
}
