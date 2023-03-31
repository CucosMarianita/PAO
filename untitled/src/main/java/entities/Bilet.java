package entities;

public class Bilet {
    private String tip;
    private int pret;
    private boolean achitat;
    private String descriere;

    public Bilet(){}
    public Bilet(String tip, int pret, boolean achitat, String descriere) {
        this.tip = tip;
        this.pret = pret;
        this.achitat = achitat;
        this.descriere = descriere;
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

    @Override
    public String toString() {
        return "Bilet: " +
                "   Tip: " + tip + '\n' +
                "   Pret: " + pret + '\n' +
                "   Achitat: " + achitat + '\n' +
                "   Descriere: " + descriere +'\n';
    }
}
