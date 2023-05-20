package Service;
import Interfaces.BiletInterface;
import Service.Persistence.CRUD_Template;
import Service.Persistence.Conn;
import entities.Bilet;
import entities.Expozitie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class BiletService implements BiletInterface, CRUD_Template<Bilet> {

    private List<Bilet> bilete = new ArrayList<>();
    private Conn connection;
    private static BiletService instance;



    private BiletService(){
        connection = new Conn();
        bilete = findAll();
    }


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
    public List<Bilet> findAll() {
        try{
            String select = "SELECT * FROM bilet";
            ResultSet rs = connection.s.executeQuery(select);

            List<Bilet> bilete = new ArrayList<>();

            while (rs.next()){
                Bilet bilet = new Bilet();
                bilet.setID_bilet(rs.getInt("ID_bilet"));
                bilet.setTip(rs.getString("tip"));
                bilet.setPret(rs.getInt("pret"));
                bilet.setAchitat(rs.getBoolean("achitat"));
                bilet.setDescriere(rs.getString("descriere"));
                bilet.setData_achizitie(rs.getDate("data_achizitie").toLocalDate());
                bilet.setID_expozitie(rs.getInt("ID_expozitie"));
                bilete.add(bilet);
            }
            rs.close();
            return bilete;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void add(Bilet obj) throws SQLException {
        this.bilete.add(obj);

        String insert = "INSERT INTO bilet (ID_bilet, tip, pret, achitat, descriere, data_achizitie, ID_expozitie) "+
                "VALUES (" + obj.getID_bilet() + ", "+ obj.getTip() + ", " + obj.getPret() + ", " + obj.isAchitat() + ", " + obj.getDescriere() + ", " + obj.getData_achizitie() + ", " + obj.getID_expozitie() + ")";
        connection.s.execute(insert);
    }

    @Override
    public void update(Bilet obj) {
        this.bilete.set(obj.getID_bilet(), obj);
        try{
            connection.s.execute("UPDATE bilet SET tip = " + obj.getTip() + ", pret = " + obj.getPret() + ", achitat = " + obj.isAchitat() +
                                    ", descriere = " + obj.getDescriere() + ", data_achizitie = " + obj.getData_achizitie() + ", ID_expozitie = " + obj.getID_expozitie() +
                                    " WHERE ID_bilet = " + obj.getID_bilet() );

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < this.bilete.size(); i++) {
            if (this.bilete.get(i).getID_bilet() == index) {
                this.bilete.remove(i);
                break;
            }
        }
        try{
            connection.s.execute("DELETE FROM bilet WHERE ID_bilet = " + index);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
