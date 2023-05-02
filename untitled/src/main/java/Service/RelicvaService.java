package Service;

import entities.Relicva;
import Interfaces.RelicvaInterface;

import java.util.*;

public class RelicvaService implements RelicvaInterface{

    private final List<Relicva> relicve = new ArrayList<>();
    private static RelicvaService instance;


    public static RelicvaService getInstance(){
        if(instance == null){
            instance = new RelicvaService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Relicva r : relicve) {
            if (r.getID_exponat() > max) {
                max = r.getID_exponat();
            }
        }
        return max;
    }


    @Override
    public Relicva readRelicva() {

        Scanner scanner = new Scanner(System.in);
        Relicva relicva = new Relicva();

        int id = getMaxId() + 1;
        relicva.setID_exponat(id);

        System.out.println("Denumire: ");
        relicva.setDenumire(scanner.nextLine());

        System.out.println("An: ");
        while (true) {
            try {
                relicva.setAn(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Anul trebuie sa fie un numar!");
            }
        }

        System.out.println("Locatie: ");
        relicva.setLocatie(scanner.nextLine());

        System.out.println("Descriere: ");
        relicva.setDescriere(scanner.nextLine());

        System.out.println("ID_galerie: ");
        while (true) {
            try {
                relicva.setID_galerie(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID_galerie trebuie sa fie un numar!");
            }
        }

        return relicva;

    }

    @Override
    public List<Relicva> getRelicve() {
        List<Relicva> relicveCopy = new ArrayList<>();
        relicveCopy.addAll(this.relicve);
        // sortare dupa an si id
        Collections.sort(relicveCopy, new Comparator<Relicva>() {
            @Override
            public int compare(Relicva o1, Relicva o2) {
                if (o1.getAn() == o2.getAn()) {
                    return o1.getID_exponat() - o2.getID_exponat();
                }
                return o1.getAn() - o2.getAn();
            }
        });
        return relicveCopy;
    }

    @Override
    public Relicva getRelicvaById(int index) {
        Relicva relicva = new Relicva();
        for (Relicva r : relicve) {
            if (r.getID_exponat() == index) {
                relicva = r;
            }
        }
        return relicva;
    }

    @Override
    public void addRelicva(Relicva relicva) {
        this.relicve.add(relicva);
    }

    @Override
    public void updateRelicva(int index, Relicva relicva) {
        for(int i = 0; i < this.relicve.size(); i++){
            if(this.relicve.get(i).getID_exponat() == index){
                this.relicve.remove(i);
                this.relicve.add(i, relicva);
                break;
            }
        }
    }

    @Override
    public void deleteRelicvaById(int index) {
        for(int i = 0; i < this.relicve.size(); i++){
            if(this.relicve.get(i).getID_exponat() == index){
                this.relicve.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteRelicva(Relicva relicva) {
        this.relicve.remove(relicva);
    }
}
