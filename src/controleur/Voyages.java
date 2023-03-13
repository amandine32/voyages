package controleur;

public class Voyages {
    private int idv;

    private String datedeb_voyages, datefin_voyages, lieu_voyage;

    public Voyages (
        int idv,
        String datedeb_voyages,
        String datefin_voyages,
        String lieu_voyage)

    {
        this.idv=idv;
        this.datedeb_voyages = datedeb_voyages;
        this.datefin_voyages=datefin_voyages;
        this.lieu_voyage=lieu_voyage;
    }

    public int getIdv(){
        return idv;
    }

    public void setIdv(int idv){
        this.idv=idv;
    }

    public String getDatedeb_voyages(){
        return datedeb_voyages;
    }

    public void setDatedeb_voyages(String datedeb_voyages){
        this.datedeb_voyages=datedeb_voyages;
    }

    public String getDatefin_voyages(){
        return datefin_voyages;
    }

    public void setSatefin_voyages(String datefin_voyages){
        this.datefin_voyages=datefin_voyages;
    }

    public String getLieu_voyage(){
        return lieu_voyage;
    }

    public void setLieu_voyage(String lieu_voyage){
        this.lieu_voyage=lieu_voyage;
    }
    
}
