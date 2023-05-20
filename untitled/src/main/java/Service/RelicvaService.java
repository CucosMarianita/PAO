package Service;

import Service.Persistence.CRUD_Template;
import entities.Relicva;
import Interfaces.RelicvaInterface;
import Service.Persistence.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RelicvaService implements RelicvaInterface, CRUD_Template<Relicva> {

    private List<Relicva> relicve = new ArrayList<>();
    private Conn connection;
    private static RelicvaService instance;

    private RelicvaService(){
        connection = new Conn();
        relicve = findAll();    // (citire din db)
    }

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
    public void add(Relicva obj) throws SQLException {
        this.relicve.add(obj);

        connection.getS().execute("INSERT INTO relicva (ID_exponat, denumire, an, locatie, descriere, ID_galerie) "+
                            "VALUES (" + obj.getID_exponat() + ", "+ obj.getDenumire() + ", " + obj.getAn() + ", " + obj.getLocatie() + ", " + obj.getDescriere() + ", " + obj.getID_galerie() + ")");

    }

    @Override
    public List<Relicva> findAll() {
        try{
            String sql = "SELECT * FROM relicva";
            ResultSet rs = connection.getS().executeQuery(sql);

            List<Relicva> relicve = new ArrayList<>();
            while (rs.next()){
                Relicva relicva = new Relicva();
                relicva.setID_exponat(rs.getInt("ID_exponat"));
                relicva.setDenumire(rs.getString("denumire"));
                relicva.setAn(rs.getInt("an"));
                relicva.setLocatie(rs.getString("locatie"));
                relicva.setDescriere(rs.getString("descriere"));
                relicva.setID_galerie(rs.getInt("ID_galerie"));
                relicve.add(relicva);
            }
            rs.close();

            return relicve;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Relicva obj) {
        this.relicve.set(obj.getID_exponat(), obj);
        try{
            String update = "UPDATE relicva SET denumire = " + obj.getDenumire() + ", an = " + obj.getAn() + ", locatie = " + obj.getLocatie() +
                    ", descriere = " + obj.getDescriere() + ", ID_galerie = " + obj.getID_galerie() +
                    " WHERE ID_exponat = " + obj.getID_exponat();
            connection.getS().execute(update);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int index) {
        for(int i = 0; i < this.relicve.size(); i++){
            if(this.relicve.get(i).getID_exponat() == index){
                this.relicve.remove(i);
                break;
            }
        }
        try{
            String delete = "DELETE FROM relicva WHERE ID_exponat = " + index;
            connection.getS().execute(delete);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
