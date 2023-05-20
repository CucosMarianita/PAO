package Service;

import Service.Persistence.CRUD_Template;
import entities.Tablou;
import Interfaces.TablouInterface;
import Service.Persistence.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TablouService implements TablouInterface, CRUD_Template<Tablou> {

    private List<Tablou> tablouri = new ArrayList<>();
    private Conn connection;
    private static TablouService instance;

    private TablouService(){
        connection = new Conn();
        tablouri = findAll();    // (citire din db)
    }

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
        // sortare dupa stil si an si id
        Collections.sort(tablouriCopy, new Comparator<Tablou>() {
            @Override
            public int compare(Tablou t1, Tablou t2) {
                if (t1.getStil().compareTo(t2.getStil()) == 0) {
                    if (t1.getAn() == t2.getAn()) {
                        return t1.getID_exponat() - t2.getID_exponat();
                    }
                    return t1.getAn() - t2.getAn();
                }
                return t1.getStil().compareTo(t2.getStil());
            }
        } );
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
    public void add(Tablou obj) throws SQLException {
        this.tablouri.add(obj);

        String sql = "INSERT INTO tablou (ID_exponat, denumire, an, stil, tehnica, descriere, ID_galerie) VALUES (" + obj.getID_exponat() + ", " + obj.getDenumire() + ", "
                + obj.getAn() + ", " + obj.getStil() + ", " + obj.getTehnica() + ", " + obj.getDescriere() + ", " + obj.getID_galerie() + ")";

        connection.getS().execute(sql);
    }

    @Override
    public List<Tablou> findAll() {
        try{
            String select = "SELECT * FROM tablou";
            ResultSet rs = connection.getS().executeQuery(select);
            List<Tablou> tablouri = new ArrayList<>();

            while (rs.next()){
                Tablou tablou = new Tablou();
                tablou.setID_exponat(rs.getInt("ID_exponat"));
                tablou.setDenumire(rs.getString("denumire"));
                tablou.setAn(rs.getInt("an"));
                tablou.setStil(rs.getString("stil"));
                tablou.setTehnica(rs.getString("tehnica"));
                tablou.setDescriere(rs.getString("descriere"));
                tablou.setID_galerie(rs.getInt("ID_galerie"));
                tablouri.add(tablou);
            }
            rs.close();

            return tablouri;
        }
        catch (SQLException e){
            e.printStackTrace();
            return  Collections.emptyList();
        }
    }

    @Override
    public void update(Tablou obj) {
        this.tablouri.set(obj.getID_exponat(), obj);
        try{
            String update = "UPDATE tablou SET denumire = '" + obj.getDenumire() + "', an = " + obj.getAn() + ", stil = '" + obj.getStil() +
                    "', tehnica = '" + obj.getTehnica() + "', descriere = '" + obj.getDescriere() + "', ID_galerie = " + obj.getID_galerie() +
                    " WHERE ID_exponat = " + obj.getID_exponat();

            connection.getS().execute(update);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < this.tablouri.size(); i++) {
            if (this.tablouri.get(i).getID_exponat() == index) {
                this.tablouri.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("DELETE FROM tablou WHERE ID_exponat = " + index);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
