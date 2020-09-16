package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DAO<T> {
    HashMap<String, T> map;

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public T get(String id) {
        T t = map.get(id);
        return t;
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        ArrayList<T> list = new ArrayList<>();
        Iterator<T> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public void delete(String id) {
        map.remove(id);
    }
}
