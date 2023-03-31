package entities;

public class ExpozitieTemporara extends Expozitie{
    private String perioada;

    public ExpozitieTemporara(){}
    public ExpozitieTemporara(String descriere, int ID_expozitie, int ID_tur, String perioada) {
        super(descriere, ID_expozitie, ID_tur);
        this.perioada = perioada;
    }

    public String getPerioada() {
        return perioada;
    }

    public void setPerioada(String perioada) {
        this.perioada = perioada;
    }

    @Override
    public String toString() {
        return "ExpozitieTemporara: " + '\n' +
                "   Perioada: " + perioada + '\n' +
                "   Descriere: " + descriere + '\n' +
                "   *ID_expozitie: " + ID_expozitie + '\n' +
                "   *ID_tur: " + ID_tur ;
    }
}
