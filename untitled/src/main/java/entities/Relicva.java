package entities;

public class Relicva extends Exponat{
    private String locatie;

    public Relicva(){}
    public Relicva(String denumire, int an, String descriere, int ID_exponat, int ID_galerie, String locatie) {
        super(denumire, an, descriere, ID_exponat, ID_galerie);
        this.locatie = locatie;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    public String toString() {
        return "Relicva: " + '\n' +
                "   Denumire: " + denumire + '\n' +
                "   An: " + an + '\n' +
                "   Locatie: " + locatie + '\n' +
                "   Descriere: " + descriere + '\n' +
                "   *ID_exponat: " + ID_exponat + '\n' +
                "   *ID_galerie: " + ID_galerie + '\n' ;
    }
}
