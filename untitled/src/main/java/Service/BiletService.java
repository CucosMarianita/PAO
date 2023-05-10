package Service;
import Interfaces.BiletInterface;
import entities.Bilet;
import entities.Expozitie;

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


    public int getMaxId_bilete(){
        int max = 0;
        for (Bilet b : bilete) {
            if (b.getID_bilet() > max) {
                max = b.getID_bilet();
            }
        }
        return max;
    }


    @Override
    public Bilet readBilet() {   // Vizitator

        Scanner scanner = new Scanner(System.in);
        Bilet bilet = new Bilet();

        int id = getMaxId_bilete() + 1;
        bilet.setID_bilet(id);

        System.out.println("Tip: (copii, elevi, studenti, adulti, pensionari) ");
        bilet.setTip(scanner.nextLine());


        System.out.println("Ati efectuat plata?: ");
        while (true) {
            String achitat = scanner.nextLine();
            if(achitat.equals("true") || achitat.equals("false") || achitat.equals("True") || achitat.equals("False")){
                bilet.setAchitat(Boolean.parseBoolean(achitat));
                break;
            } else {
                System.out.println("Achitat trebuie sa fie true/false!");
            }
        }

        // TO DO : permanente + temporare

        System.out.println("Nume expozitie: ");
        // sa verific daca este in lista de expozitii
        ExpozitieService service_expotitii = ExpozitieService.getInstance();
        List<Expozitie> expozitii = service_expotitii.getExpozitii();
        while(true){
            String nume = scanner.nextLine();
            int id_expozitie = 0;
            for(Expozitie e : expozitii){
                if(e.getDescriere().equals(nume)){
                    id_expozitie = e.getID_expozitie();
                    break;
                }
            }
            if(id_expozitie != 0){
                bilet.setID_expozitie(id_expozitie);
                break;
            } else {
                System.out.println("Expozitia nu exista!");
            }
        }

        // pret si descriere automata din lista de bilete existente
        TipBiletService service_tip_bilete = TipBiletService.getInstance();
        List<Bilet> tip_bilete_existente = service_tip_bilete.getBilete();
        for (Bilet tip : tip_bilete_existente){
            if(tip.getID_expozitie() == bilet.getID_expozitie() && tip.getTip().equals(bilet.getTip())){
                bilet.setPret(tip.getPret());
                bilet.setDescriere(tip.getDescriere());
                break;
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
            if (b.isAchitat())
                if (b.getData_achizitie().isAfter(LocalDate.now().minusMonths(nr_luni)))
                    afisare.add(b);

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
