package entities;

public class Sculptura extends Exponat{
    private String stil;
    private String material;

    public Sculptura(){}
    public Sculptura(String denumire, int an, String descriere, String stil, String material, int ID_exponat, int ID_galerie) {
        super(denumire, an, descriere, ID_exponat, ID_galerie);
        this.stil = stil;
        this.material = material;
    }

    public String getStil() {
        return stil;
    }

    public void setStil(String stil) {
        this.stil = stil;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Sculptura: " + '\n' +
                "   Denumire: " + denumire + '\n' +
                "   An: " + an + '\n' +
                "   Stil: " + stil + '\n' +
                "   Material: " + material + '\n' +
                "   Descriere: " + descriere + '\n' +
                "   *ID_exponat: " + ID_exponat + '\n' +
                "   *ID_galerie: " + ID_galerie + '\n';
    }
}
