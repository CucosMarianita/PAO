package Service;

import Service.Persistence.CRUD_Template;
import entities.Tur;
import Interfaces.TurInterface;
import Service.Persistence.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TurService implements TurInterface, CRUD_Template<Tur> {

    private List<Tur> tururi = new ArrayList<>();
    private Conn connection;
    private static TurService instance;

    private TurService(){
        connection = new Conn();
        tururi = findAll();    // (citire din db)
    }

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
    public void add(Tur obj) throws SQLException {
        this.tururi.add(obj);
        connection.getS().execute("INSERT INTO tur (ID_tur, durata, descriere, ID_ghid) VALUES (" + obj.getID_tur() + ", '" + obj.getDurata() + "', '" + obj.getDescriere() + "', " + obj.getID_ghid() + ")");
    }

    @Override
    public List<Tur> findAll() {
        try{
            String sql = "SELECT * FROM tur";
            ResultSet rs = connection.getS().executeQuery(sql);
            List<Tur> tururi = new ArrayList<>();

            while (rs.next()) {
                Tur tur = new Tur();
                tur.setID_tur(rs.getInt("ID_tur"));
                tur.setDurata(rs.getString("durata"));
                tur.setDescriere(rs.getString("descriere"));
                tur.setID_ghid(rs.getInt("ID_ghid"));
                tururi.add(tur);
            }
            rs.close();

            return tururi;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Tur obj) {
        this.tururi.set(obj.getID_tur()-1, obj);
        try{
            connection.getS().execute("UPDATE tur SET durata = '" + obj.getDurata() + "', descriere = '" + obj.getDescriere() + "', ID_ghid = " + obj.getID_ghid() + " WHERE ID_tur = " + obj.getID_tur());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < this.tururi.size(); i++) {
            if (this.tururi.get(i).getID_tur() == index) {
                this.tururi.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("DELETE FROM tur WHERE ID_tur = " + index);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
