package controleur;

public class Voyages {
    private int idv;

    private String datedeb_voyage, datefin_voyage, lieu_voyage;

    public Voyages (
        int idv,
        String datedeb_voyages,
        String datefin_voyages,
        String lieu_voyage)

    {
        this.idv=idv;
        this.datedeb_voyage = datedeb_voyage;
        this.datefin_voyage=datefin_voyage;
        this.lieu_voyage=lieu_voyage;
    }

    public int getIdv(){
        return idv;
    }

    public void setIdv(int idv){
        this.idv=idv;
    }

    public String getDatedeb_voyage(){
        return datedeb_voyage;
    }

    public void setDatedeb_voyage(String datedeb_voyage){
        this.datedeb_voyage=datedeb_voyage;
    }

    public String getDatefin_voyage(){
        return datefin_voyage;
    }

    public void setSatefin_voyage(String datefin_voyage){
        this.datefin_voyage=datefin_voyage;
    }

    public String getLieu_voyage(){
        return lieu_voyage;
    }

    public void setLieu_voyage(String lieu_voyage){
        this.lieu_voyage=lieu_voyage;
    }
    
}
