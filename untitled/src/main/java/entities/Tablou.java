package entities;

public class Tablou extends Exponat{
    private String stil;
    private String tehnica;

    public Tablou(){}
    public Tablou(String denumire, int an, String descriere, String stil, String tehnica, int ID_exponat, int ID_galerie) {
        super(denumire, an, descriere, ID_exponat, ID_galerie);
        this.stil = stil;
        this.tehnica = tehnica;
    }

    public String getStil() {
        return stil;
    }

    public void setStil(String stil) {
        this.stil = stil;
    }

    public String getTehnica() {
        return tehnica;
    }

    public void setTehnica(String tehnica) {
        this.tehnica = tehnica;
    }

    @Override
    public String toString() {
        return "Tablou: " + '\n' +
                "   Denumire: " + denumire + '\n' +
                "   An: " + an + '\n' +
                "   Stil: " + stil + '\n' +
                "   Tehnica: " + tehnica + '\n' +
                "   Descriere: " + descriere + '\n' +
                "   *ID_exponat: " + ID_exponat + '\n' +
                "   *ID_galerie: " + ID_galerie + '\n';
    }
}
