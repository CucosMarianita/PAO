package Service;
import Interfaces.BiletInterface;
import entities.Bilet;

import java.time.LocalDate;
import java.util.*;

public class BiletService implements BiletInterface {

    private List<Bilet> bilete = new ArrayList<>();
    private static BiletService instance;


// pentru etapa 2 (citire din db)
//    private BiletService(){
//        angajati = read();
//    }


    public static BiletService getInstance(){
        if(instance == null){
            instance = new BiletService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (Bilet b : bilete) {
            if (b.getID_bilet() > max) {
                max = b.getID_bilet();
            }
        }
        return max;
    }

    @Override
    public Bilet readBilet() {

        // TO DO:
        // introduc tipul si expozitia, pretul si descrierea sunt generate automat

        Scanner scanner = new Scanner(System.in);
        Bilet bilet = new Bilet();

        int id = getMaxId() + 1;
        bilet.setID_bilet(id);

        System.out.println("Tip: ");         // adulti, copii, pensionari, studenti, elevi
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

        System.out.println("Achitat: ");
        while (true) {
            String achitat = scanner.nextLine();
            if(achitat.equals("true") || achitat.equals("false") || achitat.equals("True") || achitat.equals("False")){
                bilet.setAchitat(Boolean.parseBoolean(achitat));
                break;
            } else {
                System.out.println("Achitat trebuie sa fie true/false!");
            }
        }

        System.out.println("Descriere: ");
        bilet.setDescriere(scanner.nextLine());

        System.out.println("ID_expozitie: ");
        while (true) {
            // sa verific daca este in lista de expoziitii
            try {
                bilet.setID_expozitie(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID-ul trebuie sa fie un numar!");
            }
        }

        return bilet;
    }

    @Override
    public List<Bilet> getBilete() {
        List<Bilet> bileteCopy = new ArrayList<>();
        bileteCopy.addAll(this.bilete);
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
        for (Bilet b : bilete) {
            if (b.getData_achizitie().isAfter(LocalDate.now().minusMonths(nr_luni))) {
                afisare.add(b);
            }
        }
        return afisare;
    }

    @Override
    public Bilet getBiletById(int index) {
        Bilet bilet = new Bilet();
        for(int i = 0; i < this.bilete.size(); ++i){
            if(this.bilete.get(i).getID_bilet() == index){
                bilet = this.bilete.get(i);
            }
        }
        return bilet;
    }

    @Override
    public void addBilet(Bilet bilet) {
        this.bilete.add(bilet);
    }

    @Override
    public void updateBilet(int index, Bilet bilet) {
        for (int i = 0; i < this.bilete.size(); i++) {
            if (this.bilete.get(i).getID_bilet() == index) {
                this.bilete.remove(i);
                this.bilete.add(i, bilet);
                break;
            }
        }
    }

    @Override
    public void deleteBiletById(int index) {
        for (int i = 0; i < this.bilete.size(); i++) {
            if (this.bilete.get(i).getID_bilet() == index) {
                this.bilete.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteBilet(Bilet bilet) {
        this.bilete.remove(bilet);
    }
}
