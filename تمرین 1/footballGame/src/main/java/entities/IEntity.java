package entities;

public interface IEntity<K> {

    K getId();
    void setId(K id);
}
