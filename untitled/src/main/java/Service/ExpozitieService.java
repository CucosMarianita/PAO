package Service;

import Interfaces.ExpozitieInterface;
import entities.Expozitie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpozitieService implements ExpozitieInterface {

    private List<Expozitie> expozitii = new ArrayList<>();
    private static ExpozitieService instance;


    public static ExpozitieService getInstance(){
        if(instance == null){
            instance = new ExpozitieService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Expozitie e : expozitii) {
            if (e.getID_expozitie() > max) {
                max = e.getID_expozitie();
            }
        }
        return max;
    }

    @Override
    public Expozitie readExpozitie() {

        Scanner scanner = new Scanner(System.in);
        Expozitie expozitie = new Expozitie();

        int id = getMaxId() + 1;
        expozitie.setID_expozitie(id);

        System.out.println("Descriere: ");
        expozitie.setDescriere(scanner.nextLine());

        System.out.println("ID_tur: ");
        while (true) {
            try {
                expozitie.setID_tur(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID_tur trebuie sa fie un numar!");
            }
        }

        return expozitie;
    }

    @Override
    public List<Expozitie> getExpozitii() {
        List<Expozitie> expoCopy = new ArrayList<>();
        expoCopy.addAll(this.expozitii);
        // sortare dupa id
        expoCopy.sort((o1, o2) -> o1.getID_expozitie() - o2.getID_expozitie());
        return expoCopy;
    }

    @Override
    public Expozitie getExpozitieById(int index) {
        Expozitie expozitie = new Expozitie();
        for (Expozitie e : expozitii) {
            if (e.getID_expozitie() == index) {
                expozitie = e;
            }
        }
        return expozitie;
    }

    @Override
    public void addExpozitie(Expozitie expozitie) {
        expozitii.add(expozitie);
    }

    @Override
    public void updateExpozitie(int index, Expozitie expozitie) {
        for (int i = 0; i < expozitii.size(); i++) {
            if (expozitii.get(i).getID_expozitie() == index) {
                expozitii.remove(i);
                expozitii.add(i, expozitie);
                break;
            }
        }
    }

    @Override
    public void deleteExpozitieById(int index) {
        for (int i = 0; i < expozitii.size(); i++) {
            if (expozitii.get(i).getID_expozitie() == index) {
                expozitii.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteExpozitie(Expozitie expozitie) {
        expozitii.remove(expozitie);
    }
}
