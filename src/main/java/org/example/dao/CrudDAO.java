package org.example.dao;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public boolean save(T entity) throws IOException;

    public boolean update(T entity);

    public boolean delete(String id);

    List<T> getAll() throws IOException;
}







