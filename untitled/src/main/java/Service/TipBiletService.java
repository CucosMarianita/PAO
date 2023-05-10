package Service;

import Interfaces.BiletInterface;
import entities.Bilet;
import entities.Expozitie;

import java.time.LocalDate;
import java.util.*;

public class TipBiletService implements BiletInterface {

    private List<Bilet> tip_bilete_existente = new ArrayList<>();
    private static TipBiletService instance;


    public static TipBiletService getInstance(){
        if(instance == null){
            instance = new TipBiletService();
        }
        return instance;
    }

    public int getMaxId_bilete_existente(){
        int max = 0;
        for (Bilet b : tip_bilete_existente) {
            if (b.getID_bilet() > max) {
                max = b.getID_bilet();
            }
        }
        return max;
    }

    @Override
    public Bilet readBilet() {   // admin (adaugare tip bilet)

        Scanner scanner = new Scanner(System.in);
        Bilet bilet = new Bilet();

        int id = getMaxId_bilete_existente() + 1;
        bilet.setID_bilet(id);

        System.out.println("Tip: ");
        bilet.setTip(scanner.nextLine());

        System.out.println("Pret: ");
        while (true) {
            try {
                bilet.setPret(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Pretul trebuie sa fie un numar!");
            }
        }

        bilet.setAchitat(false);

        System.out.println("Descriere: ");
        bilet.setDescriere(scanner.nextLine());

        System.out.println("ID_expozitie: ");
        ExpozitieService service_expozitii = ExpozitieService.getInstance();
        List<Expozitie> expozitii = service_expozitii.getExpozitii();
        // verific daca exista id-ul
        while (true) {
            try {
                int id_expozitie = Integer.parseInt(scanner.nextLine());
                boolean ok = false;
                for (Expozitie e : expozitii) {
                    if (e.getID_expozitie() == id_expozitie) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    bilet.setID_expozitie(id_expozitie);
                    break;
                } else {
                    System.out.println("ID-ul nu exista!");
                }
            } catch (NumberFormatException e){
                System.out.println("ID-ul trebuie sa fie un numar!");
            }
        }

        return bilet;
    }


    @Override
    public List<Bilet> getBilete() {
        List<Bilet> bileteCopy = new ArrayList<>();
        bileteCopy.addAll(this.tip_bilete_existente);
        // sortare dupa tip
        Collections.sort(bileteCopy, new Comparator<Bilet>() {
            @Override
            public int compare(Bilet b1, Bilet b2) {
                return b1.getTip().compareTo(b2.getTip());
            }
        });
        return bileteCopy;
    }

    @Override
    public List<Bilet> getBiletePerioada(int nr_luni) {
        List<Bilet> afisare = new ArrayList<>();
        for (Bilet b : tip_bilete_existente) {
            if (b.isAchitat())
                if (b.getData_achizitie().isAfter(LocalDate.now().minusMonths(nr_luni)))
                    afisare.add(b);

        }
        return afisare;
    }

    @Override
    public Bilet getBiletById(int index) {
        Bilet bilet = new Bilet();
        for(int i = 0; i < this.tip_bilete_existente.size(); ++i){
            if(this.tip_bilete_existente.get(i).getID_bilet() == index){
                bilet = this.tip_bilete_existente.get(i);
            }
        }
        return bilet;
    }

    @Override
    public void addBilet(Bilet bilet) {
        this.tip_bilete_existente.add(bilet);
    }

    @Override
    public void updateBilet(int index, Bilet bilet) {
        for (int i = 0; i < this.tip_bilete_existente.size(); i++) {
            if (this.tip_bilete_existente.get(i).getID_bilet() == index) {
                this.tip_bilete_existente.remove(i);
                this.tip_bilete_existente.add(i, bilet);
                break;
            }
        }
    }

    @Override
    public void deleteBiletById(int index) {
        for (int i = 0; i < this.tip_bilete_existente.size(); i++) {
            if (this.tip_bilete_existente.get(i).getID_bilet() == index) {
                this.tip_bilete_existente.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteBilet(Bilet bilet) {
        this.tip_bilete_existente.remove(bilet);
    }
}
