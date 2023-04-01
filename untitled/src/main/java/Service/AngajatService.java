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

//        private int CNP;
//        private int salariu;
//        private String departament;
//        private int telefon;
//        private int cod_ang;
//        private int ID_ang;

        Scanner scanner = new Scanner(System.in);
        Angajat angajat = new Angajat();

        int id = getMaxId() + 1;
        angajat.setID_ang(id);

        System.out.println("Prenume: ");
        angajat.setPrenume(scanner.nextLine());

        System.out.println("Nume: ");
        angajat.setNume(scanner.nextLine());

        System.out.println("CNP: ");
        angajat.setCNP(scanner.nextLine());

        System.out.println("Salariu: ");
        angajat.setSalariu(Integer.parseInt(scanner.nextLine()));

        System.out.println("Departament: ");
        angajat.setDepartament(scanner.nextLine());

        System.out.println("Telefon: ");
        angajat.setTelefon(scanner.nextLine());

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
