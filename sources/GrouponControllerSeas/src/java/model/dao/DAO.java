
package model.dao;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public interface DAO <E,I > {
    public ArrayList<E> findAll(E bean) throws Exception; //dinámico con LIKE
}
