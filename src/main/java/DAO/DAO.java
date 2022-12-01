package DAO;

import java.util.List;

public interface DAO <T>{
    void clear();
    List<T> getAll();
    void addShot(T entity);
    boolean isEmpty();
}
