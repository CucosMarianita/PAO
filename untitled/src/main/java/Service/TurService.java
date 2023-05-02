package Service;

import entities.Tur;
import Interfaces.TurInterface;

import java.util.*;

public class TurService implements TurInterface{

    private final List<Tur> tururi = new ArrayList<>();
    private static TurService instance;


    public static TurService getInstance(){
        if(instance == null){
            instance = new TurService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Tur t : tururi) {
            if (t.getID_tur() > max) {
                max = t.getID_tur();
            }
        }
        return max;
    }


    @Override
    public Tur readTur() {
        Scanner scanner = new Scanner(System.in);
        Tur tur = new Tur();

        int id = getMaxId() + 1;
        tur.setID_tur(id);

        System.out.println("Descriere: ");
        tur.setDescriere(scanner.nextLine());

        System.out.println("Durata: ");
        tur.setDurata(scanner.nextLine());

        System.out.println("ID_ghid: ");
        while (true) {
            try {
                tur.setID_ghid(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID-ul trebuie sa fie un numar!");
            }
        }
        return tur;
    }

    @Override
    public List<Tur> getTururi() {
        List<Tur> tururiCopy = new ArrayList<>();
        tururiCopy.addAll(tururi);
        // sortare dupa id_ghid si id_tur
        tururiCopy.sort(Comparator.comparing(Tur::getID_ghid).thenComparing(Tur::getID_tur));
        return tururiCopy;
    }

    @Override
    public Tur getTurById(int index) {
        Tur tur = new Tur();
        for (Tur t : tururi) {
            if (t.getID_tur() == index) {
                tur = t;
            }
        }
        return tur;
    }

    @Override
    public void addTur(Tur tur) {
        this.tururi.add(tur);
    }

    @Override
    public void updateTur(int index, Tur tur) {
        for (int i = 0; i < this.tururi.size(); i++) {
            if (this.tururi.get(i).getID_tur() == index) {
                this.tururi.remove(i);
                this.tururi.add(i, tur);
                break;
            }
        }
    }

    @Override
    public void deleteTurById(int index) {
        for (int i = 0; i < this.tururi.size(); i++) {
            if (this.tururi.get(i).getID_tur() == index) {
                this.tururi.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteTur(Tur tur) {
        this.tururi.remove(tur);
    }
}
