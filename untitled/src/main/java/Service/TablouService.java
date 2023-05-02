package Service;

import entities.Tablou;
import Interfaces.TablouInterface;

import java.util.*;

public class TablouService implements TablouInterface{

    private final List<Tablou> tablouri = new ArrayList<>();
    private static TablouService instance;


    public static TablouService getInstance(){
        if(instance == null){
            instance = new TablouService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Tablou t : tablouri) {
            if (t.getID_exponat() > max) {
                max = t.getID_exponat();
            }
        }
        return max;
    }

    @Override
    public Tablou readTablou() {
        Scanner scanner = new Scanner(System.in);
        Tablou tablou = new Tablou();

        int id = getMaxId() + 1;
        tablou.setID_exponat(id);

        System.out.println("Denumire: ");
        tablou.setDenumire(scanner.nextLine());

        System.out.println("An: ");
        while (true) {
            try {
                tablou.setAn(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Anul trebuie sa fie un numar!");
            }
        }

        System.out.println("Stil: ");
        tablou.setStil(scanner.nextLine());

        System.out.println("Tehnica: ");
        tablou.setTehnica(scanner.nextLine());

        System.out.println("Descriere: ");
        tablou.setDescriere(scanner.nextLine());

        System.out.println("ID_galerie: ");
        while (true) {
            try {
                tablou.setID_galerie(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID-ul galeriei trebuie sa fie un numar!");
            }
        }

        return tablou;
    }

    @Override
    public List<Tablou> getTablouri() {
        List<Tablou> tablouriCopy = new ArrayList<>();
        tablouriCopy.addAll(this.tablouri);
        // sortare dupa stil si an
        Collections.sort(tablouriCopy, new Comparator<Tablou>() {
            @Override
            public int compare(Tablou t1, Tablou t2) {
                if (t1.getStil().equals(t2.getStil())) {
                    return t1.getAn() - t2.getAn();
                }
                return t1.getStil().compareTo(t2.getStil());
            }
        });
        return tablouriCopy;
    }

    @Override
    public Tablou getTablouById(int index) {
        Tablou tablou = new Tablou();
        for (Tablou t : tablouri) {
            if (t.getID_exponat() == index) {
                tablou = t;
            }
        }
        return tablou;
    }

    @Override
    public void addTablou(Tablou tablou) {
        this.tablouri.add(tablou);
    }

    @Override
    public void updateTablou(int index, Tablou tablou) {
        for (int i = 0; i < this.tablouri.size(); i++) {
            if (this.tablouri.get(i).getID_exponat() == index) {
                this.tablouri.remove(i);
                this.tablouri.add(i, tablou);
                break;
            }
        }
    }

    @Override
    public void deleteTablouById(int index) {
        for (int i = 0; i < this.tablouri.size(); i++) {
            if (this.tablouri.get(i).getID_exponat() == index) {
                this.tablouri.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteTablou(Tablou tablou) {
        this.tablouri.remove(tablou);
    }
}
