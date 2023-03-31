package entities;

public abstract class Exponat {
    protected String denumire;
    protected int an;
    protected String descriere;
    protected int ID_exponat;
    protected int ID_galerie;

    public Exponat(){}
    public Exponat(String denumire, int an, String descriere, int ID_exponat, int ID_galerie) {
        this.denumire = denumire;
        this.an = an;
        this.descriere = descriere;
        this.ID_exponat = ID_exponat;
        this.ID_galerie = ID_galerie;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getID_exponat() {
        return ID_exponat;
    }

    public void setID_exponat(int ID_exponat) {
        this.ID_exponat = ID_exponat;
    }

    public int getID_galerie() {
        return ID_galerie;
    }

    public void setID_galerie(int ID_galerie) {
        this.ID_galerie = ID_galerie;
    }


}
