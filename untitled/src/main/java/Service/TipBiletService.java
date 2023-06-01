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

public class TipBiletService implements BiletInterface, CRUD_Template<Bilet> {

    private List<Bilet> tip_bilete_existente = new ArrayList<>();
    private Conn connection;
    private static TipBiletService instance;

    private TipBiletService(){
        connection = new Conn();
        tip_bilete_existente = findAll();    // (citire din db)
    }

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
    public void add(Bilet obj) throws SQLException {
        this.tip_bilete_existente.add(obj);
        String sql = "INSERT INTO bilet (ID_bilet, tip, pret, achitat, descriere, data_achizitie, ID_expozitie) VALUES ("+ obj.getID_bilet() + ", '" + obj.getTip() + "', " + obj.getPret() + ", " + obj.isAchitat() + ", '" + obj.getDescriere() + "', '" + obj.getData_achizitie() + "', " + obj.getID_expozitie() + ")";
        connection.getS().executeUpdate(sql);
    }

    @Override
    public List<Bilet> findAll() {
        try {
            String sql = "SELECT * FROM bilet";
            ResultSet rs = connection.getS().executeQuery(sql);
            List<Bilet> bilete = new ArrayList<>();

            while(rs.next()){
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
    public void update(Bilet obj) {
        this.tip_bilete_existente.set(obj.getID_bilet(), obj);
        try{
            String update = "UPDATE bilet SET tip = '" + obj.getTip() + "', pret = " + obj.getPret() + ", achitat = " + obj.isAchitat()
                    + ", descriere = '" + obj.getDescriere() + "', data_achizitie = '" + obj.getData_achizitie() + "', ID_expozitie = " + obj.getID_expozitie()
                    + " WHERE ID_bilet = " + obj.getID_bilet();
            connection.getS().execute(update);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < this.tip_bilete_existente.size(); i++) {
            if (this.tip_bilete_existente.get(i).getID_bilet() == index) {
                this.tip_bilete_existente.remove(i);
                break;
            }
        }
        try{
            String delete = "DELETE FROM bilet WHERE ID_bilet = " + index;
            connection.getS().execute(delete);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
