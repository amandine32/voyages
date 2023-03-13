package controleur;

public class Reservation {
    private int idr, nb_personnes;

    private String date_resa;

    public Reservation(
        int idr,
        String date_resa,
        int nb_personnes)

    {
        this.idr=idr;
        this.date_resa = date_resa;
        this.nb_personnes=nb_personnes;
    }

    public int getIdr(){
        return idr;
    }

    public void setIdr(int idr){
        this.idr=idr;
    }

    public String getDate_resa(){
        return date_resa;
    }

    public void setDate_resa(String date_resa){
        this.date_resa=date_resa;
    }

    public int getNb_personnes(){
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes){
        this.nb_personnes=nb_personnes;
    }
    
}
