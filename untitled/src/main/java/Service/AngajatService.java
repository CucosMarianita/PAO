package Service;

import Interfaces.AngajatInterface;
import entities.Angajat;

import java.util.*;


public class AngajatService implements AngajatInterface {

    private List<Angajat> angajati = new ArrayList<>();
    private static AngajatService instance;


// pentru etapa 2 (citire din db)
//    private AngajatService(){
//        angajati = read();
//    }

    public static AngajatService getInstance(){
        if(instance == null){
            instance = new AngajatService();
        }
        return instance;
    }


    public int getMaxId(){
        int max = 0;
        for(int i = 0; i < angajati.size(); i++){
            if(angajati.get(i).getID_ang() > max){
                max = angajati.get(i).getID_ang();
            }
        }
        return max;
    }

    @Override
    public Angajat readAngajat() {

        Scanner scanner = new Scanner(System.in);
        Angajat angajat = new Angajat();

        int id = getMaxId() + 1;
        angajat.setID_ang(id);

        System.out.println("Prenume: ");
        angajat.setPrenume(scanner.nextLine());

        System.out.println("Nume: ");
        angajat.setNume(scanner.nextLine());

        System.out.println("CNP: ");
        while (true) {
            String cnp = scanner.nextLine();
            try {
                Integer.parseInt(cnp);
            } catch (NumberFormatException e) {
                System.out.println("CNP-ul trebuie sa contina doar cifre!");
            }
            if (cnp.length() == 13) {
                angajat.setCNP(cnp);
                break;
            } else {
                System.out.println("CNP-ul trebuie sa aiba 13 cifre!");
            }
        }

        System.out.println("Salariu: ");
        while(true){
            try {
                angajat.setSalariu(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Salariul trebuie sa fie un numar!");
            }
        }

        System.out.println("Departament: ");
        angajat.setDepartament(scanner.nextLine());

        System.out.println("Telefon: ");
        while(true){
            String tel = scanner.nextLine();
            try {
                Integer.parseInt(tel);
            } catch (NumberFormatException e) {
                System.out.println("Numarul de telefon trebuie sa contina doar cifre!");
            }
            if(tel.length() == 10){
                angajat.setTelefon(tel);
                break;
            } else {
                System.out.println("Numarul de telefon trebuie sa aiba 10 cifre!");
            }
        }


        System.out.println("Cod angajat: ");
        boolean cauta = true;
        while(cauta){
            String cod = scanner.nextLine();
            boolean cod_ok = true;

            for(int i = 0; i < angajati.size(); i++){
                if(angajati.get(i).getCod_ang() == Integer.parseInt(cod)){
                    cod_ok = false;
                    System.out.println("Codul este deja existent! Reintroduceti: ");
                }
            }
            if(cod_ok == true) {
                cauta = false;
                angajat.setCod_ang(Integer.parseInt(cod));
            }
        }

        return angajat;
    }

    @Override
    public Map<Integer, String> getGhizi() {
        Map<Integer, String> ghizi = new HashMap<Integer, String>();
        for(Angajat ang : angajati){
            if(ang.getDepartament().toUpperCase().equals("GHID")){
                ghizi.put(ang.getID_ang(), ang.getNume() + " " + ang.getPrenume());
            }
        }
        return ghizi;
    }

    @Override
    public List<Angajat> getAngajati() {
        List<Angajat> angajatiCopy = new ArrayList<>();
        angajatiCopy.addAll(this.angajati);
        // sortare dupa salariu
        Collections.sort(angajatiCopy, new Comparator<Angajat>() {
            @Override
            public int compare(Angajat ang1, Angajat ang2) {
                if (ang1.getSalariu() - ang2.getSalariu() > 0){
                    return 1;
                }
                return 0;
            }
        });
        return angajatiCopy;
    }

    @Override
    public Angajat getAngajatById(int index) {
        Angajat angajat = new Angajat();
        for(int i = 0; i < this.angajati.size(); ++i){
            if(this.angajati.get(i).getID_ang() == index){
                angajat = this.angajati.get(i);
            }
        }
        return angajat;
    }


    @Override
    public void addAngajat(Angajat angajat) {
        this.angajati.add(angajat);
    }

    @Override
    public void updateAngajat(int index, Angajat angajat) {
        for(int i = 0; i < this.angajati.size(); i++){
            if(this.angajati.get(i).getID_ang() == index){
                this.angajati.remove(i);
                this.angajati.add(i, angajat);
                break;
            }
        }
    }

    @Override
    public void deleteAngajatById(int index) {
        for(int i = 0; i < this.angajati.size(); i++){
            if(this.angajati.get(i).getID_ang() == index){
                this.angajati.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteAngajat(Angajat angajat) {
        for(int i = 0; i < this.angajati.size(); i++){
            if(this.angajati.get(i).equals(angajat)){
                this.angajati.remove(i);
                break;
            }
        }
    }
}

