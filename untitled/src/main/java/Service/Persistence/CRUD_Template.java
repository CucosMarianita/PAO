package Service.Persistence;

import java.sql.SQLException;
import java.util.List;


public interface CRUD_Template<T> {

    void add(T obj) throws SQLException;

    List<T> findAll();

    void update(T obj);

    void delete(int index);

}


