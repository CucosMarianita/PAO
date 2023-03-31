package entities;

public class Vizitator extends User{
    private String mail;
    private String telefon;

    public Vizitator(){}

    public Vizitator(String nume, String prenume, String telefon, String mail) {
        super(nume, prenume);
        this.mail = mail;
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Vizitator: " + '\n' +
                "   Nume: " + nume + '\n' +
                "   Prenume: " + prenume + '\n' +
                "   Telefon: " + telefon + '\n' +
                "   Mail: " + mail + '\n';
    }
}
