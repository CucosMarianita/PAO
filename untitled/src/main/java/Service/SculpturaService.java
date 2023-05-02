package Service;

import entities.Sculptura;
import Interfaces.SculpturaInterface;

import java.util.*;

public class SculpturaService implements SculpturaInterface{

    private final List<Sculptura> sculpturi = new ArrayList<>();
    private static SculpturaService instance;


    public static SculpturaService getInstance(){
        if(instance == null){
            instance = new SculpturaService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Sculptura s : sculpturi) {
            if (s.getID_exponat() > max) {
                max = s.getID_exponat();
            }
        }
        return max;
    }


    @Override
    public Sculptura readSculptura() {
        Scanner scanner = new Scanner(System.in);
        Sculptura sculptura = new Sculptura();

        int id = getMaxId() + 1;
        sculptura.setID_exponat(id);

        System.out.println("Denumire: ");
        sculptura.setDenumire(scanner.nextLine());

        System.out.println("An: ");
        while (true) {
            try {
                sculptura.setAn(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Anul trebuie sa fie un numar!");
            }
        }

        System.out.println("Stil: ");
        sculptura.setStil(scanner.nextLine());

        System.out.println("Material: ");
        sculptura.setMaterial(scanner.nextLine());

        System.out.println("Descriere: ");
        sculptura.setDescriere(scanner.nextLine());

        System.out.println("ID_galerie: ");
        while (true) {
            try {
                sculptura.setID_galerie(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID-ul galeriei trebuie sa fie un numar!");
            }
        }

        return sculptura;
    }

    @Override
    public List<Sculptura> getSculpturi() {
        List<Sculptura> sculpturiCopy = new ArrayList<>();
        sculpturiCopy.addAll(this.sculpturi);
        // sortare dupa stil si id
        sculpturiCopy.sort(new Comparator<Sculptura>() {
            @Override
            public int compare(Sculptura o1, Sculptura o2) {
                if(o1.getStil().compareTo(o2.getStil()) == 0){
                    return o1.getID_exponat() - o2.getID_exponat();
                }
                return o1.getStil().compareTo(o2.getStil());
            }
        });
        return sculpturiCopy;
    }

    @Override
    public Sculptura getSculpturaById(int index) {
        Sculptura sculptura = new Sculptura();
        for (Sculptura s : sculpturi) {
            if (s.getID_exponat() == index) {
                sculptura = s;
            }
        }
        return sculptura;
    }

    @Override
    public void addSculptura(Sculptura sculptura) {
        this.sculpturi.add(sculptura);
    }

    @Override
    public void updateSculptura(int index, Sculptura sculptura) {
        for (int i = 0; i < this.sculpturi.size(); i++) {
            if (this.sculpturi.get(i).getID_exponat() == index) {
                this.sculpturi.remove(i);
                this.sculpturi.add(i, sculptura);
                break;
            }
        }
    }

    @Override
    public void deleteSculpturaById(int index) {
        for (int i = 0; i < this.sculpturi.size(); i++) {
            if (this.sculpturi.get(i).getID_exponat() == index) {
                this.sculpturi.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteSculptura(Sculptura sculptura) {
        this.sculpturi.remove(sculptura);
    }

}
