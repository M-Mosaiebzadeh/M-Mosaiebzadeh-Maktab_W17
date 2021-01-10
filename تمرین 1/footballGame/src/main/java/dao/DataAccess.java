package dao;

public interface DataAccess<K,E> {

    void save(E entity);

    E load(K id);

    void delete(E entity);

    void update(E entity);


}
