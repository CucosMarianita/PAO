package entities;

public class Bilet {
    private String tip; // adulti, copii, pensionari, studenti, elevi
    private int pret;
    private boolean achitat;
    private String descriere;
    private int ID_expozitie;

    public Bilet(){}

    public Bilet(String tip, int pret, boolean achitat, String descriere, int ID_expozitie) {
        this.tip = tip;
        this.pret = pret;
        this.achitat = achitat;
        this.descriere = descriere;
        this.ID_expozitie = ID_expozitie;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public boolean isAchitat() {
        return achitat;
    }

    public void setAchitat(boolean achitat) {
        this.achitat = achitat;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getID_expozitie() {
        return ID_expozitie;
    }

    public void setID_expozitie(int ID_expozitie) {
        this.ID_expozitie = ID_expozitie;
    }

    @Override
    public String toString() {
        return "Bilet: " +
                "   Tip: " + tip + '\n' +
                "   Pret: " + pret + '\n' +
                "   Achitat: " + achitat + '\n' +
                "   Descriere: " + descriere +'\n' +
                "   *ID_expozitie: " + ID_expozitie +'\n';
    }
}
