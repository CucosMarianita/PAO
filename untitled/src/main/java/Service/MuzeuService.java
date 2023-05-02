package Service;

import Interfaces.MuzeuInterface;
import entities.Muzeu;

import java.util.*;

public class MuzeuService implements MuzeuInterface {

    private List<Muzeu> muzee = new ArrayList<>();
    private static MuzeuService instance;


    public static MuzeuService getInstance(){
        if(instance == null){
            instance = new MuzeuService();
        }
        return instance;
    }


    @Override
    public Muzeu readMuzeu() {

        Scanner scanner = new Scanner(System.in);
        Muzeu muzeu = new Muzeu();

        System.out.println("Nume: ");
        muzeu.setNume(scanner.nextLine());

        System.out.println("Locatie: ");
        muzeu.setLocatie(scanner.nextLine());

        System.out.println("Program: ");
        muzeu.setProgram(scanner.nextLine());

        System.out.println("Descriere: ");
        muzeu.setDescriere(scanner.nextLine());

        return muzeu;
    }

    @Override
    public Muzeu getMuzeu() {
        return this.muzee.get(0);
    }

    @Override
    public void updateMuzeu(Muzeu muzeu) {
        this.muzee.set(0, muzeu);
    }

    @Override
    public void addMuzeu(Muzeu muzeu) {
        // daca lista cu muzee este goala, adauga muzeul
        if (this.muzee.isEmpty()) {
            this.muzee.add(muzeu);
        }
        else
            System.out.println("Poate exista un singur muzeu!");
    }

    @Override
    public void deleteMuzeu(Muzeu muzeu) {
        this.muzee.remove(muzeu);
    }
}
