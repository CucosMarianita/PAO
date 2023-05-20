package Service.Persistence;

import java.sql.*;

public class Conn {

    private Connection c;
    private Statement s;

    public Conn(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##proiectPAO", "password");
            s = c.createStatement();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Statement getS() {
        return s;
    }

}
